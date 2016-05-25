package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import modelo.ColeccionPersonas;
import modelo.PersonaDTO;


/**
 * Clase que implementa los metodos de PersonaDAO
 * @author Alvaro Jimenez
 * @version 1.0
 *
 */
public class PersonaDAOImp implements PersonaDAO {
	private Connection conexion = ConexionBBDD.getConexion();
	private static Statement sentencia;
	private static PreparedStatement sentenciaPreparada;
	private String sql;
	private static ColeccionPersonas coleccion = new ColeccionPersonas();

	//metodo que inserta datos en la BBDD
	@Override
	public void insertarPersonaDTO(PersonaDTO p) {
		try {
			sentencia = conexion.createStatement();
			sql = "INSERT INTO PERSONA VALUES (null,'"+p.getNombre()+"','"+p.getApellidos()+"','"+p.getGenero()+"','"+p.getPais()+"')";
			sentencia.executeUpdate(sql);
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
	
	//metodo para actualizar datos en la BBDD
	//actualmente no se esta usando en el proyecto
	@Override
	public int actualizarPersonaDTO(PersonaDTO p, int indice) {
		sql = "UPDATE PERSONA SET NOMBRE=?, APELLIDOS=?, GENERO=?, PAIS=? WHERE ID=?";
		int valor = 0;
		try {
			sentenciaPreparada = conexion.prepareStatement(sql);
			sentenciaPreparada.setString(1, p.getNombre());
			sentenciaPreparada.setString(2, p.getApellidos());
			sentenciaPreparada.setString(3, p.getGenero());
			sentenciaPreparada.setString(4, p.getPais());
			sentenciaPreparada.setInt(5, indice);
			valor = sentenciaPreparada.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				sentenciaPreparada.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return valor;
		
	}

	//metodo que borra datos de la BBDD
	//implementado pero no funciona en la interfaz
	@Override
	public int borrarPersonaDTO(String nombre) {
		sql = "DELETE FROM PERSONA WHERE NOMBRE=?";
		int valor = 0;
		try {
			sentenciaPreparada = conexion.prepareStatement(sql);
			sentenciaPreparada.setString(1, nombre);
			valor = sentenciaPreparada.executeUpdate();
			sentenciaPreparada.closeOnCompletion();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(valor);
		return valor;
	}

	//metodo que devuelve todos los registros de la BBDD
	@Override
	public List<PersonaDTO> leerTodasPersonas() {
		ColeccionPersonas.getLista().clear();
		try {
			sentencia = conexion.createStatement();
			String sql = "SELECT * FROM persona";
			ResultSet resultado = sentencia.executeQuery(sql);
			while(resultado.next()){
					coleccion.addLista(new PersonaDTO(resultado.getString("nombre"),resultado.getString("apellidos"),resultado.getString("genero"),resultado.getString("pais")));
			}
			sentencia.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ColeccionPersonas.getLista();
	}

}
