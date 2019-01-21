package Ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BaseDeDatos.BD;
import Datos.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.GridBagLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaRegistrar extends JFrame {

	private JPanel contentPane;
	private JTextField txtDNI;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtNick;
	private JTextField txtContrasenia;
	private JTextField txtCuenta;
	private JTextField txtNumtel;
	private JTextField txtDomicilio;


	public void limpiarCampos() {
		txtNick.setText("");
		txtDNI.setText("");
		txtNombre.setText("");
		txtApellido.setText("");
		txtContrasenia.setText("");
		txtCuenta.setText("");
		txtNumtel.setText("");
		txtDomicilio.setText("");


	}
	/**
	 * Create the frame.
	 */
	public VentanaRegistrar(BD bd) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null); //Para centrar la ventana
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panelNorte = new JPanel();
		contentPane.add(panelNorte, BorderLayout.NORTH);

		JPanel panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);

		JPanel panelOeste = new JPanel();
		contentPane.add(panelOeste, BorderLayout.WEST);

		JPanel panelEste = new JPanel();
		contentPane.add(panelEste, BorderLayout.EAST);

		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(null);

		JLabel lblRegistro = new JLabel("Registro");
		lblRegistro.setForeground(Color.BLUE);
		lblRegistro.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 20));
		lblRegistro.setBounds(172, 6, 102, 25);
		panelCentro.add(lblRegistro);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(56, 79, 61, 16);
		panelCentro.add(lblNombre);

		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(56, 107, 61, 16);
		panelCentro.add(lblApellido);

		JLabel lblNick = new JLabel("Nick");
		lblNick.setBounds(56, 135, 61, 16);
		panelCentro.add(lblNick);

		JLabel lblDni = new JLabel("DNI");
		lblDni.setBounds(56, 51, 61, 16);
		panelCentro.add(lblDni);

		JLabel lblContrasea = new JLabel("Contraseña");
		lblContrasea.setBounds(56, 163, 77, 16);
		panelCentro.add(lblContrasea);

		JLabel lblCuenta = new JLabel("Cuenta");
		lblCuenta.setBounds(56, 247, 61, 16);
		panelCentro.add(lblCuenta);

		txtDNI = new JTextField();
		txtDNI.setBounds(182, 43, 130, 26);
		txtDNI.setToolTipText("El campo DNI tiene que tener 9 carácteres");
		panelCentro.add(txtDNI);
		txtDNI.setColumns(10);

		txtNombre = new JTextField();
		txtNombre.setBounds(182, 74, 130, 26);
		txtNombre.setToolTipText("El campo nombre tiene un máximo de 20 carácteres y un mínimo de 3");
		panelCentro.add(txtNombre);
		txtNombre.setColumns(10);

		txtApellido = new JTextField();
		txtApellido.setBounds(182, 102, 130, 26);
		txtApellido.setToolTipText("El campo apellido tiene un máximo de 20 carácteres y un mínimo de 3");
		panelCentro.add(txtApellido);
		txtApellido.setColumns(10);

		txtNick = new JTextField();
		txtNick.setBounds(182, 130, 130, 26);
		txtNick.setToolTipText("El campo nick tiene un máximo de 20 carácteres y un mínimo de 3");
		panelCentro.add(txtNick);
		txtNick.setColumns(10);

		txtContrasenia = new JTextField();
		txtContrasenia.setBounds(182, 158, 130, 26);
		txtContrasenia.setToolTipText("El campo contraseña tiene un máximo de 20 carácteres y un mínimo de 3");
		panelCentro.add(txtContrasenia);
		txtContrasenia.setColumns(10);

		txtCuenta = new JTextField();
		txtCuenta.setBounds(182, 242, 130, 26);
		txtCuenta.setToolTipText("El campo cuenta bancaria tiene que tener 15 carácteres");
		panelCentro.add(txtCuenta);
		txtCuenta.setColumns(10);

		JFrame v = this;
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				v.dispose();
				VentanaLogin vl = new VentanaLogin();
				vl.setVisible(true);
				limpiarCampos();
			}
		});
		btnVolver.setBounds(80, 269, 117, 29);
		panelCentro.add(btnVolver);

		JButton btnAceptar = new JButton("Registrar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String DNI = txtDNI.getText();
				String nombre = txtNombre.getText();
				String ape= txtApellido.getText();
				String nick = txtNick.getText();
				String cont = txtContrasenia.getText();
				String num = txtNumtel.getText();
				String dom = txtDomicilio.getText();
				String cuenta = txtCuenta.getText();
				String Imagen = null;
				Usuario user = new Usuario(DNI,nombre,ape, nick, cont, num, dom, cuenta, Imagen);
				int resul = BD.existeUsuario(user);
				
				if(DNI.equals("")) {
					JOptionPane.showMessageDialog(null, "El campo DNI no puede estar vacio");
				}else if(nombre.equals("")) {
					JOptionPane.showMessageDialog(null, "El campo nombre no puede estar vacio");
				}else if(ape.equals("")) {
					JOptionPane.showMessageDialog(null, "El campo apellido no puede estar vacío");
				}else if(nick.equals("")) {
					JOptionPane.showMessageDialog(null, "El campo nick no puede estar vacio");
				}else if(cont.equals("")) {
					JOptionPane.showMessageDialog(null, "El campo contraseña no puede estar vacio");
				}else if(num.equals("")) {
					JOptionPane.showMessageDialog(null, "El campo número de teléfono no puede estar vacio");
				}else if(dom.equals("")) {
					JOptionPane.showMessageDialog(null, "El campo domicilio no puede estar vacio");
				}else if(cuenta.equals("")) {
					JOptionPane.showMessageDialog(null, "El campo cuenta bancaria no puede estar vacio");
				}
				else {
					if(resul==0) {
						String resp = JOptionPane.showInputDialog("No estas registrado. ¿Quierees registrarte (S/N)?");
						if(resp.equalsIgnoreCase("S")) {
							bd.registrarUsuario(user);
							JOptionPane.showMessageDialog(null, "Usuario registrado con éxito", "OK", JOptionPane.INFORMATION_MESSAGE);
							//Hacer un método para vaciar campo después de registrarse
							limpiarCampos();
						}else {
							JOptionPane.showMessageDialog(null, "Hasta la próxima");
						}
					}else if(resul==1) {
						JOptionPane.showMessageDialog(null, "La contraseña no es correcta");
					}else {
						JOptionPane.showMessageDialog(null, "Ese Nick ya existe");
						//Ya existe el Usuario
						limpiarCampos();
					}
				}
			}
		});
		btnAceptar.setBounds(322, 269, 117, 29);
		panelCentro.add(btnAceptar);

		Image imgReg = new ImageIcon("Imagenes/IconoRegistro.png").getImage();
		ImageIcon imgReg2 = new ImageIcon(imgReg.getScaledInstance(149, 212, Image.SCALE_SMOOTH));
		JLabel lblIconoRegistro = new JLabel("New label");
		lblIconoRegistro.setBounds(315, 51, 149, 212);
		lblIconoRegistro.setIcon(imgReg2);
		panelCentro.add(lblIconoRegistro);
		
		JLabel lblNmeroTelefono = new JLabel("Número telefono");
		lblNmeroTelefono.setBounds(56, 191, 117, 16);
		panelCentro.add(lblNmeroTelefono);
		
		JLabel lblDomicilio = new JLabel("Domicilio");
		lblDomicilio.setBounds(56, 219, 61, 16);
		panelCentro.add(lblDomicilio);
		
		txtNumtel = new JTextField();
		txtNumtel.setBounds(182, 186, 130, 26);
		txtNumtel.setToolTipText("El campo número de teléfono tiene que tener 9 carácteres");
		panelCentro.add(txtNumtel);
		txtNumtel.setColumns(10);
		
		txtDomicilio = new JTextField();
		txtDomicilio.setBounds(182, 214, 130, 26);
		panelCentro.add(txtDomicilio);
		txtDomicilio.setColumns(10);
	
		JButton btnCamara = new JButton();
		Image imCam = new ImageIcon("Imagenes/Camara.png").getImage();
		ImageIcon imCam1 = new ImageIcon(imCam.getScaledInstance(70, 55, Image.SCALE_SMOOTH));
		btnCamara.setIcon(imCam1);
		btnCamara.setBounds(350, 1, 70, 55);
		btnCamara.setOpaque(false);
		btnCamara.setContentAreaFilled(false);
		btnCamara.setBorderPainted(false);
		panelCentro.add(btnCamara);

	}
	private char letraDNI(String dni) {
		String letras= "TRWAGMYFPDXBNJZSQVHLCKE";
		long d = Long.parseLong(dni);
		int resto = (int)d % 23;
		return letras.charAt(resto);
	}
	public boolean dniCorrecto(String dni) {
		char letra = letraDNI(dni.substring(0, dni.length()-1));
		boolean correcto=false;
		int i=0;
		while(Character.isDigit(dni.charAt(i)) && i<dni.length())
			i++;
		if(i==8 && dni.length()==9 && dni.charAt(8)==letra)
			correcto=true;
		return correcto;
	}

	
}
