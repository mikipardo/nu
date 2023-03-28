package modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity//crear entidad
public class ProductoCarrito {

	@ManyToOne
	private Carrito carrito;
	
	@ManyToOne
	private Camiseta camiseta;
	
	private int cantidad;
	
	@Id
	@GeneratedValue
	private int id;
	
	public ProductoCarrito() {
		// TODO Auto-generated constructor stub
	}

	public Carrito getCarrito() {
		return carrito;
	}

	public void setCarrito(Carrito carrito) {
		this.carrito = carrito;
	}

	public Camiseta getCamiseta() {
		return camiseta;
	}

	public void setCamiseta(Camiseta camiseta) {
		this.camiseta = camiseta;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

}
