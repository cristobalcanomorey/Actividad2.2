package modelo.entidad;

import java.util.Date;

public class Hipoteca {

	private int id;
	private float prestamo;
	private float interes;
	private int plazo;
	private int periodicidad;
	private Date fecha;
	private Usuario idUsuario;

	public Hipoteca(float pres, float inter, int plaz, int peri) {
		this.prestamo = pres;
		this.interes = inter;
		this.plazo = plaz;
		this.periodicidad = peri;
	}

	public Hipoteca(int id, float pres, float inter, int plaz, int peri, Date fecha, Usuario idUsuario) {
		this.id = id;
		this.prestamo = pres;
		this.interes = inter;
		this.plazo = plaz;
		this.periodicidad = peri;
		this.fecha = fecha;
		this.idUsuario = idUsuario;
	}

	public int getId() {
		return id;
	}

	public float getPrestamo() {
		return prestamo;
	}

	public float getInteres() {
		return interes;
	}

	public int getPlazo() {
		return plazo;
	}

	public int getPeriodicidad() {
		return periodicidad;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getFecha() {
		return fecha;
	}

	public Usuario getIdUsuario() {
		return idUsuario;
	}

}
