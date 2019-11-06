package control;

import java.util.ArrayList;

import integracion.Integracion;
import vista.HtmlConstructor;

public class Control {

	public static Calculo calculaHipoteca(String prestamo, String interes, String plazo, String periodicidad, boolean cuadro) throws NumberFormatException{
		Calculo calculo = new Calculo(Integracion.validarDatos(prestamo,interes,plazo,periodicidad));
		if(cuadro) {
			calculo.generarCuadro();
		}
		return calculo;
	}
	
	public static HtmlConstructor creaPagina(int tipoPagina, int tipoCuerpo) {
		ArrayList<String> pagina = Integracion.tipoPagina(tipoPagina);
		ArrayList<String> paginaConContenido = Integracion.tipoCuerpo(pagina,tipoCuerpo);
		return new HtmlConstructor(paginaConContenido);
	}
	
	public static HtmlConstructor resultado(HtmlConstructor pagina,Calculo c){
		
		return pagina;
	}

}
