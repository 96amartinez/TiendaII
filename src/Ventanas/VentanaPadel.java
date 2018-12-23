package Ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import Datos.Producto;

public class VentanaPadel extends JFrame {

	private JPanel contentPane;
	private JPanel panelCentro;
	private Producto p;
	private JComboBox cbTipo;
	private JComboBox cbTalla;
	private JComboBox cbMarca;
	private JComboBox cbColor;
	private JSlider sPeso;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPadel frame = new VentanaPadel();
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
	public VentanaPadel() {
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
		
		sPeso = new JSlider(0,10,0);
		sPeso.setPaintTicks(true); //las rayitas que marcan los números
		sPeso.setMajorTickSpacing(1); // de cuanto en cuanto los números en el slider
		sPeso.setMinorTickSpacing(1); //las rayitas de cuanto en cuanto
		sPeso.setPaintLabels(true); //si se ve los números del slider
		
		cbTalla = new JComboBox();
		rellenaComboTalla();
		
		cbColor = new JComboBox();
		rellenaComboColor();
		
		cbMarca = new JComboBox();
		rellenaComboMarca();
		
		cbTipo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				rellenaSliderPeso((String)cbTipo.getSelectedItem());
				rellenaComboTalla((String)cbTipo.getSelectedItem());
				rellenaComboColor((String)cbTipo.getSelectedItem());
				rellenaComboMarca((String)cbTipo.getSelectedItem());
			}
		});
		
		JLabel lblTipo = new JLabel("Tipo");
		panelNorte.add(lblTipo);
		panelNorte.add(cbTipo);
		
		JLabel lblPeso = new JLabel("Peso");
		panelNorte.add(lblPeso);
		panelNorte.add(sPeso);
		
		JLabel lblTalla = new JLabel("Talla");
		panelNorte.add(lblTalla);
		panelNorte.add(cbTalla);
		
		JLabel lblColor = new JLabel("Color");
		panelNorte.add(lblColor);
		panelNorte.add(cbColor);
		
		JLabel lblMarca = new JLabel("Marca");
		panelNorte.add(lblMarca);
		panelNorte.add(cbMarca);

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
					double peso = 0;
					if(cbTipo.getSelectedIndex() != -1)
						tipo = (String)cbTipo.getSelectedItem();
//					else {
//						seleccionado = false;
//						JOptionPane.showMessageDialog(null, "Tienes que seleccionar todas las opciones");
//					}
					String talla = null;
					if(cbTalla.getSelectedIndex() != -1)
						talla = (String)cbTalla.getSelectedItem();
//					else {
//						seleccionado = false;
//						JOptionPane.showMessageDialog(null, "Tienes que seleccionar todas las opciones");
//					}
					peso = sPeso.getValue() * 100; 
					String color = null;
					if(cbColor.getSelectedIndex() != -1)
						color = (String) cbColor.getSelectedItem();
//					else {
//						seleccionado = false;
//						JOptionPane.showMessageDialog(null, "Tienes que seleccionar todas las opciones");
//					}
					String marca = null;
					if(cbMarca.getSelectedIndex() != -1)
						marca = (String) cbMarca.getSelectedItem();
//					else {
//						seleccionado = false;
//						JOptionPane.showMessageDialog(null, "Tienes que seleccionar todas las opciones");
//					}
					panelCentro.removeAll();
					ArrayList<String> rutasFiltradas = VentanaLogin.bd.obtenerRutasConFiltroPadel(tipo,peso,color,marca, "Pádel", talla);
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
				}while(!seleccionado);

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
		cargarImagenes();


	}


	@SuppressWarnings("unchecked")
	private void rellenaComboMarca() {
		// TODO Auto-generated method stub
		cbMarca.addItem("");
		cbMarca.addItem("Artengo");
		cbMarca.addItem("Head");
		cbMarca.addItem("Wilson");
		cbMarca.addItem("Bullpadel");
		cbMarca.addItem("Dunlop");
		cbMarca.addItem("Babolat");
		cbMarca.addItem("Varlion");
		
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
		cbTipo.addItem("Palas");
		cbTipo.addItem("Camisetas");
		cbTipo.addItem("Pantalones");
		cbTipo.addItem("Pelotas");
		cbTipo.addItem("Paleteros");
		cbTipo.addItem("Protectores");
		
	}
	
	@SuppressWarnings("unchecked")
	private void rellenaComboMarca(String selectedItem) {
		// TODO Auto-generated method stub
		cbMarca.removeAllItems();
		if(selectedItem.equals("")) {
			rellenaComboMarca();
		}else if(selectedItem.equals("Palas")) {
			cbMarca.addItem("");
			cbMarca.addItem("Artengo");
			cbMarca.addItem("Head");
			cbMarca.addItem("Wilson");
			cbMarca.addItem("Bullpadel");
			cbMarca.addItem("Dunlop");
			cbMarca.addItem("Babolat");
			cbMarca.addItem("Varlion");
		}else if(selectedItem.equals("Camisetas")) {
			cbMarca.addItem("");
			cbMarca.addItem("Artengo");
			cbMarca.addItem("Head");
			cbMarca.addItem("Wilson");
			cbMarca.addItem("Bullpadel");
			cbMarca.addItem("Dunlop");
			cbMarca.addItem("Babolat");
			cbMarca.addItem("Varlion");
		}else if(selectedItem.equals("Pantalones")) {
			cbMarca.addItem("");
			cbMarca.addItem("Artengo");
			cbMarca.addItem("Head");
			cbMarca.addItem("Wilson");
			cbMarca.addItem("Bullpadel");
			cbMarca.addItem("Dunlop");
			cbMarca.addItem("Babolat");
			cbMarca.addItem("Varlion");
		}else if(selectedItem.equals("Pelotas")) {
			cbMarca.addItem("");
			cbMarca.addItem("Artengo");
			cbMarca.addItem("Head");
			cbMarca.addItem("Wilson");
			cbMarca.addItem("Bullpadel");
			cbMarca.addItem("Dunlop");
			cbMarca.addItem("Babolat");
			cbMarca.addItem("Varlion");
		}else if(selectedItem.equals("Paleteros")){
			cbMarca.addItem("");
			cbMarca.addItem("Artengo");
			cbMarca.addItem("Head");
			cbMarca.addItem("Wilson");
			cbMarca.addItem("Bullpadel");
			cbMarca.addItem("Dunlop");
			cbMarca.addItem("Babolat");
			cbMarca.addItem("Varlion");
		}else if(selectedItem.equals("Protectores")) {
			cbMarca.addItem("");
			cbMarca.addItem("Artengo");
			cbMarca.addItem("Head");
			cbMarca.addItem("Wilson");
			cbMarca.addItem("Bullpadel");
			cbMarca.addItem("Dunlop");
			cbMarca.addItem("Babolat");
			cbMarca.addItem("Varlion");
		}
	}

	@SuppressWarnings("unchecked")
	private void rellenaComboColor(String selectedItem) {
		// TODO Auto-generated method stub
		cbColor.removeAllItems();
		if(selectedItem.equals("")) {
			rellenaComboColor();
		}else if(selectedItem.equals("Palas")) {
			cbColor.addItem("");
			cbColor.addItem("Amarillo");
			cbColor.addItem("Azul");
			cbColor.addItem("Rojo");
			cbColor.addItem("Verde");
			cbColor.addItem("Blanco");
			cbColor.addItem("Negro");
		}else if(selectedItem.equals("Camisetas")) {
			cbColor.addItem("");
			cbColor.addItem("Amarillo");
			cbColor.addItem("Azul");
			cbColor.addItem("Rojo");
			cbColor.addItem("Verde");
			cbColor.addItem("Blanco");
			cbColor.addItem("Negro");
		}else if(selectedItem.equals("Pantalones")) {
			cbColor.addItem("");
			cbColor.addItem("Amarillo");
			cbColor.addItem("Azul");
			cbColor.addItem("Rojo");
			cbColor.addItem("Verde");
			cbColor.addItem("Blanco");
			cbColor.addItem("Negro");
		}else if(selectedItem.equals("Pelotas")) {
			cbColor.addItem("");
			cbColor.addItem("Amarillo");
			cbColor.addItem("Azul");
			cbColor.addItem("Rojo");
			cbColor.addItem("Verde");
			cbColor.addItem("Blanco");
			cbColor.addItem("Negro");
		}else if(selectedItem.equals("Paleteros")) {
			cbColor.addItem("");
			cbColor.addItem("Amarillo");
			cbColor.addItem("Azul");
			cbColor.addItem("Rojo");
			cbColor.addItem("Verde");
			cbColor.addItem("Blanco");
			cbColor.addItem("Negro");
		}else if(selectedItem.equals("Protectores")) {
			cbColor.addItem("");
			cbColor.addItem("Amarillo");
			cbColor.addItem("Azul");
			cbColor.addItem("Rojo");
			cbColor.addItem("Verde");
			cbColor.addItem("Blanco");
			cbColor.addItem("Negro");
		}
	}

	@SuppressWarnings("unchecked")
	private void rellenaComboTalla(String selectedItem) {
		// TODO Auto-generated method stub
		cbTalla.removeAllItems();
		if(selectedItem.equals("")) {
			rellenaComboTalla();
		} else if(selectedItem.equals("Palas")) {
			cbTalla.removeAllItems();
		} else if(selectedItem.equals("Camisetas")) {
			cbTalla.addItem("");
			cbTalla.addItem("X");
			cbTalla.addItem("M");
			cbTalla.addItem("L");
			cbTalla.addItem("XL");
			cbTalla.addItem("XXL");
		} else if(selectedItem.equals("Pantalones")) {
			cbTalla.addItem("");
			cbTalla.addItem("X");
			cbTalla.addItem("M");
			cbTalla.addItem("L");
			cbTalla.addItem("XL");
			cbTalla.addItem("XXL");
		} else if(selectedItem.equals("Pelotas")) {
			cbTalla.removeAllItems();
		} else if(selectedItem.equals("Paleteros")) {
			cbTalla.removeAllItems();
		} else if(selectedItem.equals("Protectores")) {
			cbTalla.removeAllItems();
		}
	}

	private void rellenaSliderPeso(String selectedItem) {
		// TODO Auto-generated method stub
		sPeso.removeAll();
		if(selectedItem.equals("")) {
			sPeso.setEnabled(true);
		}else if(selectedItem.equals("Palas")) {
			sPeso.setEnabled(true);
		}else if(selectedItem.equals("Camisetas")) {
			sPeso.setEnabled(false);
		} else if(selectedItem.equals("Pantalones")) {
			sPeso.setEnabled(false);
		} else if(selectedItem.equals("Pelotas")) {
			sPeso.setEnabled(false);
		} else if(selectedItem.equals("Paleteros")) {
			sPeso.setEnabled(false);
		} else if(selectedItem.equals("Protectores")) {
			sPeso.setEnabled(false);
		}
	}

	private void cargarImagenes() {
		ArrayList<String> aRutas = VentanaLogin.bd.obtenerProductosCategoria("Pádel");
		for(int i=0;i<aRutas.size();i++) {
			String ruta = aRutas.get(i);
			ImageIcon im = new ImageIcon(ruta);
			int width =500;
			int height = 500;
			im.setImage(im.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
			JLabel lblFoto = new JLabel(im);
			panelCentro.add(lblFoto);
			lblFoto.setIcon(im);
		}
	}
}
