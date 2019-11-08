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
import modelo.entidad.Hipoteca;

@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
    	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException{

		String registrado =  Control.getLoggedUser(request);
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
				ResultSet rs = Control.getUsuarioYPass(registrado);
				Date ahora = Control.getCurrentTime();
				h.setFecha(ahora);
				while(rs.next()) {
					Control.insertHipoteca(h,rs.getString("id"));
				}
				
			}
			Calculo c = Control.calculaHipoteca(h,cuadro);
			pagina = Control.creaPagina(tipoDePagina,1,h,cuadro,registrado);
			pagina = Control.setResultado(pagina, c, h, cuadro);
		} catch(NumberFormatException e) {
			//log
			e.printStackTrace();
		} catch(NullPointerException e) {
			if(prestamo!=null^interes!=null^plazo!=null^periodicidad!=null) {
				//log se ha dejado algun campo vacío
			}
		} catch (SQLException e) {
			// log
			e.printStackTrace();
		}
		
		try {
			Control.printResponse(pagina, response);
		} catch (IOException e) {
			//log
			e.printStackTrace();
		}
	}
	

}
