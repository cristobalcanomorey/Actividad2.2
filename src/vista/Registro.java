package vista;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import control.Control;
import control.LogSingleton;
import modelo.JDBCSingleton;

@WebServlet("/Registro")
public class Registro extends HttpServlet {
	private static final long serialVersionUID = 1L;

//	Al context.xml del vostre tomcat heu d'afegir
//	
//	<Resource name="jdbc/aplicacion" auth="Container"
//	type="javax.sql.DataSource" maxActive="100" maxIdle="30"
//	maxWait="10000" username="tofol" password="1234"
//	driverClassName="com.mysql.cj.jdbc.Driver"
//	url="jdbc:mysql://localhost:3306/aplicacion?useUnicode=true&amp;useJDBCCompliantTimezoneShift=true&amp;useLegacyDatetimeCode=false&amp;serverTimezone=UTC" />
//
//	<!-- Alerta que he substituït & per &amp; a la url -->

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		LogSingleton.getInstance();
		HtmlConstructor pagina = Control.creaPagina(1, 2, null, false,"");
		try {
			Control.printResponse(pagina, response);
		} catch (IOException e) {
			// log
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LogSingleton.getInstance();
		String usuario = request.getParameter("nombre");
		String password = request.getParameter("password");
		String fPerfil = "";
		JDBCSingleton.getInstance();
		Control.getConexion("java:/comp/env", "jdbc/aplicacion");
		ResultSet usuarios = Control.getUsuariosDeBD();
		boolean encontrado = false;
		// Comprobamos que nos han pasado bien los datos
		try {
			if (usuario != null && password != null) {

				while (usuarios.next()) {
					if (usuarios.getString("nombre").equals(usuario)) {
						encontrado = true;
						break;
					}
				}

				if (!encontrado) {
					// Creamos la sesión del usuario y añadimos el nombre de usuario
					boolean errorDB = Control.guardarUsuarioEnBD(usuario, password, fPerfil);
					HttpSession session = request.getSession(true);

					session.setAttribute("usuario", usuario);

					if (!errorDB) {
						response.sendRedirect("Main?registrado=si");
					} else {
						response.sendRedirect("Registro?errorDB=si");
					}

				} else {
					response.sendRedirect("Registro?errorUsuario=si");
				}
			} else {
				System.out.println("mala cosa");
			}
		} catch (SQLException e) {
			// log
			e.printStackTrace();
		}finally {
			try {
				Control.cerrarConexionBD();
			} catch (SQLException e) {
				// log
				e.printStackTrace();
			}
		}

	}

}
