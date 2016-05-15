package modelo;

public class Persona {
	//atributos
	private String nombre;
	private String apellidos;
	private String genero;
	private String pais;
	
	public Persona(String nombre, String apellidos, String genero, String pais) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.genero = genero;
		this.pais = pais;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}
	
	
	
	
	
}
