package Ventanas;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.table.TableStringConverter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Tabla extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel tableModel = null;
	private String ruta=null;
	JButton btnFoto = null;
	String[] opciones;
	private JFrame v=this;
	JTable table ;
	TableRowSorter<TableModel> modeloOrdenado;
	private Logger log;


	private class MiRender extends DefaultTableCellRenderer{
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
			super.getTableCellRendererComponent (table, value, isSelected, hasFocus, row, column);
			this.setBackground(Color.LIGHT_GRAY);
			this.setFont(new Font(Font.SERIF, Font.PLAIN, 12));
			if ( isSelected ){
				//this.setOpaque(true);
				this.setBackground(Color.YELLOW);
				this.setForeground(Color.black);
			}

			if ( hasFocus ){
				//this.setOpaque(true);
				this.setBackground(Color.cyan);
				this.setForeground(Color.black);
			}

			if (column==7){
				boolean valor = value.equals("True");
				if (valor)
					this.setBackground(Color.GREEN);
			}
			if(column==3 ) {
				int valor = ((Integer)value).intValue();
				if(valor<20 && (valor!=0)) {
					this.setBackground(Color.orange);
				}
			}
//			}if(column==3) {
//				String nom = (String)table.getValueAt(row, 14);
//				int valor = ((Integer)value).intValue();
//				if((valor == 0 && !nom.equals("Minijuegos"))) {
//					this.setBackground(Color.RED);
//				}
//			}
			return this;
		}
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("unlikely-arg-type")
	public Tabla(String nick) {
		
		//Creamos el manejador de fichero de nuevo
		Handler handler = null;
		try {
			handler = new FileHandler("Ventana tabla.log");
		} catch(SecurityException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
		
		//En este caso no lo vamos a guardar en formato XML como antes
		//sino que usaremos un formato simple
		SimpleFormatter sf = new SimpleFormatter();
		handler.setFormatter(sf);
		
		//Creamos el log de nuevo y le asociamos el manejador de fichero previamente creado
		Logger log = Logger.getLogger("miLog2");
		log.addHandler(handler);
		
		setExtendedState(MAXIMIZED_BOTH);

		JLabel lblTxt = new JLabel("TABLA CON TODOS LOS PRODUCTOS DE LA TIENDA");
		lblTxt.setBounds(450, 3, 500,20);
		getContentPane().add(lblTxt);

		String nombresColumnas[] = {"CÓDIGO","NOMBRE","PRECIO","STOCK", "DESCRIPCIÓN","RUTA","CATEGORÍA", "OFERTA", "TALLA",
				"COLOR", "TIPO", "MARCA", "EQUIPO", "PESO", "PLATAFORMA"};

		tableModel = new DefaultTableModel() {

			//			@Override
			//			public Class getColumnClass(int columna) {
			//				if (columna == 2 || columna ==3 || columna ==13)
			//					return Integer.class; //Le dice al modelo cuales son las columnas de tipo integer
			//				//				else if(columna == 7)
			//				//					return Boolean.class;
			//				return String.class; //Si no, es String
			//			}

			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				//todas las celdas falso
				return false;
			}
		};

		// Instanciamos el TableRowSorter y lo añadimos al JTable
		modeloOrdenado = new TableRowSorter<TableModel>(tableModel);


		Object[][] a = VentanaLogin.bd.obtenerTablaProductos();
		table = new JTable(tableModel);
		table.setRowSorter(modeloOrdenado);
		//tc.setMinWidth(25);
		//tc.setMaxWidth(25);
		MiRender rr = new MiRender();
		table.setDefaultRenderer(Object.class,rr);



		JLabel lblMarca = new JLabel("Filtrar por marca:");
		lblMarca.setBounds(55,500,200,20);
		getContentPane().add(lblMarca);

		String []opcMarca = {"Todos los productos", "Cannondale", "Trek", "Orbea", "Specialized", "Spiuk", "Limar", "Tacx" ,"Catlike", "Roger", "UCI", "Argon", "Pinarello", "BH", "Canyon",   
				"Molten","Spalding", "Nike", "Adidas","Air Jordan", "Under Armour", "Bullpadel", "Artengo", "Head", "Wilson", "Babolat", "Dunlop", "Varlion", "New Balance", "Puma"};

		JComboBox cbmar = new JComboBox(opcMarca);
		cbmar.setBounds(200,500,200,20);
		getContentPane().add(cbmar);
		cbmar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				log.info("El usuario ha filtrado los productos en función de la marca");
				String itemSelec = (String)cbmar.getSelectedItem();
				if("Todos los productos".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Producto", 5));
				}else if ("Cannondale".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Cannondale", 11));
				} else if ("Trek".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Trek", 11));
				} else if ("Orbea".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Orbea", 11));
				} else if ("Specialized".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Specialized", 11));
				} else if ("Spiuk".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Spiuk", 11));
				} else if ("Limar".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Limar", 11));
				} else if ("Tacx".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Tacx", 11));
				} else if("Catlike".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Catlike", 11));
				} else if("Roger".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Roger", 11));
				}else if("UCI".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("UCI", 11));
				}else if("Argon".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Argon", 11));
				}else if("Pinarello".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Pinarello", 11));
				}else if("BH".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("BH", 11));
				}else if("Canyon".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Canyon", 11));
				}else if("Molten".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Molten", 11));
				}else if("Spalding".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Spalding", 11));
				}else if("Nike".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Nike", 11));
				}else if("Adidas".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Adidas", 11));
				}else if("Air Jordan".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Air Jordan", 11));
				}else if("Under Armour".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Under", 11));
				}else if("Bullpadel".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Bullpadel", 11));
				}else if("Artengo".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Artengo", 11));
				}else if("Head".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Head", 11));
				}else if("Wilson".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Wilson", 11));
				}else if("Babolat".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Babolat", 11));
				}else if("Dunlop".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Dunlop", 11));
				}else if("Varlion".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Varlion", 11));
				}else if("New Balance".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("New", 11));
				}else if("Puma".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Puma", 11));
				}	
			}
		});

		JLabel lblOferta = new JLabel("Filtrar por oferta:");
		lblOferta.setBounds(905, 500, 200, 20);
		getContentPane().add(lblOferta);

		String []opcOferta= {"Todos los productos", "True", "False"};

		JComboBox cof = new JComboBox(opcOferta);
		cof.setBounds(1045,500,200,20);
		getContentPane().add(cof);
		cof.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				log.info("El usuario ha filtrado los productos en función si están en oferta o no");
				String itemSelec = (String)cof.getSelectedItem();
				if("Todos los productos".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Producto", 5));
				} else if("True".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("True", 7));
				} else if("False".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("False", 7));
				}
			}
		});


		JLabel lblEquipos= new JLabel("Filtrar por equipo:");
		lblEquipos.setBounds(55,440,200,20);
		getContentPane().add(lblEquipos);

		String []opcEquipos= {"Todos los productos","Tinkoff Saxo", "Euskaltel Euskadi", "Euskadi Murias", "Astana", "Movistar", "Poza Bike Xtrem", "Sky", "Burgos BH", "Spurs", "Houston Rockets",
				"Celtics", "Cleveland Cavaliers", "Los Angeles Lakers", "Chicago Bulls", "Golden State Warriors", "New Orleands Pelicans", "PSG", "Juventus", "Manchester City", "Leicester City", 
				"Athletic Club", "Bayern Munich", "Chelsea", "Inter de Milán", "Liverpool", "Manchester United", "Arsenal", "Borussia Dortmund"};

		JComboBox cbeq = new JComboBox(opcEquipos);
		cbeq.setBounds(200,440,200,20);
		getContentPane().add(cbeq);
		cbeq.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				log.info("El usuario ha filtrado los productos en función los diferentes equipos disponibles");
				String itemSelec = (String)cbeq.getSelectedItem();
				if("Todos los productos".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Producto", 5));
				}else if ("Euskaltel Euskadi".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Euskaltel ", 12));
				} else if ("Euskadi Murias".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Euskadi ", 12));
				} else if ("Astana".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Astana", 12));
				} else if ("Movistar".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Movistar", 12));
				} else if ("Poza Bike Xtrem".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Poza ", 12));
				} else if ("Sky".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Sky", 12));
				} else if ("Burgos BH".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Burgos ", 12));
				} else if("Spurs".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Spurs", 12));
				} else if("Houston Rockets".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Houston ", 12));
				}else if("Celtics".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Celtics", 12));
				}else if("Cleveland Cavaliers".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Cleveland ", 12));
				}else if("Los Angeles Lakers".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Los Angeles Lakers", 12));
				}else if("Chicago Bulls".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Chicago ", 12));
				}else if("Golden State Warriors".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Warriors", 12));
				}else if("New Orleans Pelicans".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("New Orleans Pelicans", 12));
				} else if ("Tinkoff Saxo".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Tinkoff ", 12));
				} else if("PSG".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("PSG", 12));
				} else if("Juventus".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Juventus ", 12));
				}else if("Manchester City".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Manchester City", 12));
				}else if("Leicester City".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Leicester City ", 12));
				}else if("Athletic Club".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Athletic Club", 12));
				}else if("Bayern Munich".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Bayern ", 12));
				}else if("Chelsea".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Chelsea", 12));
				}else if("Inter de Milán".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Inter", 12));
				} else if ("Liverpool".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Liverpool ", 12));
				} else if("Manchester United".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Manchester United", 12));
				} else if("Arsenal".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Arsenal ", 12));
				}else if("Borussia Dortmund".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Borussia Dortmund", 12));

				}	

			}
		});

		JLabel lblTalla = new JLabel("Filtrar por talla:");
		lblTalla.setBounds(905,440,200,20);
		getContentPane().add(lblTalla);

		String []opcTalla= {"Todos los productos", "XS", "S", "M", "L", "XL", "XXL","34 ","35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48"};

		JComboBox cbtalla = new JComboBox(opcTalla);
		cbtalla.setBounds(1045,440,200,20);
		getContentPane().add(cbtalla);
		cbtalla.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				log.info("El usuario ha filtrado los productos en función de las diferentes tallas disponibles");
				String itemSelec = (String)cbtalla.getSelectedItem();
				if("Todos los productos".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Producto", 5));
				}else if ("XS".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("XS", 8));
				} else if ("S".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("S", 8));
				} else if ("M".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("M", 8));
				} else if ("L".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("L", 8));
				} else if ("XL".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("XL", 8));
				} else if ("XXL".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("XXL", 8));
				} else if ("34".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("34", 8));
				} else if("35".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("35", 8));
				} else if("36".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("36", 8));
				}else if("37".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("37", 8));
				}else if("38".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("38", 8));
				}else if("39".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("39", 8));
				}else if("40".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("40", 8));
				}else if("41".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("41", 8));
				}else if("42".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("42", 8));
				} else if("43".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("43", 8));
				} else if("44".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("44", 8));
				}else if("45".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("45", 8));
				}else if("46".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("46", 8));
				}else if("47".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("47", 8));
				}else if("48".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("48", 8));

				}
			}
		});


		JLabel lbltip = new JLabel("Filtrar por tipo:");
		lbltip.setBounds(55,560,200,20);
		getContentPane().add(lbltip);

		String []opcTip = {"Todos los productos","Camisetas", "Pantalones", "Botas", "Playeras", "Balones", "Sudaderas", "Guantes", "Bicicletas", "Cascos", "Maillots", "Botellines", 
				"Playeras", "Palas", "Paleteros", "Protectores", "Pelotas"}; 

		JComboBox cbtip = new JComboBox(opcTip);
		cbtip.setBounds(200,560,200,20);
		getContentPane().add(cbtip);
		cbtip.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				log.info("El usuario ha filtrado los productos en función del tipo al que pertenecen");
				String itemSelec = (String)cbtip.getSelectedItem();
				if("Todos los productos".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Producto", 5));
				}else if ("Camisetas".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Camisetas", 10));
				} else if ("Pantalones".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Pantalones", 10));
				} else if ("Botas".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Botas", 10));
				} else if ("Balones".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Balones", 10));
				} else if ("Sudaderas".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Sudaderas", 10));
				} else if ("Guantes".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Guantes", 10));
				} else if ("Cascos".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Cascos", 10));
				} else if("Maillots".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Maillots", 10));
				} else if("Palas".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Palas", 10));
				}else if("Playeras".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Playeras", 10));
				}else if("Bicicletas".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Bicicletas", 10));
				}else if("Botellines".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Botellines", 10));
				}else if("Paleteros".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Paleteros", 10));
				}else if("Protectores".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Protectores", 10));
				}else if("Pelotas".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Pelotas", 10));
				}	
			}
		});

		JLabel lblColor = new JLabel("Filtrar por color:");
		lblColor.setBounds(905, 560, 200,20);
		getContentPane().add(lblColor);

		String []opCol= {"Todos los productos","Amarillo", "Azul", "Verde", "Marrón", "Rojo", "Naranja", "Negro", "Gris", "Blanco"};

		JComboBox cbCol = new JComboBox(opCol);
		cbCol.setBounds(1045,560,200,20);
		getContentPane().add(cbCol);
		cbCol.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				log.info("El usuario ha filtrado los productos en función del color del producto");
				String itemSelec = (String)cbCol.getSelectedItem();
				if("Todos los productos".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Producto", 5));
				}else if ("Amarillo".equals(itemSelec)){
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Amarillo", 9));
				} else if ("Azul".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Azul", 9));
				} else if ("Verde".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Verde", 9));
				} else if ("Marrón".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Marrón", 9));
				} else if ("Rojo".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Rojo", 9));
				}else if ("Naranja".equals(itemSelec)){
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Naranja", 9));
				} else if ("Negro".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Negro", 9));
				} else if ("Gris".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Gris", 9));
				} else if ("Blanco".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Blanco", 9));

				}
			}
		});

		JLabel lblcat = new JLabel("Filtrar categoria:");
		lblcat.setBounds(55,620,200,20);
		getContentPane().add(lblcat);

		String []opcCat = {"Todos los productos", "Fútbol", "Baloncesto", "Pádel", "Ciclismo", "Videojuegos"};

		JComboBox cb = new JComboBox(opcCat);
		cb.setBounds(200,620,200,20);
		getContentPane().add(cb);
		cb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				log.info("El usuario ha filtrado los productos en función del deporte seleccionado");
				String itemSelec = (String)cb.getSelectedItem();
				if("Todos los productos".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Producto", 5));
				}else if ("Fútbol".equals(itemSelec)){
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Fútbol", 6));
				} else if ("Pádel".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Pádel", 6));
				} else if ("Baloncesto".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Baloncesto", 6));
				} else if ("Ciclismo".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Ciclismo", 6));
				} else if ("Videojuegos".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Videojuegos", 6));
				}
			}
		});


		JLabel lblPlataforma = new JLabel("Filtrar por plataforma:");
		lblPlataforma.setBounds(905,620,200,20);
		getContentPane().add(lblPlataforma);

		String [] opcPlat = {"Todos los productos", "Minijuegos", "PS4", "PS3", "XBox"};

		JComboBox cbPlat = new JComboBox(opcPlat);
		cbPlat.setBounds(1045,620,200,20);
		getContentPane().add(cbPlat);
		cbPlat.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				log.info("El usuario ha filtrado los productos en función la plataforma de los videojuegos");
				String itemSelec = (String)cbPlat.getSelectedItem();
				if("Todos los productos".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Producto", 5));
				}else if ("Minijuegos".equals(itemSelec)){
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Minijuegos", 14));
				} else if ("PS4".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("PS4", 14));
				} else if ("PS3".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("PS3", 14));
				} else if ("XBox".equals(itemSelec)) {
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("XBox", 14));
				}
			}
		});



		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_UP ) {
					//Up arrow key code
					int fila = table.getSelectedRow();
					ruta = (String) table.getValueAt(fila-1, 5);
					ImageIcon im1 = new ImageIcon(ruta);
					im1.setImage(im1.getImage().getScaledInstance(btnFoto.getWidth(), btnFoto.getHeight(), Image.SCALE_SMOOTH));
					btnFoto.setIcon(im1);
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN ) {
					//Down arrow key code
					int fila = table.getSelectedRow();
					ruta = (String) table.getValueAt(fila+1, 5);
					ImageIcon im1 = new ImageIcon(ruta);
					im1.setImage(im1.getImage().getScaledInstance(btnFoto.getWidth(), btnFoto.getHeight(), Image.SCALE_SMOOTH));
					btnFoto.setIcon(im1);
				}
			}
		});
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//	int columna_seleccionada = table.getSelectedColumn();
				int fila_seleccionada = table.getSelectedRow();
				ruta = (String) table.getValueAt(fila_seleccionada, 5);
				ImageIcon im1 = new ImageIcon(ruta);
				im1.setImage(im1.getImage().getScaledInstance(btnFoto.getWidth(), btnFoto.getHeight(), Image.SCALE_SMOOTH));
				btnFoto.setIcon(im1);
			}
		});
		meterColumnasTableModel(nombresColumnas);
		for(int i = 0; i< a.length; i ++) {
			tableModel.addRow(a[i]);
		}
		TableColumn tcc = table.getColumn("CÓDIGO");
		//tcc.setPreferredWidth(72);
		tcc.setMaxWidth(72);
		tcc.setMinWidth(72);
		TableColumn tcn = table.getColumn("NOMBRE");
//		//tcn.setPreferredWidth(90);
		tcn.setMinWidth(75);
		tcn.setMaxWidth(220);
		TableColumn tcp = table.getColumn("PRECIO");
//		//tcp.setPreferredWidth(82);
//		tcp.setMaxWidth(82);
		tcp.setMinWidth(65);
		TableColumn tcs = table.getColumn("STOCK");
//		//tcs.setPreferredWidth(65);
//		tcs.setMaxWidth(65);
		tcs.setMinWidth(65);
		TableColumn tcd = table.getColumn("DESCRIPCIÓN");
//		//tcd.setPreferredWidth(105);
		tcd.setMaxWidth(450);
		tcd.setMinWidth(105);
		TableColumn tcr = table.getColumn("RUTA");
//		//tcr.setPreferredWidth(105);
		tcr.setMaxWidth(350);
		tcr.setMinWidth(60);
		TableColumn tcca = table.getColumn("CATEGORÍA");
//		//tcca.setPreferredWidth(75);
		tcca.setMaxWidth(95);
		tcca.setMinWidth(95);
		TableColumn tco = table.getColumn("OFERTA");
//		//tco.setPreferredWidth(55);
		tco.setMaxWidth(70);
		tco.setMinWidth(70);
		TableColumn tct = table.getColumn("TALLA");
//		//tct.setPreferredWidth(50);
		tct.setMaxWidth(65);
		tct.setMinWidth(65);
		TableColumn tcco = table.getColumn("COLOR");
//		//tcco.setPreferredWidth(70);
		tcco.setMaxWidth(70);
		tcco.setMinWidth(70);
		TableColumn tcct = table.getColumn("TIPO");
//		//tcct.setPreferredWidth(70);
//		tcct.setMaxWidth(120);
//		tcct.setMinWidth(70);
		TableColumn tccm = table.getColumn("MARCA");
//		//tccm.setPreferredWidth(70);
//		tccm.setMaxWidth(70);
//		tccm.setMinWidth(70);
		TableColumn tcce = table.getColumn("EQUIPO");
//		//tcce.setPreferredWidth(70);
		tcce.setMaxWidth(120);
//		tcce.setMinWidth(70);
		TableColumn tccp = table.getColumn("PESO");
//		//tccp.setPreferredWidth(65);
//		tccp.setMaxWidth(65);
//		tccp.setMinWidth(65);
		TableColumn tccpl = table.getColumn("PLATAFORMA");
//		//tccpl.setPreferredWidth(105);
		tccpl.setMaxWidth(105);
		tccpl.setMinWidth(105);
		table.setModel(tableModel);



		JScrollPane scrol = new JScrollPane(table);
		//scrol.setForeground(Color.BLUE);
		table.setBounds(12,22,500,500);
		scrol.setBounds(12,22,1230,374);
		getContentPane().add(scrol);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		btnFoto = new JButton(new ImageIcon(ruta));
		btnFoto.setBounds(450, 400, 399, 266);

		getContentPane().add(btnFoto);
		setVisible(true);
		
		JLabel lblFiltro = new JLabel("Filtrar por código:");
		lblFiltro.setBounds(905,670,150,20);
		getContentPane().add(lblFiltro);
		
		JTextField jtxtFiltro = new JTextField();
		jtxtFiltro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String query = jtxtFiltro.getText().toLowerCase();
				filter(query);
			}
		});
		jtxtFiltro.setBounds(1045,670,150,20);
		getContentPane().add(jtxtFiltro);
		
	
		JButton btnVolver = new JButton("VOLVER");
		btnVolver.setBounds(600,670,100,25);
		btnVolver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				v.dispose();
				VentanaMenu vm = new VentanaMenu(v, nick);
				vm.setVisible(true);

			}
		});
		getContentPane().add(btnVolver);

	}

	private void meterColumnasTableModel(String[] columnas) {
		for(int i = 0; i<columnas.length; i++) {
			this.tableModel.addColumn(columnas[i]);
		}
	}


	

	private void filter(String query) {
		modeloOrdenado = new TableRowSorter<TableModel>(tableModel);
		table.setRowSorter(modeloOrdenado);
		
		modeloOrdenado.setRowFilter(RowFilter.regexFilter(query,0));
//		modeloOrdenado.setRowFilter(RowFilter.regexFilter(query,1));
//		modeloOrdenado.setRowFilter(RowFilter.regexFilter(query,2));
//		modeloOrdenado.setRowFilter(RowFilter.regexFilter(query,3));
//		modeloOrdenado.setRowFilter(RowFilter.regexFilter(query,4));
//		modeloOrdenado.setRowFilter(RowFilter.regexFilter(query,5));
//		modeloOrdenado.setRowFilter(RowFilter.regexFilter(query,6));
//		modeloOrdenado.setRowFilter(RowFilter.regexFilter(query,7));
//		modeloOrdenado.setRowFilter(RowFilter.regexFilter(query,8));
//		modeloOrdenado.setRowFilter(RowFilter.regexFilter(query,9));
//		modeloOrdenado.setRowFilter(RowFilter.regexFilter(query,10));
//		modeloOrdenado.setRowFilter(RowFilter.regexFilter(query,11));
//		modeloOrdenado.setRowFilter(RowFilter.regexFilter(query,12));
//		modeloOrdenado.setRowFilter(RowFilter.regexFilter(query,13));
//		modeloOrdenado.setRowFilter(RowFilter.regexFilter(query,14));
		

		
	}
}
