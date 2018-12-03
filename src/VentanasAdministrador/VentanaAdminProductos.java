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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import BaseDeDatos.BD;

import java.awt.Font;
import java.awt.Image;

public class VentanaAdminProductos extends JFrame {

	private JPanel contentPane;
	private JTextField txtRuta;
	private JFrame ventanaAnterior;
	private JTextField txtDesc;
	private JTextField txtStock;
	private JLabel lblCod, lblTipo, lblNombre, lblMarca, lblPrecio, lblEquipo, lblStock, lblPeso, lblTabla,lblDescripcin, 
			lblOferta, lblCategoria, lblTalla, lblColor;
	private JComboBox cbCategoria, cbMarca,cbEquipo,cbTipo, cbOferta, cbColor;
	private JTextField txtNombre, txtCodigo, txtPrecio, txtPeso, txtTalla;
	JFrame v = this;



	/**
	 * Create the frame.
	 */
	public VentanaAdminProductos(JFrame va) {
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
		panelCentro.add(btnBorrarArtculo);

		JLabel lblEliminar = new JLabel("Eliminar Artículo");
		lblEliminar.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		lblEliminar.setBounds(10, 600, 145, 16);
		panelCentro.add(lblEliminar);


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

		String[] tipo = {"Camisetas", "Pantalones", "Balones", "Botas", "Sudaderas", "Guantes", "Playeras", "Cascos", "Botellines", "Maillots",
				"Bicicletas", "Protectores", "Paleteros", "Palas", "Pelotas"};
		cbTipo = new JComboBox(tipo);
		cbTipo.setBounds(660, 10, 145, 25);
		cbTipo.setEnabled(false);
		panelCentro.add(cbTipo);
		cbTipo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(cbTipo.getSelectedItem().equals("Camisetas")) {
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
					lblMarca.setEnabled(true);
					cbMarca.setEnabled(true);
					
					
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

		String[] marca = {"Adidas", "Nike", "Air Jordan", "Spiuk", "Specialized", "Cannondale", "Canyon", "Roger", "New Balances", "Puma",
				"Head", "Artengo", "Bulpadel", "Vairlon", "Orbea"};
		cbMarca = new JComboBox(marca);
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

		String[] equipo = {"Athletic Club", "PSG", "Liverpool", "Chelsea", "Arsenal", "Poza Bike Xtrem", "Euskaltel Euskadi", "Euskadi Murias", "Juventus",
				"Inter de Milán", "Astana", "Movistar", "Tinkoff Saxo", "Burgos BH", "Boston Celtics"};
		cbEquipo = new JComboBox(equipo);
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
		
		
		JButton btnRuta = new JButton("Ruta");
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
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						ImageIcon im1 = new ImageIcon(im.getScaledInstance(x, y, Image.SCALE_SMOOTH));
					
						btnimSelec.setIcon(im1);
				}
			}

		});
		panelCentro.add(btnRuta);


		lblCategoria = new JLabel("CATEGORIA");
		lblCategoria.setBounds(230, 430, 100, 20);
		panelCentro.add(lblCategoria);

		String[] cat = {"Fútbol", "Pádel", "Baloncesto", "Ciclismo", "Videojuegos"};
		cbCategoria = new JComboBox(cat);
		cbCategoria.setBounds(320, 430, 145, 25);
		panelCentro.add(cbCategoria);
		cbCategoria.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub	
				cbTipo.setEnabled(true);
				lblTipo.setEnabled(true);
			}
		});


		lblOferta = new JLabel("OFERTA");
		lblOferta.setBounds(230, 500, 63, 20);
		lblOferta.setEnabled(false);
		panelCentro.add(lblOferta);

		String [] of = {"SI", "NO"};
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



		lblColor = new JLabel("COLOR");
		lblColor.setBounds(230, 640, 63, 20);
		lblColor.setEnabled(false);
		panelCentro.add(lblColor);

		String color[] = {"Rojo", "Verde", "Azul", "Rosa", "Amarillo", "Marrón", "Morado","Naranja","Blanco","Gris","Negro"};
		cbColor = new JComboBox(color);
		cbColor.setBounds(320, 640, 145, 20);
		cbColor.setEnabled(false);
		panelCentro.add(cbColor);

	}
	
}
