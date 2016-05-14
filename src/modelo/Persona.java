package modelo;

public class Persona {
	//atributos
	private int id;
	private String nombre;
	private String apellidos;
	private String genero;
	private String pais;
	private double dinero;
	
	public Persona(int id, String nombre, String apellidos, String genero, String pais, double dinero) {
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.genero = genero;
		this.pais = pais;
		this.dinero = dinero;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public double getDinero() {
		return dinero;
	}

	public void setDinero(double dinero) {
		this.dinero = dinero;
	}
	
}
