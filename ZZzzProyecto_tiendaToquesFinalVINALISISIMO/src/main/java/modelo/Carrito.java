package modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import javax.persistence.CascadeType;



@Entity //establecemos com entidad BD
public class Carrito {

	public Carrito() {
		// TODO Auto-generated constructor stub
	}
	
	@OneToOne// campo usuario con asociacion uno a uno para que en tabla Carrito salga id usuario
	private Usuario usuario;
	
//	 campo lista de ProductosCarrito de uno a muchos con borrado en cascada
	@OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL)
	private List<ProductoCarrito> productosCarrito = 
		new ArrayList<ProductoCarrito>();

	@Id//id de entidad
	@GeneratedValue//autogenerado el id
	private int id;
	
	public List<ProductoCarrito> getProductosCarrito() {
		return productosCarrito;
	}

	public void setProductosCarrito(List<ProductoCarrito> productosCarrito) {
		this.productosCarrito = productosCarrito;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
