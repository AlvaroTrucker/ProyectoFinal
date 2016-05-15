import java.awt.EventQueue;

import controlador.Controlador;
import vista.Interfaz;

public class TestPersona {
	
	public static void main(String[] args) {
		
		Controlador controlador = new Controlador(new Interfaz());

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz window = new Interfaz();
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

}
