package integracion;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import control.Calculo;
import modelo.entidad.Hipoteca;
import vista.HtmlConstructor;

public class Integracion {

	public static Hipoteca validarDatos(String prestamo, String interes, String plazo, String periodicidad)
			throws NumberFormatException, NullPointerException {

		float pres = Float.parseFloat(prestamo);
		float inter = Float.parseFloat(interes);
		int plaz = Integer.parseInt(plazo);
		int peri = Integer.parseInt(periodicidad);

		int[] p = { 12, 4, 2 };

		if (pres > 0 || inter >= 0 || inter < 100 || plaz > 0 || peri < 3 || peri >= 0) {
			Hipoteca validados = new Hipoteca(pres, inter, plaz, p[peri]);
			return validados;
		} else {
			throw new NumberFormatException();
		}

	}

	public static ArrayList<String> tipoPagina(int tipoDePagina) {
		ArrayList<String> tipoPagina = new ArrayList<String>();
		tipoPagina.add("<!DOCTYPE html>");
		tipoPagina.add("<html lang=\"es\">");
		tipoPagina.add("<head>" + "<title>Hipotecamo</title>" + "<meta charset=\"UTF-8\">"
				+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"
				+ "<link  href=\"css/style.css\" rel=\"stylesheet\" type=\"text/css\" title=\"Color\" />" + "</head>");
		tipoPagina.add("<body>");
		if (tipoDePagina == 1) {
			tipoPagina.add("<ul id=\"menu\">" + "<li>Calculo hipotecario</li>"
					+ "<li class=\"sesion\">Iniciar sesión</li>" + "</ul>");
			tipoPagina.add("");
		} else if (tipoDePagina == 2) {
			// menu con usuario logeado
		}
		tipoPagina.add("</body>");
		tipoPagina.add("</html>");

		return tipoPagina;
	}

	public static ArrayList<String> tipoCuerpo(ArrayList<String> tipoPagina, int tipoDeCuerpo) {
		if (tipoDeCuerpo == 1) {
			tipoPagina.add(5,
					"<div id=\"formulario\">" + "<h2>Calcular</h2>" + "<form action=\"Main\" method=\"get\">"
							+ "<p>Capital de prestamo:</p>" + "<input type=\"text\" name=\"capital\">"
							+ "<p>Interes</p>" + "<input type=\"text\" name=\"interes\">" + "<p>Plazos</p>"
							+ "<input type=\"text\" name=\"plazo\">" + "<p>Periodicidad</p>"
							+ "<select name=\"periodicidad\">" + "<option value=\"0\">mensual</option>"
							+ "<option value=\"1\">trimestral</option>" + "<option value=\"2\">semestral</option>"
							+ "</select>" + "<p>Visualizar cuadro de amortización</p>"
							+ "<input type=\"checkbox\" name=\"cuadro\">" + "<a href=\"Main\">Reset</a>"
							+ "<input type=\"submit\" value=\"Calcular\">" + "</form>" + "</div>");
		} else if (tipoDeCuerpo == 2) {
			// Formulario de inicio de sesion
		} else if (tipoDeCuerpo == 3) {
			// Tabla con el cuadro
		}
		return tipoPagina;
	}

	public static ArrayList<String> dibujarResultado(Calculo c, Hipoteca h) {
		ArrayList<String> resultado = new ArrayList<String>();
		resultado.add("<div id=\"resultado\">");
		resultado.add("<h2>Resultado</h2>");
		resultado.add("<p>Cuota hipotecaria: <span class=\"datosResultado\">"
				+ c.getCuota().setScale(2, RoundingMode.HALF_EVEN) + "</span></p>");
		resultado.add("<p>Coste total de la hipoteca en <span class=\"datosHipoteca\">" + h.getPlazo()
				+ "</span> años (capital más intereses): <span class=\"datosResultado\">"
				+ c.getCosteTotal().setScale(2, RoundingMode.HALF_EVEN) + "</span></p>");
		resultado.add("<p>De los cuales <span class=\"datosResultado\">"
				+ c.getCosteTotalDeInteres().setScale(2, RoundingMode.HALF_EVEN)
				+ "</span> son intereses equivalentes al <span class=\"datosHipoteca\">"
				+ c.getInteresesEquivalentes().setScale(2, RoundingMode.HALF_EVEN) + "%</span></p>");
		resultado.add("</div>");
		return resultado;
	}

	public static String resultadoToString(ArrayList<String> resultado) {
		String r = "";
		for (String resul : resultado) {
			r += resul;
		}
		return r;
	}

}
