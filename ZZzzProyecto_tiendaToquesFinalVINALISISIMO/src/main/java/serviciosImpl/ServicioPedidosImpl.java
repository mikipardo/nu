package serviciosImpl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import constantes.StatusPedido;
import constantesSQL.ConstantesSQL;
import datos.servicioWeb.ResumenPedido;
import modelo.Carrito;
import modelo.Pedido;
import modelo.ProductoCarrito;
import modelo.ProductoPedido;
import modelo.Usuario;
import servicios.ServicioCarrito;
import servicios.ServicioPedidos;

@Service
@Transactional
public class ServicioPedidosImpl implements ServicioPedidos {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private ServicioCarrito servicioCarrito;

	@Override
	public List<Pedido> obtenerPedidos() {
		@SuppressWarnings("unchecked")
		//le metemos la tabla solo con la consuta from es un select * from... si quieres restriccion hay qye meter wher
		List<Pedido> pedidos = sessionFactory.getCurrentSession().createQuery("From Pedido").list();
		return pedidos;
	}

	@Override
	public Pedido obtenerPedidoPorId(int idPedido) {
		//devuelve un objeto pedido pasando id 
		return (Pedido)sessionFactory.getCurrentSession().get(Pedido.class, 
				idPedido);
	}

	@Override
	public void actualizarEstadoPedido(int idPedido, String estado) {
		Pedido p = obtenerPedidoPorId(idPedido);
		p.setStatus(estado);
		sessionFactory.getCurrentSession().update(p);
	}

	@Override
	public void procesarPaso1(String nombreCompleto, String direccion, String provincia, String fecha, String horario,
			Usuario usuario) {

		// Nos aseguramos que un usuario solo tiene un pedido en proceso
		Pedido pedido = obtenerPedidoActual(usuario);

		pedido.setUsuario(usuario);
		pedido.setNombreCompleto(nombreCompleto);
		pedido.setDireccion(direccion);
		pedido.setProvincia(provincia);
		pedido.setFecha(fecha);
		pedido.setHorario(horario);
		sessionFactory.getCurrentSession().save(pedido);

	}

	// obtiene el pedido en estado "en proceso" del usuario
	// actual, y si no existe lo crea
	private Pedido obtenerPedidoActual(Usuario usuario) {
		Usuario uBaseDatos = (Usuario) sessionFactory.getCurrentSession().get(Usuario.class, usuario.getId());

		// Metemos un object para que no de NULL POINT EXCEPTION en caso de null |POR
		// CASTEARLO|
		Object pedidoEnProceso = sessionFactory.getCurrentSession().createCriteria(Pedido.class)
				// campo estado-> atributo en ModeloPedido
				.add(Restrictions.eq("status", StatusPedido.EN_PROCESO))
				// campo idUser de pedido-> atributo en ModeloPedido
				.add(Restrictions.eq("usuario.id", usuario.getId())).uniqueResult();// la id solo unico resultado
		// es un truco para evitar errores en casteo, toma nota
		Pedido p = null;
		// a continuacion nos aseguramos que existe y si no lo creamos
		if (pedidoEnProceso == null) {
			p = new Pedido();
			p.setStatus(StatusPedido.EN_PROCESO);
			p.setUsuario(uBaseDatos);
		} else {
			p = (Pedido) pedidoEnProceso;// Si existe la le casteamos
		}
		return p;

	}// end obtenerPedidoActual

	@Override
	public void procesarPaso2(String titular, String numero, String fechaCaducidad, Usuario usuario) {
		Pedido p = obtenerPedidoActual(usuario);
		p.setTitularTarjeta(titular);
		p.setNumeroTarjeta(numero);
		p.setFechaCaducidad(fechaCaducidad);
		sessionFactory.getCurrentSession().update(p);
	}
	
	@Override
	public void procesarPaso2BIS(String comentario, Usuario usuario) {
		Pedido p = obtenerPedidoActual(usuario);
		p.setComentario(comentario);
		sessionFactory.getCurrentSession().update(p);
	}

	@Override
	public ResumenPedido obtenerResumenDelPedido(Usuario usuario) {
		ResumenPedido resumen = new ResumenPedido();
		Pedido p = obtenerPedidoActual(usuario);
		double totalPrecio= totalCompra(usuario);
		// checkOut1
		resumen.setNombreCompleto(p.getNombreCompleto());
		resumen.setDireccion(p.getDireccion());
		resumen.setProvincia(p.getProvincia());
		resumen.setFecha(p.getFecha());
		resumen.setHorario(p.getHorario());

		// checkOut2
		resumen.setTitularTarjeta(p.getTitularTarjeta());
		resumen.setNumeroTarjeta(p.getNumeroTarjeta());
		resumen.setFechaCaducidad(p.getFechaCaducidad());
		resumen.setComentario(p.getComentario());
		resumen.setTotalCompra(totalPrecio);
		resumen.setCamisetas(servicioCarrito.obtenerProductoCarrito(usuario));

		return resumen;
	}

	@Override
	public void confirmarPedido(Usuario usuario) {
		// TODO traemos el pedido acual
		Pedido pedido = obtenerPedidoActual(usuario);
		Usuario uBaseDatos = (Usuario) sessionFactory.getCurrentSession().get(Usuario.class, usuario.getId());
		double totalPrecio= totalCompra(usuario);
		
		// pasar los productos del carrito a producto pedido
		// pedimos el carrito por el user
		Carrito carrito = uBaseDatos.getCarrito();
		// comprobamos si lo hemos traido a null y entonces for
		if (carrito != null) {
			// por cada producto que haya en el carrito lo metemos en ProductoPedido
			for (ProductoCarrito productoCarrito : carrito.getProductosCarrito()) {
				ProductoPedido productoPedido = new ProductoPedido();
				productoPedido.setCantidad(productoCarrito.getCantidad());
				productoPedido.setCamiseta(productoCarrito.getCamiseta());
				pedido.getProductosPedido().add(productoPedido);
				productoPedido.setPedido(pedido);
				sessionFactory.getCurrentSession().save(productoPedido);
			}
			// proccedemos a eliminar el carrito
			SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(ConstantesSQL.BORRAR_PRODUCTOS_CARRITO);
			query.setParameter("carrito_id", carrito.getId());
			query.executeUpdate();
			// finalizamos pedido
			pedido.setTotalCompra(totalPrecio);
			pedido.setStatus(StatusPedido.COMPLETADO);
			sessionFactory.getCurrentSession().update(pedido);
		}
	}

	@Override
	public double totalCompra(Usuario usuario) {
		Usuario uBaseDatos = (Usuario) sessionFactory.getCurrentSession().get(Usuario.class, usuario.getId());
		Carrito carrito = uBaseDatos.getCarrito();
		System.out.println("********************************************");
		System.out.println(carrito.getId());
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(ConstantesSQL.SQL_SUMAR_TOTAL);
	
			query.setParameter("num_id", carrito.getId());
			
		
		double total = Double.parseDouble(String.valueOf(query.list().get(0)));
		
		System.out.println("Mira aqui //////////////////" +total);
		
		
		return total;
	}


}
