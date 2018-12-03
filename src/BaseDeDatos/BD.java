package BaseDeDatos;

import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;

import Datos.Producto;
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
			String Usuario = "CREATE TABLE USUARIO(DNI string, nombre string, apellido string, nick string, contrasenia string, numTel string, domicilio string, cuentaBancaria string)";

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
	public ArrayList<String> obtenerRutasConFiltroPadel(String tipo,double peso,String color, String marca, String cat){
		String query = "SELECT DISTINCT(ruta) FROM PRODUCTOS WHERE (tipo='"+tipo+"' OR peso IS NOT NULL AND peso>="+(peso-200)+" OR peso<="+peso +" OR color='"+color+"' OR marca='"+marca+"') AND categoria'=" + cat+"'";
		ArrayList<String> aRutas = new ArrayList<String>();
		ResultSet rs;
		//	System.out.println(tipo+" "+peso+" "+color+" "+marca);
		try {
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				String ruta = rs.getString(1);
				//System.out.println(ruta);
				aRutas.add(ruta);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	public ArrayList<String> obtenerRutasConFiltroFutbol(String tipo, String color, String marca, String talla, String equipo, String cat){

		String query= "SELECT DISTINCT(ruta) FROM PRODUCTOS WHERE (tipo='" + tipo + "' AND color ='" + color +"' AND marca='" + marca +"' AND talla='" + talla +"' AND equipo='" + equipo +"') AND categoria='"+cat+"'";
		ArrayList<String> aRutas = new ArrayList<String>();
		ResultSet rs;
		try {
			rs = stmt.executeQuery(query);
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


	public static void insertarArtículoCiclismo(String codigo, String nombre, double precio, int stock, String desc,
			String ruta, String cat, boolean oferta, String tipo, String marca, String talla, String color, String equipo, double peso) {

		//Faltan talla equipo y peso
		String query = "SELECT * FROM PRODUCTOS WHERE codigo='" + codigo +"' AND nombre ='" + nombre + "' AND precio=" + precio + "AND stock='" + stock+ 
				"' AND descripcion='" + desc + "' AND ruta='" + ruta +"' AND categoria='" + cat +"' AND oferta =" + oferta + "AND tipo='" + tipo +"' AND marca ='" + marca +
				"' AND talla='" + talla +"' AND color='" + color + "'AND equipo ='" + equipo +"' AND peso=" + peso +";";
		try {
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()) {
				//El producto está repetido
				//Si el producto tiene el mismo código y la misma talla se le suma uno al stock(COMPROBAR)
				query = "UPDATE PRODUCTOS SET stock = stock +1 WHERE cododigo='" + codigo + "'AND talla='" + talla+"'";
				stmt.executeUpdate(query);
			}else {
				//El producto no está repetido
				//Faltan talla equipo y peso
				query = "INSERT INTO PRODUCTOS VALUES('"+codigo +"','" + nombre+ "'," + precio + ",1"+ ",'" + desc + "','" + ruta+ "','" + cat+ "'," + oferta +  ",'" + tipo + "','" + marca
						+ "','" + color + "')";
				stmt.executeUpdate(query);
			}
			rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public void borrarProducto(String codigo, String nombre, double precio, int stock, String desc,
			String ruta, String cat, boolean oferta, String tipo, String marca, String talla, String color, String equipo, double peso) {
		//Con comprobar el código y la talla valdría para borrarlo
		String query = "DELETE FROM PRODUCTOS WHERE codigo='" + codigo + "'AND talla='"+ talla+"'";
		try {
			stmt.executeUpdate(query);
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}


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


	public Object[][] obtenerTablaUsuarios(){
		int num = contarUsuarios();
		Object tabla[][] =new Object[num][7];
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
				i++;
			}
			rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return tabla;
	}

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
}
