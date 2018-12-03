package VentanasAdministrador;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Ventanas.VentanaLogin;

import javax.swing.JScrollPane;
import java.awt.Color;

public class VentanaJListProductos extends JFrame {

	private JPanel contentPane;
	private JList<String> lista;

	JFrame v = this;
	

	/**
	 * Create the frame.
	 */
	public VentanaJListProductos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				v.dispose();
				VentanaAdminProductos vap= new VentanaAdminProductos(v);
				vap.setVisible(true);
				
			}
		});
		panelSur.add(btnVolver);
		
		JPanel panelNorte = new JPanel();
		contentPane.add(panelNorte, BorderLayout.CENTER);
		
		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel1 = new JPanel();
		panelCentro.add(panel1);
		panel1.setLayout(null);
		
		JPanel panel2 = new JPanel();
		panelCentro.add(panel2);
		panel2.setLayout(null);
		
		JButton btn = new JButton();
		btn.setBounds(0, 0, 225, 229);
		Image im = new ImageIcon("Imagenes/delet.png").getImage();
		ImageIcon im2 = new ImageIcon(im.getScaledInstance(btn.getWidth(), btn.getHeight(), Image.SCALE_SMOOTH));
		panel2.add(btn);
		btn.setIcon(im2);
		btn.setOpaque(false);
		btn.setContentAreaFilled(false);
		btn.setBorderPainted(false);
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(!lista.isSelectionEmpty()) {
					DefaultListModel<String> dlm1 = (DefaultListModel<String>)lista.getModel();
					int pos = lista.getSelectedIndex();
					VentanaLogin.bd.borrarProductoJList(dlm1.getElementAt(pos));
					dlm1.removeElementAt(pos);
					lista.setModel(dlm1);
					
				}
			}
		});
		
		ArrayList<String> productos = VentanaLogin.bd.obtenerProductos();
		DefaultListModel<String> dlm = new DefaultListModel<String>();
		
		for(String nom: productos) {
			dlm.addElement(nom);
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(45, 37, 135, 193);
		panel1.add(scrollPane);
		lista = new JList<String>(new DefaultListModel<String>());
		scrollPane.setViewportView(lista);
		lista.setModel(dlm);
		
		JLabel lblUsuarios = new JLabel("Productos registrados");
		lblUsuarios.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 15));
		lblUsuarios.setForeground(Color.BLUE);
		lblUsuarios.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuarios.setBounds(16,6,185,22);
		panel1.add(lblUsuarios);
		
		
		
	}
}
