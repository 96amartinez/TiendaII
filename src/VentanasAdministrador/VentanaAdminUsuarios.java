package VentanasAdministrador;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import BaseDeDatos.BD;

import java.awt.Font;
import java.awt.Image;

public class VentanaAdminUsuarios extends JFrame {

	private JPanel contentPane;
	private JTextField txtPrecio;
	private JTextField txtRuta;
	private JFrame ventanaAnterior;
	private JTextField txtDesc;
	private JTextField txtDom;
	JFrame v = this;



	/**
	 * Create the frame.
	 */
	public VentanaAdminUsuarios(JFrame va) {
		ventanaAnterior = va;
		setExtendedState(MAXIMIZED_BOTH);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(null);

		Image imbtn = new ImageIcon("Imagenes/añadir.png").getImage();
		ImageIcon imbtn2 = new ImageIcon(imbtn.getScaledInstance(166, 178, Image.SCALE_SMOOTH));
		JButton btnAniadirArticulo = new JButton();
		btnAniadirArticulo.setBounds(10,12,166,178);
		btnAniadirArticulo.setIcon(imbtn2);
		btnAniadirArticulo.setOpaque(false);
		btnAniadirArticulo.setContentAreaFilled(false);
		btnAniadirArticulo.setBorderPainted(false);
		panelCentro.add(btnAniadirArticulo);

		JLabel lblAniadir = new JLabel("Añadir Usuario");
		lblAniadir.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		lblAniadir.setBounds(10, 186, 145, 16);
		panelCentro.add(lblAniadir);


		Image imb = new ImageIcon("Imagenes/modif.png").getImage();
		ImageIcon imb2 = new ImageIcon(imb.getScaledInstance(166, 178, Image.SCALE_SMOOTH));
		JButton btnModificar = new JButton();
		btnModificar.setBounds(10, 222, 166, 178);
		btnModificar.setIcon(imb2);
		btnModificar.setOpaque(false);
		btnModificar.setContentAreaFilled(false);
		btnModificar.setBorderPainted(false);
		panelCentro.add(btnModificar);

		JLabel lblModificar = new JLabel("Modificar Usuario");
		lblModificar.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		lblModificar.setBounds(10, 395, 145, 16);
		panelCentro.add(lblModificar);

		Image im = new ImageIcon("Imagenes/eliminarUsu.png").getImage();
		ImageIcon im2 = new ImageIcon(im.getScaledInstance(156, 178, Image.SCALE_SMOOTH));
		JButton btnBorrarArtculo = new JButton("");
		btnBorrarArtculo.setBounds(10, 422, 166, 178);
		btnBorrarArtculo.setIcon(im2);
		btnBorrarArtculo.setOpaque(false);
		btnBorrarArtculo.setContentAreaFilled(false);
		btnBorrarArtculo.setBorderPainted(false);
		panelCentro.add(btnBorrarArtculo);

		JLabel lblEliminar = new JLabel("Eliminar Usuario");
		lblEliminar.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		lblEliminar.setBounds(10, 600, 145, 16);
		panelCentro.add(lblEliminar);



		JLabel lblDNI = new JLabel("DNI: ");
		lblDNI.setBounds(230, 10, 150, 14);
		panelCentro.add(lblDNI);

		JTextField txtDNI = new JTextField ();
		txtDNI.setBounds(320, 10, 145, 20);
		panelCentro.add(txtDNI);

		
		JLabel lblNombre = new JLabel("NOMBRE: ");
		lblNombre.setBounds(230, 80, 150, 14);
		panelCentro.add(lblNombre);
		
		JTextField txtNom = new JTextField();
		txtNom.setBounds(320,80,145,20);
		panelCentro.add(txtNom);

		JLabel lblApe = new JLabel("APELLIDO: ");
		lblApe.setBounds(230, 150, 150, 14);
		panelCentro.add(lblApe);

		JTextField txtApe = new JTextField ();
		txtApe.setBounds(320, 150, 145, 20);
		panelCentro.add(txtApe);


		JLabel lblNick = new JLabel("NICK: ");
		lblNick.setBounds(230,220, 150, 14);
		panelCentro.add(lblNick);
		
		JTextField txtNick = new JTextField ();
		txtNick.setBounds(320,220,145,20);
		panelCentro.add(txtNick);


		JLabel lblCon = new JLabel("CONTRASEÑA: ");
		lblCon.setBounds(230, 290, 150, 14);
		panelCentro.add(lblCon);

		JPasswordField pssCon = new JPasswordField ();
		pssCon.setBounds(320, 290, 145, 20);
		panelCentro.add(pssCon);


		JLabel lblNumtel = new JLabel("TELÉFONO: ");
		lblNumtel.setBounds(230, 370, 150, 14);
		panelCentro.add(lblNumtel);
		
		JTextField txtNumtel = new JTextField();
		txtNumtel.setBounds(320,370,145,20);
		panelCentro.add(txtNumtel);
		
		JLabel lblDom = new JLabel("DOMICILIO: ");
		lblDom.setBounds(230, 440, 150, 14);
		panelCentro.add(lblDom);

		txtDom = new JTextField();
		txtDom.setBounds(320, 440, 145, 20);
		panelCentro.add(txtDom);
		txtDom.setColumns(10);


		JLabel lblCu = new JLabel("CUENTA BANCARIA: ");
		lblCu.setBounds(230, 510, 150, 14);
		panelCentro.add(lblCu);

		JTextField txtCu = new JTextField();
		txtCu.setBounds(320, 510, 145, 20);
		panelCentro.add(txtCu);
		
		

		Image imvolver = new ImageIcon("Imagenes/volver.png").getImage();
		ImageIcon imvolver2 = new ImageIcon(imvolver.getScaledInstance(175, 175, Image.SCALE_SMOOTH));
		JButton btnVolver = new JButton();
		btnVolver.setBounds(875, 20, 175, 175);
		btnVolver.setIcon(imvolver2);
		btnVolver.setOpaque(false);
		btnVolver.setContentAreaFilled(false);
		btnVolver.setBorderPainted(false);
		btnVolver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				v.dispose();
				VentanaAdmin va = new VentanaAdmin(v);
				va.setVisible(true);
				
			}
		});
		panelCentro.add(btnVolver);
		
		JButton btnTabla = new JButton();
		btnTabla.setBounds(810, 190, 200, 100);
		btnTabla.setOpaque(false);
		btnTabla.setContentAreaFilled(false);
		btnTabla.setBorderPainted(false);
		panelCentro.add(btnTabla);
		Image imTabla = new ImageIcon("Imagenes/table.png").getImage();
		ImageIcon imTabla1 = new ImageIcon(imTabla.getScaledInstance(btnTabla.getWidth(), btnTabla.getHeight(), Image.SCALE_SMOOTH));
		btnTabla.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				v.dispose();
				TablaAdminUsuarios vau = new TablaAdminUsuarios();
				vau.setVisible(true);
				
			}
		});
		btnTabla.setIcon(imTabla1);
		
		JLabel lblTabla = new JLabel("Modificar/Añadir/Eliminar Usuarios desde una tabla");
		lblTabla.setBounds(1010,230, 300,20);
		lblTabla.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		panelCentro.add(lblTabla);
		
		
		
		Image imus = new ImageIcon("Imagenes/elimProduc.png").getImage();
		ImageIcon imus2 = new ImageIcon(imus.getScaledInstance(175, 175, Image.SCALE_SMOOTH));
		JButton btnBorrarUsuario = new JButton();
		btnBorrarUsuario.setBounds(1090, 20, 175, 175);
		btnBorrarUsuario.setIcon(imus2);
		btnBorrarUsuario.setOpaque(false);
		btnBorrarUsuario.setContentAreaFilled(false);
		btnBorrarUsuario.setBorderPainted(false);
		btnBorrarUsuario.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				v.dispose();
				VentanaJListProductos vjl = new VentanaJListProductos();
				vjl.setVisible(true);
				
			}
		});
		panelCentro.add(btnBorrarUsuario);
		
		JLabel lblUsuario = new JLabel("Eliminar Producto desde JList");
		lblUsuario.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		lblUsuario.setBounds(1090, 195, 145, 16);
		panelCentro.add(lblUsuario);



	}
}
