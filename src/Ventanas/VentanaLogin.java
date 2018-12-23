package Ventanas;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.xml.validation.SchemaFactoryConfigurationError;

import BaseDeDatos.BD;
import Datos.Compra;
import Datos.Usuario;
import VentanasAdministrador.VentanaAdmin;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.awt.event.ActionEvent;


//PREGUNTAR ANDONI EL FILTRO
public class VentanaLogin extends JFrame implements Runnable{

	private JPanel contentPane;
	private JPasswordField pssContrasenia;
	private JTextField txtNick;
	String hora,minutos,segundos,ampm;
	Calendar calendario;    
	Thread h1;
	private JLabel lblHora;
	JFrame v = this;


	public static BD bd;
	private static Connection con;
	
	//Público y estático para poder acceder a él desde cualquier lado
	public static ArrayList<Compra> carrito;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {


		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaLogin frame = new VentanaLogin();
					//Le metemos canción al programa. 3 líneas de código solo
					Clip sonido =AudioSystem.getClip();
					sonido.open(AudioSystem.getAudioInputStream(new File("CancionProyectoo.wav")));
					sonido.start();
					frame.setVisible(true);


				} catch (Exception e) {
					e.printStackTrace();
				}


			}
		});
	}

	public void limpiarCampos() {
		txtNick.setText("");
		pssContrasenia.setText("");
	}

	/**
	 * Create the frame.
	 */
	public VentanaLogin() {
		//Creamos la base de datos
		bd = new BD();
		//Creamos las tablas
		bd.createTable(con);

		//Creamos el carrito
		carrito = new ArrayList<Compra>();
		//Creamos el manejador dentro del fichero para indicar a que fichero se mandarán los logs
		Handler man = null;
		try {
			man = new FileHandler("VentanaLogin.xml", true);
			SimpleFormatter sf = new SimpleFormatter();
			man.setFormatter(sf);
		} catch(SecurityException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}

		//El log por defecto tiene formato XML así que aquí
		//indicamos que vamos a usar el formato por defecto y en la
		//ventana menu para probar las 2 formas lo usaremos con un formato simple

		//Creamos el log y le asociamos el manegador
		Logger log = Logger.getLogger("myLog");
		log.addHandler(man);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null); //Para centrar la ventana
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panelNorte = new JPanel();
		contentPane.add(panelNorte, BorderLayout.NORTH);

		JLabel lblBienvenidoAMi = new JLabel("Bienvenido a Mi Tienda");
		lblBienvenidoAMi.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 25));
		panelNorte.add(lblBienvenidoAMi);

		JPanel panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);

		JPanel panelOeste = new JPanel();
		contentPane.add(panelOeste, BorderLayout.WEST);

		JPanel panelEste = new JPanel();
		contentPane.add(panelEste, BorderLayout.EAST);

		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(null);

		JLabel lblInicio = new JLabel("Inicio de Sesión");
		lblInicio.setForeground(Color.BLUE);
		lblInicio.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 20));
		lblInicio.setBounds(156, 26, 175, 16);
		panelCentro.add(lblInicio);
		

		JLabel lblNick = new JLabel("Nick");
		lblNick.setBounds(74, 91, 61, 16);
		panelCentro.add(lblNick);

		JLabel lblContrasea = new JLabel("Contraseña");
		lblContrasea.setBounds(74, 142, 85, 16);
		panelCentro.add(lblContrasea);

		pssContrasenia = new JPasswordField();
		pssContrasenia.setBounds(291, 137, 130, 26);
		panelCentro.add(pssContrasenia);

		txtNick = new JTextField();
		txtNick.setBounds(291, 86, 130, 26);
		panelCentro.add(txtNick);
		txtNick.setColumns(10);


		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(new Date(System.currentTimeMillis()));
		JLabel lblFecha = new JLabel("New label");
		lblFecha.setBounds(272, 195, 149, 16);
		int dia = gc.get(GregorianCalendar.DAY_OF_MONTH);
		int mes = gc.get(GregorianCalendar.MONTH)+1;
		int anio = gc.get(GregorianCalendar.YEAR);
		lblFecha.setText(dia + "-" + mes + "-" + anio);
		panelCentro.add(lblFecha);


		JFrame v = this;
		JButton btnAceptar = new JButton("Iniciar sesión");
		String texto="Este boton hace que inicies sesión";
		btnAceptar.setToolTipText(""
				+ "<html>"
				+ "<head>"
				+ "<style>"
				+ "#contenido{"
				+ "backgroun: F60;"
				+ "color: white;"
				+ "}"
				+ "</style>"
				+ "</head>"
				+ "<body>"
				+ "<div id='contenido'>"
				+ "<h1>Izquierda</h1>"
				+ "</div>"
				+ "</body>"
				+ "</html>"
				+ "");
		btnAceptar.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					log.info("El usuario ha pulsado el botón de iniciar sesión");
					String nick = txtNick.getText();
					String con = String.valueOf(pssContrasenia.getPassword());
					Usuario user = new Usuario(null,null,null,nick,con, null, null,null,null);
					int resul = BD.existeUsuario(user);
					if(resul==0){
						//NICK MAL INTRODUCIDO. 
						log.severe("El " + txtNick.getText() + " introducido no es correcto, vuelva a introducirlo correctamente o regístrese");
						JOptionPane.showMessageDialog(null, "El Nick introducido no es correcto. Vuelva a introducirlo o registrese.");
						txtNick.setText("");
						pssContrasenia.setText("");

					}else if (resul==1){
						//CONSTRASEÑA MAL INTRODUCIDA.
						log.severe("La contraseña introducida por" + txtNick.getText() + " no ha sido correcta, Vuelva a introducirla correctamente");
						JOptionPane.showMessageDialog(null, "La contraseña introducida no es correcta. Vuelva a introducirlo.");
						txtNick.setText("");
						pssContrasenia.setText("");

					}else{//Resul ==2
						//NICK Y CONTRASEÑA BIEN INTRODUCIDOS; USUARIO EXISTE.
						JOptionPane.showMessageDialog(null, "BIENVENIDO");
						if(txtNick.getText().equals("admin")&&pssContrasenia.getText().equals("admin")){
							//EL USUARIO ES ADMINISTRADOR; ACCEDE A VENTANA ADMINISTRADOR Y TIENE VENTAJAS DE ADMINISTRADOR
							//TODO			//VentanaAdministrador frame = new VentanaAdministrador();
							log.info("El administrador ha iniciado sesión");
							VentanaAdmin va = new VentanaAdmin(v);
							va.setVisible(true);
							VentanaLogin.this.dispose();

						}else{
							log.info(txtNick.getText() + " ha iniciado sesión");
							VentanaMenu frame2 = new VentanaMenu(v);
							frame2.setVisible(true);
							VentanaLogin.this.dispose();
						}
					}
				}
			}
		});
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				log.info("El usuario ha pulsado el botón de iniciar sesión");
				String nick = txtNick.getText();
				String con = String.valueOf(pssContrasenia.getPassword());
				Usuario user = new Usuario(null,null,null,nick,con, null, null,null,null);
				int resul = BD.existeUsuario(user);
				if(resul==0){
					//NICK MAL INTRODUCIDO. 
					log.severe("El " + txtNick.getText() + " introducido no es correcto, vuelva a introducirlo correctamente o regístrese");
					JOptionPane.showMessageDialog(null, "El Nick introducido no es correcto. Vuelva a introducirlo o registrese.");
					txtNick.setText("");
					pssContrasenia.setText("");

				}else if (resul==1){
					//CONSTRASEÑA MAL INTRODUCIDA.
					log.severe("La contraseña introducida por" + txtNick.getText() + " no ha sido correcta, Vuelva a introducirla correctamente");
					JOptionPane.showMessageDialog(null, "La contraseña introducida no es correcta. Vuelva a introducirlo.");
					txtNick.setText("");
					pssContrasenia.setText("");

				}else{//Resul ==2
					//NICK Y CONTRASEÑA BIEN INTRODUCIDOS; USUARIO EXISTE.
					JOptionPane.showMessageDialog(null, "BIENVENIDO");
					if(txtNick.getText().equals("admin")&&pssContrasenia.getText().equals("admin")){
						//EL USUARIO ES ADMINISTRADOR; ACCEDE A VENTANA ADMINISTRADOR Y TIENE VENTAJAS DE ADMINISTRADOR
						//TODO			//VentanaAdministrador frame = new VentanaAdministrador();
						log.info("El administrador ha iniciado sesión");
						VentanaAdmin va = new VentanaAdmin(v);
						va.setVisible(true);
						VentanaLogin.this.dispose();

					}else{
						log.info(txtNick.getText() + " ha iniciado sesión");
						VentanaMenu frame2 = new VentanaMenu(v);
						frame2.setVisible(true);
						VentanaLogin.this.dispose();
					}
				}
			}
		});
		btnAceptar.setBounds(96, 228, 117, 29);
		panelCentro.add(btnAceptar);

		//JFrame v = this;
		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					log.info("El usuario ha pulsado el botón registrarse");
					v.dispose();
					VentanaRegistrar vr = new VentanaRegistrar(bd);
					vr.setVisible(true);
					limpiarCampos();
				}

			}
		});
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				log.info("El usuario ha pulsado el botón registrarse");
				v.dispose();
				VentanaRegistrar vr = new VentanaRegistrar(bd);
				vr.setVisible(true);
				limpiarCampos();
			}
		});


		btnRegistrarse.setBounds(291, 228, 117, 29);
		panelCentro.add(btnRegistrarse);

		//	HILO PARA QUE EL TIEMPO AVANCE, REVISARLO
		//Date dia2 = new Date(System.currentTimeMillis());
		lblHora = new JLabel("New label");
		lblHora.setBounds(162, 195, 86, 16);
		//lblHora.setText(dia2.getHours() + ":" + dia2.getMinutes() + ":" + dia2.getSeconds());
		panelCentro.add(lblHora);


		Image imgNick = new ImageIcon("Imagenes/IconoNick.png").getImage();
		ImageIcon imgNick2= new ImageIcon(imgNick.getScaledInstance(45, 37, Image.SCALE_SMOOTH));
		JLabel lblFotoNick = new JLabel("New label");
		lblFotoNick.setBounds(139, 81, 45, 37);
		lblFotoNick.setIcon(imgNick2);
		panelCentro.add(lblFotoNick);


		Image imgCon = new ImageIcon("Imagenes/IconoContrasenia.png").getImage();
		ImageIcon imgCon2 = new ImageIcon(imgCon.getScaledInstance(45, 37, Image.SCALE_SMOOTH));
		JLabel lblFotoContrasenia = new JLabel("New label");
		lblFotoContrasenia.setBounds(171, 132, 45, 37);
		lblFotoContrasenia.setIcon(imgCon2);
		panelCentro.add(lblFotoContrasenia);

		Image imgTi = new ImageIcon("Imagenes/IconoTienda.jpg").getImage();
		ImageIcon imgTi2 = new ImageIcon(imgTi.getScaledInstance(93, 74, Image.SCALE_SMOOTH));
		JLabel lblIconoTienda = new JLabel("New label");
		lblIconoTienda.setBounds(360, 6, 93, 74);
		lblIconoTienda.setIcon(imgTi2);
		panelCentro.add(lblIconoTienda);
		h1 = new Thread(this);
		h1.start();
		setVisible(true);


		Runnable r = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					while(true) {
						btnAceptar.setForeground(Color.RED);
						btnRegistrarse.setForeground(Color.GREEN);
						Thread.sleep(1000);
						btnAceptar.setForeground(Color.GREEN);
						btnRegistrarse.setForeground(Color.RED);
						Thread.sleep(1000);
					}
				}catch (InterruptedException e){
					e.printStackTrace();
				}
			}

		};

		Thread t = new Thread(r);
		t.start();

	}

	//REVISAR CÓDIGO
	//El ? es como si fuera un if
	public void calcula () {        
		Calendar calendario = new GregorianCalendar();
		Date fechaHoraActual = new Date();


		calendario.setTime(fechaHoraActual);
		ampm = calendario.get(Calendar.AM_PM)==Calendar.AM?"AM":"PM";

		if(ampm.equals("PM")){
			int h = calendario.get(Calendar.HOUR_OF_DAY)-12;
			hora = h>9?""+h:"0"+h;
		}else{
			hora = calendario.get(Calendar.HOUR_OF_DAY)>9?""+calendario.get(Calendar.HOUR_OF_DAY):"0"+calendario.get(Calendar.HOUR_OF_DAY);            
		}
		minutos = calendario.get(Calendar.MINUTE)>9?""+calendario.get(Calendar.MINUTE):"0"+calendario.get(Calendar.MINUTE);
		segundos = calendario.get(Calendar.SECOND)>9?""+calendario.get(Calendar.SECOND):"0"+calendario.get(Calendar.SECOND); 
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Thread ct = Thread.currentThread();
		while(ct == h1) {   
			calcula();
			lblHora.setText(hora + ":" + minutos + ":" + segundos + " "+ampm);
			try {
				Thread.sleep(1000);
			}catch(InterruptedException e) {}
		}

	}


}
