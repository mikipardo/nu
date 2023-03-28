package modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.jmx.export.annotation.ManagedOperationParameter;

@Entity
public class ProductoPedido {

	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne
	private Camiseta camiseta;
	@ManyToOne
	private Pedido pedido;
	private int cantidad;
	
	
	public ProductoPedido() {
		// TODO Auto-generated constructor stub
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Camiseta getCamiseta() {
		return camiseta;
	}


	public void setCamiseta(Camiseta camiseta) {
		this.camiseta = camiseta;
	}


	public Pedido getPedido() {
		return pedido;
	}


	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}


	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	

}
