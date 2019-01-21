package Ventanas;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;

public class VentanaDescripcionPatrocinadores extends JFrame {

	private JPanel contentPane;
	private JFrame ventanaAnterior;


	
	/**
	 * Create the frame.
	 */
	public VentanaDescripcionPatrocinadores(String url, JFrame va, String nick) {
		ventanaAnterior = va;
		JFrame v = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 400);
		contentPane = new PanelFondo();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setLocationRelativeTo(null); //Para centrar la ventana
		setContentPane(contentPane);
		
		JPanel panelNorte = new JPanel();
		contentPane.add(panelNorte, BorderLayout.NORTH);
		
		JLabel lblDescripcinDelPatrocinador = new JLabel("DESCRIPCIÓN DEL PATROCINADOR");
		panelNorte.add(lblDescripcinDelPatrocinador);
		
		JPanel panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				v.dispose();
				VentanaPatrocinadores vp = new VentanaPatrocinadores(v, nick);
				vp.setVisible(true);
			}
		});
		panelSur.add(btnVolver);
		
		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(null);
		
		ImageIcon im = new ImageIcon(url);
		int width =212;
		int height =256;
		im.setImage(im.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
		JLabel lblFoto = new JLabel(im);
		lblFoto.setBounds(6,24,212,256);
		panelCentro.add(lblFoto);
		lblFoto.setIcon(im);
		
		String desc = VentanaLogin.bd.obtenerDescripcionPatrocinador(url);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(235, 68, 399, 156);
		panelCentro.add(scrollPane);
		JTextArea txtDescripcion = new JTextArea(desc);
		txtDescripcion.setWrapStyleWord(true);
		scrollPane.setViewportView(txtDescripcion);
		txtDescripcion.setEditable(false);
		txtDescripcion.setOpaque(false);
		txtDescripcion.setLineWrap(true);
		
		double din = VentanaLogin.bd.obtenerDinero(url);
		JTextArea txtDinero = new JTextArea("Nos pagan " + din + " euros");
		txtDinero.setBounds(266, 250, 221,20);
		txtDinero.setEditable(false);
		txtDinero.setOpaque(false);
		panelCentro.add(txtDinero);

		
		
	}
}
