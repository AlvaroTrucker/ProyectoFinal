package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.Box;
import java.awt.Component;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Interfaz extends JFrame {
	private JFrame frame;
	private JTable table;
	private JTextField txtIdfield;
	private JTextField txtGenerofield;

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

	/**
	 * Create the frame.
	 */
	public Interfaz() {
		frame = new JFrame();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 500);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);
		
		JMenuItem mntmAbrir = new JMenuItem("Abrir");
		mnArchivo.add(mntmAbrir);
		
		JMenu mnRegistro = new JMenu("Registro");
		menuBar.add(mnRegistro);
		
		JMenuItem mntmAnterior = new JMenuItem("Anterior");
		mnRegistro.add(mntmAnterior);
		
		JMenuItem mntmSiguiente = new JMenuItem("Siguiente");
		mnRegistro.add(mntmSiguiente);
		
		JMenuItem mntmNuevo = new JMenuItem("Nuevo");
		mnRegistro.add(mntmNuevo);
		
		JMenuItem mntmEliminar = new JMenuItem("Eliminar");
		mnRegistro.add(mntmEliminar);
		
		JMenu mnInforme = new JMenu("Informe");
		menuBar.add(mnInforme);
		
		JMenuItem mntmGenerarPdf = new JMenuItem("Generar PDF");
		mnInforme.add(mntmGenerarPdf);
		
		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);
		
		JMenuItem mntmMostrarAyuda = new JMenuItem("Mostrar ayuda");
		mnAyuda.add(mntmMostrarAyuda);
		
		JPanel panelEstado = new JPanel();
		
		JLabel lblEstado = new JLabel("PRUEBA");
		panelEstado.add(lblEstado);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		
		JPanel panelNorte = new JPanel();
		splitPane.setLeftComponent(panelNorte);
		
		table = new JTable();
		panelNorte.add(table);
		
		JScrollPane scrollPane = new JScrollPane();
		panelNorte.add(scrollPane);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panelEstado, GroupLayout.PREFERRED_SIZE, 434, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(splitPane, GroupLayout.PREFERRED_SIZE, 434, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(splitPane, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
					.addGap(225)
					.addComponent(panelEstado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		
		JPanel panelSur = new JPanel();
		splitPane.setRightComponent(panelSur);
		
		JPanel panel = new JPanel();
		
		JLabel lblId = new JLabel("ID");
		
		txtIdfield = new JTextField();
		txtIdfield.setColumns(10);
		
		JLabel lblGenero = new JLabel("Genero");
		
		txtGenerofield = new JTextField();
		txtGenerofield.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(5)
					.addComponent(lblId)
					.addGap(5)
					.addComponent(txtIdfield, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addComponent(lblGenero)
					.addGap(5)
					.addComponent(txtGenerofield, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(8)
					.addComponent(lblId))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(5)
					.addComponent(txtIdfield, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(8)
					.addComponent(lblGenero))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(5)
					.addComponent(txtGenerofield, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		panel.setLayout(gl_panel);
		GroupLayout gl_panelSur = new GroupLayout(panelSur);
		gl_panelSur.setHorizontalGroup(
			gl_panelSur.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSur.createSequentialGroup()
					.addGap(97)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		gl_panelSur.setVerticalGroup(
			gl_panelSur.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSur.createSequentialGroup()
					.addGap(5)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		panelSur.setLayout(gl_panelSur);
		getContentPane().setLayout(groupLayout);
		
		
	}
}