package Datos;

public class ProductoVideojuegos extends Producto{
	
	//REVISAR ATRIBUTOS
	private String plataforma;
	
	public ProductoVideojuegos() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ProductoVideojuegos(String cod, String nombre, double precio, int stock, String desc, String ruta,
			String categoria, boolean oferta, String tipo) {
		super(cod, nombre, precio, stock, desc, ruta, categoria, oferta, tipo);
		// TODO Auto-generated constructor stub
	}

	public ProductoVideojuegos(String cod, String nombre, double precio, int stock, String desc, String ruta,
			String categoria, boolean oferta, String tipo, String plataforma) {
		super(cod, nombre, precio, stock, desc, ruta, categoria, oferta, tipo);
		this.plataforma = plataforma;
	}

	public String getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}

	
	@Override
	public String toString() {
		return "ProductoVideojuegos [plataforma=" + plataforma + " ]";
	}
	
	

}
