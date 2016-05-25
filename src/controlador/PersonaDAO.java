package controlador;

import java.util.List;
import modelo.PersonaDTO;

/**
 * Clase (interfaz) que contiene metodos de insercion, actualizacion y borrado
 * @author Alvaro
 *
 */
public interface PersonaDAO {
	void insertarPersonaDTO(PersonaDTO p);
	int actualizarPersonaDTO(PersonaDTO p, int indice);
	int borrarPersonaDTO(String nombre);
	public List<PersonaDTO> leerTodasPersonas(); //Para leer todos los registros del JSON
}
