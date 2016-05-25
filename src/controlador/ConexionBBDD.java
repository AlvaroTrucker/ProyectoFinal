package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import org.sqlite.SQLiteConfig;

/**
 * Clase que realiza la conexion a la BBDD
 * @author Alvaro Jimenez
 * @version 1.0
 */

public class ConexionBBDD {
	private static Connection conexion;
	private ConexionBBDD(){};
	
	public static Connection getConexion() {
		if (conexion==null){
			Runtime.getRuntime().addShutdownHook(new MiShutdown());
			ResourceBundle rb = ResourceBundle.getBundle("sqlite");
			String url = rb.getString("url");
			String driver = rb.getString("driver");
			try {
				//carga del driver
				Class.forName(driver);
				//establecemos configuarcion de SQlite particular.
				SQLiteConfig sqConfig = new SQLiteConfig();
				sqConfig.enforceForeignKeys(true);
				//carga de url
				conexion = DriverManager.getConnection(url,sqConfig.toProperties());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conexion;
	}
	
	/**
	 * Clase que cierra la conexion
	 * @author Alvaro Jimenez
	 * @version 1.0
	 */
	
	static class MiShutdown extends Thread{
		@Override
		public void run() {
			Connection con = ConexionBBDD.getConexion();
		    if (con!=null){
		    	try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		    }
		}
	}
}
