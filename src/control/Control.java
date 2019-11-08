package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import integracion.Integracion;
import modelo.HipotecaCRUD;
import modelo.JDBCSingleton;
import modelo.UsuarioCRUD;
import modelo.entidad.Hipoteca;
import modelo.entidad.Usuario;
import vista.HtmlConstructor;

public class Control {

	public static Hipoteca generarHipoteca(String prestamo, String interes, String plazo, String periodicidad)
			throws NumberFormatException, NullPointerException {
		return Integracion.validarDatos(prestamo, interes, plazo, periodicidad);
	}

	public static Calculo calculaHipoteca(Hipoteca h, boolean cuadro) {
		Calculo calculo = new Calculo(h);
		if (cuadro) {
			calculo.generarCuadro(h.getPlazo()*12,h.getInteres(),h.getPrestamo());
		}
		return calculo;
	}
	
	public static HtmlConstructor creaPagina(int tipoPagina, int tipoCuerpo, Hipoteca h,boolean cuadroChecked, String usuarioLogeado) {
		if (tipoCuerpo == 2 | tipoCuerpo == 3 | tipoCuerpo == 4) {
			tipoPagina = 0;
		}
		ArrayList<String> pagina = Integracion.tipoPagina(tipoPagina,usuarioLogeado);
		ArrayList<String> paginaConContenido = Integracion.tipoCuerpo(pagina, tipoCuerpo,h,cuadroChecked);
		return new HtmlConstructor(paginaConContenido);
	}

	public static HtmlConstructor setResultado(HtmlConstructor pagina, Calculo c, Hipoteca h,boolean cuadro) {
		ArrayList<String> resultado = Integracion.dibujarResultado(c, h);
		pagina.setResultado(Integracion.arrayListToString(resultado));
		
		if(cuadro) {
			ArrayList<String> arrayCuadro = Integracion.dibujarCuadro(c);
			pagina.setCuadro(Integracion.arrayListToString(arrayCuadro));
		}
		
		return pagina;
	}
	
	public static void printResponse(HtmlConstructor pagina, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.print(pagina.getDoctype());
		writer.print(pagina.getAbreHtml());
		writer.print(pagina.getHead());
		writer.print(pagina.getAbreBody());
		writer.print(pagina.getNavBar());
		writer.print(pagina.getCuerpo());
		writer.print(pagina.getResultado());
		writer.print(pagina.getCuadro());
		writer.print(pagina.getCierraBody());
		writer.print(pagina.getCierraHtml());
	}

	public static ResultSet getUsuariosDeBD() {
		
		ResultSet nombres = null;
		try {
			nombres = UsuarioCRUD.selectTodos();
		} catch (SQLException e) {
			// log
			e.printStackTrace();
		}
		return nombres;
	}

	public static boolean guardarUsuarioEnBD(String usuario, String password, String fPerfil) {
		
		Usuario u = new Usuario(usuario,password);
		u.setfPerfil(fPerfil);
		try {
			UsuarioCRUD.insert(u);
			return false;
		} catch (ClassNotFoundException | SQLException | NamingException e) {
			//log
			e.printStackTrace();
			return true;
		}
	}

	public static void getConexion(String string, String string2) {
		
		try {
			JDBCSingleton.setConnection("java:/comp/env", "jdbc/aplicacion");
		} catch (ClassNotFoundException | SQLException | NamingException e) {
			// log
			e.printStackTrace();
		}
	}

	public static ResultSet getUsuarioYPass(String usuario) {
		
		ResultSet user = null;
		try {
			user = UsuarioCRUD.selectNombre(usuario);
		} catch (SQLException e) {
			// log
			e.printStackTrace();
		}
		return user;
	}

	public static void cerrarConexionBD() throws SQLException {
		
		if(JDBCSingleton.getStatement() != null) {
			JDBCSingleton.getConnection().close();
		}
		if(JDBCSingleton.getConnection() != null) {
			JDBCSingleton.getConnection().close();
		}
		
	}
	
	public static String getLoggedUser(HttpServletRequest request) {
		String user = "";

		// Comprobamos si tenemos una sesión y obtenemos su nombre de usuario.
		HttpSession session = request.getSession(false);

		if (session != null) {
			user = (String) session.getAttribute("usuario");
		}

		return user;
	}

	public static void insertHipoteca(Hipoteca h, String string) {
		
		int id = Integer.valueOf(string);
		try {
			HipotecaCRUD.insert(h,id);
		} catch (SQLException e) {
			// log
			e.printStackTrace();
		}
		
	}

	

	public static String getFechaFormatoBD(Date ahora) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(ahora);
	}

	public static HtmlConstructor setHistorial(HtmlConstructor pagina,ResultSet rs) throws SQLException {
		
		ArrayList<String> resultado = Integracion.dibujarHistorial(rs);
		pagina.setHistorial(Integracion.arrayListToString(resultado));
		
		return pagina;
	}

	public static ResultSet getHistorial(String registrado) {
		ResultSet user = null;
		ResultSet historial = null;
		try {
			user = UsuarioCRUD.selectNombre(registrado);
			while(user.next()) {
				historial = UsuarioCRUD.selectHistorial(user.getString("id"));
				break;
			}
		} catch (SQLException e) {
			// log
			e.printStackTrace();
		}
		return historial;
	}

}
