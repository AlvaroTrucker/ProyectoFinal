package controlador;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import modelo.PersonaDTO;

public class CrearTrigger {
	private static Statement sentencia;
	
	public static void trigger(Connection c, List<PersonaDTO> lista){
		String sql = "CREATE TABLE IF NOT EXISTS PAPELERA ("
				+ "ID INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "NOMBRE_BORRADO TEXT,"
				+ "APELLIDOS_BORRADO TEXT,"
				+ "GENERO_BORRADO TEXT,"
				+ "PAIS_BORRADO TEXT,"
				+ ");";
		try {
			sentencia = c.createStatement();
			sentencia.addBatch(sql);
			for (PersonaDTO persona:lista){
				String insertar = "INSERT INTO PAPELERA VALUES (null,'"+persona.getNombre()+"','"+persona.getApellidos()+"','"+persona.getGenero()+"','"+persona.getPais()+"')";
				sentencia.addBatch(insertar);
			}
			sentencia.executeBatch();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				sentencia.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
