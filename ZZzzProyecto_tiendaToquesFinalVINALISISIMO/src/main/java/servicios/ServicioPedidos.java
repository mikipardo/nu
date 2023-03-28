package servicios;

import java.util.List;

import datos.servicioWeb.ResumenPedido;
import modelo.Pedido;
import modelo.Usuario;

public interface ServicioPedidos {

	public void procesarPaso1(String nombreCompleto, String direccion, String provincia, String fecha, String horario,
			Usuario usuario);

	void procesarPaso2(String titular, String numero, String fechaCaducidad, Usuario usuario);
	
	void procesarPaso2BIS( String comentario, Usuario usuario);

	ResumenPedido obtenerResumenDelPedido(Usuario usuario);

	void confirmarPedido(Usuario usuario);
	
	public  double totalCompra(Usuario usuario);

	public List<Pedido> obtenerPedidos();

	Pedido obtenerPedidoPorId(int idPedido);

	void actualizarEstadoPedido(int idPedido, String estado);

}
