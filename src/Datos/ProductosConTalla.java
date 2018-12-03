package Datos;

public class ProductosConTalla extends ProductosDeportivos{

	private String talla;
	private String equipo;
	private double peso;
	
	public ProductosConTalla() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ProductosConTalla(String cod, String nombre, double precio, int stock, String desc, String ruta,
			String categoria, boolean oferta, String tipo, String color, String marca) {
		super(cod, nombre, precio, stock, desc, ruta, categoria, oferta, tipo, color, marca);
		// TODO Auto-generated constructor stub
	}
	
	public ProductosConTalla(String cod, String nombre, double precio, int stock, String desc, String ruta,
			String categoria, boolean oferta, String tipo) {
		super(cod, nombre, precio, stock, desc, ruta, categoria, oferta, tipo);
		// TODO Auto-generated constructor stub
	}

	public ProductosConTalla(String talla, String equipo, double peso) {
		super();
		this.talla = talla;
		this.equipo = equipo;
		this.peso = peso;
	}

	public String getTalla() {
		return talla;
	}

	public void setTalla(String talla) {
		this.talla = talla;
	}

	public String getEquipo() {
		return equipo;
	}

	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	@Override
	public String toString() {
		return "ProductosConTalla [talla=" + talla + ", equipo=" + equipo + ", peso=" + peso + "]";
	}
	
	
	
	
}
