package vista;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import controlador.CrearTablas;
import controlador.ConexionBBDD;
import controlador.CrearPDF;
import controlador.ExtensionPDF;
import controlador.InsertarPersonas;
import controlador.LeerFichero;
import controlador.PersonaDAOImp;
import controlador.Tabla;
import controlador.CrearTrigger;
import modelo.ColeccionPersonas;
import modelo.PersonaDTO;

import javax.swing.GroupLayout;
import javax.swing.JFileChooser;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.ScrollPaneConstants;

public class Interfaz extends JFrame {
	private JFrame frame;
	private JTextField txtGenerofield;
	private JTextField txtNombrefield;
	private JTextField txtApellidosfield;
	private JTextField txtPaisfield;
	private JMenuItem mntmAbrir;
	private JLabel lblEstado;
	private JMenuItem siguiente;
	
	private File fichero;
	private LeerFichero fichero1 = null;
	private int indice = 0;
	private boolean modificar = false;
	
	private Connection conexion;
	
	PersonaDAOImp jSQLite = new PersonaDAOImp();
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz frame = new Interfaz();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Interfaz(){
		setResizable(false);
		setTitle("Aplicaci\u00F3n personas del mundo");
		conexion = ConexionBBDD.getConexion();
		initialize();
		//Controlador controlador = new Controlador(this);
	}
	
	

	/**
	 * Create the frame.
	 */
	public void initialize() {
		
		/*if (new File("personas.db").exists()){
			getMntmAbrir().setEnabled(false);
			getTable().setModel(new Tabla(jSQLite.leerTodasPersonas()));
		}*/
		
		frame = new JFrame("Aplicacion personas del mundo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 513, 500);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);
		
		JMenuItem mntmAbrir = new JMenuItem("Abrir");
		mntmAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos JSON", "json");
				JFileChooser fC = new JFileChooser();
				fC.setFileFilter(filter);
				int seleccion = fC.showOpenDialog(getMntmAbrir());
				if (seleccion == JFileChooser.APPROVE_OPTION){
					fichero = fC.getSelectedFile();
					fichero1 = new LeerFichero();
					fichero1.leerFichero(fichero);
					getTable().setModel(new Tabla(fichero1.getLista()));
					CrearTablas.crearTablaPersona(conexion);
					InsertarPersonas.insertarListaPersonas(conexion, fichero1.getLista());
				}	
				if (seleccion == JFileChooser.CANCEL_OPTION){
					getLblEstado().setText("No hay fichero cargado");
				}
			}
		});
		mnArchivo.add(mntmAbrir);
		
		JMenu mnRegistro = new JMenu("Registro");
		menuBar.add(mnRegistro);
		
		JMenuItem mntmAnterior = new JMenuItem("Anterior");
		mntmAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (indice!=0){
					indice -= 1;
					rellenarFormulario(indice);
				} else {
					getLblEstado().setText("No tiene cargado ningÃºn archivo");
				}
			}
		});
		mnRegistro.add(mntmAnterior);
		
		JMenuItem mntmSiguiente = new JMenuItem("Siguiente");
		mntmSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(indice == 0 || fichero1 != null){
					indice += 1;
					rellenarFormulario(indice);
				}
			}
		});
		mnRegistro.add(mntmSiguiente);
		
		JMenuItem mntmNuevo = new JMenuItem("Nuevo");
		mntmNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getNombre().setText("");
				getApellidos().setText("");
				getGenero().setText("");
				getPais().setText("");
				modificar = false;
			}
		});
		mnRegistro.add(mntmNuevo);
		
		JMenuItem mntmEliminar = new JMenuItem("Eliminar");
		mntmEliminar.setForeground(Color.RED);
		mntmEliminar.setBackground(Color.WHITE);
		mntmEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String texto = "¿Quieres borrar "+ fichero1.getLista().get(indice).getNombre() + "?";
				if(fichero1!=null){
					if (confirmar(texto)==0){
						CrearTrigger.trigger(conexion, ColeccionPersonas.getLista());
						fichero1.getLista().remove(fichero1.getLista().get(indice));
						//jSQLite.borrarPersonaDTO(ColeccionPersonas.getLista().get(indice).getNombre());
						getTable().setModel(new Tabla(fichero1.getLista()));
					}
				} else if (fichero1 == null) {
					getLblEstado().setText("No tiene cargado ningun archivo");
				}
			}
		});
		mnRegistro.add(mntmEliminar);
		
		JMenu mnInforme = new JMenu("Informe");
		menuBar.add(mnInforme);
		
		JMenuItem mntmGenerarPdf = new JMenuItem("Generar PDF");
		mntmGenerarPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String texto = "¿Quieres generar informe con los elementos seleccionados?";
				if (fichero1!=null){
					if (confirmar(texto)==0){
						FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos PDF", "pdf");
						JFileChooser jFPDF = new JFileChooser();
						jFPDF.setFileFilter(filtro);
						int seleccionSave = jFPDF.showSaveDialog(null);
						if (seleccionSave == JFileChooser.APPROVE_OPTION){
							List<PersonaDTO> lista = new ArrayList<PersonaDTO>();
							int[] arraySeleccion = getTable().getSelectedRows();
							for (int i = 0; i < arraySeleccion.length; i++ ){
								lista.add(fichero1.getLista().get(arraySeleccion[i]));
							}
							CrearPDF.crearPDF(lista, ExtensionPDF.obtenerExtension(jFPDF));
						}
					} 
				} else getLblEstado().setText("No tiene cargado ningÃºn archivo");
			}
		});
		mnInforme.add(mntmGenerarPdf);
		
		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);
		
		JMenuItem mntmAcercaDe = new JMenuItem("Acerca de...");
		mntmAcercaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(getFrame(),"Aplicacion personas del mundo\n"
						+ "Proyecto realizado en el modulo de Programacion de 1ºDAM (IES Virgen del Carmen)\n"
						+ "Hecho por Alvaro Jimenez Jimenez"
						+ "\nInicio 10/05/2016\n"
						+ "¿Final? 26/05/2016","Acerca de...",JOptionPane.PLAIN_MESSAGE);
			}
		});
		mnAyuda.add(mntmAcercaDe);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		
		JPanel panelNorte = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelNorte.getLayout();
		flowLayout.setVgap(90);
		splitPane.setLeftComponent(panelNorte);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panelNorte.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(43, Short.MAX_VALUE)
					.addComponent(splitPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(splitPane, GroupLayout.PREFERRED_SIZE, 420, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(20, Short.MAX_VALUE))
		);
		
		JPanel panelSur = new JPanel();
		splitPane.setRightComponent(panelSur);
		
		JPanel panel = new JPanel();
		
		JLabel lblGenero = new JLabel("Genero");
		
		txtGenerofield = new JTextField();
		txtGenerofield.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		
		txtNombrefield = new JTextField();
		txtNombrefield.setColumns(10);
		
		JLabel lblApellidos = new JLabel("Apellidos");
		
		txtApellidosfield = new JTextField();
		txtApellidosfield.setColumns(10);
		
		JLabel lblPais = new JLabel("Pais");
		
		txtPaisfield = new JTextField();
		txtPaisfield.setColumns(10);
		
		JButton btnAnadir = new JButton("Guardar registro");
		btnAnadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PersonaDTO persona = new PersonaDTO(getNombre().getText(), getApellidos().getText(), getGenero().getText(), getPais().getText());
				fichero1.getLista().add(persona);
				getTable().setModel(new Tabla(fichero1.getLista()));
				jSQLite.insertarPersonaDTO(persona);
				getTable().setModel(new Tabla(jSQLite.leerTodasPersonas()));
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNombre)
							.addGap(18)
							.addComponent(txtNombrefield, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblGenero)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(txtGenerofield, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblApellidos)
							.addGap(18)
							.addComponent(txtApellidosfield, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addComponent(lblPais)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(txtPaisfield, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(36))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(165, Short.MAX_VALUE)
					.addComponent(btnAnadir)
					.addGap(152))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(43)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombre)
						.addComponent(txtApellidosfield, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblApellidos)
						.addComponent(txtNombrefield, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblGenero)
							.addComponent(txtGenerofield, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(txtPaisfield, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblPais)))
					.addPreferredGap(ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
					.addComponent(btnAnadir)
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		
		JPanel panelEstado = new JPanel();
		panelEstado.setBackground(new Color(51, 255, 255));
		
		JLabel lblEstado = new JLabel("Barra de estado");
		panelEstado.add(lblEstado);
		GroupLayout gl_panelSur = new GroupLayout(panelSur);
		gl_panelSur.setHorizontalGroup(
			gl_panelSur.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panelSur.createSequentialGroup()
					.addGroup(gl_panelSur.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panelSur.createSequentialGroup()
							.addContainerGap()
							.addComponent(panelEstado, GroupLayout.PREFERRED_SIZE, 406, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.LEADING, gl_panelSur.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 406, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(26, Short.MAX_VALUE))
		);
		gl_panelSur.setVerticalGroup(
			gl_panelSur.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panelSur.createSequentialGroup()
					.addGap(5)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
					.addComponent(panelEstado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panelSur.setLayout(gl_panelSur);
		getContentPane().setLayout(groupLayout);
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	public JMenuItem getMntmAbrir() {
		return mntmAbrir;
	}
	
	public JTable getTable() {
		return table;
	}
	
	public JLabel getLblEstado() {
		return lblEstado;
	}
	
	public JTextField getNombre() {
		return txtNombrefield;
	}

	public void setNombre(JTextField txtNombrefield) {
		this.txtNombrefield = txtNombrefield;
	}
	
	public JTextField getApellidos() {
		return txtApellidosfield;
	}

	public void setApellidos(JTextField txtApellidosfield) {
		this.txtApellidosfield = txtApellidosfield;
	}
	
	public JTextField getGenero() {
		return txtGenerofield;
	}

	public void setGenero(JTextField txtGenerofield) {
		this.txtGenerofield = txtGenerofield;
	}
	
	public JTextField getPais() {
		return txtPaisfield;
	}

	public void setPais(JTextField txtPaisfield) {
		this.txtPaisfield = txtPaisfield;
	}
	
	public JMenuItem getSiguiente() {
		return siguiente;
	}
	
	private void rellenarFormulario(int indice){
		modificar = true;
		getNombre().setText(fichero1.getLista().get(indice).getNombre());
		getApellidos().setText(fichero1.getLista().get(indice).getApellidos());
		getGenero().setText(fichero1.getLista().get(indice).getGenero());
		getPais().setText(fichero1.getLista().get(indice).getPais());
		String cadena = "Registro "+indice+" de "+fichero1.getLista().size();
		getLblEstado().setText(cadena);
	}
	
	private int confirmar(String texto){
		int seleccion = JOptionPane.showOptionDialog(getFrame(), texto , "Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[] {"Si", "No"}, "No");
		return seleccion;
	}
}