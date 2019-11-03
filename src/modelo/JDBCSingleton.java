package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCSingleton {
	private static final JDBCSingleton INSTANCE = new JDBCSingleton();
	private static Connection conn = null;
	private static Statement stmt = null;
	
	private JDBCSingleton() {}
	
	public static JDBCSingleton getInstance() {
		return INSTANCE;
	}
	
	public static Connection getConnection() {
		return conn;
	}
	
	public static void setConnection(String dri, String db, String usr, String pas) throws ClassNotFoundException, SQLException {
		Class.forName(dri);
		JDBCSingleton.conn = DriverManager.getConnection(db,usr,pas);
	}
	
	public static Statement getStatement() {
		return stmt;
	}

	public static void setStatement() throws SQLException {
		JDBCSingleton.stmt = conn.createStatement();
	}
	
}
