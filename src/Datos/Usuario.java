package Datos;

public class Usuario {

	private String DNI;
	private String nombre;
	private String apellido;
	private String nick;
	private String contrasenia;
	private String numTel;
	private String domicilio;
	private String cuentaBancaria;


	/**
	 * Constructor vacío de la clase usuario
	 */
	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}


	/**
	 * Constructor con parametros de la Clase Usuario
	 * @param DNI : DNI del usuario
	 * @param nombre: Nombre del usuario
	 * @param apellido: Apellido del usuario
	 * @param contrasenia : Contrasenia del usuario
	 * @param nick : Nick del usuario
	 * @param cuenta : Cuenta bancaria del usuario
	 */
	public Usuario(String dNI, String nombre, String apellido,String nick ,String contrasenia , String numTel, String domicilio, String cuentaBancaria) {
		super();
		DNI = dNI;
		this.nombre = nombre;
		this.apellido = apellido;
		this.nick = nick;
		this.contrasenia = contrasenia;
		this.numTel=numTel;
		this.domicilio=domicilio;
		this.cuentaBancaria = cuentaBancaria;
	}

	/**
	 * Métodos getters y Setters de la clase Usuario
	 * @return
	 */

	public String getDNI() {
		return DNI;
	}


	public void setDNI(String dNI) {
		DNI = dNI;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNick() {
		return nick;
	}


	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getContrasenia() {
		return contrasenia;
	}


	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getNumTel() {
		return numTel;
	}
	
	public void setNumTel(String numTel) {
		this.numTel=numTel;
	}
	
	public String getDomicilio() {
		return domicilio;
	}
	
	public void setDomicilio(String domicilio) {
		this.domicilio=domicilio;
	}

	public String getCuenta() {
		return cuentaBancaria;
	}


	public void setCuenta(String cuentaBancaria) {
		this.cuentaBancaria = cuentaBancaria;
	}


	/**
	 * Método toString de la Clase Usuario
	 */
	@Override
	public String toString() {
		return "Usuario [DNI=" + DNI + ", nombre=" + nombre + ", apellido=" + apellido + ", nick=" + nick
				+ ", contrasenia=" + contrasenia + ", numTel=" + numTel + ", domicilio=" + domicilio
				+ ", cuentaBancaria=" + cuentaBancaria + "]";
	}







}
