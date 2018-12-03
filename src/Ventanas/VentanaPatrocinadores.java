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
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

public class VentanaPatrocinadores extends JFrame {

	private JPanel contentPane;
	private JPanel panelCentro;
	private static JFrame ventanaAnterior;
	

	private void cargarImagenes() {
		//ArrayList<String> aRutas = GestionFicheros.leerRutasImagenes();
		ArrayList<String> aRutas = VentanaLogin.bd.obtenerRutaPatrocinadores();
		for(int i=0;i<aRutas.size();i++) {
			String ruta = aRutas.get(i);
			ImageIcon im = new ImageIcon(ruta);
			int width =300;
			int height = 300;
			im.setImage(im.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
			JLabel lblFoto = new JLabel(im);
			panelCentro.add(lblFoto);
			lblFoto.setIcon(im);
		}
	}

	/**
	 * Create the frame.
	 */
	//Como maximizarla y que no se pueda modificar, ajustar el tamaño de las imagenes
	public VentanaPatrocinadores(JFrame va) {
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
		
		panelCentro.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent evento) {
				// TODO Auto-generated method stub
				Point p = evento.getPoint();
				JLabel lblProducto = (JLabel)panelCentro.getComponentAt(p);
				ImageIcon im = (ImageIcon)lblProducto.getIcon();
				String url = im.getDescription();
				v.dispose();
				VentanaDescripcionPatrocinadores vdp = new VentanaDescripcionPatrocinadores(url,v);
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
	}

	public static ArrayList<String> leerRutasPatrocinadores(){
		ArrayList<String> aRutas = new ArrayList<String>();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader("patrocinadores.txt"));
			String linea = br.readLine();
			while(linea!=null) {
				aRutas.add(linea);
				linea = br.readLine();
			}
			br.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return aRutas;
	}


}
