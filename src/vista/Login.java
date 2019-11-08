package vista;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import control.Control;
import control.LogSingleton;
import modelo.JDBCSingleton;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

//	Al context.xml del vostre tomcat heu d'afegir
//	
//	<Resource name="jdbc/aplicacion" auth="Container"
//	type="javax.sql.DataSource" maxActive="100" maxIdle="30"
//	maxWait="10000" username="tofol" password="1234"
//	driverClassName="com.mysql.cj.jdbc.Driver"
//	url="jdbc:mysql://localhost:3306/aplicacion?useUnicode=true&amp;useJDBCCompliantTimezoneShift=true&amp;useLegacyDatetimeCode=false&amp;serverTimezone=UTC" />
//
//	<!-- Alerta que he substituït & per &amp; a la url -->

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LogSingleton.getInstance();
		HtmlConstructor pagina = Control.creaPagina(1, 3, null, false,"");
		try {
			Control.printResponse(pagina, response);
		} catch (IOException e) {
			// log
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String usuario = request.getParameter("nombre");
		String password = request.getParameter("password");
		JDBCSingleton.getInstance();
		Control.getConexion("java:/comp/env", "jdbc/aplicacion");
		ResultSet usuarios = Control.getUsuarioYPass(usuario);

		try {
			// Comprobamos que nos han pasado bien los datos
			if ((usuario != null) && (password != null)) {

				if(usuarios!=null) {
					while (usuarios.next()) {
						String usrBD = usuarios.getString("nombre");
						String pasBD = usuarios.getString("password");
						if ((usuario.equals(usrBD)) && (password.equals(pasBD))) {
							// Creamos la sesión del usuario y añadimos el nombre de usuario
							HttpSession session = request.getSession(true);

							session.setAttribute("usuario", usuario);

							response.sendRedirect("Main");
						} else {
							// Nos han pasado unos datos erróneos de login
							response.sendRedirect("Login?error=true");
						}
					}

				}
				
			}
		} catch (SQLException e) {
			// log
			e.printStackTrace();
		} finally {
			try {
				Control.cerrarConexionBD();
			} catch (SQLException e) {
				// log
				e.printStackTrace();
			}
		}

	}

}
