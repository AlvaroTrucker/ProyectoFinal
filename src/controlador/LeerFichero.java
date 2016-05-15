package controlador;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.stream.JsonReader;

import modelo.Persona;

public class LeerFichero {
	
	private List<Persona> lista = new ArrayList<Persona>();

	public void leerFichero(File file){
		try (JsonReader jReader = new JsonReader(new FileReader(file));){
			jReader.beginArray();
			//int id = 0;
			String nombre = "";
			String apellidos = "";
			String genero = "";
			String pais = "";
			//double dinero = 0.0;
			
			while (jReader.hasNext()){
				jReader.beginObject();
				/*if (jReader.nextName().equals("ID"))
					id = jReader.nextInt();*/
				if (jReader.nextName().equals("Genero"))
					genero = jReader.nextString();
				if (jReader.nextName().equals("Nombre"))
					nombre = jReader.nextString();
				if (jReader.nextName().equals("Apellidos"))
					apellidos = jReader.nextString();
				if (jReader.nextName().equals("Pais"))
					pais = jReader.nextString();
				/*if (jReader.nextName().equals("Dinero")) 
					dinero = jReader.nextDouble();*/
				
				lista.add(new Persona(nombre, apellidos, genero, pais));
				jReader.endObject();
			}
			jReader.endArray();
			

			
		} catch (IOException e) {
			e.printStackTrace();}
		}
	public List<Persona> getLista() {
		return lista;
	}
}
