package BaseDeDatos;

import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import Datos.Producto;
import Datos.ProductosConTalla;
import Datos.Usuario;

public class BD {

	private static Connection con;
	private static Statement stmt;

	/**
	 * Método que crea una sentencia para acceder a la base de datos
	 */
	public static void crearSentencia() {
		try {
			stmt = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Método que permite conectarse a la base de datos
	 */
	public static void conectar() {
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
			crearSentencia();
		}catch (Exception e) {
			System.out.println("No se ha podido conectar a la base de datos");
			e.printStackTrace();
		}
	}

	/**
	 * Método que cierra una sentencia
	 */
	public static void cerrarSentencia() {
		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Método que permite desconectarse de la base de datos
	 */
	public static void desconectar() {
		try {
			cerrarSentencia();
			con.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}


	public BD() {
		conectar();
	}

	//A partir de aquí hacemos las consultas específicas de cada proyecto

	/**
	 * Método que crea las distintas tablas en la base de datos
	 * Tablas: Usuario, patrocinadores, Productos y fotos
	 * @param con
	 * @return null
	 */
	public Statement createTable(Connection con) {
		//Crear tabla usuario
		try {
			stmt.setQueryTimeout(30);
			String Usuario = "CREATE TABLE USUARIO(DNI string, nombre string, apellido string, nick string, contrasenia string, numTel string, domicilio string, cuentaBancaria string, Imagen string)";

			stmt.executeUpdate(Usuario);
		} catch(SQLException e) {
			//Tabla ya existe, nada que hacer
		}
		//Crear tabla Patrocinadores
		try {
			stmt.setQueryTimeout(30);
			String Patrocinadores = "CREATE TABLE PATROCINADORES (Descripcion string, rutaFoto string, dinero int)";
			stmt.executeUpdate(Patrocinadores);
		} catch(SQLException e) {
			//Tabla ya existe, nada que hacer
		}
		//Crear la tabla Fotos
		try {
			stmt.setQueryTimeout(30);
			String Fotos = "CREATE TABLE Fotos (ruta string, tipo string)";
			stmt.executeUpdate(Fotos);
		} catch(SQLException e) {
			//Tabla ya existe, nada que hacer	
		}
		//Crear tabla Productos
		try {
			stmt.setQueryTimeout(30);
			String Productos = "CREATE TABLE PRODUCTOS (codigo string, nombre string, precio double, stock int, descripcion string,"+
					"ruta string, categoria string, oferta string, talla string, color string, tipo string, marca string, equipo string,"+
					" peso double, Plataforma string)";
			stmt.executeUpdate(Productos);
		} catch(SQLException e) {
			//Tabla ya existe, nada que hacer
		}
		//Crear tabla Pedido
		//		try {
		//			stmt.setQueryTimeout(30);
		//			String Pedido = "CREATE TABLE PEDIDO";
		//			stmt.executeUpdate(Pedido);
		//		} catch(SQLException e) {
		//			//Tabla ya existe, nada que hacer
		//		}
		//		//Crear tabla Tarjeta
		//		try {
		//			stmt.setQueryTimeout(30);
		//			String Tarjeta = "CREATE TABLE TARJETA";
		//			stmt.executeUpdate(Tarjeta);
		//		} catch(SQLException e){
		//			//Tabla ya existe, nada que hacer
		//		}
		return null;
	}

	/**
	 * Método que comprueba si el usuario existe o no
	 * @param nic: Nick introducido por el usuario
	 * @param con: Contraseña introducida por el usuario
	 * @return	0: Si no existe el usuario
	 * 			1: Si existe el nick del usuario pero la contraseña es incorrecta
	 * 			2: Si el nick del usuario es correcto y la contraseña también
	 * 	
	 */

	//TODO HE TENIDO QUE PONER STATIC PARA PODER HACER EL JUNIT, NO SE SI ESTARÁ BIEN
	public static int existeUsuario(Usuario u) {
		String query = "SELECT * FROM Usuario WHERE nick='"+u.getNick()+"'";
		ResultSet rs = null;
		int resul=0;
		try {
			rs = stmt.executeQuery(query);
			if(rs.next()){ //AquÌ estamos comprobando si la SELECT ha devuelto alguna fila
				String n = rs.getString("nick");
				String c = rs.getString("contrasenia");
				if(!n.equals(u.getNick()))
					resul=0;
				else if(!c.equals(u.getContrasenia()))
					resul=1;
				else
					resul=2;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resul;
	}

	/**
	 * Método para Registrar Usuario.
	 * @param Usuario u: pasa los atributos de la clase usuario
	 */
	public void registrarUsuario(Usuario u) {
		String query= "INSERT INTO Usuario(DNI, nombre, apellido, nick, contrasenia, numTel, domicilio, cuentaBancaria ) VALUES('"+u.getDNI()+"','"+u.getNombre()+
				"','" + u.getApellido() + "','" +u.getNick() +"','" + u.getContrasenia() +"','" + u.getNumTel() + "','" + u.getDomicilio() + "','" + u.getCuenta()+ "')" ;
		//No podemos REsultSet pq una INSERT no devuelve filas, solo inserta en la tabla
		try {
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}

	/**
	 * Método que obtiene las rutas de los patrocinadores almacenados en la base de datos
	 * @return aRutas que es un ArrayList con todas las rutas de los patrocinadores
	 */
	public ArrayList<String> obtenerRutaPatrocinadores(){
		ArrayList<String> aRutas = new ArrayList<String>();
		String query = "SELECT rutaFoto FROM Patrocinadores";
		try {
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				String ruta = rs.getString(1);
				aRutas.add(ruta);
			}
			rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return aRutas;
	}

	/**
	 * Método que obtiene las rutas de los productos almacenados en la base de datos
	 * @return aRutas que es un ArrayList con todas las rutas de los productos
	 */
	public ArrayList<String> obtenerRutaProductos(){
		ArrayList<String> aRutas = new ArrayList<String>();
		String query = "SELECT ruta FROM Productos " ;
		try {
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				String ruta = rs.getString(1);
				aRutas.add(ruta);
			}
			rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return aRutas;
	}



	/**
	 * Método que obtiene todas las fotos de la tabla fotos 
	 * @param tipo: atributo para saber que la imagen seleccionada es un gif, es una foto del menu, de las ofertas...
	 * @return fotos
	 */
	public static String [] obtenerFotos(String tipo) {
		String query = "SELECT COUNT(ruta) FROM Fotos WHERE tipo='"+tipo+"'";
		String [] fotos = null;
		try {
			ResultSet rs = stmt.executeQuery(query);
			int cont = rs.getInt(1);
			rs.close();
			fotos = new String[cont];
			query = "SELECT ruta FROM Fotos WHERE tipo='"+tipo+"'";
			rs = stmt.executeQuery(query);
			int i=0;
			while(rs.next()) {
				String ruta = rs.getString(1);
				fotos[i] = ruta;
				i++;
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return fotos;
	}

	/**
	 * Método para filtrar las fotos de los productos almacenados en la base de datosdependiendo de la categoría
	 * @param cat: atributo que hace referencia a la categoría(Pádel, Fútbol, Ciclismo, Baloncesto o Videojuegos)
	 * @return aProductos que es un ArrayList con todas las rutas de los productos con una categoría en concreto
	 */
	public ArrayList<String> obtenerProductosCategoria(String cat){
		String query = "SELECT DISTINCT ruta FROM PRODUCTOS WHERE categoria='" + cat + "'";
		ArrayList<String> aProductos = new ArrayList<String>();
		try {
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				String ruta = rs.getString(1);
				aProductos.add(ruta);
			}
			rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return aProductos;
	}

	/**
	 * Método que obtiene los productos almanecados en la base de datos que sean de una categoría y tipo concreto
	 * @param cat : atributo que hace referencia a la categoría (Pádel, Fútbol, Baloncesto, Ciclismo o Videojuegos)
	 * @param tipo : atributo que hace referencia al tipo (Camisetas, botas, sudaderas, balones...)
	 * @return aProductos que es un ArrayList con todas las rutas de los productos con una categoria y tipo en concreto
	 */
	public ArrayList<String> obtenerProductosCategoriaYTipo(String cat, String tipo){
		String query = "SELECT DISTINCT ruta FROM PRODUCTOS WHERE categoria ='" + cat + "' AND tipo='" + tipo +"'";
		ArrayList<String> aProductos = new ArrayList<String>();
		try {
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				String ruta = rs.getString(1);
				aProductos.add(ruta);
			}
			rs.close();
		} catch(SQLException e){
			e.printStackTrace();
		}
		return aProductos;

	}

	/**
	 * Método que obtiene los productos de videojuegos almacenados en la base de datos que sean de una categoría, platagorma y timpo concreto
	 * @param cat: atributo que hace referencia a la categoría(Pádel, Fútbol, Ciclismo, Baloncesto o Videojuegos)
	 * @param plat: atributo que hace referencia a la plataforma (PS3, PS4, XBOX, Gratuitos)
	 * @param tipo: atributo que hace referencia al tipo (Camisetas, botas, sudaderas, balones...)
	 * @return aProductos que es un ArrayList con todas las rutas de los productos con una categoria, plataforma y tipo en concreto
	 */
	public ArrayList<String> obtenerVideojuegosCategoriaPlataformaYTipo(String cat,String plat, String tipo){
		String query = "SELECT ruta FROM PRODUCTOS WHERE categoria ='" + cat + "' AND Plataforma ='" + plat+ "' AND tipo='" + tipo +"'";
		ArrayList<String> aProductos = new ArrayList<String>();
		try {
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				String ruta = rs.getString(1);
				aProductos.add(ruta);
			}
			rs.close();
		} catch(SQLException e){
			e.printStackTrace();
		}
		return aProductos;

	}

	/**
	 * Método que obtienes las rutas de los productos con filtro de la categoría Pádel
	 * @param tipo: atributo que hace referencia al tipo (Camisetas, botas, sudaderas, balones...)
	 * @param peso: atributo que hace referencia al peso de la pala
	 * @param color: atributo que hace referencia a los colores de los productos
	 * @param marca: atributo que hace referencia a las marcas de los productos
	 * @return aRutas que es un ArrayList con todas las rutas de los productos filtrados
	 */
		public ArrayList<String> obtenerRutasConFiltroPadel(String tipo, double peso, String color, String marca, String talla, String cat){
			tipo = tipo == null ? "" : tipo;
			//peso = peso == null ? "" : peso;
			color = color == null ? "" : color;
			marca = marca == null ? "" : marca;
			talla = talla == null ? "" : talla;
			cat = cat == null? "" : cat;
			ResultSet rs;
			String query = "SELECT DISTINCT(ruta) FROM PRODUCTOS";
			query += (!tipo.isEmpty() ||/* peso.isEmpty() ||*/ color.isEmpty() || marca.isEmpty() || talla.isEmpty() || cat.isEmpty() ? " WHERE " : "");
			int params = 0;
			if(!tipo.isEmpty()) {
				query += " tipo = '"+tipo+"'";
				params++;
			}
//			if(!peso.isEmpty()) {
//				query += params > 0 ? " and peso = '"+ peso + "'" : " peso = '" + peso + "'";
//				params++;
//			}
			if(!color.isEmpty()) {
				query += params > 0 ? " and color = '"+ color + "'" : " color = '" + color + "'";
				params++;
			}
			if(!marca.isEmpty()) {
				query += params > 0 ? " and marca = '"+ marca + "'" : " marca = '" + marca + "'";
				params++;
			}
			if(!talla.isEmpty()) {
				query += params > 0 ? " and talla = '"+ talla + "'" : " talla = '" + talla + "'";
				params++;
			}
			if(!cat.isEmpty()) {
				query += params > 0 ? " and cat = '"+ cat + "'" : " cat = '" + cat + "'";
				params++;
			}
			ArrayList<String> aRutas = new ArrayList<String>();
			try {
				rs = stmt.executeQuery(query);
				while (rs.next()) {
					String ruta = rs.getString(1);
					aRutas.add(ruta);
				}
				rs.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
			return aRutas;
		}

	/**
	 * Método que obtiene los productos filtrados de la categoria fútbol
	 * @param tipo: atributo que hace referencia al tipo(Camisetas, botas, sudaderas, baloens...)
	 * @param color: atributo que hace referencia a los colores de los productos
	 * @param marca: atributo que hace referencia a las marcas de los productos
	 * @param talla: atributo que hace referencia a las tallas de los productos
	 * @param equipo: atributo que hace referencia al equipo del producto
	 * @param cat: atributo que hace referencia a la categoria del producto
	 * @return aRutas que es un ArrayList con todas las rutas de los productos que cumplen las condiciones anteriores
	 */
	public ArrayList<String> obtenerRutasConFiltroFutbol(String tipo, String color, String marca, String talla, String equipo, String cat) {
		/*if(tipo==null)
			tipo="";*/
		tipo = tipo == null ? "" : tipo;
		color = color == null ? "" : color;
		marca = marca == null ? "" : marca;
		talla = talla == null ? "" : talla;
		equipo = equipo == null ? "" : equipo;
		cat = cat == null ? "" : cat;
		PreparedStatement pst = null;
		ResultSet rs;
		String query = "SELECT DISTINCT(ruta) FROM PRODUCTOS";
		/*Si todos los parámetros recibidos están vacios la consulta se queda SELECT(DISTINCT(ruta)) FROM PRODUCTOS y por lo tanto,
		 * muestra todos los productos
		 * Sin embargo, si alguno de los campos tiene información a la consulta le añadirmos el where para filtrar los productos*/
		query += (!tipo.isEmpty() || !color.isEmpty() || !marca.isEmpty() || !talla.isEmpty() || !equipo.isEmpty() || !cat.isEmpty()) ? " where " : "";

		int params = 0;
		if(!tipo.isEmpty()){
			query += " tipo = '"+tipo+"'";
			params++;
		}
		if(!color.isEmpty()){
			query += params > 0 ? " and color = '"+color+"'" : " color = '"+color+"'";
			params++;
		}
		if(!marca.isEmpty()){
			query += params > 0 ? " and marca = '"+marca+"'" : " marca = '"+marca+"'";
			params++;
		}
		if(!talla.isEmpty()){
			query += params > 0 ? " and talla = '"+talla+"'" : " talla = '"+talla+"'";
			params++;
		}
		if(!equipo.isEmpty()){
			query += params > 0 ? " and equipo = '"+equipo+"'" : " equipo = '"+equipo+"'";
			params++;
		}
		if(!cat.isEmpty()){
			query += params > 0 ? " and categoria = '"+cat+"'" : " categoria = '"+cat+"'";
			params++;
		}
		ArrayList<String> aRutas = new ArrayList<String>();
		try {
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				String ruta = rs.getString(1);
				aRutas.add(ruta);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace(System.out);
		} 
		return aRutas;
	}
	/**
	 * Método que obtiene la descripción de los patrocinadores 
	 * @param ruta: atributo que muestra la ruta de la imagen
	 * @return desc que es el texto que hay almacenado en la base de datos
	 */
	public String obtenerDescripcionPatrocinador(String ruta) {
		String query = "SELECT Descripcion FROM PATROCINADORES WHERE rutaFoto='"+ruta+"'";
		String desc="";
		try {
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next())
				desc = rs.getString(1);
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return desc;
	}

	/**
	 * Método que obtiene el dinero que nos pagan los patrocinadores
	 * @param ruta: atributo que muestra la ruta de la imagen
	 * @return desc que es el valor de dinero almacenado en la base de datos
	 */
	public double obtenerDinero(String ruta) {
		String query = "SELECT dinero FROM PATROCINADORES WHERE rutaFoto='" + ruta +"'";
		double desc=0;
		try {
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next())
				desc = rs.getDouble(1);
			rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return desc;
	}

	/**
	 * Método que obtiene todos los productos registrados y almacenados en la base de datos
	 * @return productos que es un ArrayList con todos los productos almacenados en la base de datos
	 */
	public ArrayList<String> obtenerProductos(){
		ArrayList<String> productos = new ArrayList<String>();
		String query = "SELECT nombre,talla FROM PRODUCTOS";
		try {
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				String nom = rs.getString("nombre");
				String talla = rs.getString("talla");
				if(talla==null)
					talla="unica";
				productos.add(nom+"-"+talla);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productos;
	}


	/**
	 * Método que cuenta los productos que hay en la BD para luego usarlo en el siguiente método
	 * @return num atributo que da el número de productos
	 */
	public int contarProductos() {
		String query = "SELECT COUNT (*) FROM PRODUCTOS";
		int num=0;
		try {
			ResultSet rs = stmt.executeQuery(query);
			num = rs.getInt(1);
			rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return num;
	}


	/**
	 * Método que obtiene todos los productos para insertar en la tabla
	 * @return tabla
	 */
	public Object[][] obtenerTablaProductos(){
		int num = contarProductos();
		Object tabla [][] = new Object[num][15];
		String query = "SELECT * FROM PRODUCTOS";
		try {
			ResultSet rs = stmt.executeQuery(query);
			int i=0;
			while(rs.next()) {
				tabla[i][0] = rs.getString("codigo");
				tabla[i][1] = rs.getString("nombre");
				tabla[i][2] = rs.getDouble("precio");
				tabla[i][3] = rs.getInt("stock");
				tabla[i][4] = rs.getString("descripcion");
				tabla[i][5] = rs.getString("ruta");
				tabla[i][6] = rs.getString("categoria");
				tabla[i][7] = rs.getString("oferta");
				tabla[i][8] = rs.getString("talla");
				tabla[i][9] = rs.getString("color");
				tabla[i][10] = rs.getString("tipo");
				tabla[i][11] = rs.getString("marca");
				tabla[i][12] = rs.getString("equipo");
				tabla[i][13] = rs.getDouble("peso");
				tabla[i][14] = rs.getString("Plataforma");
				i++;
			}
			rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return tabla;
	}



	/**
	 * Método que borra un producto
	 * @param cod: atributo que hace referencia al código del producto que se va a borrar
	 */
	public void borrarProductoJList(String nom) {
		String query = "DELETE FROM PRODUCTOS WHERE nombre='"+nom+"'";
		try {
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Método que muestra un ArrayList con todas las tallas de los productos
	 * @param cod: atributo que hace referencia al código del producto
	 * @return tallas que es una ArrayList con todas las tallas de los productos
	 */
	public ArrayList<String> obtenerTallas(String cod){
		ArrayList<String> tallas = new ArrayList<String>();
		String query = "SELECT talla FROM PRODUCTOS WHERE cod='" + cod +"'";
		try {
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				String t = rs.getString("talla");
				tallas.add(t);
			}
			rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return tallas;
	}


	/**
	 * Método que borra un producto insertando el código del producto y la talla
	 * @param codigo: atributo que hace referencia al código del producto
	 * @param talla: atributo que hace referencia a la talla del producto
	 */
	public void borrarProductoConTalla(String codigo, String nombre, double precio, int stock, String desc,
			String ruta, String cat, boolean oferta, String tipo, String marca, String talla, String color, String equipo, double peso) {
		//Con comprobar el código y la talla valdría para borrarlo
		String query = "DELETE FROM PRODUCTOS WHERE codigo='" + codigo + "'AND talla='"+ talla+"'";
		try {
			stmt.executeUpdate(query);
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método que borra un producto sin talla insertando el código del producto
	 * @param codigo: atributo que hace referencia al código del producto
	 */
	//TODO Comprobar
	public void borrarProductoSinTalla(String codigo) {
		//A los productos que no tienen talla con pasar el código vale
		String query = "DELETE FROM PRODUCTOS WHERE codigo='" + codigo + "'";
		try {
			stmt.executeUpdate(query);
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método que cuenta los usuarios que hay en la tabla para usarlos posteriormente
	 * @return num: que es el número de usuarios que hay almacenados en la BD
	 */
	public int contarUsuarios() {
		String query = "SELECT COUNT(*) FROM USUARIO";
		int num=0;
		try {
			ResultSet rs = stmt.executeQuery(query);
			num = rs.getInt(1);
			rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return num;
	}

	/**
	 * Método que obtiene todos los Usuarios para insertar en la tabla
	 * @return tabla
	 */
	public Object[][] obtenerTablaUsuarios(){
		int num = contarUsuarios();
		Object tabla[][] =new Object[num][8];
		String query = "SELECT * FROM USUARIO";
		try {
			ResultSet rs = stmt.executeQuery(query);
			int i=0;
			while(rs.next()){
				tabla[i][0] = rs.getString("DNI");
				tabla[i][1] = rs.getString("nombre");
				tabla[i][2] = rs.getString("apellido");
				tabla[i][3] = rs.getString("nick");
				tabla[i][4] = rs.getString("contrasenia");
				tabla[i][5] = rs.getInt("numTel");
				tabla[i][6] = rs.getString("domicilio");
				tabla[i][7] = rs.getString("cuentaBancaria");
				tabla[i][8] = rs.getString("Imagen");
				i++;
			}
			rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return tabla;
	}

	/**
	 * Método que inserta un usuario nuevo desde la clase administrador
	 * @param u: atributo u que hace referencia al usuario
	 */
	public void insertarNuevoUsuario(Usuario u) {
		String query = "SELECT * FROM USUARIO WHERE DNI='"+ u.getDNI() +"' AND nombre='" + u.getNombre() + "' AND apellido='" + 
				u.getApellido() + "' AND nick='" + u.getNick() + "' AND contrasenia='" + u.getContrasenia() + "' AND numTel=" + 
				u.getNumTel() + " AND domicilio='" + u.getDomicilio() + "' AND cuentaBancaria='" + u.getCuenta() + "'";
		try {
			ResultSet rs = stmt.executeQuery(query);
			if(!rs.next()) {
				//El usuario no está repetido
				query = "INSERT INTO USUARIO VALUES('" + u.getDNI() + "','" + u.getNombre() +" ','" + u.getApellido() + "','" + u.getNick() + "','" + u.getContrasenia() + "'," + u.getNumTel() + ",'" + u.getDomicilio() + "','" + u.getCuenta() +"'";
				stmt.executeUpdate(query);
			}
			rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método que obtiene todos los usuarios
	 * @return usuarios que es un ArrayList que obtiene una lista de todos los usuarios
	 */
	public ArrayList<String> obtenerUsuarios(){
		ArrayList<String> usuarios = new ArrayList<String>();
		String query = "SELECT nick FROM USUARIO";
		try {
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				String nick = rs.getString("nick");
				usuarios.add(nick);
			}
			rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return usuarios;
	}


	/**
	 * Método que obtiene el precio del producto seleccionado
	 * @param ruta: es el atributo que hace referencia al producto seleccionado
	 * @return precio: que es el precio del producto seleccionado anteriormente
	 */
	public static double obtenerPrecioProducto(String ruta) {
		String query = "SELECT precio FROM PRODUCTOS WHERE ruta='"+ruta+"'";
		ResultSet rs;
		double precio=0;
		try {
			rs = stmt.executeQuery(query);
			precio = rs.getDouble(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return precio;

	}

	/**
	 * Método que obtiene el nombre del producto seleccionado
	 * @param ruta: es el atributo que hace referencia al producto seleccionado
	 * @return nombre: es el nombre del producto seleccionado
	 */
	public static String obtenerNombreProducto(String ruta) {
		String query = "SELECT nombre FROM PRODUCTOS WHERE ruta='" + ruta+"'";
		ResultSet rs;
		String nombre = null;
		try {
			rs = stmt.executeQuery(query);
			nombre = rs.getString(1);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return nombre;
	}

	/**
	 * Método que obtiene un ArrayList de tallas ya que los productos pueden tener más de una talla
	 * @param ruta: es el atributo que hace referencia al producto que hemos seleccionado
	 * @return tallas: que es un ArrayList con todas las tallas que tiene el producto seleccionado
	 */
	public static ArrayList<String> obtenerTallaProducto(String ruta) {
		String query = "SELECT talla FROM PRODUCTOS WHERE ruta='" + ruta + "'";
		ResultSet rs;
		ArrayList<String> tallas = new ArrayList<String>();
		try {
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				tallas.add(rs.getString(1));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return tallas;
	}

	/**
	 * Método que obtiene el tipo del producto
	 * @param ruta: es el atributo que hace referencia al producto
	 * @return tipo: que es el tipo de producto
	 */
	public static String obtenerTipoProducto(String ruta) {
		String query = "SELECT tipo FROM PRODUCTOS WHERE ruta='" + ruta +"'";
		ResultSet rs;
		String tipo = null;
		try {
			rs = stmt.executeQuery(query);
			tipo = rs.getString(1);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return tipo;
	}

	/**
	 * Método que obtiene la descripción del producto seleccionado
	 * @param ruta: es el atributo que hace referencia a dicho producto seleccionado
	 * @return desc: que es la descripción del producto seleccionado
	 */
	public static String obtenerDescProducto(String ruta) {
		String query = "SELECT descripcion FROM PRODUCTOS WHERE ruta='" + ruta +"'";
		ResultSet rs;
		String desc = null;
		try {
			rs = stmt.executeQuery(query);
			desc = rs.getString(1);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return desc;
	}

	/**
	 * Método que guarda la imagen en la base de datos y en la carpeta usuarios del workspace
	 * @param imagen: atributo que hace referencia al imagen
	 * @param nick: atributo que hace referencia al nick del usuario que toma la foto
	 */
	public static void btnGuardar(String imagen, String nick) {
		String query = "UPDATE USUARIO SET Imagen ='"+imagen+"' WHERE nick='"+nick+"'";
		PreparedStatement pst;
		try {
			pst = con.prepareStatement(query);
			if(pst.executeUpdate()>0) {
				JOptionPane.showMessageDialog(null, "REGISTRADO CORRECTAMENTE");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método que obtiene todos los productos comprados por el usuario
	 * @param nom: atributo que hace referencia al nombre del producto comprado
	 * @return
	 */
	public Producto obtenerProductosComprados(String nom) {
		String query = "SELECT * FROM PRODUCTOS WHERE nombre='"+ nom + "'";
		Producto p = null;
		try {
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next())
				p = new Producto (rs.getString("codigo"), rs.getString("nombre"), rs.getDouble("precio"), rs.getInt("stock"), rs.getString("descripcion"), rs.getString("ruta"), rs.getString("categoria"), rs.getString("oferta"), rs.getString("tipo"));
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}

	/**
	 * Método que borra un Usuario
	 * @param nick: atributo que hace referencia al nick del usuario que se va a borrar
	 */
	public void borrarUsuarioJList(String nic) {
		String query = "DELETE FROM USUARIO WHERE nick='" + nic +"'";
		try {
			stmt.executeUpdate(query);
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método que sirve para insertar un producto sin equipo, sin talla y sin peso
	 * Balones de fútbol, balones de baloncesto, botellines, paleteros, protectores y pelotas
	 * @param cod: atributo que hace referencia al código del producto
	 * @param nom: atributo que hace referencia al nombre del producto
	 * @param prec: atributo que hace referencia al precio del producto
	 * @param stoc: atributo que hace referencia al stock del producto
	 * @param desc: atributo que hace referencia a la descripción del producto
	 * @param ruta: atributo que hace referencia a la ruta del producto
	 * @param cat: atributo que hace referencia a la categoria del producto
	 * @param oferta: atributo que hace referencia a si el producto tiene oferta o no
	 * @param tipo: atributo que hace referencia al tipo de producto
	 * @param col: atributo que hace referencia al color del producto
	 * @param marca: atributo que hace referencia a la marca del producto
	 */
	//TODO COMPROBAR
	//Estoy separando los métodos de insertar ya que no todos los productos deben de tener todos los atributos
	//(Balones de Fútbol y Baloncesto, Botellines, Paleteros, protectores y pelotas
	public static void insertarProductosSinEquipoTallaPeso(String cod, String nom, double prec, int stoc, String desc, 
			String ruta, String cat, String oferta, String tipo, String col, String marca) {
		String query = "SELECT * FROM PRODUCTOS WHERE codigo='" + cod + "' AND nombre='" + nom + "' AND precio=" + prec +  
				" AND descripcion='" + desc + "' AND ruta='" + ruta + "' AND categoria='" + cat + "' AND oferta ='" + oferta + "' AND tipo ='" + tipo +
				"' AND color ='" + col + "' AND marca='" + marca + "'";
		try {
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()) {
				//Si el producto está repetido añadimos una unidad al stock
				//El stock habría que quitarlo no??. DUDAS CON EL STOCK 
				query = "UPDATE PRODUCTOS SET stock = stock " + stoc+ " WHERE codigo='" + cod + "' AND nombre='" + nom + "' AND precio=" + prec +    
						" AND descripcion='" + desc + "' AND ruta='" + ruta + "' AND categoria='" + cat + "' AND oferta ='" + oferta + "' AND tipo ='" + tipo  +  
						"' AND color ='" + col + "' AND marca='" + marca + "'";
				stmt.executeUpdate(query);
			}else {
				//El producto no está registrado
				query = "INSERT INTO PRODUCTOS VALUES('" + cod +"','" + nom + "'," + prec + "," + stoc + ",'" + desc + "','" + ruta
						+"','" + cat + "','" + oferta + "','" + tipo + "','" + col + "','" + marca +"'";
				stmt.executeUpdate(query);
			}
			rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método para insertar productos con equipo y talla
	 * Camisetas, pantalones, sudaderas...
	 * @param cod: atributo que hace referencia al código del producto
	 * @param nom: atributo que hace referencia al nombre del producto
	 * @param prec: atributo que hace referencia al precio del producto
	 * @param stoc: atributo que hace referencia al stock del producto
	 * @param desc: atributo que hace referencia a la descripción del producto
	 * @param ruta: atributo que hace referencia a la ruta del producto
	 * @param cat: atributo que hace referencia a la categoría del producto
	 * @param oferta: atributo que hace referencia a si el producto tiene oferta o no
	 * @param talla: atributo que hace referencia a la talla del producto
	 * @param col: atributo que hace referencia al color del producto
	 * @param tipo: atributo que hace referencia al tipo del producto
	 * @param marca: atributo que hcae referencia a la marca del producto
	 * @param equipo: atributo que hace referencia al equipo del producto
	 */
	//TODO COMPROBAR
	//Este hace referencia a los productos que tienen equipo y talla (Camisetas, pantalones y sudaderas de Fútbol y Basket, Cascos y maillots)
	public static void insertarProductosConEquipoTalla(String cod, String nom, String prec, String stoc, String desc, String ruta, 
			String cat, String oferta, String talla, String col, String tipo, String marca, String equipo) {

		String query = "SELECT * FROM PRODUCTOS WHERE codigo='" + cod + "' AND nombre='" + nom + "' AND precio=" + prec +  " AND descripcion='" + desc +
				"' AND ruta ='" + ruta + "' AND categoria='" + cat + "' AND oferta='" + oferta + " ' AND talla='" + talla + "' AND color='" + col + "' AND tipo='" + tipo +
				"' AND marca='" + marca + "'AND equipo='" + equipo + "'";
		try {
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()) {
				//Si el producto está repetido añadimos una unidad al stock
				//El stock habria que ponerlo? DUDAS STOCK
				query = "UPDATE PRODUCTOS SET stock = stock " + stoc+ " WHERE codigo='" + cod + "' AND nombre='" + nom + "' AND precio=" + prec +     
						" AND descripcion='" + desc + "' AND ruta='" + ruta + "' AND categoria='" + cat + "' AND oferta ='" + oferta + "' AND talla='" + talla + "' AND tipo ='" + tipo  +   
						"' AND color ='" + col + "' AND marca='" + marca + "' AND equipo='" + equipo + "'";
				stmt.executeUpdate(query);
			}else {
				//El producto no está registrado
				query = "INSERT INTO PRODUCTOS VALUES('" + cod + "','" + nom + "'," + prec + "," + stoc + ",'" + desc + "','" + ruta + "','" + cat + "','" + oferta + 
						"','" + talla + "','" + tipo + "','" + col + "','" + marca + "','" + equipo + "'";
				stmt.executeUpdate(query);
			}
			rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método que sirve para insertar los videojuegos de pago
	 * @param cod: atributo que hace referencia al código del producto
	 * @param nom: atributo que hace referencia al nombre del producto
	 * @param prec: atributo que hace referencia al precio del producto
	 * @param stoc: atributo que hace referencia al stock del producto
	 * @param desc: atributo que hace referencia a la descripción del producto
	 * @param ruta: atributo que hace referencia a la ruta del producto
	 * @param cat: atributo que hace referencia a la categoría del producto
	 * @param oferta: atributo que hace referencia a si el producto tiene oferta o no
	 * @param tipo: atributo que hace referencia al tipo de producto
	 * @param plat: atributo que hace referencia a la plataforma del producto
	 */
	//TODO COMPROBAR
	//En este caso son los artículos de videojuegos de pago
	public static void insertarVideojuegosPago(String cod, String nom, String prec, String stoc, String desc, 
			String ruta, String cat, String oferta, String tipo, String plat) {
		String query = "SELECT * FROM PRODUCTOS WHERE codigo='" + cod + "' AND nombre='" + nom + "' AND precio=" + prec +  
				" AND descripcion='" + desc + "' AND ruta='" + ruta + "' AND categoria='" + cat + "' AND oferta ='" + oferta + "' AND tipo ='" + tipo + "' AND plataforma ='" + plat + "'";
		try {
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()) {
				//Si el producto está repetido añadimos una unidad al stock
				//El stock habría que quitarlo?. DUDAS CON EL STOCK
				query = "UPDATE PRODUCTOS SET stock = stock " + stoc+ " WHERE codigo='" + cod + "' AND nombre='" + nom + "' AND precio=" + prec  +   
						" AND descripcion='" + desc + "' AND ruta='" + ruta + "' AND categoria='" + cat + "' AND oferta ='" + oferta + "' AND tipo ='" + tipo  +  
						"' AND plataforma ='" + plat + "'";
				stmt.executeUpdate(query);
			}else {
				//El producto no está registrado
				query = "INSERT INTO PRODUCTOS VALUES('" + cod +"','" + nom + "'," + prec + "," + stoc + ",'" + desc + "','" + ruta
						+"','" + cat + "','" + oferta + "','" + tipo + "','" + plat +"'";
				stmt.executeUpdate(query);
			}
			rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método que borra los usuarios desde la ventana Administrador
	 * @param dni: hace referencia al dni del usuario a eliminar
	 * @param nick: hace referencia al nick del usuario a eliminar
	 * @param con: hace referencia a la contraseña del usuario a eliminar
	 */
	//TODO 
	//Mirar si está bien. Es para borrar los usuarios desde la VentanaAdminUsuarios
	//Me ha obligado a poner static
	public static void borrarUsuario(String dni, String nick, String con) {
		//Con saber el DNI, el nick y la contraseña valdría para borrar un usuario no?
		//Como comprobar si el usuario está registrado
		String query = "DELETE FROM USUARIO WHERE DNI='" + dni + "' AND nick='" + nick + "' AND contrasenia='" + con + "'";
		try {
			stmt.executeUpdate(query);
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método que sirve para modificar los atributos de los usuarios 
	 * @param u: atributo que hace referencia a la clase Usuario
	 */
	//TODO
	//Mirar si está bien.
	public static void modificarUsuario(Usuario u) {
		String query = "UPDATE USUARIO SET DNI='" + u.getDNI() + "' , nombre='" + u.getNombre() + "' , apellido='" + u.getApellido() +
				"' , nick='" + u.getNick() + "' , contrasenia='" + u.getContrasenia() + "' , numTel='" + u.getNumTel() + 
				"' , domicilio='" + u.getDomicilio() + "' , cuentaBancaria='" + u.getCuenta() + "' WHERE nick='" + u.getNick()+"'" ;//+ "' AND contrasenia='" + u.getContrasenia() + "'";
		try {
			stmt.executeUpdate(query);
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método que obtiene la dirección del usuario
	 * @param nick: es el atributo que hace referencia al usuario que ha iniciado sesión
	 * @return domicio: que es el domicilio del usuario a donde hay que mandar el pedido
	 */
	public static String obtenerDireccionUsuario(String nick) {
		String query = "SELECT domicilio FROM USUARIO WHERE nick='" + nick + "'";
		ResultSet rs;
		String domicilio = null;
		try {
			rs = stmt.executeQuery(query);
			domicilio = rs.getString(1);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return domicilio;
	}

	/**
	 * Método que devuelve el nick del usuario que está realizando las compras
	 * @param nick: atributo que hace referencia al nick del usuario
	 * @return: nic que es el nick del usuario que está realizando la compra
	 */
	//TODO
	public static String obtenerNick(String nick) {
		String query = "SELECT nick FROM USUARIO WHERE nick='" + nick + "'";
		ResultSet rs;
		String nic = null;
		try {
			rs = stmt.executeQuery(query);
			nic = rs.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nic;
	}
	
	/**
	 * Método que obtiene el código del producto seleccionado
	 * @param ruta es el atributo que hace referencia al producto seleccionado
	 * @return cod: que es el código del producto seleccionado
	 */
	public static String obtenerCodProducto(String ruta) {
		String query = "SELECT codigo FROM PRODUCTOS WHERE ruta='" + ruta + "'";
		ResultSet rs;
		String cod = null;
		try {
			rs = stmt.executeQuery(query);
			cod = rs.getString(1);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return cod;
	}
	
	/**
	 * Método que obtiene los productos con tallas
	 * @param nom: atributo que hace referencia al nombre del producto
	 * @return pt :todos los productos con talla
	 */
	public ProductosConTalla obtenerProductosConTallas(String nom) {
		String query = "SELECT * FROM PRODUCTOS WHERE nombre='" + nom +"'";
		ProductosConTalla pt = null;
		try {
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next())
				pt = new ProductosConTalla (rs.getString("talla"), rs.getString("equipo"), rs.getDouble("peso"));
			rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return pt;
	}


}
