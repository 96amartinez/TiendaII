package Datos;

public class Producto {
	
	private String cod;
	private String nombre;
	private double precio;
	private int stock;
	private String desc;
	private String ruta;
	private String categoria; //Fútbol, padel, baloncesto, ciclismo y videojuegos
	private String oferta;
	private String tipo;
	
	public Producto(String cod, String nombre, double precio, int stock, String desc, String ruta, String categoria, String oferta, String tipo) {
		super();
		this.cod = cod;
		this.nombre = nombre;
		this.precio = precio;
		this.stock = stock;
		this.desc = desc;
		this.ruta = ruta;
		this.categoria = categoria;
		this.oferta = oferta;
		this.tipo = tipo;
	}

	public Producto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	
	public String getCategoria() {
		return categoria;
	}
	
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public String getOferta() {
		return oferta;
	}
	
	public void setOferta(String oferta) {
		this.oferta = oferta;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Producto [cod=" + cod + ", nombre=" + nombre + ", precio=" + precio + ", stock=" + stock + ", desc="
				+ desc + ", ruta=" + ruta + ", categoria=" + categoria + ", oferta=" + oferta + ", tipo=" + tipo + "]";
	}

	
	
	

}
