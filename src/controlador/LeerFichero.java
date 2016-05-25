package controlador;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.stream.JsonReader;

import modelo.PersonaDTO;

/**
 * Clase que lee el fichero JSON
 * @author Alvaro Jimenez
 * @version 1.0
 *
 */
public class LeerFichero {
	
	private List<PersonaDTO> lista = new ArrayList<PersonaDTO>();

	public void leerFichero(File file){
		try (JsonReader jReader = new JsonReader(new FileReader(file));){
			jReader.beginArray();
			String nombre = " ";
			String apellidos = " ";
			String genero = " ";
			String pais = " ";
			
			while (jReader.hasNext()){
				jReader.beginObject();
				if (jReader.nextName().equals("Genero"))
					genero = jReader.nextString();
				if (jReader.nextName().equals("Nombre"))
					nombre = jReader.nextString();
				if (jReader.nextName().equals("Apellidos"))
					apellidos = jReader.nextString();
				if (jReader.nextName().equals("Pais"))
					pais = jReader.nextString();
				
				lista.add(new PersonaDTO(nombre, apellidos, genero, pais));
				jReader.endObject();
			}
			jReader.endArray();
			

			
		} catch (IOException e) {
			e.printStackTrace();}
		}
	public List<PersonaDTO> getLista() {
		return lista;
	}
}
