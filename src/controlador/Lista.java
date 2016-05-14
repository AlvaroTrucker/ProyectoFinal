package controlador;

import java.util.ArrayList;
import java.util.List;

import modelo.Persona;

public class Lista {
	protected static String[][] getLista(List<Persona> lista){
		String[][] array = new String[lista.size()][lista.size()*4];
		List<String> cadenas = new ArrayList<String>();
		for (int i=0; i<lista.size(); i++){
			Persona persona = (Persona) lista.get(i);
			for (int j=0; j<6; j++){
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
