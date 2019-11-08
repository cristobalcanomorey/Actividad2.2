package vista;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.Calculo;
import control.Control;
import control.LogSingleton;
import modelo.JDBCSingleton;
import modelo.entidad.Hipoteca;

@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
    	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException{
		LogSingleton log =LogSingleton.getInstance();
		String registrado =  Control.getLoggedUser(request);
		String historial = request.getParameter("historial");
		int tipoDePagina = 1;
		
		boolean guardarHipotecas = false;
		HtmlConstructor pagina;
		if(registrado != "") {
			tipoDePagina = 2;
			guardarHipotecas = true;
		}
		pagina = Control.creaPagina(tipoDePagina,1,null,false,registrado);
		
		String prestamo = request.getParameter("capital");
		String interes = request.getParameter("interes");
		String plazo = request.getParameter("plazo");
		String periodicidad = request.getParameter("periodicidad");
		boolean cuadro = request.getParameter("cuadro") != null;
		try {
			Hipoteca h = Control.generarHipoteca(prestamo,interes,plazo,periodicidad);
			if(guardarHipotecas) {
				JDBCSingleton.getInstance();
				Control.getConexion("java:/comp/env", "jdbc/aplicacion");
				ResultSet rs = Control.getUsuarioYPass(registrado);
				Date ahora = new Date();
				h.setFecha(ahora);
				while(rs.next()) {
					Control.insertHipoteca(h,rs.getString("id"));
				}
				
			}
			Calculo c = Control.calculaHipoteca(h,cuadro);
			pagina = Control.creaPagina(tipoDePagina,1,h,cuadro,registrado);
			if(historial != null) {
				System.out.println("entra");
				ResultSet histor = Control.getHistorial(registrado);
				pagina = Control.setHistorial(pagina,histor);
			}
			pagina = Control.setResultado(pagina, c, h, cuadro);
		} catch(NumberFormatException e) {

			log.getLog().error("Ha introducido mal los datos: ", e);
		} catch(NullPointerException e) {
			if(prestamo!=null^interes!=null^plazo!=null^periodicidad!=null) {

				log.getLog().error("Ha dejado algún campo vacío", e);
			}
		} catch (SQLException e) {

			log.getLog().error("Hay un error con la base de datos ", e);
		}
		
		try {
			Control.printResponse(pagina, response);
		} catch (IOException e) {
		
			log.getLog().error("IOException: ", e);
		}
	}
	

}
