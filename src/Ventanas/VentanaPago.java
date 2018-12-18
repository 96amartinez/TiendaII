package Ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import BaseDeDatos.BD;

import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class VentanaPago extends JFrame {

	private JPanel contentPane;
	private JTextField txtPaypal;
	private JTextField txtTarjeta;
	private JRadioButton rdbtnPaypal, rdbtnTarjetaDeCredito;
	
	private void vaciarCampos() {
		txtPaypal.setText("");
		txtTarjeta.setText("");
	}

	/**
	 * Create the frame.
	 */
	public VentanaPago() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		
		JPanel panelNorte = new JPanel();
		contentPane.add(panelNorte, BorderLayout.NORTH);
		
		JLabel lblDir = new JLabel("\t Enviar el pedido a " );
		panelNorte.add(lblDir);
		
		JPanel panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		JButton btnPagar = new JButton("Pagar");
		panelSur.add(btnPagar);
		
		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(null);
		
		rdbtnPaypal = new JRadioButton("Paypal");
		rdbtnPaypal.setBounds(52,33,162,23);
		panelCentro.add(rdbtnPaypal);
		
		rdbtnTarjetaDeCredito = new JRadioButton("Tarjeta de Crédito");
		rdbtnTarjetaDeCredito.setBounds(245, 30, 162, 30);
		panelCentro.add(rdbtnTarjetaDeCredito);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnPaypal);
		bg.add(rdbtnTarjetaDeCredito);
		
		txtPaypal = new JTextField();
		txtPaypal.setBounds(37, 83, 130, 26);
		panelCentro.add(txtPaypal);
		txtPaypal.setColumns(10);
		
		txtTarjeta = new JTextField();
		txtTarjeta.setBounds(255, 83, 130, 26);
		panelCentro.add(txtTarjeta);
		txtTarjeta.setColumns(10);
		
		txtTarjeta.setVisible(false);
		txtPaypal.setVisible(false);
		
		JLabel lblImagen = new JLabel();
		lblImagen.setBounds(140, 110, 135, 87);
		//ImageIcon im = null;
		rdbtnTarjetaDeCredito.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ImageIcon im = new ImageIcon("Imagenes/tarjeta.png");
			 	im.setImage(im.getImage().getScaledInstance(lblImagen.getWidth(), lblImagen.getHeight(), Image.SCALE_SMOOTH));
			 	lblImagen.setIcon(im);
			 	txtPaypal.setVisible(false);
			 	txtTarjeta.setVisible(true);
			 	vaciarCampos();
			}
		});
		
		rdbtnPaypal.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ImageIcon im = new ImageIcon("Imagenes/paypal.png");
				im.setImage(im.getImage().getScaledInstance(lblImagen.getWidth(), lblImagen.getHeight(), Image.SCALE_SMOOTH));
			 	lblImagen.setIcon(im);
			 	txtPaypal.setVisible(true);
			 	txtTarjeta.setVisible(false);
			 	vaciarCampos();

			}
		});
		panelCentro.add(lblImagen);
		
		
	}
}
