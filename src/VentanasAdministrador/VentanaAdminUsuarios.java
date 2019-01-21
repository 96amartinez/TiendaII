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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import BaseDeDatos.BD;
import Datos.Usuario;

import java.awt.Font;
import java.awt.Image;

public class VentanaAdminUsuarios extends JFrame {

	private JPanel contentPane;
	private JFrame ventanaAnterior;
	JFrame v = this;
	private JTextField txtDNI, txtNom, txtApe,txtNick, txtDom, txtNumtel,txtCu, txtRuta ;
	private JButton btnRuta, btnAceptar;
	private JLabel lblDNI, lblNombre, lblApe, lblCon, lblNick, lblNumtel, lblDom, lblCu; 
	private JPasswordField pssCon;



	private void EnabledTodo() {
		lblDNI.setEnabled(false);
		txtDNI.setEnabled(false);
		lblNombre.setEnabled(false);
		txtNom.setEnabled(false);
		lblApe.setEnabled(false);
		txtApe.setEnabled(false);
		lblNick.setEnabled(false);
		txtNick.setEnabled(false);
		lblNumtel.setEnabled(false);
		txtNumtel.setEnabled(false);
		lblDom.setEnabled(false);
		txtDom.setEnabled(false);
		lblCu.setEnabled(false);
		txtCu.setEnabled(false);
		btnRuta.setEnabled(false);
		txtRuta.setEnabled(false);
		lblCon.setEnabled(false);
		pssCon.setEnabled(false);
		btnAceptar.setEnabled(false);

	}

	public void limpiarCampos() {
		txtDNI.setText("");
		txtNom.setText("");
		txtApe.setText("");
		txtDom.setText("");
		txtNumtel.setText("");
		txtCu.setText("");
		txtNick.setText("");
		pssCon.setText("");
		txtRuta.setText("");
	}

	/**
	 * Create the frame.
	 */
	public VentanaAdminUsuarios(JFrame va) {
		JOptionPane.showMessageDialog(null, "Primero tienes que elegir si quieres eliminar, modificar o añadir un Usuario. En función de eso se habilitaran las opciones necesarias", "Aviso", JOptionPane.INFORMATION_MESSAGE);

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
		btnAniadirArticulo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				EnabledTodo();
				lblDNI.setEnabled(true);
				txtDNI.setEnabled(true);
				lblNombre.setEnabled(true);
				txtNom.setEnabled(true);
				lblApe.setEnabled(true);
				txtApe.setEnabled(true);
				lblNick.setEnabled(true);
				txtNick.setEnabled(true);
				lblNumtel.setEnabled(true);
				txtNumtel.setEnabled(true);
				lblDom.setEnabled(true);
				txtDom.setEnabled(true);
				lblCu.setEnabled(true);
				txtCu.setEnabled(true);
				btnRuta.setEnabled(true);
				txtRuta.setEnabled(true);
				lblCon.setEnabled(true);
				pssCon.setEnabled(true);
				btnAceptar.setEnabled(true);
				btnAceptar.setText("AÑADIR");

				//limpiarCampos();

			}
		});
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
		btnModificar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				EnabledTodo();
				lblDNI.setEnabled(true);
				txtDNI.setEnabled(true);
				lblNombre.setEnabled(true);
				txtNom.setEnabled(true);
				lblApe.setEnabled(true);
				txtApe.setEnabled(true);
				lblNick.setEnabled(true);
				txtNick.setEnabled(true);
				lblNumtel.setEnabled(true);
				txtNumtel.setEnabled(true);
				lblDom.setEnabled(true);
				txtDom.setEnabled(true);
				lblCu.setEnabled(true);
				txtCu.setEnabled(true);
				btnRuta.setEnabled(true);
				txtRuta.setEnabled(true);
				lblCon.setEnabled(true);
				pssCon.setEnabled(true);
				btnAceptar.setEnabled(true);
				btnAceptar.setText("MODIFICAR");

				//Usuario u = new Usuario(txtDNI.getText(), txtNom.getText(), txtApe.getText(), txtNick.getText(), pssCon.getText(), txtNumtel.getText(), txtDom.getText(), txtCu.getText(), "");
				//BD.modificarUsuario(u);
				//limpiarCampos();
			}
		});
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
		btnBorrarArtculo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				EnabledTodo(); 
				lblDNI.setEnabled(true);
				txtDNI.setEnabled(true);
				lblNick.setEnabled(true);
				txtNick.setEnabled(true);
				lblCon.setEnabled(true);
				pssCon.setEnabled(true);

				btnAceptar.setEnabled(true);
				btnAceptar.setText("ELIMINAR");
				//				
				//String dni = txtDNI.getText();
				//String nic = txtNick.getText();
				//String con = pssCon.getText();
				//BD.borrarUsuario(dni, nic, con);
				limpiarCampos();
			}
		});
		panelCentro.add(btnBorrarArtculo);

		JLabel lblEliminar = new JLabel("Eliminar Usuario");
		lblEliminar.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		lblEliminar.setBounds(10, 600, 145, 16);
		panelCentro.add(lblEliminar);



		lblDNI = new JLabel("DNI: ");
		lblDNI.setBounds(230, 10, 150, 14);
		lblDNI.setEnabled(false);
		panelCentro.add(lblDNI);

		txtDNI = new JTextField ();
		txtDNI.setBounds(320, 10, 145, 20);
		txtDNI.setEnabled(false);
		panelCentro.add(txtDNI);


		lblNombre = new JLabel("NOMBRE: ");
		lblNombre.setBounds(230, 80, 150, 14);
		lblNombre.setEnabled(false);
		panelCentro.add(lblNombre);

		txtNom = new JTextField();
		txtNom.setBounds(320,80,145,20);
		txtNom.setEnabled(false);
		panelCentro.add(txtNom);

		lblApe = new JLabel("APELLIDO: ");
		lblApe.setBounds(230, 150, 150, 14);
		lblApe.setEnabled(false);
		panelCentro.add(lblApe);

		txtApe = new JTextField ();
		txtApe.setBounds(320, 150, 145, 20);
		txtApe.setEnabled(false);
		panelCentro.add(txtApe);


		lblNick = new JLabel("NICK: ");
		lblNick.setBounds(230,220, 150, 14);
		lblNick.setEnabled(false);
		panelCentro.add(lblNick);

		txtNick = new JTextField ();
		txtNick.setBounds(320,220,145,20);
		txtNick.setEnabled(false);
		panelCentro.add(txtNick);


		lblCon = new JLabel("CONTRASEÑA: ");
		lblCon.setBounds(230, 290, 150, 14);
		lblCon.setEnabled(false);
		panelCentro.add(lblCon);

		pssCon = new JPasswordField ();
		pssCon.setBounds(320, 290, 145, 20);
		pssCon.setEnabled(false);
		panelCentro.add(pssCon);


		lblNumtel = new JLabel("TELÉFONO: ");
		lblNumtel.setBounds(230, 370, 150, 14);
		lblNumtel.setEnabled(false);
		panelCentro.add(lblNumtel);

		txtNumtel = new JTextField();
		txtNumtel.setBounds(320,370,145,20);
		txtNumtel.setEnabled(false);
		panelCentro.add(txtNumtel);

		lblDom = new JLabel("DOMICILIO: ");
		lblDom.setBounds(230, 440, 150, 14);
		lblDom.setEnabled(false);
		panelCentro.add(lblDom);

		txtDom = new JTextField();
		txtDom.setBounds(320, 440, 145, 20);
		txtDom.setEnabled(false);
		panelCentro.add(txtDom);
		txtDom.setColumns(10);


		//Para poner varias líneas en el JLabel
		String texto= "<html><body>CUENTA <br> BANCARIA: <br></body></html>" ;
		lblCu = new JLabel(texto);
		lblCu.setBounds(230, 500, 150, 40);
		lblCu.setEnabled(false);
		panelCentro.add(lblCu);

		txtCu = new JTextField();
		txtCu.setBounds(320, 510, 145, 20);
		txtCu.setEnabled(false);
		panelCentro.add(txtCu);
		
		btnAceptar = new JButton();
		btnAceptar.setBounds(20, 630, 120, 35);
		btnAceptar.setEnabled(false);
		btnAceptar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Usuario u = new Usuario(txtDNI.getText(), txtNom.getText(), txtApe.getText(), txtNick.getText(), pssCon.getText(), txtNumtel.getText(), txtDom.getText(), txtCu.getText(), txtRuta.getText());
				if(btnAceptar.getText().equals("ELIMINAR")) {
					BD.borrarUsuario(txtDNI.getText(), txtNick.getText(), pssCon.getText());
					limpiarCampos();
				}else if(btnAceptar.getText().equals("MODIFICAR")) {
					BD.modificarUsuario(u);
					limpiarCampos();
				}else if(btnAceptar.getText().equals("AÑADIR")) {
					BD.insertarUsuario(u);
					limpiarCampos();
				}
			}
		});
		panelCentro.add(btnAceptar);



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
				VentanaJListUsuarios vjl = new VentanaJListUsuarios();
				vjl.setVisible(true);

			}
		});
		panelCentro.add(btnBorrarUsuario);

		JLabel lblUsuario = new JLabel("Eliminar Producto desde JList");
		lblUsuario.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		lblUsuario.setBounds(1090, 195, 145, 16);
		panelCentro.add(lblUsuario);
		
		JButton btnimSelec = new JButton();
		btnimSelec.setBounds(570,290, 650, 375);
//		btnimSelec.setOpaque(false);
//		btnimSelec.setContentAreaFilled(false);
//		btnimSelec.setBorderPainted(false);
		panelCentro.add(btnimSelec);


		JButton btnFoto = new JButton();
		btnFoto.setBounds(500,20,250,175);
		Image fot = new ImageIcon("Imagenes/camara1.png").getImage();
		ImageIcon foto = new ImageIcon(fot.getScaledInstance(230, 200, Image.SCALE_SMOOTH));
		btnFoto.setIcon(foto);
		btnFoto.setOpaque(false);
		btnFoto.setContentAreaFilled(false);
		btnFoto.setBorderPainted(false);
		btnFoto.setToolTipText("Pulsa para hacer una foto");
		btnFoto.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				VentanaCamaraPrueba vcm = new VentanaCamaraPrueba();
//				vcm.setVisible(true);
			}
		});
		panelCentro.add(btnFoto);
		
		JScrollPane sp = new JScrollPane();
		sp.setBounds(320,570,130,50);
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		panelCentro.add(sp);
		
		txtRuta = new JTextField();
		sp.setViewportView(txtRuta);
		txtRuta.setEnabled(false);
		//txtRuta.setBounds(320, 570, 130, 50);
		//panelCentro.add(txtRuta);
		
		btnRuta = new JButton("Ruta");
		btnRuta.setBounds(200, 570, 130, 50);
		btnRuta.setToolTipText("Click para seleccionar imagen de la carpeta");
		btnRuta.setEnabled(false);
		Image rut = new ImageIcon("Imagenes/ruta.png").getImage();
		ImageIcon ruta = new ImageIcon(rut.getScaledInstance(130, 50, Image.SCALE_SMOOTH));
		btnRuta.setIcon(ruta);
		btnRuta.setOpaque(false);
		btnRuta.setContentAreaFilled(false);
		btnRuta.setBorderPainted(false);
		btnRuta.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser selector=new JFileChooser();
				File d = new File("/Users/aitorclase/Desktop/prog3-2018/Tienda/usuarios");
				selector.setCurrentDirectory(d);
				FileNameExtensionFilter filtroImagen=new FileNameExtensionFilter("JPG, PNG & GIF","jpg","png","gif");
				selector.setFileFilter(filtroImagen);
				int r=selector.showOpenDialog(null);
				if(r==JFileChooser.APPROVE_OPTION){
					File f=selector.getSelectedFile();

					int x= 650;
					int y=375;
					Image im = null;
					try {
						im = new ImageIcon(selector.getSelectedFile().toURL()).getImage();
					} catch (MalformedURLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					ImageIcon im1 = new ImageIcon(im.getScaledInstance(x, y, Image.SCALE_SMOOTH));
					btnimSelec.setIcon(im1);
					try {
						txtRuta.setText(selector.getSelectedFile().toURL().toString());
					} catch (MalformedURLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}

		});
		panelCentro.add(btnRuta);



	}
}
