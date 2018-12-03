package Ventanas;

import java.awt.BorderLayout;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.xml.validation.SchemaFactoryConfigurationError;

import org.omg.Messaging.SyncScopeHelper;

import BaseDeDatos.BD;

import javax.swing.JButton;


public class VentanaMenu extends JFrame {

	private JPanel contentPane;
	JMenuBar barra; //Barra menu
	JMenu menuElegirOpcion, menuDeportes, menuVideojuegos, menuNuestrasTiendas, menuInformacion, menuOpciones, menuAyuda/*, menuPatrocinadores*/, menuMostrarProductos;
	JMenuItem menuPatrocinadores, itemFutbol, itemPadel, itemBaloncesto, itemCiclismo, itemBilbao, itemMadrid, itemBarcelona, itemPozaDeLaSal, 
	itemSobreMi, itemJuegosInfantiles, itemJuegosDeportivos, itemJuegosAccion, itemJuegosEstrategia, itemTodos,itemProductos;
	JFrame v = this;
	JLabel JLabel1;
	private JLabel lblNewLabel;
	public static String path;
	private JFrame ventanaAnterior;
	JLabel lblFotos;
	private String menu [];
	private int indexFotos = 0;
	private JLabel lblDescubreLasMejores;
	private JLabel label;


	/**
	 * Create the frame.
	 */
	public VentanaMenu(JFrame va) {
				
		//JFrame v = this;
		ventanaAnterior=va;
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null); //Para centrar la ventana
		setContentPane(contentPane);
		contentPane.setLayout(null);



		String [] g = BD.obtenerFotos("gif");
		Random r;
		r= new Random();
		int i3 = r.nextInt(19);
		JLabel lblFoto1 = new JLabel("");
		lblFoto1.setBounds(33,352,126,120);
		contentPane.add(lblFoto1);
		ImageIcon image = new ImageIcon(g[i3]);
		int width = 100;
		int height = 100;
		image.setImage(image.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		lblFoto1.setIcon(image);


		String [] g2 = BD.obtenerFotos("gif");
		Random r1;
		r1= new Random(System.currentTimeMillis());
		int i1 = r1.nextInt(19);
		JLabel lblFoto2 = new JLabel("");
		lblFoto2.setBounds(542,26,126,120);
		contentPane.add(lblFoto2);
		ImageIcon image1 = new ImageIcon(g2[i1]);
		int width1=100;
		int height1=100;
		image1.setImage(image1.getImage().getScaledInstance(width1, height1, Image.SCALE_DEFAULT));
		lblFoto2.setIcon(image1);


		String ofertas[] = BD.obtenerFotos("ofertas");
		//		for(int i=0;i<ofertas.length;i++)
		//			System.out.println(ofertas[i]);
		Random r2 = new Random(System.currentTimeMillis());
		int i2 = r2.nextInt(5);
		JLabel lblOfertas = new JLabel("");
		lblOfertas.setBounds(33, 261, 635, 202);
		contentPane.add(lblOfertas);
		ImageIcon ioferta = new ImageIcon(ofertas[i2]);
		int x2= 635;
		int y2= 202;
		ioferta.setImage(ioferta.getImage().getScaledInstance(x2, y2, Image.SCALE_SMOOTH));
		lblOfertas.setIcon(ioferta);



		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//	log.info("El usuario a pulsado el botón de volver");
				v.dispose();
				VentanaLogin vl = new VentanaLogin();
				vl.setVisible(true);
			}
		});
		btnVolver.setBounds(338, 471, 117, 29);
		contentPane.add(btnVolver);


		JButton btnFotoIzq = new JButton("");
		ImageIcon im1 = new ImageIcon("Imagenes/flechaIzqd.png");
		int w1 =48;
		int y1=48;
		im1.setImage(im1.getImage().getScaledInstance(w1, y1, Image.SCALE_SMOOTH));
		btnFotoIzq.setOpaque(false);
		btnFotoIzq.setContentAreaFilled(false);
		btnFotoIzq.setBorderPainted(false);
		btnFotoIzq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//log.info("El usuario ha pulsado el botón de pasar la foto a la izquierda");
				if(indexFotos - 1 >= 0) {
					indexFotos--;
					ImageIcon imenu = new ImageIcon(menu[indexFotos]);
					imenu.setImage(imenu.getImage().getScaledInstance(lblFotos.getWidth(), lblFotos.getHeight(), Image.SCALE_SMOOTH));
					lblFotos.setIcon(imenu);
				}
			}
		});
		btnFotoIzq.setBounds(6, 98, 48, 48);
		contentPane.add(btnFotoIzq);
		btnFotoIzq.setIcon(im1);

		JButton btnFotoDch = new JButton("");
		ImageIcon im = new ImageIcon("Imagenes/flechadrch.png");
		int w = 48;
		int y = 48;
		im.setImage(im.getImage().getScaledInstance(w, y, Image.SCALE_SMOOTH));
		btnFotoDch.setOpaque(false);
		btnFotoDch.setContentAreaFilled(false);
		btnFotoDch.setBorderPainted(false);
		btnFotoDch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				//log.info("EL usuario ha pulsado el botón de pasar la foto a la derecha");
				if(indexFotos + 1 < 5) {
					indexFotos++;
					ImageIcon imenu = new ImageIcon(menu[indexFotos]);
					imenu.setImage(imenu.getImage().getScaledInstance(lblFotos.getWidth(), lblFotos.getHeight(), Image.SCALE_SMOOTH));
					lblFotos.setIcon(imenu);
				}
			}

		});
		btnFotoDch.setBounds(650, 98, 48, 48);
		contentPane.add(btnFotoDch);
		btnFotoDch.setIcon(im);

		menu  = BD.obtenerFotos("menu");
		lblFotos = new JLabel("");
		lblFotos.setBounds(33, 25, 635, 202);
		contentPane.add(lblFotos);
		ImageIcon imenu = new ImageIcon(menu[indexFotos]);
		imenu.setImage(imenu.getImage().getScaledInstance(lblFotos.getWidth(), lblFotos.getHeight(), Image.SCALE_SMOOTH));
		lblFotos.setIcon(imenu);
		
		lblDescubreLasMejores = new JLabel("DESCUBRE LAS MEJORES OFERTAS!!!");
		lblDescubreLasMejores.setBounds(277, 239, 234, 16);
		contentPane.add(lblDescubreLasMejores);
		
		label = new JLabel("__________________________________________");
		label.setBounds(232, 242, 335, 16);
		contentPane.add(label);






		crearMenu();



		/**
		 * Hilo para mover el gif de izquierda a derecha
		 */
		new Thread() {
			public void run() {
				int x = 0;
				int y = lblFoto1.getLocation().y;
				while(true) {
					x++;
					if(x > getWidth()) {
						x=0;
					}
					lblFoto1.setLocation(x,y);
					try {
						sleep(10);
					} catch(Exception e) {

					}
				}
			}
		}.start();

		/**
		 * Hilo para mover el gif de arriba a abajo
		 */
		new Thread() {
			public void run() {
				int x = lblFoto2.getLocation().x;
				int y = 0;
				while(true) {
					y++;
					if(y > getHeight()) {
						y=0;
					}
					lblFoto2.setLocation(x,y);
					try {
						sleep(10);
					} catch(Exception e) {

					}
				}
			}
		}.start();

	}


	private void crearMenu() {
		// TODO Auto-generated method stub
		barra = new JMenuBar();
		menuElegirOpcion= new JMenu("Elegir Opción");
		menuDeportes = new JMenu("Deportes");
		menuVideojuegos = new JMenu("Videojuegos");
		menuNuestrasTiendas = new JMenu("Nuestras Tiendas");
		menuInformacion = new JMenu("Información");
		menuOpciones = new JMenu("Opciones");
		menuPatrocinadores = new JMenuItem("Patrocinadores");
		menuMostrarProductos = new JMenu("Mostrar productos");
		menuAyuda = new JMenu("Ayuda");

		itemJuegosInfantiles = new JMenuItem("Videojuegos infantiles");
		itemJuegosInfantiles.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				v.dispose();
				VVideojuegosInfantiles ii = new VVideojuegosInfantiles();
				ii.setVisible(true);
				//log.info("El usuario ha elegido la opción de ver los videojuegos infantiles");
			}
		});
		itemJuegosDeportivos = new JMenuItem("Videojuegos de deportes");
		itemJuegosDeportivos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				v.dispose();
				VVideojuegosDeportes id = new VVideojuegosDeportes();
				id.setVisible(true);
				//log.info("El usuario ha elegido la opción de ver los videojuegos de deportes");
			}
		});
		itemJuegosEstrategia = new JMenuItem("Videojuegos de estrategia");
		itemJuegosEstrategia.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				v.dispose();
				VVideojuegosEstrategia ie = new VVideojuegosEstrategia();
				ie.setVisible(true);
				//log.info("El usuario ha decidido ver los videojuegos de la categoria estrategia");
			}
		});
		itemJuegosAccion = new JMenuItem("Videojuegos de acción");
		itemJuegosAccion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				v.dispose();
		//		VVideojuegosAccion ia = new VVideojuegosAccion();
		//		ia.setVisible(true);
				//log.info("El usuario ha tomado la decisión de ver los videojuegos de acción");
			}
		});
		itemFutbol = new JMenuItem("Fútbol");
		itemPadel = new JMenuItem("Pádel");
		itemPadel.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				v.dispose();
				VentanaPadel vp = new VentanaPadel();
				vp.setVisible(true);
				//log.info("El usuario ha decidido ver los productos relacionados con el Pádel");
			}

		});
		itemBaloncesto = new JMenuItem("Baloncesto");
		itemBaloncesto.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			//	log.info("El usuario ha decidido ver los productos correspondientes al deporte Baloncesto");
				VentanaBaloncesto vb = new VentanaBaloncesto();
				vb.setVisible(true);
				v.dispose();
			}
		});
		itemCiclismo = new JMenuItem("Ciclismo");
		itemCiclismo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				v.dispose();
				VentanaCiclismo vc = new VentanaCiclismo();
				vc.setVisible(true);
			//	log.info("El usuario ha tomado la decisión de ver los productos de ciclismo");
			}
		});

		itemBilbao = new JMenuItem("Bilbao");
		itemBilbao.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				v.dispose();
				String texto = "Como bien puedes apreciar en la foto de la derecha, nuestra tienda se situa en la calle Doctor Areilza Nº4. Es una de las tiendas en la que más vendemos y fue la primera. \n" + 
						"Bilbao o Bilbo en euskera, es un municipio situado al norte de España.Posee una importante infraestructura de transportes que conecta con las principales capitales de \n" + 
						"Europa, por tierra, mar y aire. La capital vizcaína es el corazón de una metrópoli de más de 1.000.000 habitantes. Fundada en 1300, es eje principal del desarrollo \n" + 
						"económico-social y factor base de la modernización de la provincia.\n" + 
						"En Bilbao existen muchos equipos de fúbol pero el más conocido es el Athletic Club. Fue fundado en 1989 y junto con Real Madrid y Barcelona son los únicos equipos que \n" + 
						"no han bajado a 2ª división. Es por esto que es una de las tiendas que más vende, ya que aquí el 95% de la población es del athletic y compran mucho material del\n"+
						"Athletic ya que son de gran calidad y a un precio muy económico";
				VentanaTiendas vl = new VentanaTiendas("Bilbao","Imagenes/locBilbao.png","Imagenes/FotoBilbao.jpg",texto);
				vl.setVisible(true);
				//log.info("El usuario ha decidido buscar información sobre la tienda de Bilbao");
			}

		});
		itemMadrid = new JMenuItem("Madrid");
		itemMadrid.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				v.dispose();
				String text = "Madrid es un municipio y ciudad de España. La localidad, con categoría histórica de villa,9​ es la capital del Estado10​ y de la Comunidad de Madrid. Dentro del término  \n" + 
						"municipal de Madrid, el más poblado de España, viven 3 182 981 personas empadronadas, según el INE de 2017. El área metropolitana de Madrid tiene una población de \n" + 
						"6 543 031 habitantes,11​ por lo que es la tercera o cuarta área metropolitana de la Unión Europea, según la fuente, por detrás de las de París y Londres, y en \n" + 
						"algunas fuentes detrás también de la Región del Ruhr, así como la tercera ciudad más poblada de la Unión Europea, por detrás de Berlín y Londres.\n"
						+ "La ciudad cuenta con un PIB nominal de 227 411 millones USD y un PIB per cápita nominal de 34 425 USD, lo que representa un PIB PPA per cápita de 40 720 USD,16​ siendo la\n"
						+ " 1.ª área metropolitana española en actividad económica; y la décima de Europa. Madrid es también la ciudad española con más pernoctaciones hoteleras" ;
				
				VentanaTiendas vl = new VentanaTiendas("Madrid","Imagenes/locMadrid.png","Imagenes/fotoMadrid.jpg",text);
				vl.setVisible(true);
			//	log.info("El usuario quiere tener más información sobre Madrid y su tienda");
			}

		});
		itemBarcelona = new JMenuItem("Barcelona");
		itemBarcelona.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				v.dispose();
				String texto = "Barcelona es una ciudad española, capital de la comunidad autónoma de Cataluña, de la comarca del Barcelonés y de la provincia homónima. \n" + 
						"Con una población de 1 620 809 habitantes en 2017,6​ es la segunda ciudad más poblada de España después de Madrid, y la undécima de la Unión Europea. \n" + 
						"El área metropolitana de Barcelona, incluida en el ámbito metropolitano de Barcelona, cuenta con 5 029 181 habitantes (2011), siendo así la \n" + 
						"sexta ciudad de mayor población de la Unión Europea.\n"
						+ "Se ubica a orillas del mar Mediterráneo, a unos 120 km al sur de la cadena montañosa de los Pirineos y de la frontera con Francia, en un pequeño \n"
						+ "llano litoral limitado por el mar al este, la sierra de Collserola al oeste, el río Llobregat al sur y el río Besós al norte. Por haber sido capital del condado \n"
						+ "de Barcelona, se suele aludir a ella con la denominación antonomástica de Ciudad Condal." ;
				VentanaTiendas vb = new VentanaTiendas("Barcelona","Imagenes/fotoBarcelona.jpg","Imagenes/locBarcelona.png",texto);
				vb.setVisible(true);
				//log.info("El usuario ha seleccionado la opción de nuestra tienda de Barcelona");
			}

		});
		itemPozaDeLaSal = new JMenuItem("Poza de la Sal");
		itemPozaDeLaSal.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				v.dispose();
				String text = "Poza de la Sal es una villa situada a 43 km al norte de Burgos capital con unos 338 habitantes más o menos, ya que cada año va disminuyendo. Junto a los municipios de \n" + 
						"Oña y Frías conforma la mancomunidad de Raices de Castilla. La localidad se encuentra enriscada en la ladera de una sierra, fortificada y protegida por el castillo de los \n" + 
						"Rojas y con vistas sobre las llanuras de la comarca de La Bureba. La plaza vieja tiene un espolón el cuál es denominado el balcón de la bureba ya que desde hay se puede \n" + 
						"ver toda la bureba. Este precioso pueblo fue declarado de conjunto Histórico-Artístico en 1982.\n" + 
						"Decidimos abrir una tienda aquí ya que siempre que tengo un hueco me gusta escaparme al pueblo de mis abuelos en el que tan buenos momentos he pasado y porque \n" + 
						"paso practicamente todas las vacaciones en él. De esta manera cada vez que me fuera al pueblo podría estar pendiente de que todo estaría funcionando correctamente \n"+
						"por no hablar de la cantidad de camisetas, videojuegos, bicicletas... que se venden a lo largo del verano";
				VentanaTiendas vp = new VentanaTiendas("Poza de la Sal","Imagenes/locPoza.png","Imagenes/FotoPoza.png", text);
				vp.setVisible(true);
			//	log.info("El usuario quiere saber información sobre Poza de la Sal");
			}

		});
		itemSobreMi = new JMenuItem("Sobre Mi");
		itemSobreMi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				v.dispose();
				VentanaSobreMi vs = new VentanaSobreMi();
				vs.setVisible(true);
			//	log.info("El usuario ha elegido la opción sobre mi para saber algo sobre el autor de la aplicación");
			}

		});

		itemTodos = new JMenuItem("Todos");
		itemTodos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				v.dispose();
				VentanaPatrocinadores vp = new VentanaPatrocinadores(v);
				vp.setVisible(true);
			//	log.info("El usuario quiere ver todos los patrocinadores que colaboran con la tienda");
			}

		});

		itemProductos = new JMenuItem("Productos");
		itemProductos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				v.dispose();
				Tabla t = new Tabla();
				t.setVisible(true);
			//	log.info("El usuario quiere ver todos los productos disponibles en la tienda");
			}
		});


		menuElegirOpcion.add(menuDeportes);
		menuElegirOpcion.addSeparator();
		//itemDeportes.add(itemFutbol);
		menuDeportes.add(itemFutbol);
		itemFutbol.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				v.dispose();
				VentanaFutbol vf = new VentanaFutbol();
				vf.setVisible(true);
			//	log.info("El usuario ha eleigo la opción para ver todos los productos relacionados con el Fútbol");

			}
		});
		menuDeportes.addSeparator();
		menuDeportes.add(itemPadel);
		menuDeportes.addSeparator();
		menuDeportes.add(itemBaloncesto);
		menuDeportes.addSeparator();
		menuDeportes.add(itemCiclismo);
		menuElegirOpcion.add(menuVideojuegos);
		menuVideojuegos.add(itemJuegosAccion);
		menuVideojuegos.addSeparator();
		menuVideojuegos.add(itemJuegosInfantiles);
		menuVideojuegos.addSeparator();
		menuVideojuegos.add(itemJuegosEstrategia);
		menuVideojuegos.addSeparator();
		menuVideojuegos.add(itemJuegosDeportivos);


		menuNuestrasTiendas.add(itemBilbao);
		menuNuestrasTiendas.addSeparator();
		menuNuestrasTiendas.add(itemMadrid);
		
		menuNuestrasTiendas.addSeparator();
		menuNuestrasTiendas.add(itemBarcelona);
		
		menuNuestrasTiendas.addSeparator();
		menuNuestrasTiendas.add(itemPozaDeLaSal);

		menuInformacion.add(itemSobreMi);

		menuMostrarProductos.add(itemProductos);

		//menuPatrocinadores.add(itemTodos);
		menuPatrocinadores.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				v.dispose();
				VentanaPatrocinadores vp = new VentanaPatrocinadores(v);
				vp.setVisible(true);
			}
		});

		barra.add(menuElegirOpcion);
		barra.add(menuNuestrasTiendas);
		barra.add(menuMostrarProductos);
		barra.add(menuInformacion);
		barra.add(menuPatrocinadores);
		barra.add(menuAyuda);
		setJMenuBar(barra);



	}
}
