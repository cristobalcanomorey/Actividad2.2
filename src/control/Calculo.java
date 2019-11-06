package control;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;

import modelo.entidad.Hipoteca;

public class Calculo {

	private BigDecimal cuota;
	private BigDecimal costeTotal;
	private BigDecimal interesesTotal;
	private BigDecimal interesesEquivalentes;
	private BigDecimal costeTotalDeInteres;
	private HashMap<Integer, ArrayList<Float>> cuadro;

	public Calculo(Hipoteca datos) {

		BigDecimal[] resultados = calcularHipoteca(datos);
		this.cuota = resultados[0];
		this.costeTotal = resultados[1];
		this.interesesTotal = resultados[2];
		this.interesesEquivalentes = resultados[3];
		this.costeTotalDeInteres = resultados[4];
	}

	public void generarCuadro() {

	}

	private BigDecimal[] calcularHipoteca(Hipoteca datos) {
		BigDecimal[] resultado = new BigDecimal[5];

		float importe = datos.getPrestamo();
		float interesAnual = datos.getInteres() / 100/12;
		int plazoPeriodico = datos.getPlazo() *-12;

		System.out.println("importe: " + importe);
		System.out.println("interesAnual: " + interesAnual);
		System.out.println("plazoPeriodico: " + plazoPeriodico);
		
		BigDecimal imp = new BigDecimal(importe);
		BigDecimal interAnu = new BigDecimal(interesAnual);
		
		BigDecimal pow = new BigDecimal(1);	
		pow.add(interAnu);
		pow.pow(plazoPeriodico);
		
		BigDecimal divisio = new BigDecimal(1).subtract(pow);
		divisio.divide(interAnu, RoundingMode.HALF_EVEN);
		
		BigDecimal cuota = imp.divide(divisio, RoundingMode.HALF_EVEN);
		
		
//		BigDecimal pow = interAnu.add(new BigDecimal(1)).pow(plazoPeriodico);
//
//		BigDecimal cuota = imp.divide((new BigDecimal(1).subtract(pow)).divide(interAnu, RoundingMode.HALF_EVEN),
//				RoundingMode.HALF_EVEN);

		BigDecimal costeTotal = cuota.multiply(new BigDecimal(plazoPeriodico));
		BigDecimal interesesTotal = cuota.subtract(imp);
		BigDecimal interesesEquivalentes = new BigDecimal(100)
				.subtract((imp.divide(costeTotal, RoundingMode.HALF_EVEN)).multiply(new BigDecimal(100)));
		BigDecimal costeTotalIntereses = costeTotal.subtract(imp);

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

	public BigDecimal getCuota() {
		return cuota;
	}

	public BigDecimal getCosteTotal() {
		return costeTotal;
	}

	public BigDecimal getInteresesTotal() {
		return interesesTotal;
	}

	public BigDecimal getInteresesEquivalentes() {
		return interesesEquivalentes;
	}

	public BigDecimal getCosteTotalDeInteres() {
		return costeTotalDeInteres;
	}

}
