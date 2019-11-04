package integracion;

import modelo.entidad.Hipoteca;

public class Integracion {

	public static Hipoteca validarDatos(String prestamo, String interes, String plazo, String periodicidad) throws NumberFormatException{
				
		float pres = Float.parseFloat(prestamo);
		float inter = Float.parseFloat(interes);
		int plaz = Integer.parseInt(plazo);
		int peri = Integer.parseInt(periodicidad);
		
		int[] p = {12,4,2};
		
		if(pres < 0 || inter <= 0 || inter > 100 || plaz < 0 || peri > 2 || peri < 0) {
			Hipoteca validados = new Hipoteca(pres,inter,plaz,p[peri]);
			return validados;
		} else {
			throw new NumberFormatException();
		}
		
	}

}
