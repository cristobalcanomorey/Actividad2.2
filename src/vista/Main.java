package vista;

import java.io.IOException;
import java.io.PrintWriter;

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
		//página con/sin usuario
		HtmlConstructor pagina = Control.creaPagina(1,1,null,false);
		String prestamo = request.getParameter("capital");
		String interes = request.getParameter("interes");
		String plazo = request.getParameter("plazo");
		String periodicidad = request.getParameter("periodicidad");
		boolean cuadro = request.getParameter("cuadro") != null;
		try {
			Hipoteca h = Control.generarHipoteca(prestamo,interes,plazo,periodicidad);
			Calculo c = Control.calculaHipoteca(h,cuadro);
			pagina = Control.creaPagina(1,1,h,cuadro);
			pagina = Control.setResultado(pagina, c, h, cuadro);
		} catch(NumberFormatException e) {
			//log
			e.printStackTrace();
		} catch(NullPointerException e) {
			if(prestamo!=null^interes!=null^plazo!=null^periodicidad!=null) {
				//log se ha dejado algun campo vacío
			}
		}
		
		try {
			Control.printResponse(pagina, response);
		} catch (IOException e) {
			//log
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {

	}
	
	

}
