package controlador;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Clase que obtiene la extension .pdf para los PDFs
 * @author Alvaro Jimenez
 * @version 1.0
 *
 */

public class ExtensionPDF {
	public static File obtenerExtension(JFileChooser jF){
		File archivo = jF.getSelectedFile();
		if (jF.getFileFilter() instanceof FileNameExtensionFilter){
			String[] extension = ((FileNameExtensionFilter)jF.getFileFilter()).getExtensions();
			String nombreMinusculas = archivo.getName().toLowerCase();
			for (String ext : extension) {
				if (nombreMinusculas.endsWith('.'+ ext.toLowerCase())){
					return archivo;
				}
			}
			archivo = new File(archivo.toString()+'.'+extension[0].toString());
		}
		return archivo;
	}
}
