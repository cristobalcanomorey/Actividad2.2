package modelo;

import java.sql.SQLException;
import java.sql.Statement;

import control.Control;
import modelo.entidad.Hipoteca;

public class HipotecaCRUD {

	public static void insert(Hipoteca h, int id) throws SQLException {

		Statement stmt = null;

		JDBCSingleton.setStatement();
		stmt = JDBCSingleton.getStatement();

		String fecha = Control.getFechaFormatoBD(h.getFecha());

		stmt.executeUpdate("INSERT INTO hipoteca (fecha,prestamo,interes,plazo,periodicidad,idUsuario) VALUES ('"
				+ fecha + "','" + h.getPrestamo() + "','" + h.getInteres() + "','" + h.getPlazo() + "','"
				+ h.getPeriodicidad() + "','" + id + "')");

	}

}
