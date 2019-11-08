package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import integracion.Integracion;
import modelo.entidad.Hipoteca;
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
	
	public static HtmlConstructor creaPagina(int tipoPagina, int tipoCuerpo, Hipoteca h,boolean cuadroChecked) {
		ArrayList<String> pagina = Integracion.tipoPagina(tipoPagina);
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

}
