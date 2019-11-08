package integracion;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import control.Calculo;
import modelo.entidad.Hipoteca;

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

	public static ArrayList<String> tipoPagina(int tipoDePagina, String usuarioLogeado) {
		ArrayList<String> tipoPagina = new ArrayList<String>();
		tipoPagina.add("<!DOCTYPE html>");
		tipoPagina.add("<html lang=\"es\">");
		tipoPagina.add("<head>" + "<title>Hipotecamo</title>" + "<meta charset=\"UTF-8\">"
				+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"
				+ "<link  href=\"css/style.css\" rel=\"stylesheet\" type=\"text/css\" title=\"Color\" />" + "</head>");
		tipoPagina.add("<body>");
		if (tipoDePagina == 1) {
			tipoPagina.add("<ul id=\"menu\">" + "<li><a href='Main'>Calculo hipotecario</a></li>"
					+ "<li class=\"sesion\"><a href='Login'>Iniciar sesión</a></li>"
					+ "<li class=\"sesion\"><a href='Registro'>Registrate</a></li>" + "</ul>");
			tipoPagina.add("");
		} else if (tipoDePagina == 2) {
			// menu con usuario logeado
			tipoPagina.add("<ul id=\"menu\">" + "<li><a href='Main'>Calculo hipotecario</a></li>"
					+ "<li class=\"sesion\">" + usuarioLogeado + "</li>"
					+ "<li class='historial'><a href='Main?historial=si'>Historial</a></li>"
					+ "<li class='logout'><a href='Logout'>Logout</a></li>" + "</ul>");
			tipoPagina.add("");
		} else {
			tipoPagina.add("");
			tipoPagina.add("");
		}
		tipoPagina.add("</body>");
		tipoPagina.add("</html>");

		return tipoPagina;
	}

	public static ArrayList<String> tipoCuerpo(ArrayList<String> tipoPagina, int tipoDeCuerpo, Hipoteca h,
			boolean cuadroChecked) {
		if (tipoDeCuerpo == 1) {
			String capital = "";
			String interes = "";
			String plazo = "";
			String c = "";
			if (h != null) {
				capital = "value='" + h.getPrestamo() + "'";
				interes = "value='" + h.getInteres() + "'";
				plazo = "value='" + h.getPlazo() + "'";
				if (cuadroChecked) {
					c = "checked";
				}
			}
			tipoPagina.add(5, "<div id=\"formulario\">" + "<h2>Calcular</h2>" + "<form action=\"Main\" method=\"get\">"
					+ "<p>Capital de prestamo:</p>" + "<input type=\"text\" name=\"capital\" " + capital + ">€"
					+ "<p>Interes</p>" + "<input type=\"text\" name=\"interes\" " + interes + ">%" + "<p>Plazos</p>"
					+ "<input type=\"text\" name=\"plazo\" " + plazo + ">años" + "<p>Periodicidad</p>"
					+ "<select name=\"periodicidad\">" + "<option value=\"0\">mensual</option>"
					+ "<option value=\"1\">trimestral</option>" + "<option value=\"2\">semestral</option>" + "</select>"
					+ "<p><input type='checkbox' name='cuadro' " + c + ">Visualizar cuadro de amortización</p>" + ""
					+ "<input type=\"submit\" value=\"Calcular\">" + "</form>" + "</div>");
		} else if (tipoDeCuerpo == 2) {
			// Formulario de registrarse
			tipoPagina.add(5,
					"<div id=\"registro\">\r\n" + "            <h2>Registrate</h2>\r\n"
							+ "            <form action=\"Registro\" method=\"post\">\r\n"
							+ "                <p>Nombre de usuario</p>\r\n"
							+ "                <input type=\"text\" name=\"nombre\">\r\n"
							+ "                <p>Contraseña</p>\r\n"
							+ "                <input type=\"password\" name=\"password\">\r\n"
							+ "                <input type=\"submit\" value=\"Registrar\">\r\n"
							+ "            </form>\r\n" + "        </div>");
		} else if (tipoDeCuerpo == 3) {
			// formulario de login
			tipoPagina.add(5,
					"<div id=\"login\">\r\n" + "            <h2>Iniciar sesión</h2>\r\n"
							+ "            <form action=\"Login\" method=\"post\">\r\n"
							+ "                <p>Nombre de usuario</p>\r\n"
							+ "                <input type=\"text\" name=\"nombre\">\r\n"
							+ "                <p>Contraseña</p>\r\n"
							+ "                <input type=\"password\" name=\"password\">\r\n"
							+ "                <input type=\"submit\" value=\"Iniciar Sesion\">\r\n"
							+ "            </form>\r\n" + "        </div>");
		}
		return tipoPagina;
	}

	public static ArrayList<String> dibujarResultado(Calculo c, Hipoteca h) throws IllegalArgumentException {
		ArrayList<String> resultado = new ArrayList<String>();
		resultado.add("<div id=\"resultado\">");
		resultado.add("<h2>Resultado</h2>");
		resultado
				.add("<p>Cuota hipotecaria: <span class=\"datosResultado\">" + round(c.getCuota(), 2) + "€</span></p>");
		resultado.add("<p>Coste total de la hipoteca en <span class=\"datosHipoteca\">" + h.getPlazo()
				+ "</span> años (capital más intereses): <span class=\"datosResultado\">" + round(c.getCosteTotal(), 2)
				+ "€</span></p>");
		resultado.add("<p>De los cuales <span class=\"datosResultado\">" + round(c.getCosteTotalDeInteres(), 2) + "€"
				+ "</span> son intereses equivalentes al <span class=\"datosHipoteca\">"
				+ round(c.getInteresesEquivalentes(), 2) + "%</span></p>");
		resultado.add("</div>");
		return resultado;
	}

	public static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		BigDecimal bd = BigDecimal.valueOf(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

	public static String arrayListToString(ArrayList<String> resultado) {
		String r = "";
		for (String resul : resultado) {
			r += resul;
		}
		return r;
	}

	public static ArrayList<String> dibujarCuadro(Calculo c) {
		ArrayList<String> cuadroString = new ArrayList<String>();
		ArrayList<ArrayList<Double>> cuadro = c.getCuadro();
		cuadroString.add("<div id=\"cuadro\">\r\n" + "            <h2>Cuadro de amortización</h2>\r\n"
				+ "            <table>\r\n" + "                <tbody>\r\n" + "                    <tr>\r\n"
				+ "                        <th>Mes</th>\r\n"
				+ "                        <th>Capital pendiente anterior</th>\r\n"
				+ "                        <th>Cuota a pagar</th>\r\n"
				+ "                        <th>Parte de la cuota que es amortización</th>\r\n"
				+ "                        <th>Parte de la cuota que es interés</th>\r\n"
				+ "                        <th>Capital pendiente posterior</th>\r\n" + "                    </tr>");
		for (ArrayList<Double> fila : cuadro) {
			cuadroString.add("<tr>");
			for (double dato : fila) {
				if (fila.indexOf(dato) == 0) {
					cuadroString.add("<td>" + (int) dato + "</td>");
				} else {
					cuadroString.add("<td>" + round(dato, 2) + "</td>");
				}
			}
			cuadroString.add("</tr>");
		}

		cuadroString.add("</tbody>\r\n" + "            </table>\r\n" + "        </div>");

		return cuadroString;
	}

	public static ArrayList<String> dibujarHistorial(ResultSet tabla) throws SQLException {
		ArrayList<String> historial = new ArrayList<String>();
		historial.add("<div id=\"historial\">\r\n" + "            <h2>Historial</h2>\r\n" + "            <table>\r\n"
				+ "                <tbody>\r\n" + "                    <tr>\r\n"
				+ "                        <th>Fecha</th>\r\n" + "                        <th>Prestamo</th>\r\n"
				+ "                        <th>Interes</th>\r\n" + "                        <th>Plazo</th>\r\n"
				+ "                        <th>Periodicidad</th>\r\n"
				+ "                        <th>Volver a calcular</th>\r\n" + "                    </tr>\r\n"
				+ "                    <tr>");
		while (tabla.next()) {
			historial.add("<tr><form action='Main' method='post'>");
			historial.add("<input type=\"hidden\" name=\"fecha\" value='" + tabla.getString("fecha") + "'>"
					+ tabla.getString("fecha"));
			historial.add("<input type=\"hidden\" name=\"prestamo\" value='" + tabla.getString("prestamo") + "'>"
					+ tabla.getString("prestamo"));
			historial.add("<input type=\"hidden\" name=\"interes\" value='" + tabla.getString("interes") + "'>"
					+ tabla.getString("interes"));
			historial.add("<input type=\"hidden\" name=\"plazo\" value='" + tabla.getString("plazo") + "'>"
					+ tabla.getString("plazo"));
			historial.add("<input type=\"hidden\" name=\"periodicidad\" value='" + tabla.getString("periodicidad")
					+ "'>" + tabla.getString("plazo"));
			historial.add("<input type=\"submit\" value=\"Calcular\">");
			historial.add("</form></tr>");
		}
		historial.add("</tbody>\r\n" + "            </table>\r\n" + "        </div>");
		return historial;
	}

}
