package controlador;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import controlador.LeerFichero;
import controlador.Tabla;

import vista.Interfaz;

public class Controlador {
	private Interfaz interfaz;
	private File fichero;
	private LeerFichero fichero1 = null;
	
	public Controlador(Interfaz interfaz){
		this.interfaz = interfaz;
		inicializar();
	}
	
	public void inicializar(){
		interfaz.getMntmAbrir().addActionListener(l->{
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos JSON", "json");
			JFileChooser fC = new JFileChooser();
			fC.setFileFilter(filter);
			int seleccion = fC.showOpenDialog(interfaz.getMntmAbrir());
			if (seleccion == JFileChooser.APPROVE_OPTION){
				fichero = fC.getSelectedFile();
				fichero1 = new LeerFichero();
				fichero1.leerFichero(fichero);
				//interfaz.getTabla().setModel(new Tabla(fichero1.getLista()));
			}			
			if (seleccion == JFileChooser.CANCEL_OPTION){
				//interfaz.getLblEstado().setText("No hay fichero cargado");
			}
			
		});
	}
	
}
