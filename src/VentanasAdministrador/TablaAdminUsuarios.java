package VentanasAdministrador;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.table.TableStringConverter;

import Ventanas.VentanaLogin;
import Ventanas.VentanaMenu;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TablaAdminUsuarios extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel tableModel = null;
	private String ruta=null;
	JButton btnFoto = null;
	String[] opciones;
	JFrame v=this;
	private JTable table ;
	TableRowSorter<TableModel> modeloOrdenado;



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

			return this;
		}
	}


	/**
	 * Create the frame.
	 */
	@SuppressWarnings("unlikely-arg-type")
	public TablaAdminUsuarios() {
		setExtendedState(MAXIMIZED_BOTH);

		JLabel lblTxt = new JLabel("TABLA CON TODOS LOS USUARIOS REGISTRADOS");
		lblTxt.setBounds(450, 3, 500,20);
		getContentPane().add(lblTxt);

		String nombresColumnas[] = {"DNI","NOMBRE","APELLIDO","NICK", "CONTRASEÑA","NÚMERO TELÉFONO","DOMICILIO", "CUENTA BANCARIA"};


		// Instanciamos el TableRowSorter y lo añadimos al JTable
		modeloOrdenado = new TableRowSorter<TableModel>(tableModel);


		Object[][] a = VentanaLogin.bd.obtenerTablaProductos();
		table = new JTable();
		table.setRowSorter(modeloOrdenado);
		MiRender rr = new MiRender();
		table.setDefaultRenderer(Object.class, rr);
		//table.setDefaultRenderer (Object.class, new MiRender());

		//		String []opciones = {"Todos los productos","Fútbol", "Pádel", "Baloncesto","Ciclismo", "Videojuegos","Camisetas", "Pantalones", "Botas", "Balones", "Sudaderas", "Guantes", "Bicicletas", "Cascos",
		//				"Maillots", "Botellines", "Playeras", "Palas", "Pelotas", "Paleteros", "Protectores", "Nike", "PS4", "PS3", "XBOX", "Gratuitos"
		//		};


		//		JLabel lblMarca = new JLabel("Filtrar productos por marca");
		//		lblMarca.setBounds(55,500,200,20);
		//		getContentPane().add(lblMarca);
		//		
		//		String []opcMarca = {"Todos los productos", "Cannondale", "Trek", "Orbea", "Specialized", "Spiuk", "Limar", "Tacx" ,"Catlike", "Roger", "UCI", "Argon", "Pinarello", "BH", "Canyon"};
		//
		//		JComboBox cbmar = new JComboBox(opcMarca);
		//		cbmar.setBounds(250,500,200,20);
		//		getContentPane().add(cbmar);
		//		cbmar.addActionListener(new ActionListener() {
		//
		//			@Override
		//			public void actionPerformed(ActionEvent e) {
		//				// TODO Auto-generated method stub
		//				String itemSelec = (String)cbmar.getSelectedItem();
		//				if("Todos los productos".equals(itemSelec)) {
		//					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Producto", 5));
		//				}else if ("Cannondale".equals(itemSelec)) {
		//					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Cannondale", 11));
		//				} else if ("Trek".equals(itemSelec)) {
		//					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Trek", 11));
		//				} else if ("Orbea".equals(itemSelec)) {
		//					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Orbea", 11));
		//				} else if ("Specialized".equals(itemSelec)) {
		//					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Specialized", 11));
		//				} else if ("Spiuk".equals(itemSelec)) {
		//					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Spiuk", 11));
		//				} else if ("Limar".equals(itemSelec)) {
		//					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Limar", 11));
		//				} else if ("Tacx".equals(itemSelec)) {
		//					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Tacx", 11));
		//				} else if("Catlike".equals(itemSelec)) {
		//					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Catlike", 11));
		//				} else if("Roger".equals(itemSelec)) {
		//					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Roger", 11));
		//				}else if("UCI".equals(itemSelec)) {
		//					modeloOrdenado.setRowFilter(RowFilter.regexFilter("UCI", 11));
		//				}else if("Argon".equals(itemSelec)) {
		//					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Argon", 11));
		//				}else if("Pinarello".equals(itemSelec)) {
		//					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Pinarello", 11));
		//				}else if("BH".equals(itemSelec)) {
		//					modeloOrdenado.setRowFilter(RowFilter.regexFilter("BH", 11));
		//				}else if("Canyon".equals(itemSelec)) {
		//					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Canyon", 11));
		//				}else if("Pelotas".equals(itemSelec)) {
		//					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Pelotas", 11));
		//				}	
		//			}
		//		});
		//
		//		
		//		
		//		JLabel lblEquipos= new JLabel("Filtrar productos por equipo");
		//		lblEquipos.setBounds(55,440,200,20);
		//		getContentPane().add(lblEquipos);
		//		
		//		String []opcEquipos= {"Todos los productos","Tinkoff Saxo", "Euskaltel Euskadi", "Euskadi Murias", "Astana", "Movistar", "Poza Bike Xtrem", "Sky", "Burgos BH", "Spurs", "Houston Rockets",
		//				"Celtics", "Cleveland Cavaliers", "Los Angeles Lakers", "Chicago Bulls", "Golden State Warriors", "New Orleands Pelicans", "PSG", "Juventus", "Manchester City", "Leicester City", 
		//				"Athletic Club", "Bayern Munich", "Chelsea", "Inter de Milán", "Liverpool", "Manchester United", "Arsenal", "Borussia Dortmund"};
		//		
		//		JComboBox cbeq = new JComboBox(opcEquipos);
		//		cbeq.setBounds(250,440,200,20);
		//		getContentPane().add(cbeq);
		//		cbeq.addActionListener(new ActionListener() {
		//
		//			@Override
		//			public void actionPerformed(ActionEvent e) {
		//				// TODO Auto-generated method stub
		//				String itemSelec = (String)cbeq.getSelectedItem();
		//				if("Todos los productos".equals(itemSelec)) {
		//					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Producto", 5));
		//				}else if ("Euskaltel Euskadi".equals(itemSelec)) {
		//					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Euskaltel ", 12));
		//				} else if ("Euskadi Murias".equals(itemSelec)) {
		//					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Euskadi ", 12));
		//				} else if ("Astana".equals(itemSelec)) {
		//					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Astana", 12));
		//				} else if ("Movistar".equals(itemSelec)) {
		//					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Movistar", 12));
		//				} else if ("Poza Bike Xtrem".equals(itemSelec)) {
		//					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Poza ", 12));
		//				} else if ("Sky".equals(itemSelec)) {
		//					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Sky", 12));
		//				} else if ("Burgos BH".equals(itemSelec)) {
		//					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Burgos ", 12));
		//				} else if("Spurs".equals(itemSelec)) {
		//					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Spurs", 12));
		//				} else if("Houston Rockets".equals(itemSelec)) {
		//					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Houston ", 12));
		//				}else if("Celtics".equals(itemSelec)) {
		//					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Celtics", 12));
		//				}else if("Cleveland Cavaliers".equals(itemSelec)) {
		//					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Cleveland ", 12));
		//				}else if("Los Angeles Lakers".equals(itemSelec)) {
		//					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Los Angeles Lakers", 12));
		//				}else if("Chicago Bulls".equals(itemSelec)) {
		//					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Chicago ", 12));
		//				}else if("Golden State Warriors".equals(itemSelec)) {
		//					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Warriors", 12));
		//				}else if("New Orleans Pelicans".equals(itemSelec)) {
		//					modeloOrdenado.setRowFilter(RowFilter.regexFilter("New Orleans Pelicans", 12));
		//				} else if ("Tinkoff Saxo".equals(itemSelec)) {
		//					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Tinkoff ", 12));
		//				} else if("PSG".equals(itemSelec)) {
		//					modeloOrdenado.setRowFilter(RowFilter.regexFilter("PSG", 12));
		//				} else if("Juventus".equals(itemSelec)) {
		//					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Juventus ", 12));
		//				}else if("Manchester City".equals(itemSelec)) {
		//					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Manchester City", 12));
		//				}else if("Leicester City".equals(itemSelec)) {
		//					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Leicester City ", 12));
		//				}else if("Athletic Club".equals(itemSelec)) {
		//					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Athletic Club", 12));
		//				}else if("Bayern Munich".equals(itemSelec)) {
		//					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Bayern ", 12));
		//				}else if("Chelsea".equals(itemSelec)) {
		//					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Chelsea", 12));
		//				}else if("Inter de Milán".equals(itemSelec)) {
		//					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Inter", 12));
		//				} else if ("Liverpool".equals(itemSelec)) {
		//					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Liverpool ", 12));
		//				} else if("Manchester United".equals(itemSelec)) {
		//					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Manchester United", 12));
		//				} else if("Arsenal".equals(itemSelec)) {
		//					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Arsenal ", 12));
		//				}else if("Borussia Dortmund".equals(itemSelec)) {
		//					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Borussia Dortmund", 12));
		//				
		//				}	
		//				
		//			}
		//		});
		//
		//
		//		JLabel lbltip = new JLabel("Filtrar productos por tipo:");
		//		lbltip.setBounds(55,560,200,20);
		//		getContentPane().add(lbltip);
		//		
		//		String []opcTip = {"Todos los productos","Camisetas", "Pantalones", "Botas", "Playeras", "Balones", "Sudaderas", "Guantes", "Bicicletas", "Cascos", "Maillots", "Botellines", 
		//				"Playeras", "Palas", "Paleteros", "Protectores", "Pelotas"}; 
		//		
		//		JComboBox cbtip = new JComboBox(opcTip);
		//		cbtip.setBounds(250,560,200,20);
		//		getContentPane().add(cbtip);
		//		cbtip.addActionListener(new ActionListener() {
		//
		//			@Override
		//			public void actionPerformed(ActionEvent e) {
		//				// TODO Auto-generated method stub
		//				String itemSelec = (String)cbtip.getSelectedItem();
		//				if("Todos los productos".equals(itemSelec)) {
		//					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Producto", 5));
		//				}else if ("Camisetas".equals(itemSelec)) {
		//					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Camisetas", 10));
		//				} else if ("Pantalones".equals(itemSelec)) {
		//					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Pantalones", 10));
		//				} else if ("Botas".equals(itemSelec)) {
		//					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Botas", 10));
		//				} else if ("Balones".equals(itemSelec)) {
		//					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Balones", 10));
		//				} else if ("Sudaderas".equals(itemSelec)) {
		//					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Sudaderas", 10));
		//				} else if ("Guantes".equals(itemSelec)) {
		//					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Guantes", 10));
		//				} else if ("Cascos".equals(itemSelec)) {
		//					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Cascos", 10));
		//				} else if("Maillots".equals(itemSelec)) {
		//					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Maillots", 10));
		//				} else if("Palas".equals(itemSelec)) {
		//					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Palas", 10));
		//				}else if("Playeras".equals(itemSelec)) {
		//					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Playeras", 10));
		//				}else if("Bicicletas".equals(itemSelec)) {
		//					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Bicicletas", 10));
		//				}else if("Botellines".equals(itemSelec)) {
		//					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Botellines", 10));
		//				}else if("Paleteros".equals(itemSelec)) {
		//					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Paleteros", 10));
		//				}else if("Protectores".equals(itemSelec)) {
		//					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Protectores", 10));
		//				}else if("Pelotas".equals(itemSelec)) {
		//					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Pelotas", 10));
		//				}	
		//			}
		//		});
		//
		//		JLabel lblcat = new JLabel("Filtrar productos por categoria:");
		//		lblcat.setBounds(55,620,200,20);
		//		getContentPane().add(lblcat);
		//		
		//		String []opcCat = {"Todos los productos", "Fútbol", "Baloncesto", "Pádel", "Ciclismo", "Videojuegos"};
		//
		//		JComboBox cb = new JComboBox(opcCat);
		//		cb.setBounds(250,620,200,20);
		//		getContentPane().add(cb);
		//		cb.addActionListener(new ActionListener() {
		//
		//			@Override
		//			public void actionPerformed(ActionEvent e) {
		//				// TODO Auto-generated method stub
		//				String itemSelec = (String)cb.getSelectedItem();
		//				if("Todos los productos".equals(itemSelec)) {
		//					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Producto", 5));
		//				}else if ("Fútbol".equals(itemSelec)){
		//					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Fútbol", 6));
		//				} else if ("Pádel".equals(itemSelec)) {
		//					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Pádel", 6));
		//				} else if ("Baloncesto".equals(itemSelec)) {
		//					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Baloncesto", 6));
		//				} else if ("Ciclismo".equals(itemSelec)) {
		//					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Ciclismo", 6));
		//				} else if ("Videojuegos".equals(itemSelec)) {
		//					modeloOrdenado.setRowFilter(RowFilter.regexFilter("Videojuegos", 6));
		//				}
		//			}
		//		});



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
		table.setModel(tableModel);


		JScrollPane scrol = new JScrollPane(table);
		//scrol.setForeground(Color.BLUE);
		table.setBounds(12,22,500,500);
		scrol.setBounds(12,22,1230,374);
		getContentPane().add(scrol);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		btnFoto = new JButton(new ImageIcon(ruta));
		btnFoto.setBounds(20, 400, 399, 266);

		getContentPane().add(btnFoto);
		setVisible(true);
		
		JLabel lblDNI= new JLabel("DNI: ");
		lblDNI.setBounds(450,420,100,20);
		getContentPane().add(lblDNI);
		
		JTextField txtDNI = new JTextField();
		txtDNI.setBounds(540,420,100,20);
		getContentPane().add(txtDNI);
		
		JLabel lblnom= new JLabel("Nombre: ");
		lblnom.setBounds(450,460,100,20);
		getContentPane().add(lblnom);
		
		JTextField txtnom = new JTextField();
		txtnom.setBounds(540,460,100,20);
		getContentPane().add(txtnom);
		
		JLabel lblape= new JLabel("Apellido: ");
		lblape.setBounds(450,500,100,20);
		getContentPane().add(lblape);
		
		JTextField txtape = new JTextField();
		txtape.setBounds(540,500,100,20);
		getContentPane().add(txtape);
		
		JLabel lblnic= new JLabel("Nick: ");
		lblnic.setBounds(450,540,100,20);
		getContentPane().add(lblnic);
		
		JTextField txtnic = new JTextField();
		txtnic.setBounds(540,540,100,20);
		getContentPane().add(txtnic);
		
		JLabel lblcon= new JLabel("Contraseña: ");
		lblcon.setBounds(450,580,100,20);
		getContentPane().add(lblcon);
		
		JPasswordField pssCon = new JPasswordField();
		pssCon.setBounds(540,580,100,20);
		getContentPane().add(pssCon);
		
		JLabel lblnumTel= new JLabel("Número Teléfono: ");
		lblnumTel.setBounds(655,440,150,20);
		getContentPane().add(lblnumTel);
		
		JTextField txtnumTel = new JTextField();
		txtnumTel.setBounds(800,440,100,20);
		getContentPane().add(txtnumTel);
		
		JLabel lbldom= new JLabel("Domicilio: ");
		lbldom.setBounds(655,480,100,20);
		getContentPane().add(lbldom);
		
		JTextField txtdom = new JTextField();
		txtdom.setBounds(800,480,100,20);
		getContentPane().add(txtdom);
		
		JLabel lblcu= new JLabel("Cuenta Bancaria: ");
		lblcu.setBounds(655,520,150,20);
		getContentPane().add(lblcu);
		
		JTextField txtcu = new JTextField();
		txtcu.setBounds(800,520,100,20);
		getContentPane().add(txtcu);
		
		JLabel lblFiltro = new JLabel("Filtrar por código:");
		lblFiltro.setBounds(655,560,150,20);
		getContentPane().add(lblFiltro);
		
		JTextField jtxtFiltro = new JTextField();
		jtxtFiltro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String query = jtxtFiltro.getText().toLowerCase();
				filterCodigo(query);
			}
		});
		jtxtFiltro.setBounds(800,560,150,20);
		getContentPane().add(jtxtFiltro);
		
		



		JButton btnVolver = new JButton("VOLVER");
		btnVolver.setBounds(600,670,100,20);
		btnVolver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				v.dispose();
				VentanaAdminUsuarios vau = new VentanaAdminUsuarios(v);
				vau.setVisible(true);

			}
		});
		getContentPane().add(btnVolver);

		JButton btnAñadir = new JButton("Añadir fila");
		btnAñadir.setBounds(750,670,100,20);
		btnAñadir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jButtonAgregarActionPerformed(e);
				
			}
		});
		getContentPane().add(btnAñadir);

		JButton btnEliminar = new JButton("Eliminar fila");
		btnEliminar.setBounds(450, 670, 100, 20);
		btnEliminar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jButtonEliminarActionPerformed(e);
			}
		});
		getContentPane().add(btnEliminar);

	}

	private void meterColumnasTableModel(String[] columnas) {
		for(int i = 0; i<columnas.length; i++) {
			this.tableModel.addColumn(columnas[i]);
		}
	}

	private void jButtonAgregarActionPerformed(java.awt.event.ActionEvent evt){
		DefaultTableModel temp = (DefaultTableModel) table.getModel();
		Object nuevo[]= {temp.getRowCount()+1,"",""};
		temp.addRow(nuevo);
	}

	private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt){
		try
		{
			DefaultTableModel temp = (DefaultTableModel) table.getModel();
			temp.removeRow(temp.getRowCount()-1);
		}
		catch(ArrayIndexOutOfBoundsException e){;}
	}
	
	private void filterCodigo(String query) {
		modeloOrdenado = new TableRowSorter<TableModel>(tableModel);
		table.setRowSorter(modeloOrdenado);
		
		modeloOrdenado.setRowFilter(RowFilter.regexFilter(query,0));

	}
	
	private void filter(String query) {
		modeloOrdenado = new TableRowSorter<TableModel>(tableModel);
		table.setRowSorter(modeloOrdenado);
		
		modeloOrdenado.setRowFilter(RowFilter.regexFilter(query,0));

	}
}
