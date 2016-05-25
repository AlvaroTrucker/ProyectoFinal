package controlador;

import java.util.ArrayList;
import java.util.List;

import modelo.PersonaDTO;

/**
 * Clase que crea un array bidimensional para crear la tabla
 * @author Alvaro Jimenez
 * @version 1.0
 *
 */
public class Lista {
	protected static String[][] getLista(List<PersonaDTO> lista){
		String[][] array = new String[lista.size()][lista.size()*4];
		List<String> cadenas = new ArrayList<String>();
		for (int i=0; i<lista.size(); i++){
			PersonaDTO persona = (PersonaDTO) lista.get(i);
			for (int j=0; j<4; j++){
				switch (j){
				case 0:
					cadenas.add(persona.getNombre());
					break;
				case 1:
					cadenas.add(persona.getApellidos());
					break;
				case 2:
					cadenas.add(persona.getGenero());
					break;
				case 3:
					cadenas.add(persona.getPais());
					break;
				}
				array[i][j] = cadenas.get(j);
			}
			cadenas.clear();
		}
		
		return array;

	}
	
}
