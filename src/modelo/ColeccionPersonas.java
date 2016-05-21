package modelo;

import java.util.ArrayList;
import java.util.List;

public class ColeccionPersonas {
private static List<PersonaDTO> lista = new ArrayList<PersonaDTO>();
	
	public boolean addLista(PersonaDTO persona){
		return lista.add(persona);
	}
	
	public static List<PersonaDTO> getLista() {
		return lista;
	}
}
