package Ventanas;

import java.awt.BorderLayout;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URI;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import BaseDeDatos.BD;

import java.awt.Color;
import java.awt.Desktop;

public class VentanaBaloncesto extends JFrame {

	private JPanel contentPane;
	private JFrame v = this;
	private JPanel panelCamisetas, panelPlayeras, panelPantalones, panelSudaderas, panelBalones;

	private String cat, nom;
	private double precio;
	

	/**
	 * Create the frame.
	 */
	public VentanaBaloncesto() {
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panelNorte = new JPanel();
		contentPane.add(panelNorte, BorderLayout.NORTH);

		JLabel lbTexto = new JLabel("Productos de baloncesto");
		panelNorte.add(lbTexto);

		JPanel panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);

		JButton btnVolver = new JButton("Volver");
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

		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		GridBagLayout gbl_panelCentro = new GridBagLayout();
		gbl_panelCentro.columnWidths = new int[]{0, 0, 0, 0, 0 };
		gbl_panelCentro.rowHeights = new int[]{0, 0, 0};
		gbl_panelCentro.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0};
		gbl_panelCentro.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		panelCentro.setLayout(gbl_panelCentro);


		//PANEL CAMISETAS GRIDBAGLAYOUT
		JScrollPane s1 = new JScrollPane();
		s1.setHorizontalScrollBarPolicy(s1.HORIZONTAL_SCROLLBAR_NEVER);
		GridBagConstraints gbc_s1 = new GridBagConstraints();
		gbc_s1.fill = GridBagConstraints.BOTH;
		gbc_s1.insets = new Insets(0, 0, 5, 5);
		gbc_s1.gridx = 0;
		gbc_s1.gridy = 0;
		panelCentro.add(s1, gbc_s1);

		panelCamisetas = new JPanel();
		panelCamisetas.setBorder(new LineBorder(Color.GREEN));
		s1.setViewportView(panelCamisetas);
		panelCamisetas.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.GREEN));
		s1.setColumnHeaderView(panel);
		
		JLabel lblCamisetas = new JLabel("Camisetas");
		panel.add(lblCamisetas);
		panelCamisetas.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void mouseClicked(MouseEvent evento) {
				// TODO Auto-generated method stub
				Point p = evento.getPoint();
				JLabel lblProducto = (JLabel)panelCamisetas.getComponentAt(p);
				ImageIcon im = (ImageIcon)lblProducto.getIcon();
				String url = im.getDescription();
				VentanaDescProducto vdp = new VentanaDescProducto(url,v, "Baloncesto", BD.obtenerNombreProducto(url), BD.obtenerPrecioProducto(url),"Camisetas", BD.obtenerDescProducto(url));
				vdp.setVisible(true);
				v.dispose();
			}
		});



		//PANEL PLAYERAS GRIDBAGLAYOUT
		JScrollPane s2 = new JScrollPane();
		s2.setHorizontalScrollBarPolicy(s2.HORIZONTAL_SCROLLBAR_NEVER);
		GridBagConstraints gbc_s2 = new GridBagConstraints();
		gbc_s2.fill = GridBagConstraints.BOTH;
		gbc_s2.insets = new Insets(0, 0, 5, 5);
		gbc_s2.gridx = 1;
		gbc_s2.gridy = 0;
		panelCentro.add(s2, gbc_s2);

		panelPlayeras = new JPanel();
		panelPlayeras.setBorder(new LineBorder(Color.BLUE));
		s2.setViewportView(panelPlayeras);
		panelPlayeras.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.BLUE));
		s2.setColumnHeaderView(panel_1);
		
		JLabel lblNewLabel = new JLabel("Playeras");
		panel_1.add(lblNewLabel);
		panelPlayeras.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseClicked(MouseEvent evento) {
				// TODO Auto-generated method stub
				Point p = evento.getPoint();
				JLabel lblProducto = (JLabel)panelPlayeras.getComponentAt(p);
				ImageIcon im = (ImageIcon)lblProducto.getIcon();
				String url = im.getDescription();
				VentanaDescProducto vdp = new VentanaDescProducto(url,v, "Baloncesto", BD.obtenerNombreProducto(url), BD.obtenerPrecioProducto(url),"Playeras",BD.obtenerDescProducto(url));
				vdp.setVisible(true);
				v.dispose();
			}
		});



		//PANEL PANTALONES GRIDBAGLAYOUT
		JScrollPane s3 = new JScrollPane();
		s3.setHorizontalScrollBarPolicy(s3.HORIZONTAL_SCROLLBAR_NEVER);
		GridBagConstraints gbc_s3 = new GridBagConstraints();
		gbc_s3.fill = GridBagConstraints.BOTH;
		gbc_s3.insets = new Insets(0, 0, 5, 5);
		gbc_s3.gridx = 2;
		gbc_s3.gridy = 0;
		panelCentro.add(s3, gbc_s3);

		panelPantalones = new JPanel();
		panelPantalones.setBorder(new LineBorder(Color.RED));
		s3.setViewportView(panelPantalones);
		panelPantalones.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(Color.RED));
		s3.setColumnHeaderView(panel_2);
		
		JLabel lblPantalones = new JLabel("Pantalones");
		panel_2.add(lblPantalones);
		panelPantalones.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseClicked(MouseEvent evento) {
				// TODO Auto-generated method stub
				Point p = evento.getPoint();
				JLabel lblProducto = (JLabel)panelPantalones.getComponentAt(p);
				ImageIcon im = (ImageIcon)lblProducto.getIcon();
				String url = im.getDescription();
				VentanaDescProducto vdp = new VentanaDescProducto(url,v, "Baloncesto", BD.obtenerNombreProducto(url), BD.obtenerPrecioProducto(url),"Pantalones",BD.obtenerDescProducto(url));
				vdp.setVisible(true);
				v.dispose();
			}
		});



		//PANEL SUDADERAS GRIDBAGLAYOUT
		JScrollPane s4 = new JScrollPane();
		s4.setHorizontalScrollBarPolicy(s4.HORIZONTAL_SCROLLBAR_NEVER);
		GridBagConstraints gbc_s4 = new GridBagConstraints();
		gbc_s4.fill = GridBagConstraints.BOTH;
		gbc_s4.insets = new Insets(0, 0, 5, 5);
		gbc_s4.gridx = 3;
		gbc_s4.gridy = 0;
		panelCentro.add(s4, gbc_s4);

		panelSudaderas = new JPanel();
		panelSudaderas.setBorder(new LineBorder(Color.MAGENTA));
		s4.setViewportView(panelSudaderas);
		panelSudaderas.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(Color.MAGENTA));
		s4.setColumnHeaderView(panel_3);
		
		JLabel lblSudaderas = new JLabel("Sudaderas");
		panel_3.add(lblSudaderas);
		panelSudaderas.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void mouseClicked(MouseEvent evento) {
				// TODO Auto-generated method stub
				Point p = evento.getPoint();
				JLabel lblProducto = (JLabel)panelSudaderas.getComponentAt(p);
				ImageIcon im = (ImageIcon)lblProducto.getIcon();
				String url = im.getDescription();
				VentanaDescProducto vdp = new VentanaDescProducto(url,v, "Baloncesto", BD.obtenerNombreProducto(url), BD.obtenerPrecioProducto(url),"Sudaderas",BD.obtenerDescProducto(url));
				vdp.setVisible(true);
				v.dispose();
			}
		});


		//PANEL BALONES GRIDBAGLAYOUT
		JScrollPane s5 = new JScrollPane();
		s5.setHorizontalScrollBarPolicy(s5.HORIZONTAL_SCROLLBAR_NEVER);
		GridBagConstraints gbc_s5 = new GridBagConstraints();
		gbc_s5.insets = new Insets(0, 0, 5, 0);
		gbc_s5.fill = GridBagConstraints.BOTH;
		gbc_s5.gridx = 4;
		gbc_s5.gridy = 0;
		panelCentro.add(s5, gbc_s5);

		panelBalones = new JPanel();
		panelBalones.setBorder(new LineBorder(Color.YELLOW));
		s5.setViewportView(panelBalones);
		panelBalones.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(Color.YELLOW));
		s5.setColumnHeaderView(panel_4);
		
		JLabel lblBalones = new JLabel("Balones");
		panel_4.add(lblBalones);
		panelBalones.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseClicked(MouseEvent evento) {
				// TODO Auto-generated method stub
				Point p = evento.getPoint();
				JLabel lblProducto = (JLabel)panelBalones.getComponentAt(p);
				ImageIcon im = (ImageIcon)lblProducto.getIcon();
				String url = im.getDescription();
				VentanaDescProducto vdp = new VentanaDescProducto(url,v, "Baloncesto", BD.obtenerNombreProducto(url), BD.obtenerPrecioProducto(url),"Balones",BD.obtenerDescProducto(url));
				vdp.setVisible(true);
				v.dispose();
			}
		});



		cargarImagenesCamisetas();
		cargarImagenesPlayeras();
		cargarImagenesPantalones();
		cargarImagenesSudaderas();
		cargarImagenesBalones();	


	}



	private void cargarImagenesCamisetas() {
		ArrayList<String> aRutas = VentanaLogin.bd.obtenerProductosCategoriaYTipo("Baloncesto","Camisetas");
		for(int i=0;i<aRutas.size();i++) {
			String ruta = aRutas.get(i);
			ImageIcon im = new ImageIcon(ruta);
			int width =229;
			int height = 250;
			im.setImage(im.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
			JLabel lblFoto = new JLabel(im);
			panelCamisetas.add(lblFoto);
			lblFoto.setIcon(im);
		}
	}

	private void cargarImagenesPlayeras() {
		ArrayList<String> aRutas = VentanaLogin.bd.obtenerProductosCategoriaYTipo("Baloncesto","Playeras");
		for(int i=0;i<aRutas.size();i++) {
			String ruta = aRutas.get(i);
			ImageIcon im = new ImageIcon(ruta);
			int width =229;
			int height = 250;
			im.setImage(im.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
			JLabel lblFoto = new JLabel(im);
			panelPlayeras.add(lblFoto);
			lblFoto.setIcon(im);
		}
	}

	private void cargarImagenesPantalones() {
		ArrayList<String> aRutas = VentanaLogin.bd.obtenerProductosCategoriaYTipo("Baloncesto","Pantalones");
		for(int i=0;i<aRutas.size();i++) {
			String ruta = aRutas.get(i);
			ImageIcon im = new ImageIcon(ruta);
			int width =229;
			int height = 250;
			im.setImage(im.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
			JLabel lblFoto = new JLabel(im);
			panelPantalones.add(lblFoto);
			lblFoto.setIcon(im);
		}
	}

	private void cargarImagenesSudaderas() {
		ArrayList<String> aRutas = VentanaLogin.bd.obtenerProductosCategoriaYTipo("Baloncesto","Sudaderas");
		for(int i=0;i<aRutas.size();i++) {
			String ruta = aRutas.get(i);
			ImageIcon im = new ImageIcon(ruta);
			int width =229;
			int height = 250;
			im.setImage(im.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
			JLabel lblFoto = new JLabel(im);
			panelSudaderas.add(lblFoto);
			lblFoto.setIcon(im);
		}
	}

	private void cargarImagenesBalones() {
		ArrayList<String> aRutas = VentanaLogin.bd.obtenerProductosCategoriaYTipo("Baloncesto","Balones");
		for(int i=0;i<aRutas.size();i++) {
			String ruta = aRutas.get(i);
			ImageIcon im = new ImageIcon(ruta);
			int width =229;
			int height = 250;
			im.setImage(im.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
			JLabel lblFoto = new JLabel(im);
			panelBalones.add(lblFoto);
			lblFoto.setIcon(im);
		}
	}

}
