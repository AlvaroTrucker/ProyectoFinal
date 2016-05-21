package controlador;

import java.util.List;
import modelo.PersonaDTO;

public interface PersonaDAO {
	void insertarPersonaDTO(PersonaDTO p);
	int actualizarPersonaDTO(PersonaDTO p, int indice);
	int borrarPersonaDTO(String nombre);
	public List<PersonaDTO> leerTodasPersonas();
}
