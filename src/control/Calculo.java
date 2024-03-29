package control;

import java.util.ArrayList;

import modelo.entidad.Hipoteca;

public class Calculo {

	private double cuota;
	private double costeTotal;
	private double interesesTotal;
	private double interesesEquivalentes;
	private double costeTotalDeInteres;
	private ArrayList<ArrayList<Double>> cuadro = new ArrayList<ArrayList<Double>>();
	private ArrayList<Double> fila = new ArrayList<Double>();

	public Calculo(Hipoteca datos) {

		double[] resultados = calcularHipoteca(datos);
		this.cuota = resultados[0];
		this.costeTotal = resultados[1];
		this.interesesTotal = resultados[2];
		this.interesesEquivalentes = resultados[3];
		this.costeTotalDeInteres = resultados[4];
	}

	public void generarCuadro(int meses, float interes, float capital) {
		for (int i = 0; i < meses; i++) {
			double capitalPendienteAnterior;
			if (i == 0) {
				capitalPendienteAnterior = (double) capital;
			} else {
				capitalPendienteAnterior = fila.get(5);
				fila = new ArrayList<Double>();
			}
			generarFila(i, capitalPendienteAnterior, interes);
			cuadro.add(fila);
		}
	}

	public void generarFila(int mes, double capitalPendienteAnterior, double interes) {
		double parteQEsInteres = capitalPendienteAnterior * (interes / 100 / 12);
		double parteQEsAmortizacion = cuota - parteQEsInteres;
		double dMes = mes + 1;
		fila.add(dMes);
		fila.add(capitalPendienteAnterior);
		fila.add(cuota);
		fila.add(parteQEsAmortizacion);
		fila.add(parteQEsInteres);
		fila.add(capitalPendienteAnterior - parteQEsAmortizacion);

	}

	public double parteQEsAmortizacion(double parteQEsInteres) {
		return cuota - parteQEsInteres;
	}

	public double parteQEsInteres(double capitalPendienteAnterior, double interes) {
		return capitalPendienteAnterior * (interes / 100 / 12);
	}

	public double capitalPendientePosterior(double capitalPendienteAnterior, double parteQEsAmortizacion) {
		return capitalPendienteAnterior - parteQEsAmortizacion;
	}

	private double[] calcularHipoteca(Hipoteca datos) {
		double[] resultado = new double[5];

		double importe = (double) datos.getPrestamo();
		double interesAnual = (double) datos.getInteres() / 100 / 12;
		int plazoPeriodico = datos.getPlazo();

		double powr = Math.pow(1 + interesAnual, plazoPeriodico * -12);

		double division = (1 - powr) / interesAnual;

		double cuota = importe / division;

		double costeTotal = cuota * (plazoPeriodico * 12);
		double interesesTotal = cuota - importe;
		double interesesEquivalentes = 100 - ((importe / costeTotal) * 100);
		double costeTotalIntereses = costeTotal - importe;

		resultado[0] = cuota;
		resultado[1] = costeTotal;
		resultado[2] = interesesTotal;
		resultado[3] = interesesEquivalentes;
		resultado[4] = costeTotalIntereses;
		return resultado;
	}

	public ArrayList<ArrayList<Double>> getCuadro() {
		return cuadro;
	}

	public void setCuadro(ArrayList<ArrayList<Double>> cuadro) {
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
