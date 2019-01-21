package VentanasAdministrador;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.table.TableStringConverter;

import BaseDeDatos.BD;
import Ventanas.VentanaLogin;
import Ventanas.VentanaMenu;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.sql.Statement;
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
				filterNick(query);
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
				AgregarFila(e);
				
			}
		});
		getContentPane().add(btnAñadir);

		JButton btnEliminar = new JButton("Eliminar fila");
		btnEliminar.setBounds(450, 670, 100, 20);
		btnEliminar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				EliminarFila(e);
			}
		});
		getContentPane().add(btnEliminar);

		tableModel.addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				// TODO Auto-generated method stub
				if(e.getType() == TableModelEvent.UPDATE) {
					String col = null;
					if (e.getColumn()==0) {
						col = "DNI";
					}else if(e.getColumn() ==1) {
						col = "nombre";
					}else if(e.getColumn()==2) {
						col = "apellido";
					}else if(e.getColumn()==3) {
						col = "nick";
					}else if(e.getColumn()==4){
						col = "contrasenia";
					}
					String query = "UPDATE USUARIO SET " + col + "='" + tableModel.getValueAt(e.getFirstRow(), e.getColumn())+ "' WHERE DNI='" + tableModel.getValueAt(e.getFirstRow(), 0) + "'";
					try {
						Statement stmt = VentanaLogin.bd.getSentencia();
						stmt.executeUpdate(query);
						JOptionPane.showMessageDialog(null, "Hubo cambio en la fila " + (e.getFirstRow() +1) + " de la columna " + (e.getColumn() +1));
					}catch (SQLException ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(null, "Error al actualizar");
					}

				}

			}
		});
	}

	private void meterColumnasTableModel(String[] columnas) {
		for(int i = 0; i<columnas.length; i++) {
			this.tableModel.addColumn(columnas[i]);
		}
	}

	private void AgregarFila(ActionEvent evt){
		tableModel = (DefaultTableModel) table.getModel();
		Object nuevo[]= {"","","","","",0,"",""};
		tableModel.addRow(nuevo);
		BD.insertarFila();

	}

	private void EliminarFila(ActionEvent evt){
		try
		{
			tableModel= (DefaultTableModel) table.getModel();
			//temp.removeRow(temp.getRowCount()-1);
			tableModel.removeRow(table.getSelectedRow());
		}
		catch(ArrayIndexOutOfBoundsException e){;}
	}
	
	
	private void filterNick(String query) {
		modeloOrdenado = new TableRowSorter<TableModel>(tableModel);
		table.setRowSorter(modeloOrdenado);
		
		modeloOrdenado.setRowFilter(RowFilter.regexFilter(query,3));

	}
}
