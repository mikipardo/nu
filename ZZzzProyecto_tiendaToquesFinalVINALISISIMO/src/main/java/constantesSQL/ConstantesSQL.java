package constantesSQL;

public class ConstantesSQL {

	public final static String SQL_OBTENER_CATEGORIAS_PARA_DESPLEGABLE = "select id, nombre from tabla_categorias order by nombre asc";
	// consulta para traer el id y el nombre de las categorias
	// para setearlo en el select del html

	public final static String SQL_OBTENER_PRODUCTOS_CARRITO = " select camiseta.id as camiseta_id ,  camiseta.modelo, "
			+ "camiseta.precio, camiseta.talla, camiseta.color,camiseta.categoria_id, productocarrito.cantidad "
			+ "from camiseta , productocarrito " + "where productocarrito.camiseta_id = camiseta.id and "
			+ "productocarrito.carrito_id = :carrito_id " + "ORDER by productocarrito.id asc";
	
	public final static String SQL_OBTENER_VENTA_PROCESO = " SELECT SUM( (camiseta.precio * productocarrito.cantidad) ) AS resultado  FROM productocarrito,camiseta WHERE camiseta.id = productocarrito.camiseta_id GROUP BY productocarrito.carrito_id ";	

	public final static String SQL_SUMAR_TOTAL = " SELECT SUM( (camiseta.precio * productocarrito.cantidad) ) AS resultado  FROM productocarrito,camiseta WHERE camiseta.id = productocarrito.camiseta_id AND productocarrito.carrito_id = :num_id";	

	public static final String BORRAR_PRODUCTOS_CARRITO = "delete from productocarrito where carrito_id = :carrito_id";

	public static final String OBTENER_TOTAL_CAMISETAS = "select count(id) from camiseta where modelo like :modelo ";

	public static final String OBTENER_TOTAL_USUARIOS = "select count(id) from usuario where nombre like :nombre ";
	
	//estadisticas
	
	public static final String OBTENER_TOTAL_CAMISETAS_ESTADISTICAS = "select count(id) from camiseta  ";

	public static final String OBTENER_TOTAL_USUARIOS_ESTADISTICAS = "select count(id) from usuario ";

	public static final String OBTENER_TOTAL_PEDIDOS = " SELECT COUNT(pedido.id) FROM pedido ";

	public static final String OBTENER_TOTAL_VENTAS = " SELECT COUNT(*) FROM pedido WHERE STATUS like \"enviado\" ";

	public static final String OBTENER_TOTAL_ALTAS = " SELECT COUNT(*) FROM camiseta WHERE alta = 1 ";

	public static final String OBTENER_TOTAL_BAJAS = " SELECT COUNT(*) FROM camiseta WHERE alta = 0 ";
	
	public static final String OBTENER_TOTAL_FUERA_TEMPORADA = " SELECT COUNT(*) FROM camiseta WHERE temporada < 2021 ";
	
	public static final String OBTENER_TOTAL_STOCK = " SELECT sum(precio) FROM camiseta WHERE alta = 1 ";
	
	
	public static final String OBTENER_PRECIO_MAS_CARO = " 	SELECT precio FROM camiseta ORDER BY precio desc ";
	
	public static final String OBTENER_PRECIO_MAS_BAJO = " 	SELECT precio FROM camiseta ORDER BY precio asc ";
	
	public static final String OBTENER_PRECIO_MEDIA_CAMISETAS = " SELECT avg(precio) FROM camiseta  ";
	
	public static final String OBTENER_PORCENTAJE = " SELECT AVG(totalCompra) FROM pedido ";
	
	public static final String OBTENER_LISTADO_VENTAS = " SELECT totalCompra FROM pedido ORDER BY fecha ";
	
	public static final String OBTENER_LISTADO_FECHAS_VENTAS = " SELECT fecha FROM pedido ORDER BY fecha ";
	

	
}
