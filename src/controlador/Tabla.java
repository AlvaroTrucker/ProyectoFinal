package controlador;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import modelo.Persona;

public class Tabla extends AbstractTableModel{
	
	private static final String[] CABECERA = {"ID","Nombre","Apellidos","Genero","Pais","Dinero"};
	private String[][] array;
	
	public Tabla(List<Persona> lista) {
		 array = Lista.getLista(lista);
	}

	@Override
	public int getRowCount() {
		return array.length;
	}
	@Override
	public int getColumnCount() {
		return 4;
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return array[rowIndex][columnIndex];
	}
	
	@Override
	public String getColumnName(int column) {
		return CABECERA[column];
	}
	
}
