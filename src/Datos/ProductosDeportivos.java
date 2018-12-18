package Datos;

public class ProductosDeportivos extends Producto{
	
	private String color;
	private String marca;
	
	public ProductosDeportivos() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductosDeportivos(String cod, String nombre, double precio, int stock, String desc, String ruta,
			String categoria, String oferta, String tipo) {
		super(cod, nombre, precio, stock, desc, ruta, categoria, oferta, tipo);
		// TODO Auto-generated constructor stub
	}
	
	public ProductosDeportivos(String cod, String nombre, double precio, int stock, String desc, String ruta,
			String categoria, String oferta, String tipo, String color, String marca) {
		super(cod, nombre, precio, stock, desc, ruta, categoria, oferta, tipo);
		this.color = color;
		this.marca = marca;
	}
	
	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public String getMarca() {
		return marca;
	}
	
	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	
	@Override
	public String toString() {
		return "ProductosDeportivos [color=" + color + ", marca=" + marca + "]";
	}
	
	
	

}
