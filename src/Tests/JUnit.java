package Tests;

import static org.junit.Assert.*;
import BaseDeDatos.BD;
import Datos.Producto;
import Datos.ProductosConTalla;
import Datos.Usuario;

import org.junit.Test;

import junit.framework.TestCase;

//Tiene que heredar de TestCase
public class JUnit extends TestCase{

	public void setUp() throws Exception {
		//Como vamos a testear una clase que sólo tiene métodos que son static no hace falta poner nada
	}

	//TODO JUNIT PARA COMPROBAR QUE EL USUARIO EXISTE, HAY QUE HACER MÁS JUNITS
	@Test
	public void testExisteUsuario() {
	
		BD.conectar();

		//Tanto el Usuario "u" como el Usuario "u1" están registrados en la BD, el Usuario "u2" no.
		Usuario u = new Usuario("11111111A", "Aitor", "Martinez", "96amartinez", "96amartinez", "623665897", "Barakaldo", "1234567890");
		Usuario u1 = new Usuario("22222222A", "Aitor2", "Martinez2", "admin", "admin", "699883451", "Barakaldo", "9876543210");
		Usuario u2 = new Usuario("W", "W", "W", "W", "W", "W", "W", "W");

		//Si el usuario está registrado == 2, si no está registrado ==0
		assertTrue("Usuario registrado en la BD", BD.existeUsuario(u)==2);
		assertTrue("Usuario registrado en la BD", BD.existeUsuario(u1)==2);
		assertTrue("Usuario no registrado esta en la BD", BD.existeUsuario(u2)==0);
		
		//assertTrue("NO", u2.getApellido().equals("W"));
		

		
	}
	

}
