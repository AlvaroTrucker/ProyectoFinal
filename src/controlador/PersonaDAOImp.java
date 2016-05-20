package controlador;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import modelo.PersonaDTO;

public class PersonaDAOImp implements PersonaDAO {
	private Connection conexion = ConexionBBDD.getConexion();
	private Statement sentencia;
	private String sql;			

	@Override
	public void insertarPersonaDTO(PersonaDTO p) {
		sql = "INSERT INTO PERSONA VALUES (null,'"+p.getNombre()+"','"+p.getApellidos()+"','"+p.getGenero()+"','"+p.getPais()+"')";
		try {
			sentencia = conexion.createStatement();
			sentencia.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void actualizarPersonaDTO(PersonaDTO p, String nombre, String apellidos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void borrarPersonaDTO(String nombre, String apellidos) {
		// TODO Auto-generated method stub
		
	}

}
