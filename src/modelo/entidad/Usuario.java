package modelo.entidad;

public class Usuario {

	private int id;
	private String nombre;
	private String password;
	private String fPerfil;

	public Usuario(String nombre, String password) {
		this.nombre = nombre;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getPassword() {
		return password;
	}

	public String getfPerfil() {
		return fPerfil;
	}

	public void setfPerfil(String fPerfil) {
		this.fPerfil = fPerfil;
	}

}
