package controlador;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import controlador.LeerFichero;
import controlador.Tabla;

import vista.Interfaz;

public class Controlador {
	private Interfaz interfaz;
	private LeerFichero fichero1 = null;
	private int indice = 0;
	private boolean modificar = true;
	
	public Controlador(Interfaz interfaz){
		this.interfaz = interfaz;
		inicializar();
	}
	
	public void inicializar(){
		
	}
	
}
