package VentanasAdministrador;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import BaseDeDatos.BD;
import Ventanas.VentanaLogin;

import java.awt.Font;
import java.awt.Image;

public class VentanaAdminProductos extends JFrame {

	private JPanel contentPane;
	private JTextField txtRuta;
	private JFrame ventanaAnterior;
	private JTextField txtDesc;
	private JTextField txtStock, txtUrl;
	private JLabel lblCod, lblTipo, lblNombre, lblMarca, lblPrecio, lblEquipo, lblStock, lblPeso, lblTabla,lblDescripcin, 
			lblOferta, lblCategoria, lblTalla, lblColor, lblUrl, lblPlataforma;
	private JComboBox cbCategoria, cbMarca,cbEquipo,cbTipo, cbOferta, cbColor, cbPlataforma;
	private JTextField txtNombre, txtCodigo, txtPrecio, txtPeso, txtTalla;
	JFrame v = this;
	private JButton btnRuta , btnAceptar;
	private JScrollPane scrollPane;



	private void Enabledtodo() {
		lblCod.setEnabled(false);
		txtCodigo.setEnabled(false);
		lblNombre.setEnabled(false);
		txtNombre.setEnabled(false);
		lblMarca.setEnabled(false);
		cbMarca.setEnabled(false);
		lblPrecio.setEnabled(false);
		txtPrecio.setEnabled(false);
		lblStock.setEnabled(false);
		txtStock.setEnabled(false);
		lblEquipo.setEnabled(false);
		cbEquipo.setEnabled(false);
		lblDescripcin.setEnabled(false);
		txtDesc.setEnabled(false);
		lblTipo.setEnabled(false);
		cbTipo.setEnabled(false);
		lblOferta.setEnabled(false);
		cbOferta.setEnabled(false);
		lblTalla.setEnabled(false);
		txtTalla.setEnabled(false);
		lblColor.setEnabled(false);
		cbColor.setEnabled(false);
		lblPeso.setEnabled(false);
		txtPeso.setEnabled(false);
		btnRuta.setEnabled(false);
		lblCategoria.setEnabled(false);
		cbCategoria.setEnabled(false);
		lblPlataforma.setEnabled(false);
		cbPlataforma.setEnabled(false);
		txtUrl.setEnabled(false);
		lblUrl.setEnabled(false);
	}

	private void vaciarCampos() {
		txtCodigo.setText("");
		txtTalla.setText("");
		txtPrecio.setText("");
		txtNombre.setText("");
		txtDesc.setText("");
		txtStock.setText("");
		txtRuta.setText("");
		txtPeso.setText("");
		txtUrl.setText("");
	}

	/**
	 * Create the frame.
	 */
	public VentanaAdminProductos(JFrame va) {
		JOptionPane.showMessageDialog(null, "Primero tienes que elegir si quieres eliminar, modificar o añadir un producto. En función de eso se habilitaran las opciones necesarias", "Aviso", JOptionPane.INFORMATION_MESSAGE);

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

		Image imbtn = new ImageIcon("Imagenes/Articulo.png").getImage();
		ImageIcon imbtn2 = new ImageIcon(imbtn.getScaledInstance(166, 178, Image.SCALE_SMOOTH));
		JButton btnAniadirArticulo = new JButton();
		btnAniadirArticulo.setBounds(10,12,166,178);
		btnAniadirArticulo.setIcon(imbtn2);
		btnAniadirArticulo.setOpaque(false);
		btnAniadirArticulo.setContentAreaFilled(false);
		btnAniadirArticulo.setBorderPainted(false);
		panelCentro.add(btnAniadirArticulo);
		btnAniadirArticulo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Enabledtodo();
				btnAceptar.setText("AÑADIR");
				btnAceptar.setEnabled(true);
				lblCategoria.setEnabled(true);
				cbCategoria.setEnabled(true);
				Object seleccion =  JOptionPane.showInputDialog(null,"¿Qué producto quieres añadir?","Selector de opciones",JOptionPane.QUESTION_MESSAGE,null, new Object[] { "Videojuegos de pago","Videojuegos online", "Productos con peso (Palas y Bicicletas)", "Productos sin equipo,talla y peso (Balones, paleteros, botellines, protectores y pelotas)" ,"Productos con talla y sin equipo (Playeras, botas, guantes, camisetas de pádel y pantalones de pádel)", "Productos con equipo y talla( Cascos y maillots de ciclismo y camisetas, sudaderas y pantalones de fútbol y baloncesto)" }, null);
				if(seleccion.equals("Videojuegos de pago")) {
					Enabledtodo();
					cbCategoria.setEnabled(true);
					lblCategoria.setEnabled(true);
					cbCategoria.setSelectedItem("Videojuegos");
					btnAceptar.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							if((txtStock!=null && !txtStock.equals("")) && (txtPrecio!=null && !txtPrecio.equals(""))) {
								double prec = Double.parseDouble(txtPrecio.getText());
								int stoc = Integer.parseInt(txtStock.getText());
								BD.insertarVideojuegosPago(txtCodigo.getText(), txtNombre.getText(), prec, stoc, txtDesc.getText(), txtRuta.getText(), cbCategoria.getSelectedItem().toString(), cbOferta.getSelectedItem().toString(), cbTipo.getSelectedItem().toString(), cbPlataforma.getSelectedItem().toString());
								vaciarCampos();
							}
						}
					});

				}else if(seleccion.equals("Videojuegos online")){
					Enabledtodo();
					cbCategoria.setEnabled(true);
					lblCategoria.setEnabled(true);
					cbCategoria.setSelectedItem("Videojuegos");
					btnAceptar.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							if((txtStock!=null && !txtStock.equals("")) && (txtPrecio!=null && !txtPrecio.equals(""))) {
								double prec = Double.parseDouble(txtPrecio.getText());
								int stoc = Integer.parseInt(txtStock.getText());
								BD.insertarVideojuegosOnline(txtCodigo.getText(), txtNombre.getText(), prec, stoc, txtDesc.getText(), txtRuta.getText(), cbCategoria.getSelectedItem().toString(), cbOferta.getSelectedItem().toString(), cbTipo.getSelectedItem().toString(), cbPlataforma.getSelectedItem().toString(), txtUrl.getText());
								vaciarCampos();
							}
						}
					});

				}else if(seleccion.equals("Productos con peso (Palas y Bicicletas)")) {
					Enabledtodo();
					cbCategoria.setEnabled(true);
					lblCategoria.setEnabled(true);
					btnAceptar.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							if((txtStock!=null && !txtStock.equals("")) && (txtPrecio!=null && !txtPrecio.equals(""))) {
								double prec = Double.parseDouble(txtPrecio.getText());
								int stoc = Integer.parseInt(txtStock.getText());
								double peso = Double.parseDouble(txtPeso.getText());
								BD.insertarProductosConPeso(txtCodigo.getText(), txtNombre.getText(), prec, stoc, txtDesc.getText(), txtRuta.getText(), cbCategoria.getSelectedItem().toString(), cbOferta.getSelectedItem().toString(), txtTalla.getText(), cbColor.getSelectedItem().toString(), cbTipo.getSelectedItem().toString(), cbMarca.getSelectedItem().toString(), peso);
								vaciarCampos();
							}
						}
					});
				}else if(seleccion.equals("Productos sin equipo,talla y peso (Balones, paleteros, botellines, protectores y pelotas)")) {
					Enabledtodo();
					cbCategoria.setEnabled(true);
					lblCategoria.setEnabled(true);
					btnAceptar.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							if((txtStock!=null && !txtStock.equals("")) && (txtPrecio!=null && !txtPrecio.equals(""))) {
								double prec = Double.parseDouble(txtPrecio.getText());
								int stoc = Integer.parseInt(txtStock.getText());
								BD.insertarProductosSinEquipTallaPeso(txtCodigo.getText(), txtNombre.getText(), prec, stoc, txtDesc.getText(), txtRuta.getText(), cbCategoria.getSelectedItem().toString(), cbOferta.getSelectedItem().toString(), cbColor.getSelectedItem().toString(), cbTipo.getSelectedItem().toString(), cbMarca.getSelectedItem().toString());
								vaciarCampos();
							}
						}
					});
				}else if(seleccion.equals("Productos con talla y sin equipo (Playeras, botas, guantes, camisetas de pádel y pantalones de pádel)")) {
					Enabledtodo();
					cbCategoria.setEnabled(true);
					lblCategoria.setEnabled(true);
					btnAceptar.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							if((txtStock!=null && !txtStock.equals("")) && (txtPrecio!=null && !txtPrecio.equals(""))) {
								double prec = Double.parseDouble(txtPrecio.getText());
								int stoc = Integer.parseInt(txtStock.getText());
								BD.insertarProductosConTallaYSinEquipo(txtCodigo.getText(), txtNombre.getText(), prec, stoc, txtDesc.getText(), txtRuta.getText(), cbCategoria.getSelectedItem().toString(), cbOferta.getSelectedItem().toString(), txtTalla.getText(), cbColor.getSelectedItem().toString(), cbTipo.getSelectedItem().toString(), cbMarca.getSelectedItem().toString());
								vaciarCampos();
							}
						}
					});
				}else if(seleccion.equals("Productos con equipo y talla( Cascos y maillots de ciclismo y camisetas, sudaderas y pantalones de fútbol y baloncesto)")) {
					Enabledtodo();
					cbCategoria.setEnabled(true);
					lblCategoria.setEnabled(true);
					btnAceptar.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							if((txtStock!=null && !txtStock.equals("")) && (txtPrecio!=null && !txtPrecio.equals(""))) {
								double prec = Double.parseDouble(txtPrecio.getText());
								int stoc = Integer.parseInt(txtStock.getText());
								BD.insertarProductosConEquipoYTalla(txtCodigo.getText(), txtNombre.getText(), prec, stoc, txtDesc.getText(), txtRuta.getText(), cbCategoria.getSelectedItem().toString(), cbOferta.getSelectedItem().toString(), txtTalla.getText(), cbColor.getSelectedItem().toString(), cbTipo.getSelectedItem().toString(), cbMarca.getSelectedItem().toString(), cbEquipo.getSelectedItem().toString());
								vaciarCampos();
							}
						}
					});

				}
			}
		});


		JLabel lblAniadir = new JLabel("Añadir Artículo");
		lblAniadir.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		lblAniadir.setBounds(10, 186, 145, 16);
		panelCentro.add(lblAniadir);


		Image imb = new ImageIcon("Imagenes/modificar.png").getImage();
		ImageIcon imb2 = new ImageIcon(imb.getScaledInstance(166, 178, Image.SCALE_SMOOTH));
		JButton btnModificar = new JButton("MODIFICAR ARTÍCULO");
		btnModificar.setBounds(10, 222, 166, 178);
		btnModificar.setIcon(imb2);
		btnModificar.setOpaque(false);
		btnModificar.setContentAreaFilled(false);
		btnModificar.setBorderPainted(false);
		panelCentro.add(btnModificar);
		btnModificar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Enabledtodo();
				btnAceptar.setText("MODIFICAR");
				btnAceptar.setEnabled(true);
				lblCategoria.setEnabled(true);
				cbCategoria.setEnabled(true);
				Object seleccion =  JOptionPane.showInputDialog(null,"¿Qué producto quieres modificar?","Selector de opciones",JOptionPane.QUESTION_MESSAGE,null, new Object[] { "Videojuegos (Videojuegos de pago o Videojuegos online)", "Productos de peso (Palas y Bicicletas)", "Productos con equipo (Camisetas, sudaderas y pantalones de fútbol y baloncesto, Cascos y Maillots de ciclismo)" ,"Productos sin equipo y peso (Balones, playeras, botas, guantes y materiales de pádel [menos las palas])" }, null);
				if(seleccion.equals("Videojuegos (Videojuegos de pago o Videojuegos online)")) {
					Enabledtodo();
					//lblCod.setEnabled(true);
					//txtCodigo.setEnabled(true);
					//lblNombre.setEnabled(true);
					//txtNombre.setEnabled(true);
					//lblPrecio.setEnabled(true);
					//txtPrecio.setEnabled(true);
					//lblStock.setEnabled(true);
					//txtStock.setEnabled(true);
					//lblDescripcin.setEnabled(true);
					//txtDesc.setEnabled(true);
					//btnRuta.setEnabled(true);
					//txtRuta.setEnabled(true);
					lblCategoria.setEnabled(true);
					cbCategoria.setEnabled(true);
					cbCategoria.setSelectedItem("Videojuegos");
					//lblOferta.setEnabled(true);
					//cbOferta.setEnabled(true);
					//cbTipo.setEnabled(true);
					//lblTipo.setEnabled(true);
					//lblPlataforma.setEnabled(true);
					//cbPlataforma.setEnabled(true);
					//lblUrl.setEnabled(true);
					//txtUrl.setEnabled(true);
					btnAceptar.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							double precio = Double.parseDouble(txtPrecio.getText());
							int stock = Integer.parseInt(txtStock.getText());
							BD.modificarVideojuegos(txtCodigo.getText(), txtNombre.getText(), precio, stock, txtDesc.getText(), txtRuta.getText(), cbCategoria.getSelectedItem().toString(), cbOferta.getSelectedItem().toString(), cbTipo.getSelectedItem().toString(), cbPlataforma.getSelectedItem().toString(), txtUrl.getText());
							vaciarCampos();
							Enabledtodo();

						}
					});

				}else if(seleccion.equals("Productos de peso (Palas y Bicicletas)")) {
					Enabledtodo();
					//lblCod.setEnabled(true);
					//txtCodigo.setEnabled(true);
					//lblNombre.setEnabled(true);
					//txtNombre.setEnabled(true);
					//blPrecio.setEnabled(true);
					//txtPrecio.setEnabled(true);
					//lblStock.setEnabled(true);
					//txtStock.setEnabled(true);
					//lblDescripcin.setEnabled(true);
					//txtDesc.setEnabled(true);
					//btnRuta.setEnabled(true);
					//txtRuta.setEnabled(true);
					lblCategoria.setEnabled(true);
					cbCategoria.setEnabled(true);
					//lblOferta.setEnabled(true);
					//cbOferta.setEnabled(true);
					//lblTalla.setEnabled(true);
					//txtTalla.setEnabled(true);
					//lblColor.setEnabled(true);
					//cbColor.setEnabled(true);
					//lblTipo.setEnabled(true);
					//cbTipo.setEnabled(true);
					//lblMarca.setEnabled(true);
					//cbMarca.setEnabled(true);
					//lblPeso.setEnabled(true);
					//txtPeso.setEnabled(true);
					btnAceptar.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							double precio = Double.parseDouble(txtPrecio.getText());
							int stock = Integer.parseInt(txtStock.getText());
							double peso = Double.parseDouble(txtPeso.getText());
							BD.modificarProductosConPeso(txtCodigo.getText(), txtNombre.getText(), precio, stock, txtDesc.getText(), txtRuta.getText(), cbCategoria.getSelectedItem().toString(), cbOferta.getSelectedItem().toString(), txtTalla.getText(), cbColor.getSelectedItem().toString(), cbTipo.getSelectedItem().toString(), cbMarca.getSelectedItem().toString(), peso);
							vaciarCampos();
							Enabledtodo();

						}
					});
				}else if(seleccion.equals("Productos con equipo (Camisetas, sudaderas y pantalones de fútbol y baloncesto, Cascos y Maillots de ciclismo)")) {
					Enabledtodo();
					//lblCod.setEnabled(true);
					//txtCodigo.setEnabled(true);
					//lblNombre.setEnabled(true);
					//txtNombre.setEnabled(true);
					//lblPrecio.setEnabled(true);
					//txtPrecio.setEnabled(true);
					//lblStock.setEnabled(true);
					//txtStock.setEnabled(true);
					//lblDescripcin.setEnabled(true);
					//txtDesc.setEnabled(true);
					//btnRuta.setEnabled(true);
					//txtRuta.setEnabled(true);
					lblCategoria.setEnabled(true);
					cbCategoria.setEnabled(true);
					//lblOferta.setEnabled(true);
					//cbOferta.setEnabled(true);
					//lblTalla.setEnabled(true);
					//txtTalla.setEnabled(true);
					//lblColor.setEnabled(true);
					//cbColor.setEnabled(true);
					//lblTipo.setEnabled(true);
					//cbTipo.setEnabled(true);
					//lblMarca.setEnabled(true);
					//cbMarca.setEnabled(true);
					//lblEquipo.setEnabled(true);
					//cbEquipo.setEnabled(true);
					btnAceptar.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							double precio = Double.parseDouble(txtPrecio.getText());
							int stock = Integer.parseInt(txtStock.getText());
							BD.modificarProductosConEquipo(txtCodigo.getText(), txtNombre.getText(), precio, stock, txtDesc.getText(), txtRuta.getText(), cbCategoria.getSelectedItem().toString(), cbOferta.getSelectedItem().toString(), txtTalla.getText(), cbColor.getSelectedItem().toString(), cbTipo.getSelectedItem().toString(), cbMarca.getSelectedItem().toString(), cbEquipo.getSelectedItem().toString());
							vaciarCampos();
							Enabledtodo();

						}
					});
				}else if(seleccion.equals("Productos sin equipo y peso (Balones, playeras, botas, guantes y materiales de pádel [menos las palas])")) {
					Enabledtodo();
					//lblCod.setEnabled(true);
					//txtCodigo.setEnabled(true);
					//lblNombre.setEnabled(true);
					//txtNombre.setEnabled(true);
					//lblPrecio.setEnabled(true);
					//txtPrecio.setEnabled(true);
					//lblStock.setEnabled(true);
					//txtStock.setEnabled(true);
					//lblDescripcin.setEnabled(true);
					//txtDesc.setEnabled(true);
					//btnRuta.setEnabled(true);
					//txtRuta.setEnabled(true);
					lblCategoria.setEnabled(true);
					cbCategoria.setEnabled(true);
					//lblOferta.setEnabled(true);
					//cbOferta.setEnabled(true);
					//lblColor.setEnabled(true);
					//cbColor.setEnabled(true);
					//lblTipo.setEnabled(true);
					//cbTipo.setEnabled(true);
					//lblMarca.setEnabled(true);
					//cbMarca.setEnabled(true);
					btnAceptar.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							double precio = Double.parseDouble(txtPrecio.getText());
							int stock = Integer.parseInt(txtStock.getText());
							BD.modificarProductosSinEquipoYPeso(txtCodigo.getText(), txtNombre.getText(), precio, stock, txtDesc.getText(), txtRuta.getText(), cbCategoria.getSelectedItem().toString(), cbOferta.getSelectedItem().toString(), txtTalla.getText(), cbColor.getSelectedItem().toString(), cbTipo.getSelectedItem().toString(), cbMarca.getSelectedItem().toString());
							vaciarCampos();
							Enabledtodo();
						}
					});
				}

			}
		});


		JLabel lblModificar = new JLabel("Modificar Artículo");
		lblModificar.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		lblModificar.setBounds(10, 395, 145, 16);
		panelCentro.add(lblModificar);

		Image im = new ImageIcon("Imagenes/eliminar.png").getImage();
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
				Enabledtodo();
				btnAceptar.setText("ELIMINAR");
				btnAceptar.setEnabled(true);
				lblCod.setEnabled(true);
				txtCodigo.setEnabled(true);
				lblTalla.setEnabled(false);
				txtTalla.setEnabled(false);
				int resp = JOptionPane.showOptionDialog(null, "¿El producto a eliminar tiene talla o no? ", "Selector de opciones", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Producto sin talla", "Producto con talla"} , null);
				if(resp==1) {
					lblTalla.setEnabled(true);
					txtTalla.setEnabled(true);
					btnAceptar.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							String codigo = txtCodigo.getText();
							String talla = txtTalla.getText();
							VentanaLogin.bd.borrarProductoConTalla(codigo, talla);
							vaciarCampos();
							Enabledtodo();
						}
					});
				}else if(resp==0){
					btnAceptar.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							String codigo = txtCodigo.getText();
							VentanaLogin.bd.borrarProductoSinTalla(codigo);
							vaciarCampos();
							Enabledtodo();
						}
					});
				}
			}


		});
		panelCentro.add(btnBorrarArtculo);
		
		btnAceptar = new JButton();
		btnAceptar.setBounds(20, 630, 120, 35);
		btnAceptar.setEnabled(false);
		panelCentro.add(btnAceptar);

		JLabel lblEliminar = new JLabel("Eliminar Artículo");
		lblEliminar.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		lblEliminar.setBounds(10, 600, 145, 16);
		panelCentro.add(lblEliminar);

		lblCategoria = new JLabel("CATEGORIA");
		lblCategoria.setBounds(230, 430, 100, 20);
		lblCategoria.setEnabled(false);
		panelCentro.add(lblCategoria);


		lblCod = new JLabel("CÓDIGO");
		lblCod.setBounds(230, 10, 63, 14);
		lblCod.setEnabled(false);
		panelCentro.add(lblCod);

		txtCodigo = new JTextField ();
		txtCodigo.setBounds(320, 10, 145, 20);
		txtCodigo.setEnabled(false);
		panelCentro.add(txtCodigo);

		lblTipo = new JLabel("TIPO");
		lblTipo.setBounds(570, 10, 63, 14);
		lblTipo.setEnabled(false);
		panelCentro.add(lblTipo);
		
		String[] cat = {"Fútbol", "Pádel", "Baloncesto", "Ciclismo", "Videojuegos"};
		cbCategoria = new JComboBox(cat);
		cbCategoria.setBounds(320, 430, 145, 25);
		cbCategoria.setEnabled(false);
		panelCentro.add(cbCategoria);
		cbCategoria.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Enabledtodo();
				cbTipo.setEnabled(true);
				lblTipo.setEnabled(true);
				rellenaComboTipo((String) cbCategoria.getSelectedItem());
				rellenaComboMarca((String) cbCategoria.getSelectedItem());
				rellenaComboEquipo((String) cbCategoria.getSelectedItem());
				lblMarca.setEnabled(false);
				cbMarca.setEnabled(false);
				lblEquipo.setEnabled(false);
				cbEquipo.setEnabled(false);
				if(cbCategoria.getSelectedItem().equals("Videojuegos")) {
					Enabledtodo();
					lblCod.setEnabled(true);
					txtCodigo.setEnabled(true);
					lblNombre.setEnabled(true);
					txtNombre.setEnabled(true);
					lblPrecio.setEnabled(true);
					txtPrecio.setEnabled(true);
					lblStock.setEnabled(true);
					txtStock.setEnabled(true);
					lblDescripcin.setEnabled(true);
					txtDesc.setEnabled(true);
					btnRuta.setEnabled(true);
					lblTipo.setEnabled(true);
					cbTipo.setEnabled(true);
					lblCategoria.setEnabled(true);
					cbCategoria.setEnabled(true);
					lblOferta.setEnabled(true);
					cbOferta.setEnabled(true);
					lblPlataforma.setEnabled(true);
					cbPlataforma.setEnabled(true);
					lblUrl.setEnabled(true);
					txtUrl.setEnabled(true);

					lblTalla.setVisible(false);
					txtTalla.setVisible(false);
					lblColor.setVisible(false);
					cbColor.setVisible(false);
					lblPlataforma.setVisible(true);
					cbPlataforma.setVisible(true);
					txtUrl.setVisible(true);
					lblUrl.setVisible(true);
				}


			}
		});

		cbTipo = new JComboBox();
		cbTipo.setBounds(660, 10, 145, 25);
		cbTipo.setEnabled(false);
		panelCentro.add(cbTipo);
		cbTipo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Enabledtodo();
				lblCategoria.setEnabled(true);
				cbCategoria.setEnabled(true);
				lblTipo.setEnabled(true);
				cbTipo.setEnabled(true);
				if(cbTipo.getSelectedIndex()!=-1) {
					if((cbTipo.getSelectedItem().equals("Camisetas") && (cbCategoria.getSelectedItem().equals("Fútbol") || cbCategoria.getSelectedItem().equals("Baloncesto"))) || cbTipo.getSelectedItem().equals("Sudaderas") ||   
							(cbTipo.getSelectedItem().equals("Pantalones") && (cbCategoria.getSelectedItem().equals("Fútbol") || cbCategoria.getSelectedItem().equals("Baloncesto"))) || cbTipo.getSelectedItem().equals("Maillots") || cbTipo.getSelectedItem().equals("Cascos")) {
						Enabledtodo();
						lblCod.setEnabled(true);
						txtCodigo.setEnabled(true);
						lblNombre.setEnabled(true);
						txtNombre.setEnabled(true);
						lblMarca.setEnabled(true);
						cbMarca.setEnabled(true);
						lblPrecio.setEnabled(true);
						txtPrecio.setEnabled(true);
						lblEquipo.setEnabled(true);
						cbEquipo.setEnabled(true);
						lblStock.setEnabled(true);
						txtStock.setEnabled(true);
						lblDescripcin.setEnabled(true);
						txtDesc.setEnabled(true);
						lblOferta.setEnabled(true);
						cbOferta.setEnabled(true);
						lblTalla.setEnabled(true);
						txtTalla.setEnabled(true);
						lblColor.setEnabled(true);
						cbColor.setEnabled(true);
						lblTipo.setEnabled(true);
						cbTipo.setEnabled(true);
						btnRuta.setEnabled(true);
						cbCategoria.setEnabled(true);
						lblCategoria.setEnabled(true);

						lblPlataforma.setVisible(false);
						cbPlataforma.setVisible(false);
						lblUrl.setVisible(false);
						txtUrl.setVisible(false);

						lblColor.setVisible(true);
						cbColor.setVisible(true);
						lblTalla.setVisible(true);
						txtTalla.setVisible(true);
					}else if(cbTipo.getSelectedItem().equals("Balones")) {
						Enabledtodo();
						lblCod.setEnabled(true);
						txtCodigo.setEnabled(true);
						lblNombre.setEnabled(true);
						txtNombre.setEnabled(true);
						lblPrecio.setEnabled(true);
						txtPrecio.setEnabled(true);
						lblStock.setEnabled(true);
						txtStock.setEnabled(true);
						lblDescripcin.setEnabled(true);
						txtDesc.setEnabled(true);
						lblOferta.setEnabled(true);
						cbOferta.setEnabled(true);
						lblColor.setEnabled(true);
						cbColor.setEnabled(true);
						lblMarca.setEnabled(true);
						cbMarca.setEnabled(true);
						lblTipo.setEnabled(true);
						cbTipo.setEnabled(true);
						btnRuta.setEnabled(true);
						cbCategoria.setEnabled(true);
						lblCategoria.setEnabled(true);

						lblPlataforma.setVisible(false);
						cbPlataforma.setVisible(false);
						lblUrl.setVisible(false);
						txtUrl.setVisible(false);

						lblColor.setVisible(true);
						cbColor.setVisible(true);
						lblTalla.setVisible(true);
						txtTalla.setVisible(true);
					}else if(cbTipo.getSelectedItem().equals("Guantes") || cbTipo.getSelectedItem().equals("Botas") || cbTipo.getSelectedItem().equals("Playeras")) {
						Enabledtodo();
						lblCod.setEnabled(true);
						txtCodigo.setEnabled(true);
						lblNombre.setEnabled(true);
						txtNombre.setEnabled(true);
						lblMarca.setEnabled(true);
						cbMarca.setEnabled(true);
						lblPrecio.setEnabled(true);
						txtPrecio.setEnabled(true);
						lblStock.setEnabled(true);
						txtStock.setEnabled(true);
						lblDescripcin.setEnabled(true);
						txtDesc.setEnabled(true);
						lblOferta.setEnabled(true);
						cbOferta.setEnabled(true);
						lblTipo.setEnabled(true);
						cbTipo.setEnabled(true);
						lblTalla.setEnabled(true);
						txtTalla.setEnabled(true);
						lblColor.setEnabled(true);
						cbColor.setEnabled(true);
						btnRuta.setEnabled(true);
						cbCategoria.setEnabled(true);
						lblCategoria.setEnabled(true);

						lblPlataforma.setVisible(false);
						cbPlataforma.setVisible(false);
						lblUrl.setVisible(false);
						txtUrl.setVisible(false);

						lblColor.setVisible(true);
						cbColor.setVisible(true);
						lblTalla.setVisible(true);
						txtTalla.setVisible(true);
					}else if(cbTipo.getSelectedItem().equals("Paleteros") ||cbTipo.getSelectedItem().equals("Pelotas") || cbTipo.getSelectedItem().equals("Protectores")) {
						Enabledtodo();
						lblCod.setEnabled(true);
						txtCodigo.setEnabled(true);
						lblNombre.setEnabled(true);
						txtNombre.setEnabled(true);
						lblMarca.setEnabled(true);
						cbMarca.setEnabled(true);
						lblPrecio.setEnabled(true);
						txtPrecio.setEnabled(true);
						lblStock.setEnabled(true);
						txtStock.setEnabled(true);
						lblDescripcin.setEnabled(true);
						txtDesc.setEnabled(true);
						lblOferta.setEnabled(true);
						cbOferta.setEnabled(true);
						lblTipo.setEnabled(true);
						cbTipo.setEnabled(true);
						lblColor.setEnabled(true);
						cbColor.setEnabled(true);
						btnRuta.setEnabled(true);
						cbCategoria.setEnabled(true);
						lblCategoria.setEnabled(true);

						lblPlataforma.setVisible(false);
						cbPlataforma.setVisible(false);
						lblUrl.setVisible(false);
						txtUrl.setVisible(false);

						lblColor.setVisible(true);
						cbColor.setVisible(true);
						lblTalla.setVisible(true);
						txtTalla.setVisible(true);
					}else if((cbTipo.getSelectedItem().equals("Camisetas") && cbCategoria.getSelectedItem().equals("Pádel")) || (cbTipo.getSelectedItem().equals("Pantalones") && (cbCategoria.getSelectedItem().equals("Pádel")))) {
						Enabledtodo();
						lblCod.setEnabled(true);
						txtCodigo.setEnabled(true);
						lblNombre.setEnabled(true);
						txtNombre.setEnabled(true);
						lblMarca.setEnabled(true);
						cbMarca.setEnabled(true);
						lblPrecio.setEnabled(true);
						txtPrecio.setEnabled(true);
						lblStock.setEnabled(true);
						txtStock.setEnabled(true);
						lblDescripcin.setEnabled(true);
						txtDesc.setEnabled(true);
						lblTipo.setEnabled(true);
						cbTipo.setEnabled(true);
						lblOferta.setEnabled(true);
						cbOferta.setEnabled(true);
						lblTalla.setEnabled(true);
						txtTalla.setEnabled(true);
						lblColor.setEnabled(true);
						cbColor.setEnabled(true);
						btnRuta.setEnabled(true);
						cbCategoria.setEnabled(true);
						lblCategoria.setEnabled(true);

						lblPlataforma.setVisible(false);
						cbPlataforma.setVisible(false);
						lblUrl.setVisible(false);
						txtUrl.setVisible(false);

						lblColor.setVisible(true);
						cbColor.setVisible(true);
						lblTalla.setVisible(true);
						txtTalla.setVisible(true);
					}else if(cbTipo.getSelectedItem().equals("Bicicletas")) {
						Enabledtodo();
						lblCod.setEnabled(true);
						txtCodigo.setEnabled(true);
						lblNombre.setEnabled(true);
						txtNombre.setEnabled(true);
						lblPrecio.setEnabled(true);
						txtPrecio.setEnabled(true);
						lblStock.setEnabled(true);
						txtStock.setEnabled(true);
						lblDescripcin.setEnabled(true);
						txtDesc.setEnabled(true);
						btnRuta.setEnabled(true);
						lblCategoria.setEnabled(true);
						cbCategoria.setEnabled(true);
						lblTipo.setEnabled(true);
						cbTipo.setEnabled(true);
						lblOferta.setEnabled(true);
						cbOferta.setEnabled(true);
						lblTalla.setEnabled(true);
						txtTalla.setEnabled(true);
						lblColor.setEnabled(true);
						cbColor.setEnabled(true);
						cbMarca.setEnabled(true);
						lblMarca.setEnabled(true); 
						lblPeso.setEnabled(true);
						txtPeso.setEnabled(true);

						lblPlataforma.setVisible(false);
						cbPlataforma.setVisible(false);
						lblUrl.setVisible(false);
						txtUrl.setVisible(false);

						lblColor.setVisible(true);
						cbColor.setVisible(true);
						lblTalla.setVisible(true);
						txtTalla.setVisible(true);
					}else if(cbTipo.getSelectedItem().equals("Palas")) {
						Enabledtodo();
						lblCod.setEnabled(true);
						txtCodigo.setEnabled(true);
						lblNombre.setEnabled(true);
						txtNombre.setEnabled(true);
						lblPrecio.setEnabled(true);
						txtPrecio.setEnabled(true);
						lblStock.setEnabled(true);
						txtStock.setEnabled(true);
						lblDescripcin.setEnabled(true);
						txtDesc.setEnabled(true);
						btnRuta.setEnabled(true);
						lblCategoria.setEnabled(true);
						cbCategoria.setEnabled(true);
						lblTipo.setEnabled(true);
						cbTipo.setEnabled(true);
						lblOferta.setEnabled(true);
						cbOferta.setEnabled(true);
						lblTalla.setEnabled(true);
						txtTalla.setEnabled(true);
						lblColor.setEnabled(true);
						cbColor.setEnabled(true);
						cbMarca.setEnabled(true);
						lblMarca.setEnabled(true); 
						lblPeso.setEnabled(true);
						txtPeso.setEnabled(true);

						lblPlataforma.setVisible(false);
						cbPlataforma.setVisible(false);
						lblUrl.setVisible(false);
						txtUrl.setVisible(false);

						lblColor.setVisible(true);
						cbColor.setVisible(true);
						lblTalla.setVisible(true);
						txtTalla.setVisible(true);
					}else if(cbTipo.getSelectedItem().equals("Botellines")) {
						Enabledtodo();
						lblCod.setEnabled(true);
						txtCodigo.setEnabled(true);
						lblNombre.setEnabled(true);
						txtNombre.setEnabled(true);
						lblPrecio.setEnabled(true);
						txtPrecio.setEnabled(true);
						lblStock.setEnabled(true);
						txtStock.setEnabled(true);
						lblDescripcin.setEnabled(true);
						txtDesc.setEnabled(true);
						btnRuta.setEnabled(true);
						lblCategoria.setEnabled(true);
						cbCategoria.setEnabled(true);
						lblTipo.setEnabled(true);
						cbTipo.setEnabled(true);
						lblOferta.setEnabled(true);
						cbOferta.setEnabled(true);
						lblColor.setEnabled(true);
						cbColor.setEnabled(true);
						cbMarca.setEnabled(true);
						lblMarca.setEnabled(true); 

						lblPlataforma.setVisible(false);
						cbPlataforma.setVisible(false);
						lblUrl.setVisible(false);
						txtUrl.setVisible(false);

						lblColor.setVisible(true);
						cbColor.setVisible(true);
						lblTalla.setVisible(true);
						txtTalla.setVisible(true);
					}
					else if(cbCategoria.getSelectedItem().equals("Videojuegos") && cbPlataforma.getSelectedItem().equals("Online")) {
						Enabledtodo();
						lblCod.setEnabled(true);
						txtCodigo.setEnabled(true);
						lblNombre.setEnabled(true);
						txtNombre.setEnabled(true);
						lblPrecio.setEnabled(true);
						txtPrecio.setEnabled(true);
						lblStock.setEnabled(true);
						txtStock.setEnabled(true);
						lblDescripcin.setEnabled(true);
						txtDesc.setEnabled(true);
						btnRuta.setEnabled(true);
						lblCategoria.setEnabled(true);
						cbCategoria.setEnabled(true);
						lblTipo.setEnabled(true);
						cbTipo.setEnabled(true);
						lblOferta.setEnabled(true);
						cbOferta.setEnabled(true);
						lblPlataforma.setEnabled(true);
						cbPlataforma.setEnabled(true);
						lblUrl.setEnabled(true);
						txtUrl.setEnabled(true);

						lblTalla.setVisible(false);
						txtTalla.setVisible(false);
						lblColor.setVisible(false);
						cbColor.setVisible(false);
						lblPlataforma.setVisible(true);
						cbPlataforma.setVisible(true);
						txtUrl.setVisible(true);
						lblUrl.setVisible(true);

					} else if(cbCategoria.getSelectedItem().equals("Videojuegos") && (cbPlataforma.getSelectedItem().equals("PS4") || cbPlataforma.getSelectedItem().equals("PS3") || cbPlataforma.getSelectedItem().equals("XBOX"))) {
						Enabledtodo();
						lblCod.setEnabled(true);
						txtCodigo.setEnabled(true);
						lblNombre.setEnabled(true);
						txtNombre.setEnabled(true);
						lblPrecio.setEnabled(true);
						txtPrecio.setEnabled(true);
						lblStock.setEnabled(true);
						txtStock.setEnabled(true);
						lblDescripcin.setEnabled(true);
						txtDesc.setEnabled(true);
						btnRuta.setEnabled(true);
						lblCategoria.setEnabled(true);
						cbCategoria.setEnabled(true);
						lblTipo.setEnabled(true);
						cbTipo.setEnabled(true);
						lblOferta.setEnabled(true);
						cbOferta.setEnabled(true);
						lblPlataforma.setEnabled(true);
						cbPlataforma.setEnabled(true);

						lblTalla.setVisible(false);
						txtTalla.setVisible(false);
						lblColor.setVisible(false);
						cbColor.setVisible(false);
						lblPlataforma.setVisible(true);
						cbPlataforma.setVisible(true);
						txtUrl.setVisible(true);
						lblUrl.setVisible(true);
					}


				}
			}
		});



		lblNombre = new JLabel("NOMBRE");
		lblNombre.setBounds(230, 80, 63, 14);
		lblNombre.setEnabled(false);
		panelCentro.add(lblNombre);

		txtNombre = new JTextField ();
		txtNombre.setBounds(320, 80, 145, 20);
		txtNombre.setEnabled(false);
		panelCentro.add(txtNombre);



		lblMarca = new JLabel("MARCA");
		lblMarca.setBounds(570, 80, 63, 14);
		lblMarca.setEnabled(false);
		panelCentro.add(lblMarca);

		cbMarca = new JComboBox();
		cbMarca.setBounds(660, 80, 145, 25);
		cbMarca.setEnabled(false);
		panelCentro.add(cbMarca);



		lblPrecio = new JLabel("PRECIO");
		lblPrecio.setBounds(230, 150, 46, 14);
		lblPrecio.setEnabled(false);
		panelCentro.add(lblPrecio);

		txtPrecio = new JTextField ();
		txtPrecio.setBounds(320, 150, 145, 20);
		txtPrecio.setEnabled(false);
		panelCentro.add(txtPrecio);



		lblEquipo = new JLabel("EQUIPO");
		lblEquipo.setBounds(570, 150, 63, 14);
		lblEquipo.setEnabled(false);
		panelCentro.add(lblEquipo);

		cbEquipo = new JComboBox();
		cbEquipo.setBounds(660, 150, 145, 25);
		cbEquipo.setEnabled(false);
		panelCentro.add(cbEquipo);



		lblStock = new JLabel("STOCK");
		lblStock.setBounds(230, 220, 46, 14);
		lblStock.setEnabled(false);
		panelCentro.add(lblStock);

		txtStock = new JTextField();
		txtStock.setBounds(320, 220, 145, 20);
		txtStock.setEnabled(false);
		panelCentro.add(txtStock);
		txtStock.setColumns(10);



		lblPeso = new JLabel("PESO");
		lblPeso.setBounds(570, 220, 63, 14);
		lblPeso.setEnabled(false);
		panelCentro.add(lblPeso);

		txtPeso = new JTextField();
		txtPeso.setBounds(660, 220, 145, 20);
		txtPeso.setEnabled(false);
		panelCentro.add(txtPeso);
		
		

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
				TablaAdminProductos vap = new TablaAdminProductos();
				vap.setVisible(true);
				
			}
		});
		btnTabla.setIcon(imTabla1);
		
		lblTabla = new JLabel("Modificar/Añadir/Eliminar productos desde una tabla");
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

		
		
		lblDescripcin = new JLabel("DESCRIPCIÓN");
		lblDescripcin.setBounds(230, 290, 90, 16);
		lblDescripcin.setEnabled(false);
		panelCentro.add(lblDescripcin);

		txtDesc = new JTextField();
		txtDesc.setBounds(320, 290, 145, 20);
		txtDesc.setEnabled(false);
		panelCentro.add(txtDesc);
		txtDesc.setColumns(10);


		JButton btnimSelec = new JButton();
		btnimSelec.setBounds(570,290, 650, 375);
		btnimSelec.setOpaque(false);
		btnimSelec.setContentAreaFilled(false);
		btnimSelec.setBorderPainted(false);
		panelCentro.add(btnimSelec);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(320, 360, 140, 40);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		panelCentro.add(scrollPane);
		
		txtRuta = new JTextField();
		scrollPane.setViewportView(txtRuta);
		txtRuta.setEnabled(true);
		
		
		btnRuta = new JButton("Ruta");
		btnRuta.setBounds(230, 360, 230, 20);
		btnRuta.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser selector=new JFileChooser();
				File d = new File("/Users/aitorclase/Desktop/prog3-2018/Tienda/Productos");
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
							e1.printStackTrace();
						}
						ImageIcon im1 = new ImageIcon(im.getScaledInstance(x, y, Image.SCALE_SMOOTH));
						btnimSelec.setIcon(im1);
						try {
							txtRuta.setText(selector.getSelectedFile().toURL().toString());
						} catch (MalformedURLException e1) {
							e1.printStackTrace();
						}
					}
				}

			});
		panelCentro.add(btnRuta);


		lblOferta = new JLabel("OFERTA");
		lblOferta.setBounds(230, 500, 63, 20);
		lblOferta.setEnabled(false);
		panelCentro.add(lblOferta);

		String [] of = {"True", "False"};
		cbOferta = new JComboBox(of);
		cbOferta.setEnabled(false);
		cbOferta.setBounds(320, 500, 145, 20);
		panelCentro.add(cbOferta);



		lblTalla = new JLabel("TALLA");
		lblTalla.setBounds(230, 570, 63, 20);
		lblTalla.setEnabled(false);
		panelCentro.add(lblTalla);

		txtTalla = new JTextField();
		txtTalla.setBounds(320, 570, 145, 20);
		txtTalla.setEnabled(false);
		panelCentro.add(txtTalla);

		lblPlataforma = new JLabel("PLATAFORMA");
		lblPlataforma.setBounds(230, 570, 105, 20);
		lblPlataforma.setEnabled(false);
		lblPlataforma.setVisible(false);
		panelCentro.add(lblPlataforma);

		String [] plat = {"PS4", "PS3", "XBOX", "Online"};
		cbPlataforma = new JComboBox(plat);
		cbPlataforma.setBounds(320, 570, 145, 30);
		cbPlataforma.setEnabled(false);
		cbPlataforma.setVisible(false);
		cbPlataforma.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(cbPlataforma.getSelectedItem().equals("Online")) {
					Enabledtodo();
					lblCod.setEnabled(true);
					txtCodigo.setEnabled(true);
					lblNombre.setEnabled(true);
					txtNombre.setEnabled(true);
					lblPrecio.setEnabled(true);
					txtPrecio.setEnabled(true);
					lblStock.setEnabled(true);
					txtStock.setEnabled(true);
					lblDescripcin.setEnabled(true);
					txtDesc.setEnabled(true);
					btnRuta.setEnabled(true);
					lblCategoria.setEnabled(true);
					cbCategoria.setEnabled(true);
					lblOferta.setEnabled(true);
					cbOferta.setEnabled(true);
					lblPlataforma.setEnabled(true);
					cbPlataforma.setEnabled(true);
					lblTipo.setEnabled(true);
					cbTipo.setEnabled(true);
					lblUrl.setEnabled(true);
					txtUrl.setEnabled(true);

					lblTalla.setVisible(false);
					txtTalla.setVisible(false);
					lblColor.setVisible(false);
					cbColor.setVisible(false);
					lblPlataforma.setVisible(true);
					cbPlataforma.setVisible(true);
					txtUrl.setVisible(true);
					lblUrl.setVisible(true);
				}else if(cbPlataforma.getSelectedItem().equals("PS4") || cbPlataforma.getSelectedItem().equals("PS3") || cbPlataforma.getSelectedItem().equals("XBOX")) {
					Enabledtodo();
					lblCod.setEnabled(true);
					txtCodigo.setEnabled(true);
					lblNombre.setEnabled(true);
					txtNombre.setEnabled(true);
					lblPrecio.setEnabled(true);
					txtPrecio.setEnabled(true);
					lblStock.setEnabled(true);
					txtStock.setEnabled(true);
					lblDescripcin.setEnabled(true);
					txtDesc.setEnabled(true);
					btnRuta.setEnabled(true);
					lblCategoria.setEnabled(true);
					cbCategoria.setEnabled(true);
					lblOferta.setEnabled(true);
					cbOferta.setEnabled(true);
					lblPlataforma.setEnabled(true);
					cbPlataforma.setEnabled(true);
					lblTipo.setEnabled(true);
					cbTipo.setEnabled(true);


					lblTalla.setVisible(false);
					txtTalla.setVisible(false);
					lblColor.setVisible(false);
					cbColor.setVisible(false);
					lblPlataforma.setVisible(true);
					cbPlataforma.setVisible(true);
					txtUrl.setVisible(true);
					lblUrl.setVisible(true);
				}
			}
		});
		panelCentro.add(cbPlataforma);

		lblUrl = new JLabel("URL");
		lblUrl.setBounds(230, 640, 105, 20);
		lblUrl.setEnabled(false);
		lblUrl.setVisible(false);
		panelCentro.add(lblUrl);

		txtUrl = new JTextField();
		txtUrl.setBounds(320, 640, 145, 30);
		txtUrl.setEnabled(false);
		txtUrl.setVisible(false);
		panelCentro.add(txtUrl);

		lblColor = new JLabel("COLOR");
		lblColor.setBounds(230, 640, 63, 20);
		lblColor.setEnabled(false);
		panelCentro.add(lblColor);

		String color[] = {"Rojo", "Verde", "Azul", "Rosa", "Amarillo", "Marrón", "Morado","Naranja","Blanco","Gris","Negro", "Otro"};
		cbColor = new JComboBox(color);
		cbColor.setBounds(320, 640, 145, 20);
		cbColor.setEnabled(false);
		panelCentro.add(cbColor);

final DefaultComboBoxModel model = new DefaultComboBoxModel();
		
		cbTipo.addActionListener(new ActionListener() {
			private int si = -1;
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int in = cbTipo.getSelectedIndex();
				if(in == 7 && cbCategoria.getSelectedItem().equals("Fútbol")) {
					si = in;
					cbTipo.setEditable(true);
				}else if (in !=7 && cbCategoria.getSelectedItem().equals("Fútbol")) {
					cbTipo.setEditable(false);
				}else if(in ==6 && cbCategoria.getSelectedItem().equals("Baloncesto")) {
					si = in;
					cbTipo.setEditable(true);
				}else if(in !=6 && cbCategoria.getSelectedItem().equals("Baloncesto")) {
					cbTipo.setEditable(false);
				}else if(in == 7 && cbCategoria.getSelectedItem().equals("Pádel")) {
					si =in;
					cbTipo.setEditable(true);
				}else if(in != 7 && cbCategoria.getSelectedItem().equals("Pádel")) {
					cbTipo.setEditable(false);
				}else if(in == 5 && cbCategoria.getSelectedItem().equals("Ciclismo")) {
					si = in;
					cbTipo.setEditable(true);
				}else if (in != 5 && cbCategoria.getSelectedItem().equals("Ciclismo")) {
					cbTipo.setEditable(false);
				}else if (in == 5 && cbCategoria.getSelectedItem().equals("Videojuegos")) {
					si = in;
					cbTipo.setEditable(true);
				}else if( in != 5 && cbCategoria.getSelectedItem().equals("Videojuegos")) {
					cbTipo.setEditable(false);
				}
			}
		});
		
		cbMarca.addActionListener(new ActionListener() {
			private int si = -1;
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int in = cbMarca.getSelectedIndex();
				if(in == 14 && cbCategoria.getSelectedItem().equals("Fútbol")) {
					si = in;
					cbMarca.setEditable(true);
				}else if (in !=14 && cbCategoria.getSelectedItem().equals("Fútbol")) {
					cbMarca.setEditable(false);
				}else if(in ==9 && cbCategoria.getSelectedItem().equals("Baloncesto")) {
					si = in;
					cbMarca.setEditable(true);
				}else if(in !=9 && cbCategoria.getSelectedItem().equals("Baloncesto")) {
					cbMarca.setEditable(false);
				}else if(in == 8 && cbCategoria.getSelectedItem().equals("Ciclismo")) {
					si = in;
					cbMarca.setEditable(true);
				}else if (in != 8 && cbCategoria.getSelectedItem().equals("Ciclismo")) {
					cbMarca.setEditable(false);
				}
			}
		});
		
		cbEquipo.addActionListener(new ActionListener() {
			private int si = -1;
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int in = cbEquipo.getSelectedIndex();
				if(in == 6 && cbCategoria.getSelectedItem().equals("Fútbol")) {
					si = in;
					cbEquipo.setEditable(true);
				}else if (in !=6 && cbCategoria.getSelectedItem().equals("Fútbol")) {
					cbEquipo.setEditable(false);
				}else if(in ==7 && cbCategoria.getSelectedItem().equals("Baloncesto")) {
					si = in;
					cbEquipo.setEditable(true);
				}else if(in !=7 && cbCategoria.getSelectedItem().equals("Baloncesto")) {
					cbEquipo.setEditable(false);
				}else if(in == 8 && cbCategoria.getSelectedItem().equals("Pádel")) {
					si =in;
					cbEquipo.setEditable(true);
				}else if(in != 8 && cbCategoria.getSelectedItem().equals("Pádel")) {
					cbEquipo.setEditable(false);
				}else if(in == 16 && cbCategoria.getSelectedItem().equals("Ciclismo")) {
					si = in;
					cbEquipo.setEditable(true);
				}else if (in != 16 && cbCategoria.getSelectedItem().equals("Ciclismo")) {
					cbEquipo.setEditable(false);
				}
			}
		});
		
		cbColor.addActionListener(new ActionListener() {
			private int selectedIndex = -1;

			@Override
			public void actionPerformed(ActionEvent e) {
				int index = cbColor.getSelectedIndex();
				if(index == 11) {
					selectedIndex = index;
					cbColor.setEditable(true);
				}else if(index !=11) {
					cbColor.setEditable(false);
				}
				//Todo esto sobra 
//				else if("comboBoxEdited".equals(e.getActionCommand())) {
//					Object newValue = model.getSelectedItem();
//					model.removeElementAt(selectedIndex);
//					model.addElement(newValue);
//					cbColor.setSelectedItem(newValue);
//					selectedIndex = model.getIndexOf(newValue);
//				}
			}
		});
		cbColor.setSelectedIndex(0);
	}
	
	private void rellenaComboTipo(String selecCat) {
		// Se borran los valores previos
		if(cbTipo!=null)
			cbTipo.removeAllItems();
		if (selecCat.equals("Fútbol")) {
			cbTipo.removeAllItems();
			cbTipo.addItem("");
			cbTipo.addItem("Camisetas");
			cbTipo.addItem("Pantalones");
			cbTipo.addItem("Balones");
			cbTipo.addItem("Sudaderas");
			cbTipo.addItem("Guantes");
			cbTipo.addItem("Botas");
			cbTipo.addItem("Otro");
		} else if (selecCat.equals("Pádel")) {
			cbTipo.removeAllItems();
			cbTipo.addItem("");
			cbTipo.addItem("Camisetas");
			cbTipo.addItem("Protectores");
			cbTipo.addItem("Paleteros");
			cbTipo.addItem("Palas");
			cbTipo.addItem("Pelotas");
			cbTipo.addItem("Pantalones");
			cbTipo.addItem("Otro");
		} else if (selecCat.equals("Ciclismo")) {
			cbTipo.removeAllItems();
			cbTipo.addItem("");
			cbTipo.addItem("Bicicletas");
			cbTipo.addItem("Cascos");
			cbTipo.addItem("Maillots");
			cbTipo.addItem("Botellines");
			cbTipo.addItem("Otro");
		} else if(selecCat.equals("Baloncesto")) {
			cbTipo.removeAllItems();
			cbTipo.addItem("");
			cbTipo.addItem("Camisetas");
			cbTipo.addItem("Sudaderas");
			cbTipo.addItem("Pantalones");
			cbTipo.addItem("Playeras");
			cbTipo.addItem("Balones");
			cbTipo.addItem("Otro");
		} else if(selecCat.equals("Videojuegos")) {
			cbTipo.removeAllItems();
			cbTipo.addItem("");
			cbTipo.addItem("Acción");
			cbTipo.addItem("Infantil");
			cbTipo.addItem("Deportes");
			cbTipo.addItem("Estrategia");
			cbTipo.addItem("Otro");
		}
	} 


	private void habilitarCampos(String selecTipo) {
		if(selecTipo.equals("Balones") || selecTipo.equals("Botellines") || selecTipo.equals("Paleteros") 
				|| selecTipo.equals("Protectores") || selecTipo.equals("Pelotas")) {
			Enabledtodo();
			lblCod.setEnabled(true);
			txtCodigo.setEnabled(true);
			lblNombre.setEnabled(true);
			txtNombre.setEnabled(true);
			lblPrecio.setEnabled(true);
			txtPrecio.setEnabled(true);
			lblMarca.setEnabled(true);
			cbMarca.setEnabled(true);
			lblColor.setEnabled(true);
			cbColor.setEnabled(true);
			lblDescripcin.setEnabled(true);
			txtDesc.setEnabled(true);
			lblStock.setEnabled(true);
			txtStock.setEnabled(true);
			lblOferta.setEnabled(true);
			cbOferta.setEnabled(true);
		}
	}

	private void rellenaComboMarca(String selecCat) {
		cbMarca.removeAllItems();
		if(selecCat.equals("Fútbol")) {
			cbMarca.removeAllItems();
			cbMarca.addItem("");
			cbMarca.addItem("Nike");
			cbMarca.addItem("Adidas");
			cbMarca.addItem("Air Jordan");
			cbMarca.addItem("New Balance");
			cbMarca.addItem("Puma");
			cbMarca.addItem("Otro");
			lblMarca.setEnabled(true);
			cbMarca.setEnabled(true);
		} else if( selecCat.equals("Baloncesto")) {
			cbMarca.removeAllItems();
			cbMarca.addItem("");
			cbMarca.addItem("Molten");
			cbMarca.addItem("Spalding");
			cbMarca.addItem("Nike");
			cbMarca.addItem("Adidas");
			cbMarca.addItem("Air Jordan");
			cbMarca.addItem("Under Armour");
			cbMarca.addItem("Otro");
			lblMarca.setEnabled(true);
			cbMarca.setEnabled(true);
		} else if(selecCat.equals("Pádel")) {
			cbMarca.removeAllItems();
			cbMarca.addItem("");
			cbMarca.addItem("Bullpadel");
			cbMarca.addItem("Artengo");
			cbMarca.addItem("Head");
			cbMarca.addItem("Wilson");
			cbMarca.addItem("Babolat");
			cbMarca.addItem("Dundolp");
			cbMarca.addItem("Varlion");
			cbMarca.addItem("Otro");
			lblMarca.setEnabled(true);
			cbMarca.setEnabled(true);
		} else if(selecCat.equals("Ciclismo")) {
			cbMarca.removeAllItems();
			cbMarca.addItem("");
			cbMarca.addItem("Cannondale");
			cbMarca.addItem("Trek");
			cbMarca.addItem("Orbea");
			cbMarca.addItem("Specialized");
			cbMarca.addItem("Spiuk");
			cbMarca.addItem("Limar");
			cbMarca.addItem("Pinarello");
			cbMarca.addItem("BH");
			cbMarca.addItem("Canyon");
			cbMarca.addItem("Isostar");
			cbMarca.addItem("Camelback");
			cbMarca.addItem("Catlike");
			cbMarca.addItem("Roger");
			cbMarca.addItem("Scott");
			cbMarca.addItem("UCI");
			cbMarca.addItem("Otro");
			lblMarca.setEnabled(true);
			cbMarca.setEnabled(true);
		}
	}

	private void rellenaComboEquipo(String selecCat) {
		cbEquipo.removeAllItems();
		if(selecCat.equals("Fútbol")) {
			cbEquipo.addItem("");
			cbEquipo.addItem("PSG");
			cbEquipo.addItem("Juventus");
			cbEquipo.addItem("Manchester City");
			cbEquipo.addItem("Leicester City");
			cbEquipo.addItem("Athletic Club");
			cbEquipo.addItem("Bayern Munich");
			cbEquipo.addItem("Chelsea");
			cbEquipo.addItem("Inter de Milán");
			cbEquipo.addItem("Los Angeles Galaxy");
			cbEquipo.addItem("Liverpool");
			cbEquipo.addItem("Manchester United");
			cbEquipo.addItem("Arsenal");
			cbEquipo.addItem("Borussia Dortmund");
			cbEquipo.addItem("Otro");
			lblEquipo.setEnabled(true);
			cbEquipo.setEnabled(true);
		} else if(selecCat.equals("Baloncesto")) {
			cbEquipo.addItem("");
			cbEquipo.addItem("San Antonio Spurs");
			cbEquipo.addItem("Houston Rockets");
			cbEquipo.addItem("Boston Celtics");
			cbEquipo.addItem("Los Angeles Lakers");
			cbEquipo.addItem("Cleveland Cavaliers");
			cbEquipo.addItem("Chicago Bulls");
			cbEquipo.addItem("Golden State Warriors");
			cbEquipo.addItem("New Orleands Pelicans");
			cbEquipo.addItem("Otro");
			lblEquipo.setEnabled(true);
			cbEquipo.setEnabled(true);
		} else if(selecCat.equals("Ciclismo")) {
			cbEquipo.addItem("");
			cbEquipo.addItem("Poza Bike Xtrem");
			cbEquipo.addItem("Burgos BH");
			cbEquipo.addItem("Astana");
			cbEquipo.addItem("Euskadi Murias");
			cbEquipo.addItem("Movistar");
			cbEquipo.addItem("Euskaltel Euskadi");
			cbEquipo.addItem("Tinkoff Saxo");
			cbEquipo.addItem("Otro");
			lblEquipo.setEnabled(true);
			cbEquipo.setEnabled(true);
		}
	}

	
}
