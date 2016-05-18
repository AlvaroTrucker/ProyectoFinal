package controlador;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import modelo.Persona;

public class InsertarPersonas {
	
	private static Statement sentencia;
	public static void insertarListaPersonas(Connection con, List<Persona> lista){
		String sql = "";
		for (Persona persona : lista) {
			sql = "INSERT INTO PERSONA VALUES (null,'"+persona.getNombre()+"','"+persona.getApellidos()+"','"+persona.getGenero()+"','"+persona.getPais()+"')";
			try {
				sentencia = con.createStatement();
				sentencia.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void insertarNuevaPersona(Connection con, Persona persona){
		String sql = "INSERT INTO PERSONA VALUES (null,'"+persona.getNombre()+"','"+persona.getApellidos()+"','"+persona.getGenero()+"','"+persona.getPais()+"')";
		
		try {
			sentencia = con.createStatement();
			sentencia.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
