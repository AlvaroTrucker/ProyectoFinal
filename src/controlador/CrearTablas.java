package controlador;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CrearTablas {
	private static Statement sentencia;
	public static  void crearTablaPersona(Connection con){
		String sql = "CREATE TABLE IF NOT EXISTS PERSONA ("
				+ "ID INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "NOMBRE TEXT,"
				+ "APELLIDOS TEXT,"
				+ "GENERO TEXT,"
				+ "PAIS TEXT)";
		try {
			sentencia = con.createStatement();
			sentencia.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
