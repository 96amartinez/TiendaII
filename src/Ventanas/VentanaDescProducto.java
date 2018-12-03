package Ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import BaseDeDatos.BD;
import Datos.Producto;
import Datos.Usuario;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class VentanaDescProducto extends JFrame {

	private JPanel contentPane;
	private JFrame ventanaAnterior;
	private Producto p;
	private JLabel lblNombre,lblPrecio,lblFoto, lblCantidad, lblTalla,lblFoto1;
	JComboBox cbCantidad,cbTalla;


	private ImageIcon im;
	private JScrollPane scrollPane;
	
	private void ponerCamposInvisibles() {
		lblCantidad.setVisible(false);
		cbCantidad.setVisible(false);
		lblTalla.setVisible(false);
		cbTalla.setVisible(false);
	}

	/**
	 * Create the frame.
	 */
	public VentanaDescProducto(String url, JFrame va, String cat, String nom, double precio, String tipo, String desc) {
		setResizable(false);
		ventanaAnterior = va;
		JFrame v=this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new PanelFondo();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setLocationRelativeTo(null);
		setContentPane(contentPane);

		

		JPanel paneSur = new PanelFondo();
		paneSur.setOpaque(true);
		contentPane.add(paneSur, BorderLayout.SOUTH);

		JButton btnVolver = new JButton("Volver al Menú");
		btnVolver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				v.dispose();
				VentanaMenu vp = new VentanaMenu(v);
				vp.setVisible(true);

			}
		});
		paneSur.add(btnVolver);

		JButton btnAadirAlCarrito = new JButton("Añadir al Carrito");
		paneSur.add(btnAadirAlCarrito);

		JPanel panel = new PanelFondo();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		lblNombre = new JLabel();
		lblNombre.setBounds(20, 6, 396, 20);
		panel.add(lblNombre);

		lblPrecio = new JLabel("Precio: ");
		lblPrecio.setBounds(20, 197, 61, 16);
		panel.add(lblPrecio);

		ImageIcon im = new ImageIcon(url);
		lblFoto = new JLabel(im);
		lblFoto.setBounds(10,39,173,158);
		im.setImage(im.getImage().getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.SCALE_SMOOTH));
		panel.add(lblFoto);
		
		//im.setImage(im.getImage().getScaledInstance(lblFoto.getWidth()*2, lblFoto.getHeight()*2, Image.SCALE_SMOOTH));



		String [] cant = {"1", "2","3","4", "5","6","7", "8","9","10", "11"};
		cbCantidad = new JComboBox(cant);
		cbCantidad.setBounds(337, 53, 75, 27);
		panel.add(cbCantidad);

		ArrayList<String> tallas = BD.obtenerTallaProducto(url);
		cbTalla = new JComboBox();
		cbTalla.setBounds(337, 92, 75, 27);
		for(String t: tallas)
			cbTalla.addItem(t);
		panel.add(cbTalla);

		lblNombre.setText("                                      " +nom);
		//lblPrecio.setText(BD.obtenerPrecioProducto(url));

		lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setBounds(264, 57, 61, 16);
		panel.add(lblCantidad);

		lblTalla = new JLabel("Talla: ");
		lblTalla.setBounds(264, 96, 61, 16);
		panel.add(lblTalla);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(195, 136, 234, 55);
		panel.add(scrollPane);
		JTextArea textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		scrollPane.setViewportView(textArea);
		textArea.setText("" + desc);
		textArea.setEditable(false);
		textArea.setOpaque(false);
		textArea.setLineWrap(true);
		
		ponerCamposInvisibles();
		if(cat.equals("Baloncesto")) {
			if(tipo.equals("Camisetas") || tipo.equals("Sudaderas") || tipo.equals("Pantalones") || tipo.equals("Playeras")) {
				lblTalla.setVisible(true);
				cbTalla.setVisible(true);
				lblCantidad.setVisible(true);
				cbCantidad.setVisible(true);
			}else if(tipo.equals("Balones")) {
				lblCantidad.setVisible(true);
				cbCantidad.setVisible(true);
			}
		} else if(cat.equals("Fútbol")) {
			if(tipo.equals("Camisetas") || tipo.equals("Sudaderas") || tipo.equals("Guantes") || tipo.equals("Botas") || tipo.equals("Pantalones")) {
				lblTalla.setVisible(true);
				cbTalla.setVisible(true);
				lblCantidad.setVisible(true);
				cbCantidad.setVisible(true);
			}else if(tipo.equals("Balones")) {
				lblCantidad.setVisible(true);
				cbCantidad.setVisible(true);
			}
		} else if(cat.equals("Videojuegos")) {
			cbCantidad.setVisible(true);
			lblCantidad.setVisible(true);
		} else if(cat.equals("Pádel")) {
			if(tipo.equals("Camisetas") || tipo.equals("Pantalones")) {
				lblTalla.setVisible(true);
				cbTalla.setVisible(true);
				cbCantidad.setVisible(true);
				lblCantidad.setVisible(true);
			}else if(tipo.equals("Paleteros") || tipo.equals("Palas") || tipo.equals("Pelotas") || tipo.equals("Protectores")) {
				cbCantidad.setVisible(true);
				lblCantidad.setVisible(true);
			}
		} else if(cat.equals("Ciclismo")) {
			if(tipo.equals("Maillots") || tipo.equals("Bicicletas Carretera") || tipo.equals("Bicicletas Montaña") || tipo.equals("Cascos")) {
				cbTalla.setVisible(true);
				lblTalla.setVisible(true);
				cbCantidad.setVisible(true);
				lblCantidad.setVisible(true);
			}else if(tipo.equals("Botellines")) {
				cbCantidad.setVisible(true);
				lblCantidad.setVisible(true);

			}
		}
	}
}