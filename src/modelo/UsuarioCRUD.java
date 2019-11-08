package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.NamingException;

import modelo.entidad.Usuario;

public class UsuarioCRUD {

	public static void insert(Usuario u) throws ClassNotFoundException, SQLException, NamingException {

		Statement stmt = null;

		JDBCSingleton.setStatement();
		stmt = JDBCSingleton.getStatement();

		stmt.executeUpdate(
				"INSERT INTO usuario (nombre, password) VALUES ('" + u.getNombre() + "','" + u.getPassword() + "')");

	}

	public static ResultSet selectTodos() throws SQLException {

		Statement stmt = null;

		JDBCSingleton.setStatement();
		stmt = JDBCSingleton.getStatement();

		ResultSet rs = stmt.executeQuery("SELECT * FROM usuario");

		return rs;
	}

	public static ResultSet selectNombre(String usuario) throws SQLException {
		Statement stmt = null;

		JDBCSingleton.setStatement();
		stmt = JDBCSingleton.getStatement();

		ResultSet rs = stmt.executeQuery("SELECT * FROM usuario WHERE nombre = '" + usuario + "'");

		return rs;
	}

	public static ResultSet selectHistorial(String idUsuario) throws SQLException {
		Statement stmt = null;

		JDBCSingleton.setStatement();
		stmt = JDBCSingleton.getStatement();

		ResultSet rs = stmt.executeQuery("SELECT * FROM hipoteca WHERE idUsuario = '" + idUsuario + "'");

		return rs;
	}

}
