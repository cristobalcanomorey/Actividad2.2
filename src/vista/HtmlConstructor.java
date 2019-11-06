package vista;

import java.util.ArrayList;

public class HtmlConstructor {
	private String doctype = "";
	private String abreHtml = "";
	private String head = "";
	private String abreBody = "";
	private String navBar = "";
	private String cuerpo = "";
	private String resultado = "";
	private String cuadro = "";
	private String cierraBody = "";
	private String cierraHtml = "";
	
	public HtmlConstructor(ArrayList<String> pagina) {
		this.doctype = pagina.get(0);
		this.abreHtml = pagina.get(1);
		this.head = pagina.get(2);
		this.abreBody = pagina.get(3);
		this.navBar = pagina.get(4);
		this.cuerpo = pagina.get(5);
		this.cierraBody = pagina.get(6);
		this.cierraHtml = pagina.get(7);
	}

	
	
	public String getResultado() {
		return resultado;
	}



	public void setResultado(String resultado) {
		this.resultado = resultado;
	}



	public String getCuadro() {
		return cuadro;
	}



	public void setCuadro(String cuadro) {
		this.cuadro = cuadro;
	}



	public String getDoctype() {
		return doctype;
	}

	public String getAbreHtml() {
		return abreHtml;
	}

	public String getHead() {
		return head;
	}

	public String getAbreBody() {
		return abreBody;
	}

	public String getNavBar() {
		return navBar;
	}

	public String getCuerpo() {
		return cuerpo;
	}

	public String getCierraBody() {
		return cierraBody;
	}

	public String getCierraHtml() {
		return cierraHtml;
	}
	
		
	
}
