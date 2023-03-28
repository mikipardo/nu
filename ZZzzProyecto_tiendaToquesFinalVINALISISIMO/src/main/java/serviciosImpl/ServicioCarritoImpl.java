package serviciosImpl;

import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import constantesSQL.ConstantesSQL;
import modelo.Camiseta;
import modelo.Carrito;
import modelo.ProductoCarrito;
import modelo.Usuario;
import servicios.ServicioCarrito;

@Service // conecta a varios repositorios y agrupa su funcionalidad. solo para la logica
			// empresarial no hay mucha relevancia
@Transactional
public class ServicioCarritoImpl implements ServicioCarrito {

	public ServicioCarritoImpl() {
		// TODO Auto-generated constructor stub
	}

	// una alternativa a @Autowired es @Resource que hace basicamente lo mismo
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void agregarProducto(Usuario u, int idProducto, int cantidad) {
		// TODO Auto-generated method stub
		// obtener el carrito del usuario o crearlo si no existe
		// Ver usaurio en BD la info del usuario recibido como parametro que es el
		// guardado en sesion
		Usuario uBaseDeDatos = (Usuario) sessionFactory.getCurrentSession().get(Usuario.class, u.getId());
		// a un usuario le metemos un campo carrti con relacion oneToOne
		Carrito c = uBaseDeDatos.getCarrito();

		// si no esta creado el carrito lo hacemos nosotoros
		if (c == null) {
			c = new Carrito();
			c.setUsuario(uBaseDeDatos);
			uBaseDeDatos.setCarrito(c);
			sessionFactory.getCurrentSession().save(c);
		}
		// creamos un for para ver si se repite el producto
		boolean producto_en_carrito = false;

		for (ProductoCarrito pc_en_carrito : c.getProductosCarrito()) {
			if (pc_en_carrito.getCamiseta().getId() == idProducto) {
				producto_en_carrito = true;
				pc_en_carrito.setCantidad(pc_en_carrito.getCantidad() + cantidad);
				sessionFactory.getCurrentSession().merge(pc_en_carrito);
			}
		}
		// si el producto no esta en el carrito
		// si no existe lo creamos y le metemos los valores del campo al productoCarrito
		if (!producto_en_carrito) {
			ProductoCarrito pc = new ProductoCarrito();
			pc.setCarrito(c);
			pc.setCantidad(cantidad);
			pc.setCamiseta((Camiseta) sessionFactory.getCurrentSession().get(Camiseta.class, idProducto));
			sessionFactory.getCurrentSession().save(pc);
		}

	}

	@Override
	public List<Map<String, Object>> obtenerProductoCarrito(Usuario u) {
		// TODO Auto-generated method stub
		Usuario uBaseDatos = (Usuario) sessionFactory.getCurrentSession().get(Usuario.class, u.getId());
		Carrito carrito = uBaseDatos.getCarrito();
		List<Map<String, Object>> res = null;
		
		if (carrito != null) {
			SQLQuery query = sessionFactory.getCurrentSession()
					.createSQLQuery(ConstantesSQL.SQL_OBTENER_PRODUCTOS_CARRITO);
			query.setParameter("carrito_id", carrito.getId());
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			//https://www.tutorialspoint.com/springjdbc/springjdbc_sqlquery.htm para ver docu de mapear a objetos
			System.out.println("************************");
			System.out.println(res);
			res = query.list();
		}
		return res;
	}

	@Override // Usado para actualizar solo en checkOut pedidosProducto
	public void actualizaProducto(Usuario usuario, int idProducto, int cantidad) {
		Usuario uBaseDeDatos = (Usuario) sessionFactory.getCurrentSession().get(Usuario.class, usuario.getId());

		Carrito carrito = uBaseDeDatos.getCarrito();

		for (ProductoCarrito pc_en_carrito : carrito.getProductosCarrito()) {
			if (pc_en_carrito.getCamiseta().getId() == idProducto) {
				pc_en_carrito.setCantidad(cantidad);
				sessionFactory.getCurrentSession().merge(pc_en_carrito);

			}
		}
	}
	@Override
	public ProductoCarrito productoCarritoPorID(Usuario usuario,int idProducto) {
		Usuario uBaseDeDatos = (Usuario) sessionFactory.getCurrentSession().get(Usuario.class, usuario.getId());
		Carrito carrito = uBaseDeDatos.getCarrito();
		for (ProductoCarrito pc_en_carrito : carrito.getProductosCarrito()) {
		if (pc_en_carrito.getCamiseta().getId() == idProducto) {
			return pc_en_carrito;
		}
		}
		return new ProductoCarrito();
	}
	

	@Override
	public void borrarCamisetaCarrito(Usuario usuario,int idProducto) {
		Usuario uBaseDeDatos = (Usuario) sessionFactory.getCurrentSession().get(Usuario.class, usuario.getId());
		ProductoCarrito pc_en_carrito=productoCarritoPorID(uBaseDeDatos, idProducto);
		sessionFactory.getCurrentSession().clear();
		sessionFactory.getCurrentSession().delete(pc_en_carrito);

	}



}
