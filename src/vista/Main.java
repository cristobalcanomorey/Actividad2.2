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
		HtmlConstructor pagina = Control.creaPagina(1,1);
		String prestamo = request.getParameter("capital");
		String interes = request.getParameter("interes");
		String plazo = request.getParameter("plazo");
		String periodicidad = request.getParameter("periodicidad");
		boolean cuadro = false;//request.getParameter("cuadro") != null
		try {
			Hipoteca h = Control.generarHipoteca(prestamo,interes,plazo,periodicidad);
			Calculo c = Control.calculaHipoteca(h,cuadro);
			pagina = Control.setResultado(pagina, c, h);
		} catch(NumberFormatException e) {
			//log
			e.printStackTrace();
		} catch(NullPointerException e) {
			//log
			e.printStackTrace();
		}
		
		try {
			printResponse(pagina, response);
		} catch (IOException e) {
			//log
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {

	}
	
	private void printResponse(HtmlConstructor pagina, HttpServletResponse response) throws IOException {
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

}
