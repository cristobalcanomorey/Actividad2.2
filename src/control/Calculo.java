package control;

import java.util.ArrayList;
import java.util.HashMap;

import modelo.entidad.Hipoteca;

public class Calculo {

	private float cuota;
	private float costeTotal;
	private float interesesTotal;
	private float interesesEquivalentes;
	private HashMap<Integer, ArrayList<Float>> cuadro;
	
	public Calculo(Hipoteca datos) {
		float[] resultados = calcularHipoteca(datos);
		this.cuota = resultados[0];
		this.costeTotal = resultados[1];
		this.interesesTotal = resultados[2];
		this.interesesEquivalentes = resultados[3];
	}

	public void generarCuadro() {
		
		
	}

	private float[] calcularHipoteca(Hipoteca datos) {
		float[] resultado = new float[4];
		
		
		
		float importe = datos.getPrestamo();
		float interesAnual = datos.getInteres()/datos.getPeriodicidad();
		float plazoMensual = datos.getPlazo()/datos.getPeriodicidad();
		
		double cuota = importe/(1-Math.pow((1+interesAnual),-plazoMensual)/interesAnual);
		double costeTotal = cuota*plazoMensual;
		float interesesTotal = (float) (cuota-importe); 
		float interesesEquivalentes = (float) (100-(importe/costeTotal)*100);
		
		resultado[0] = (float) cuota;
		resultado[1] = (float) costeTotal;
		resultado[2] = interesesTotal;
		resultado[3] = interesesEquivalentes;
		return resultado;
	}

	public HashMap<Integer, ArrayList<Float>> getCuadro() {
		return cuadro;
	}

	public void setCuadro(HashMap<Integer, ArrayList<Float>> cuadro) {
		this.cuadro = cuadro;
	}

	public float getCuota() {
		return cuota;
	}

	public float getCosteTotal() {
		return costeTotal;
	}

	public float getInteresesTotal() {
		return interesesTotal;
	}

	public float getInteresesEquivalentes() {
		return interesesEquivalentes;
	}
	
}
