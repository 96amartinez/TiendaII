package VentanasAdministrador;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.LineBorder;

import Ventanas.VentanaLogin;

import java.awt.Color;

public class VentanaAdmin extends JFrame {

	private JPanel contentPane;

	JFrame v = this;

	/**
	 * Create the frame.
	 */
	public VentanaAdmin(JFrame va) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setLocationRelativeTo(null); //Para centrar la ventana

		
		JPanel panelNorte = new JPanel();
		contentPane.add(panelNorte, BorderLayout.NORTH);
		
		JLabel lblBienvenidoALa = new JLabel("Bienvenido a la ventana del Administrador");
		panelNorte.add(lblBienvenidoALa);
		
		JPanel panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				v.dispose();
				VentanaLogin vl = new VentanaLogin();
				vl.setVisible(true);
				
			}
		});
		panelSur.add(btnVolver);
		
		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new BorderLayout(0, 0));
		
		JPanel panelNorte1 = new JPanel();
		panelCentro.add(panelNorte1, BorderLayout.NORTH);
		
		JLabel lbldeseaTrabajarCon = new JLabel("¿Desea trabajar con los productos o con los usuarios?");
		panelNorte1.add(lbldeseaTrabajarCon);
		
		JPanel panelCentro2 = new JPanel();
		panelCentro.add(panelCentro2, BorderLayout.CENTER);
		panelCentro2.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel1 = new JPanel();
		panel1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelCentro2.add(panel1);
		panel1.setLayout(null);
	
		
		JLabel lblUsuarios = new JLabel("Usuarios");
		lblUsuarios.setBounds(79, 153, 61, 16);
		panel1.add(lblUsuarios);

		JButton btnUsuario = new JButton();
		btnUsuario.setBounds(26, 6, 169, 135);
		btnUsuario.setOpaque(false);
		btnUsuario.setContentAreaFilled(false);
		btnUsuario.setBorderPainted(false);
		Image im = new ImageIcon("Imagenes/logoUsu.png").getImage();
		ImageIcon im1 = new ImageIcon(im.getScaledInstance(btnUsuario.getWidth(), btnUsuario.getHeight(), Image.SCALE_SMOOTH));
		panel1.add(btnUsuario);
		btnUsuario.setIcon(im1);
		btnUsuario.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				v.dispose();
				VentanaAdminUsuarios vau = new VentanaAdminUsuarios(v);
				vau.setVisible(true);
				
			}
		});

		
		
		JPanel panel2 = new JPanel();
		panel2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelCentro2.add(panel2);
		panel2.setLayout(null);
		
		JButton btnProducto = new JButton();
		btnProducto.setBounds(23, 6, 169, 135);
		Image imp = new ImageIcon("Imagenes/logoProd.png").getImage();
		ImageIcon imp1 = new ImageIcon(imp.getScaledInstance(btnProducto.getWidth(), btnProducto.getHeight(), Image.SCALE_SMOOTH));
		panel2.add(btnProducto);
		btnProducto.setIcon(imp1);
		btnProducto.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				v.dispose();
				VentanaAdminProductos vap = new VentanaAdminProductos(v);
				vap.setVisible(true);
				
			}
		});
		
		JLabel label = new JLabel("Productos");
		label.setBounds(78, 153, 76, 16);
		panel2.add(label);
		
		
		
	}
}
