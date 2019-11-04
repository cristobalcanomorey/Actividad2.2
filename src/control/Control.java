package control;

import integracion.Integracion;

public class Control {

	public static Calculo calculaHipoteca(String prestamo, String interes, String plazo, String periodicidad, boolean cuadro) throws NumberFormatException{
		Calculo calculo = new Calculo(Integracion.validarDatos(prestamo,interes,plazo,periodicidad));
		if(cuadro) {
			calculo.generarCuadro();
		}
		return calculo;
	}

}
