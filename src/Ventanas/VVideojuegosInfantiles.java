package Ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import BaseDeDatos.BD;

import java.awt.Color;
import java.awt.Desktop;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.awt.event.ActionEvent;

public class VVideojuegosInfantiles extends JFrame {

	private JPanel contentPane, panelPS4, panelPS3, panelXBOX, panelGratis;
	private JFrame v = this;
	

	/**
	 * Create the frame.
	 */
	public VVideojuegosInfantiles(String nick) {
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelNorte = new JPanel();
		contentPane.add(panelNorte, BorderLayout.NORTH);
		
		JLabel lblVideojuegosInfantiles = new JLabel("Videojuegos Infantiles");
		panelNorte.add(lblVideojuegosInfantiles);
		
		JPanel panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				v.dispose();
				VentanaMenu vm = new VentanaMenu(v,nick);
				vm.setVisible(true);
			}
		});
		panelSur.add(btnVolver);
		
		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		GridBagLayout gbl_panelCentro = new GridBagLayout();
		gbl_panelCentro.columnWidths = new int[]{0, 0, 0};
		gbl_panelCentro.rowHeights = new int[]{0, 0, 0};
		gbl_panelCentro.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panelCentro.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		panelCentro.setLayout(gbl_panelCentro);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		panelCentro.add(scrollPane, gbc_scrollPane);
		
		panelPS4 = new JPanel();
		scrollPane.setViewportView(panelPS4);
		panelPS4.setBorder(new LineBorder(new Color(255, 0, 0)));
		
		JPanel panelPS4txt = new JPanel();
		panelPS4txt.setBorder(new LineBorder(new Color(255, 0, 0)));
		scrollPane.setColumnHeaderView(panelPS4txt);
		
		JLabel lblPs = new JLabel("PS4");
		panelPS4txt.add(lblPs);
//		panelPS4.setLayout(null);
//		JLabel lblPs = new JLabel("PS4");
//		lblPs.setBounds(86, 7, 22, 16);
//		//lblPs.setHorizontalAlignment(SwingConstants.CENTER);
//		lblPs.setVerticalAlignment(SwingConstants.TOP);
//		//lblPs.setBounds(20,20,20, getDefaultCloseOperation());
//		panelPS4.add(lblPs);
		panelPS4.setLayout(new GridLayout(0,2));
		cargarImagenesPS4();
panelPS4.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				
			}
			
			@Override
			public void mouseClicked(MouseEvent evento) {
				Point p = evento.getPoint();
				JLabel lblProducto = (JLabel)panelPS4.getComponentAt(p);
				ImageIcon im = (ImageIcon)lblProducto.getIcon();
				String url = im.getDescription();
				v.dispose();
				VentanaDescProducto vdp = new VentanaDescProducto(nick, url,v, "Videojuegos", BD.obtenerNombreProducto(url), BD.obtenerPrecioProducto(url),"PS4",BD.obtenerDescProducto(url),BD.obtenerCodProducto(url));
				vdp.setVisible(true);
			}
		});

		

		
		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_1.gridx = 1;
		gbc_scrollPane_1.gridy = 0;
		panelCentro.add(scrollPane_1, gbc_scrollPane_1);
		
		panelXBOX = new JPanel();
		scrollPane_1.setViewportView(panelXBOX);
		panelXBOX.setBorder(new LineBorder(new Color(255, 0, 255)));
		
		JPanel panelXBOXtxt = new JPanel();
		panelXBOXtxt.setBorder(new LineBorder(Color.MAGENTA));
		scrollPane_1.setColumnHeaderView(panelXBOXtxt);
		
		JLabel lblXbox = new JLabel("XBOX");
		panelXBOXtxt.add(lblXbox);
		
//		JLabel lblXbox = new JLabel("Xbox");
//		panelXBOX.add(lblXbox);
		
		panelXBOX.setLayout(new GridLayout(0, 2));
		cargarImagenesXBOX();
panelXBOX.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				
			}
			
			@Override
			public void mouseClicked(MouseEvent evento) {
				Point p = evento.getPoint();
				JLabel lblProducto = (JLabel)panelXBOX.getComponentAt(p);
				ImageIcon im = (ImageIcon)lblProducto.getIcon();
				String url = im.getDescription();
				v.dispose();
				VentanaDescProducto vdp = new VentanaDescProducto(nick,url,v, "Videojuegos", BD.obtenerNombreProducto(url), BD.obtenerPrecioProducto(url),"XBox",BD.obtenerDescProducto(url),BD.obtenerCodProducto(url));
				vdp.setVisible(true);
			}
		});
		
		
		JScrollPane scrollPane_2 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
		gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_2.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane_2.gridx = 0;
		gbc_scrollPane_2.gridy = 1;
		panelCentro.add(scrollPane_2, gbc_scrollPane_2);
		
		panelPS3 = new JPanel();
		scrollPane_2.setViewportView(panelPS3);
		panelPS3.setBorder(new LineBorder(new Color(0, 0, 255)));
		
		JPanel panelPS3txt = new JPanel();
		panelPS3txt.setBorder(new LineBorder(Color.BLUE));
		scrollPane_2.setColumnHeaderView(panelPS3txt);
		
		JLabel lblPS3 = new JLabel("PS3");
		panelPS3txt.add(lblPS3);
		
//		JLabel lblPs_1 = new JLabel("PS3");
//		panelPS3.add(lblPs_1);
		
		panelPS3.setLayout(new GridLayout(0, 2));
		cargarImagenesPS3();
panelPS3.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				
			}
			
			@Override
			public void mouseClicked(MouseEvent evento) {
				Point p = evento.getPoint();
				JLabel lblProducto = (JLabel)panelPS3.getComponentAt(p);
				ImageIcon im = (ImageIcon)lblProducto.getIcon();
				String url = im.getDescription();
				v.dispose();
				VentanaDescProducto vdp = new VentanaDescProducto(nick, url,v, "Videojuegos", BD.obtenerNombreProducto(url), BD.obtenerPrecioProducto(url),"PS3",BD.obtenerDescProducto(url),BD.obtenerCodProducto(url));
				vdp.setVisible(true);
			}
		});
		
		JScrollPane scrollPane_3 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_3 = new GridBagConstraints();
		gbc_scrollPane_3.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_3.gridx = 1;
		gbc_scrollPane_3.gridy = 1;
		panelCentro.add(scrollPane_3, gbc_scrollPane_3);
		
		panelGratis = new JPanel();
		scrollPane_3.setViewportView(panelGratis);
		panelGratis.setBorder(new LineBorder(new Color(0, 255, 0)));
		
		JPanel panelGratistxt = new JPanel();
		panelGratistxt.setBorder(new LineBorder(Color.GREEN));
		scrollPane_3.setColumnHeaderView(panelGratistxt);
		
		JLabel lblGratuitos = new JLabel("Gratuitos");
		panelGratistxt.add(lblGratuitos);
		
		panelGratis.setLayout(new GridLayout(0, 2));
		cargarImagenesGratis();
		
panelGratis.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				
			}
			
			@Override
			public void mouseClicked(MouseEvent evento) {
				Point p = evento.getPoint();
				JLabel lblProducto = (JLabel)panelGratis.getComponentAt(p);
				ImageIcon im = (ImageIcon)lblProducto.getIcon();
				String imagen = im.getDescription();
				//v.dispose();
				URL url=null;
				try {
				    url = new URL(BD.obtenerURLJuego(imagen));
				    try {
				        Desktop.getDesktop().browse(url.toURI());
				    } catch (IOException e) {
				        e.printStackTrace();
				    } catch (URISyntaxException e) {
				        e.printStackTrace();
				    }
				} catch (MalformedURLException e1) {
				    e1.printStackTrace();
				}
			}
		});
		
		


	}
	
	private void cargarImagenesPS4() {
		ArrayList<String> aRutas = VentanaLogin.bd.obtenerVideojuegosCategoriaPlataformaYTipo("Videojuegos", "PS4", "Infantil");
		for(int i=0;i<aRutas.size();i++) {
			String ruta = aRutas.get(i);
			ImageIcon im = new ImageIcon(ruta);
			int width =250;
			int height = 250;
			im.setImage(im.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
			JLabel lblFoto = new JLabel(im);
			panelPS4.add(lblFoto);
			lblFoto.setIcon(im);
		}
	}
	
	private void cargarImagenesPS3() {
		ArrayList<String> aRutas = VentanaLogin.bd.obtenerVideojuegosCategoriaPlataformaYTipo("Videojuegos", "PS3", "Infantil");
		for(int i=0;i<aRutas.size();i++) {
			String ruta = aRutas.get(i);
			ImageIcon im = new ImageIcon(ruta);
			int width =250;
			int height = 250;
			im.setImage(im.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
			JLabel lblFoto = new JLabel(im);
			panelPS3.add(lblFoto);
			lblFoto.setIcon(im);
		}
	}
	
	private void cargarImagenesXBOX() {
		ArrayList<String> aRutas = VentanaLogin.bd.obtenerVideojuegosCategoriaPlataformaYTipo("Videojuegos", "XBox", "Infantil");
		for(int i=0;i<aRutas.size();i++) {
			String ruta = aRutas.get(i);
			ImageIcon im = new ImageIcon(ruta);
			int width =250;
			int height = 250;
			im.setImage(im.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
			JLabel lblFoto = new JLabel(im);
			panelXBOX.add(lblFoto);
			lblFoto.setIcon(im);
		}
	}
	
	private void cargarImagenesGratis() {
		ArrayList<String> aRutas = VentanaLogin.bd.obtenerVideojuegosCategoriaPlataformaYTipo("Videojuegos", "Minijuegos", "Infantil");
		for(int i=0;i<aRutas.size();i++) {
			String ruta = aRutas.get(i);
			ImageIcon im = new ImageIcon(ruta);
			int width =250;
			int height = 250;
			im.setImage(im.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
			JLabel lblFoto = new JLabel(im);
			panelGratis.add(lblFoto);
			lblFoto.setIcon(im);
		}
	}
}
