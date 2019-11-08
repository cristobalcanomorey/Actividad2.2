package modelo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class JDBCSingleton {
	private static final JDBCSingleton INSTANCE = new JDBCSingleton();
	private static Connection conn = null;
	private static Statement stmt = null;
	private static Context initContext;

	private JDBCSingleton() {
	}

	public static JDBCSingleton getInstance() {
		return INSTANCE;
	}

	public static Connection getConnection() {
		return conn;
	}

	public static void setConnection(String initC, String envC)
			throws ClassNotFoundException, SQLException, NamingException {
		initContext = new InitialContext();
		Context envContext = (Context) initContext.lookup(initC);
		DataSource ds = (DataSource) envContext.lookup(envC);

		JDBCSingleton.conn = ds.getConnection();
	}

	public static Statement getStatement() {
		return stmt;
	}

	public static void setStatement() throws SQLException {
		JDBCSingleton.stmt = conn.createStatement();
	}

}
