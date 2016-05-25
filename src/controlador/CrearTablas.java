package controlador;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import modelo.PersonaDTO;

/**
 * Clase que genera las tablas, triggers y vistas
 * @author Alvaro Jimenez
 * @version 1.0
 * 
 */
public class CrearTablas {
	private static Statement sentencia;
	public static void crearTablaPersona(Connection con){
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
	public static void CrearTrigger(Connection c){
		String sql = "CREATE TABLE IF NOT EXISTS PAPELERA("
				+ "ID INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "NOMBRE TEXT,"
				+ "FECHABORRADO DATE);";
		try {
			sentencia = c.createStatement();
			sentencia.addBatch(sql);
			sentencia.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				sentencia.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void BorrarTrigger(Connection c){
		String sql= "CREATE TRIGGER IF NOT EXISTS OLD "
				+ "AFTER DELETE ON PERSONA FOR EACH ROW "
				+ "BEGIN "
				+ "INSERT INTO PAPELERA VALUES (null, old.NOMBRE, date('now'));"
				+ "END";
		try {
			sentencia = c.createStatement();
			sentencia.addBatch(sql);
			sentencia.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				sentencia.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	public static void crearVista(Connection c){
		String sql = "CREATE VIEW IF NOT EXISTS PEOPLE(NOMBRE, APELLIDOS)AS SELECT NOMBRE, APELLIDOS FROM PERSONA;";
		try {
			sentencia = c.createStatement();
			sentencia.execute(sql);
		} catch (SQLException e) {
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
