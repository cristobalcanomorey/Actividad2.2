package vista;

public class HtmlConstructor {
	private String doctype = "";
	private String abreHtml = "";
	private String head = "";
	private String abreBody = "";
	private String navBar = "";
	private String cuerpo = "";
	private String cierraBody = "";
	private String cierraHtml = "";
	private String[] tipoPagina = new String[3];
	private String[] tipoCuerpo = new String[4];
	
	public HtmlConstructor(int tipoPag, int tipoCue) {
		this.doctype = "<!DOCTYPE html>";
		this.abreHtml = "<html>";
		this.head = "<head>\r\n" + 
				"        <title>TODO supply a title</title>\r\n" + 
				"        <meta charset=\"UTF-8\">\r\n" + 
				"        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + 
				"        <link  href=\"css/style.css\" rel=\"stylesheet\" type=\"text/css\" title=\"Color\" />\r\n" + 
				"    </head>";
		this.abreBody = "<body>";
		this.navBar = "<ul id=\"menu\">\r\n" + 
				"            <li>Calculo hipotecario</li>\r\n" + 
				"            <li class=\"sesion\">Iniciar sesión</li>\r\n" + 
				"        </ul>";
		this.cuerpo = "<div id=\"formulario\">\r\n" + 
				"            <h2>Calcular</h2>\r\n" + 
				"            <form action=\"post\" class=\"Main\">\r\n" + 
				"                <p>Capital de prestamo:</p>\r\n" + 
				"                <input type=\"text\" name=\"capital\">\r\n" + 
				"                <p>Interes</p>\r\n" + 
				"                <input type=\"text\" name=\"interes\">\r\n" + 
				"                <p>Plazos</p>\r\n" + 
				"                <input type=\"text\" name=\"plazo\">\r\n" + 
				"                <p>Periodicidad</p>\r\n" + 
				"                <select name=\"periodicidad\">\r\n" + 
				"                    <option value=\"0\">mensual</option>\r\n" + 
				"                    <option value=\"1\">trimestral</option>\r\n" + 
				"                    <option value=\"2\">semestral</option>\r\n" + 
				"                </select>\r\n" + 
				"                <p>Visualizar cuadro de amortización</p>\r\n" + 
				"                <input type=\"checkbox\" name=\"cuadro\">\r\n" + 
				"                <a href=\"Main\">Reset</a>\r\n" + 
				"                <input type=\"submit\" value=\"Calcular\">\r\n" + 
				"            </form>\r\n" + 
				"        </div>";
		this.cierraBody = "</body>";
		this.cierraHtml = "</html>";
	}
	
	private String tipoPagina(int tipo) {
		String[] tipoPagina = new String[3];
		tipoPagina[0] = "";
		return tipoPagina[tipo];
	}
	
}
