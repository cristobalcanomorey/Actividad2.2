package modelo;

import java.sql.SQLException;
import java.sql.Statement;

import modelo.entidad.Hipoteca;

public class HipotecaCRUD {

	public static void insert(Hipoteca h, int id) throws SQLException {

		Statement stmt = null;

		JDBCSingleton.setStatement();
		stmt = JDBCSingleton.getStatement();
		
		
		stmt.executeUpdate(
				"INSERT INTO hipoteca (fecha,prestamo,interes,plazo,periodicidad,idUsuario) VALUES ('" + h.getFecha() + "','" + h.getPrestamo() + "','"+h.getInteres()+ "','"+h.getPlazo()+ "','"+h.getPeriodicidad()+ "','"+id+"')");

	}

}
