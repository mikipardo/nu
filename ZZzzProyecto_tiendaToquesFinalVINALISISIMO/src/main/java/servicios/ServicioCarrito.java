package servicios;

import java.util.List;
import java.util.Map;

import modelo.ProductoCarrito;
import modelo.Usuario;

public interface ServicioCarrito {
	
	public void agregarProducto(Usuario u, int idProducto, int cantidad);	
	public List<Map<String, Object>> obtenerProductoCarrito(Usuario u);	
	public void actualizaProducto(Usuario usuario,int idProducto,int cantidad);
	public void borrarCamisetaCarrito(Usuario usuario,int idProducto);
	public ProductoCarrito productoCarritoPorID(Usuario usuario,int idProducto);
}
