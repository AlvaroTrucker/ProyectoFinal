package modelo;

import java.util.ArrayList;
import java.util.List;
import modelo.PersonaDTO;

/**
 * Clase consistente en una coleccion de personas
 * @author Alvaro Jimenez
 * @version 1.0
 */

public class ColeccionPersonas {
private static List<PersonaDTO> lista = new ArrayList<PersonaDTO>();
	
	public boolean addLista(PersonaDTO persona){
		return lista.add(persona);
	}
	
	public static List<PersonaDTO> getLista() {
		return lista;
	}
}
