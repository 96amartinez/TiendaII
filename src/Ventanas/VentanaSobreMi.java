package Ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.omg.CORBA.MARSHAL;
import org.omg.Messaging.SyncScopeHelper;

import BaseDeDatos.BD;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import java.awt.Desktop;

public class VentanaSobreMi extends JFrame {

	private JPanel contentPane;
	private JTextField txtMiNombreEs;
	JFrame v = this;
	private String link ;
	private boolean dentro;
	

	

	/**
	 * Create the frame.
	 */
	public VentanaSobreMi(String nick) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null); //Para centrar la ventana

		
		Image img = new ImageIcon("Imagenes/yo.png").getImage();
		ImageIcon img12 = new ImageIcon(img.getScaledInstance(102, 242, Image.SCALE_SMOOTH));
		JLabel lblfoto = new JLabel("New label");
		lblfoto.setBounds(364, 32, 102, 242);
		lblfoto.setIcon(img12);
		contentPane.add(lblfoto);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(184, 293, 117, 29);
		btnVolver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				v.dispose();
				VentanaMenu vm = new VentanaMenu(v, nick);
				vm.setVisible(true);
				
			}
			
		});
		contentPane.add(btnVolver);
		
		String txt = " Mi nombre es Aitor Martínez y tengo 22 años\n" + "\n"+
		"Siempre me han gustado mucho los deportes,\n" + 
		"especialmente el fúbol, el pádel , el ciclismo y\n" + 
		"el baloncesto y es por eso por lo que se me\n" + 
		"ocurrió poder hacer una tienda y vender esos\n" + 
		"productos que yo tanto utilizo.\n" + 
		"\n" + 
		"También me han gustado siempre mucho los\n" + 
		"videojuegos y creo que ya que hacía una tienda\n" + 
		"no podía dejar escapar la oportunidad de \n" + 
		"vender videojuegos, ya que hay mucha gente \n"
		+ "que utiliza videojuegos y eso genera \n"
		+ "dinerito que es lo importante ;) jeje ";
		JTextArea texto = new JTextArea(txt,20,10);
		texto.setEditable(false);
		texto.setOpaque(false);
		texto.setBounds(30,50,307,224);
		contentPane.add(texto);
		
		
		
		JLabel lblSobreMiAitor = new JLabel("Sobre Mi: Aitor Martínez");
		lblSobreMiAitor.setForeground(Color.BLUE);
		lblSobreMiAitor.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 20));
		lblSobreMiAitor.setBounds(134, 6, 289, 16);
		contentPane.add(lblSobreMiAitor);
		

		JButton btnTwitter = new JButton("");
		btnTwitter.setBounds(5, 3, 49, 35);
		contentPane.add(btnTwitter);
		ImageIcon i = new ImageIcon("Imagenes/twitter.png");
		int x=49;
		int y=35;
		i.setImage(i.getImage().getScaledInstance(x, y, Image.SCALE_SMOOTH));
		btnTwitter.setIcon(i);
		btnTwitter.setOpaque(false);
		btnTwitter.setContentAreaFilled(false);
		btnTwitter.setBorderPainted(false);
		btnTwitter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop.getDesktop().browse(new URI("https://twitter.com/96amartinez"));
				} catch(Exception ex){
					JOptionPane.showMessageDialog(null, "Error, no se ha podido ejecutar correctamente");
				}
			}
			
		});
	
		
		JButton btnFacebook = new JButton("");
		btnFacebook.setBounds(66, 3, 49, 35);
		contentPane.add(btnFacebook);
		ImageIcon im = new ImageIcon("Imagenes/facebook.png");
		int x1=49;
		int y2=35;
		im.setImage(im.getImage().getScaledInstance(x1, y2, Image.SCALE_SMOOTH));
		btnFacebook.setIcon(im);
		btnFacebook.setOpaque(false);
		btnFacebook.setContentAreaFilled(false);
		btnFacebook.setBorderPainted(false);
		btnFacebook.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop.getDesktop().browse(new URI("https://www.facebook.com/aitor.martinez.9699"));
				} catch(Exception ex){
					JOptionPane.showMessageDialog(null, "Error, no se ha podido ejecutar correctamente");
				}
			}
			
		});
		
		
	}
}
