package Ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import BaseDeDatos.BD;
import JPanelWebCam.JPanelWebCam;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;

public class VentanaCamaraPrueba extends JFrame{

	private JPanel contentPane;
	//private JPanel panelCamara;
	private JPanelWebCam panelCamara;
	
	private static Connection con;
	public static BD bd;
	private JFrame v = this;

	public VentanaCamaraPrueba(String nick) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null); //Para centrar la ventana
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panelNorte = new JPanel();
		contentPane.add(panelNorte, BorderLayout.NORTH);

		JLabel lblCmaraUsuario = new JLabel("Cámara Usuario");
		panelNorte.add(lblCmaraUsuario);

		JPanel panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);


		JButton btnGuardar = new JButton("Guardar");
		panelSur.add(btnGuardar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				v.dispose();
				
			}
		});
		panelSur.add(btnVolver);
		btnGuardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Image imagen = panelCamara.getImage();
				File fichero = new File("usuarios/"+nick+".jpg");
				String formato = "jpg";
				try {
					ImageIO.write((RenderedImage)imagen, formato, fichero);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				BD.btnGuardar("usuarios/"+nick+".jpg",nick);
			}
		});

		//Solo con el jar y está línea de código funciona la cámara
		panelCamara = new JPanelWebCam();
		panelCamara.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		panelCamara.setToolTipText("Click para acceder a la cámara");
		contentPane.add(panelCamara, BorderLayout.CENTER);

	}
}
