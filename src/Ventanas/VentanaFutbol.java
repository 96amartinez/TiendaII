package Ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BaseDeDatos.BD;
import Ventanas.VentanaPatrocinadores;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;

public class VentanaFutbol extends JFrame {

	private JPanel contentPane;
	private JPanel panelCentro;
	public static BD bd;
	private static Connection con;
	private JComboBox cbTipo;
	private JComboBox cbTalla;
	private JComboBox cbColor;
	private JComboBox cbMarca;
	private JComboBox cbEquipo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaFutbol frame = new VentanaFutbol();
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
	public VentanaFutbol() {
		JFrame v = this;
		setExtendedState(MAXIMIZED_BOTH);
		//v.setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelNorte = new JPanel();
		contentPane.add(panelNorte, BorderLayout.NORTH);
		
		cbTipo = new JComboBox();
		rellenaComboTipo();
		
		cbTalla = new JComboBox();
		rellenaComboTalla();
		
		cbColor = new JComboBox();
		rellenaComboColor();
		
		cbMarca = new JComboBox();
		rellenaComboMarca();
		
		cbEquipo = new JComboBox();
		rellenaComboEquipo();
		
		cbTipo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				rellenaComboTalla((String)cbTipo.getSelectedItem());
				rellenaComboColor((String)cbTipo.getSelectedItem());
				rellenaComboMarca((String)cbTipo.getSelectedItem());
				rellenaComboEquipo((String)cbTipo.getSelectedItem());
			}

		});

		JLabel lblTipo = new JLabel("Tipo");
		panelNorte.add(lblTipo);
		panelNorte.add(cbTipo);
		
		JLabel lblTalla = new JLabel("Talla");
		panelNorte.add(lblTalla);
		panelNorte.add(cbTalla);
		
		JLabel lblColor = new JLabel("Color");
		panelNorte.add(lblColor);
		panelNorte.add(cbColor);
		
		JLabel lblMarca = new JLabel("Marca");
		panelNorte.add(lblMarca);
		panelNorte.add(cbMarca);
		
		JLabel lblEquipo = new JLabel("Equipo");
		panelNorte.add(lblEquipo);
		panelNorte.add(cbEquipo);
		

		JButton btnFiltrar = new JButton("FILTRAR");
		panelNorte.add(btnFiltrar);
		btnFiltrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				boolean seleccionado;
				do {
					seleccionado = true;
					String tipo = null;
					if(cbTipo.getSelectedIndex()!=-1)
						tipo=(String)cbTipo.getSelectedItem();
//					else {
//						seleccionado = false;
//						JOptionPane.showMessageDialog(null, "Tiene que seleccionar una opción");
//					}
					String color = null;
					if(cbColor.getSelectedIndex()!=-1)
						color = (String) cbColor.getSelectedItem();
//					else {
//						seleccionado = false;
//						JOptionPane.showMessageDialog(null, "Tiene que seleccionar una opción");
//					}
					String marca = null;
					if(cbMarca.getSelectedIndex()!=-1)
						marca = (String) cbMarca.getSelectedItem();
//					else {
//						seleccionado = false;
//						JOptionPane.showMessageDialog(null, "Tiene que seleccionar una opción");
//					}
					String talla =null;
					if(cbTalla.getSelectedIndex()!=-1)
						talla = (String) cbTalla.getSelectedItem();
//					else {
//						seleccionado = false;
//						JOptionPane.showMessageDialog(null, "Tiene que seleccionar una opción");
//					}
					String equipo = null;
					if(cbEquipo.getSelectedIndex()!=-1)
						equipo = (String) cbEquipo.getSelectedItem();
//					else {
//						seleccionado = false;
//						JOptionPane.showMessageDialog(null, "Tiene que seleccionar una opción");
//					}
					panelCentro.removeAll();
					ArrayList<String> rutasFiltradas = VentanaLogin.bd.obtenerRutasConFiltroFutbol(tipo, color, marca, talla, equipo, "Fútbol");
					for(int i=0;i<rutasFiltradas.size();i++) {
						String ruta = rutasFiltradas.get(i);
						ImageIcon im = new ImageIcon(ruta);
						int width =500;
						int height = 500;
						im.setImage(im.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
						JLabel lblFoto = new JLabel(im);
						panelCentro.add(lblFoto);
						lblFoto.setIcon(im);
					}
					panelCentro.updateUI();
				} while(!seleccionado);
			}
			
		});
		
		JPanel panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		JButton btnVolver = new JButton("VOLVER");
		btnVolver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				v.dispose();
				VentanaMenu vm = new VentanaMenu(v);
				vm.setVisible(true);
			}
			
		});
		panelSur.add(btnVolver);
		
		panelCentro = new JPanel();
		JScrollPane sc = new JScrollPane(panelCentro);
		sc.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
	//	sc.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		contentPane.add(sc, BorderLayout.CENTER);
		panelCentro.setLayout(new GridLayout(0,5,0,0));
		panelCentro.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent evento) {
				// TODO Auto-generated method stub
				Point p = evento.getPoint();
				JLabel lblProducto = (JLabel)panelCentro.getComponentAt(p);
				ImageIcon im = (ImageIcon)lblProducto.getIcon();
				String url = im.getDescription();
				v.dispose();
				VentanaDescProducto vdp = new VentanaDescProducto(url,v, "Fútbol", BD.obtenerNombreProducto(url), BD.obtenerPrecioProducto(url),BD.obtenerTipoProducto(url), BD.obtenerDescProducto(url));
				vdp.setVisible(true);
				}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		cargarImagenes();
		
		
	}

	private void cargarImagenes() {
		ArrayList<String> aRutas = VentanaLogin.bd.obtenerProductosCategoria("Fútbol");
		for(int i=0;i<aRutas.size();i++) {
			String ruta = aRutas.get(i);
			ImageIcon im = new ImageIcon(ruta);
			int width =450;
			int height = 400;
			im.setImage(im.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
			JLabel lblFoto = new JLabel(im);
			panelCentro.add(lblFoto);
			lblFoto.setIcon(im);
		}
	}
	
	@SuppressWarnings("unchecked")
	private void rellenaComboEquipo() {
		// TODO Auto-generated method stub
		cbEquipo.addItem("");
		cbEquipo.addItem("Athletic Club");
		cbEquipo.addItem("Liverpool");
		cbEquipo.addItem("Chelsea");
		cbEquipo.addItem("PSG");
		cbEquipo.addItem("Leicester City");
		cbEquipo.addItem("Manchester City");
		cbEquipo.addItem("Manchester United");
		cbEquipo.addItem("Arsenal");
	}


	@SuppressWarnings("unchecked")
	private void rellenaComboMarca() {
		// TODO Auto-generated method stub
		cbMarca.addItem("");
		cbMarca.addItem("Nike");
		cbMarca.addItem("Adidas");
		cbMarca.addItem("New Balance");
		cbMarca.addItem("Puma");
		cbMarca.addItem("AirJordan");
	}


	@SuppressWarnings("unchecked")
	private void rellenaComboColor() {
		// TODO Auto-generated method stub
		cbColor.addItem("");
		cbColor.addItem("Amarillo");
		cbColor.addItem("Azul");
		cbColor.addItem("Rojo");
		cbColor.addItem("Verde");
		cbColor.addItem("Blanco");
		cbColor.addItem("Negro");
	}


	@SuppressWarnings("unchecked")
	private void rellenaComboTalla() {
		// TODO Auto-generated method stub
		cbTalla.addItem("");
		cbTalla.addItem("35");
		cbTalla.addItem("36");
		cbTalla.addItem("37");
		cbTalla.addItem("38");
		cbTalla.addItem("39");
		cbTalla.addItem("40");
		cbTalla.addItem("41");
		cbTalla.addItem("42");
		cbTalla.addItem("43");
		cbTalla.addItem("44");
		cbTalla.addItem("45");
		cbTalla.addItem("46");
		cbTalla.addItem("S");
		cbTalla.addItem("M");
		cbTalla.addItem("L");
		cbTalla.addItem("XL");
		cbTalla.addItem("XXL");
	}


	@SuppressWarnings("unchecked")
	private void rellenaComboTipo() {
		// TODO Auto-generated method stub
		cbTipo.addItem("");
		cbTipo.addItem("Botas");
		cbTipo.addItem("Camisetas");
		cbTipo.addItem("Pantalones");
		cbTipo.addItem("Balones");
		cbTipo.addItem("Sudaderas");
		cbTipo.addItem("Guantes");
	}
	

	@SuppressWarnings("unchecked")
	private void rellenaComboTalla(String selectedItem) {
		// TODO Auto-generated method stub
		cbTalla.removeAllItems();
		if(selectedItem.equals("")) {
			rellenaComboTalla();
		}else if(selectedItem.equals("Botas")) {
			cbTalla.addItem("");
			cbTalla.addItem("35");
			cbTalla.addItem("36");
			cbTalla.addItem("37");
			cbTalla.addItem("38");
			cbTalla.addItem("39");
			cbTalla.addItem("40");
			cbTalla.addItem("41");
			cbTalla.addItem("42");
			cbTalla.addItem("43");
			cbTalla.addItem("44");
			cbTalla.addItem("45");
			cbTalla.addItem("46");
		} else if(selectedItem.equals("Camisetas")) {
			cbTalla.addItem("");
			cbTalla.addItem("S");
			cbTalla.addItem("M");
			cbTalla.addItem("L");
			cbTalla.addItem("XL");
			cbTalla.addItem("XXL");
		} else if(selectedItem.equals("Pantalones")) {
			cbTalla.addItem("");
			cbTalla.addItem("S");
			cbTalla.addItem("M");
			cbTalla.addItem("L");
			cbTalla.addItem("XL");
			cbTalla.addItem("XXL");
		} else if(selectedItem.equals("Balones")) {
			cbTalla.removeAllItems();
		} else if(selectedItem.equals("Sudaderas")) {
			cbTalla.addItem("");
			cbTalla.addItem("S");
			cbTalla.addItem("M");
			cbTalla.addItem("L");
			cbTalla.addItem("XL");
			cbTalla.addItem("XXL");
		} else if(selectedItem.equals("Guantes")) {
			cbTalla.addItem("");
			cbTalla.addItem("S");
			cbTalla.addItem("M");
			cbTalla.addItem("L");
			cbTalla.addItem("XL");
			cbTalla.addItem("XXL");
		}
	}
	
	@SuppressWarnings("unchecked")
	private void rellenaComboMarca(String selectedItem) {
		// TODO Auto-generated method stub
		cbMarca.removeAllItems();
		if(selectedItem.equals("")) {
			rellenaComboMarca();
		}else if(selectedItem.equals("Botas")) {
			rellenaComboMarca();
		}else if(selectedItem.equals("Camisetas")) {
			rellenaComboMarca();
		}else if(selectedItem.equals("Pantalones")) {
			rellenaComboMarca();
		}else if(selectedItem.equals("Balones")) {
			rellenaComboMarca();
		}else if(selectedItem.equals("Sudaderas")) {
			rellenaComboMarca();
		}else if(selectedItem.equals("Guantes")) {
			rellenaComboMarca();
		}

	}

	@SuppressWarnings("unchecked")
	private void rellenaComboColor(String selectedItem) {
		// TODO Auto-generated method stub
		cbColor.removeAllItems();
		if(selectedItem.equals("")) {
			rellenaComboColor();
		}else if(selectedItem.equals("Botas")) {
			rellenaComboColor();
		}else if(selectedItem.equals("Camisetas")) {
			rellenaComboColor();
		}else if(selectedItem.equals("Pantalones")) {
			rellenaComboColor();
		}else if(selectedItem.equals("Balones")) {
			rellenaComboColor();
		}else if(selectedItem.equals("Sudaderas")) {
			rellenaComboColor();
		}else if(selectedItem.equals("Guantes")) {
			rellenaComboColor();
		}

	}
	
	@SuppressWarnings("unchecked")
	private void rellenaComboEquipo(String selectedItem) {
		// TODO Auto-generated method stub
		cbEquipo.removeAllItems();
		if(selectedItem.equals("")) {
			rellenaComboEquipo();
		}else if(selectedItem.equals("Botas")) {
			cbEquipo.removeAllItems();
		}else if(selectedItem.equals("Camisetas")) {
			rellenaComboEquipo();
		}else if(selectedItem.equals("Pantalones")) {
			rellenaComboEquipo();
		}else if(selectedItem.equals("Balones")) {
			rellenaComboEquipo();
		}else if(selectedItem.equals("Sudaderas")) {
			rellenaComboEquipo();
		}else if(selectedItem.equals("Guantes")) {
			cbEquipo.removeAllItems();
		}

	}
	

}
