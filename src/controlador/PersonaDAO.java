package controlador;

import modelo.PersonaDTO;

public interface PersonaDAO {
	void insertarPersonaDTO(PersonaDTO p);
	void actualizarPersonaDTO(PersonaDTO p, String nombre, String apellidos);
	void borrarPersonaDTO(String nombre, String apellidos);
}
