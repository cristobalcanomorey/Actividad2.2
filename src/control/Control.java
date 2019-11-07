package control;

import java.util.ArrayList;

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
	
	public static HtmlConstructor creaPagina(int tipoPagina, int tipoCuerpo) {
		ArrayList<String> pagina = Integracion.tipoPagina(tipoPagina);
		ArrayList<String> paginaConContenido = Integracion.tipoCuerpo(pagina, tipoCuerpo);
		return new HtmlConstructor(paginaConContenido);
	}

	public static HtmlConstructor setResultado(HtmlConstructor pagina, Calculo c, Hipoteca h) {
		ArrayList<String> resultado = Integracion.dibujarResultado(c, h);
		pagina.setResultado(Integracion.resultadoToString(resultado));
		return pagina;
	}

}
