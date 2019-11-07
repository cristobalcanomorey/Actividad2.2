package control;

import java.util.ArrayList;
import java.util.HashMap;

import modelo.entidad.Hipoteca;

public class Calculo {

	private double cuota;
	private double costeTotal;
	private double interesesTotal;
	private double interesesEquivalentes;
	private double costeTotalDeInteres;
	private HashMap<Integer, ArrayList<Float>> cuadro;

	public Calculo(Hipoteca datos) {

		double[] resultados = calcularHipoteca(datos);
		this.cuota = resultados[0];
		this.costeTotal = resultados[1];
		this.interesesTotal = resultados[2];
		this.interesesEquivalentes = resultados[3];
		this.costeTotalDeInteres = resultados[4];
	}

	public void generarCuadro() {

	}

	private double[] calcularHipoteca(Hipoteca datos) {
		double[] resultado = new double[5];

		double importe = (double) datos.getPrestamo();
		double interesAnual = (double) datos.getInteres() / 100/12;
		int plazoPeriodico = datos.getPlazo();
		
		double powr = Math.pow(1+interesAnual,plazoPeriodico*-12);
		
		double division = (1-powr)/interesAnual;
		
		double cuota = importe/division;
				
		double costeTotal = cuota*(plazoPeriodico*12);
		double interesesTotal = cuota-importe;
		double interesesEquivalentes = 100-((importe/costeTotal)*100);
		double costeTotalIntereses = costeTotal-importe;

		resultado[0] = cuota;
		resultado[1] = costeTotal;
		resultado[2] = interesesTotal;
		resultado[3] = interesesEquivalentes;
		resultado[4] = costeTotalIntereses;
		return resultado;
	}

	public HashMap<Integer, ArrayList<Float>> getCuadro() {
		return cuadro;
	}

	public void setCuadro(HashMap<Integer, ArrayList<Float>> cuadro) {
		this.cuadro = cuadro;
	}

	public double getCuota() {
		return cuota;
	}

	public double getCosteTotal() {
		return costeTotal;
	}

	public double getInteresesTotal() {
		return interesesTotal;
	}

	public double getInteresesEquivalentes() {
		return interesesEquivalentes;
	}

	public double getCosteTotalDeInteres() {
		return costeTotalDeInteres;
	}

}
