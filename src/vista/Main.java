package vista;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.Calculo;
import control.Control;

@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//página con/sin usuario
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//página con/sin usuario y con cálculo
//		doGet(request, response);
		String prestamo = "";
		String interes = "";
		String plazo = "";
		String periodicidad = "";
		boolean cuadro = false; //request.getParameter("cuadro") != null
		Calculo c = Control.calculaHipoteca(prestamo,interes,plazo,periodicidad,cuadro);
	}

}
