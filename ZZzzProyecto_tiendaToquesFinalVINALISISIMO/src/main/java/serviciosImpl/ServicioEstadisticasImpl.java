package serviciosImpl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import constantesSQL.ConstantesSQL;
import servicios.ServicioCarrito;
import servicios.ServicioEstadisticas;

@Service
@Transactional
public class ServicioEstadisticasImpl implements ServicioEstadisticas {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public int totalClientes() {
		// TODO Auto-generated method stub

		SQLQuery query = sessionFactory.getCurrentSession()
				.createSQLQuery(ConstantesSQL.OBTENER_TOTAL_USUARIOS_ESTADISTICAS);

		int total = Integer.parseInt(String.valueOf(query.list().get(0)));

		return total;
	}

	@Override
	public int totalCamisetas() {
		// TODO Auto-generated method stub

		SQLQuery query = sessionFactory.getCurrentSession()
				.createSQLQuery(ConstantesSQL.OBTENER_TOTAL_CAMISETAS_ESTADISTICAS);

		int total = Integer.parseInt(String.valueOf(query.list().get(0)));

		return total;

	}

	@Override
	public int totalPedidos() {

		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(ConstantesSQL.OBTENER_TOTAL_PEDIDOS);

		int total = Integer.parseInt(String.valueOf(query.list().get(0)));

		return total;
	}

	@Override
	public int totalVentas() {// total de ventas con status enviado

		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(ConstantesSQL.OBTENER_TOTAL_VENTAS);

		int total = Integer.parseInt(String.valueOf(query.list().get(0)));

		// query.setParameter("estado", "enviado");

		return total;
	}

	@Override
	public int totalAltas() {

		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(ConstantesSQL.OBTENER_TOTAL_ALTAS);

		int total = Integer.parseInt(String.valueOf(query.list().get(0)));

		return total;

	}

	@Override
	public int totalBajas() {

		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(ConstantesSQL.OBTENER_TOTAL_BAJAS);

		int total = Integer.parseInt(String.valueOf(query.list().get(0)));

		return total;
	}

	@Override
	public double totalTotales() {// camisetas fuera de temporada

		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(ConstantesSQL.OBTENER_TOTAL_FUERA_TEMPORADA);

		double total =  Double.parseDouble(String.valueOf(query.list().get(0)));

		return parseaDoble(total);
	}

	@Override
	public double precioTotalStock() {// todo lo que hay de dinero en stock

		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(ConstantesSQL.OBTENER_TOTAL_STOCK);

		double total = Double.parseDouble(String.valueOf(query.list().get(0)));

		return parseaDoble(total);

	}

	@Override
	public double porcentajeVentas() {// media del pedido total Compra

		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(ConstantesSQL.OBTENER_PORCENTAJE);

		double total = Double.parseDouble(String.valueOf(query.list().get(0)));

		return parseaDoble(total);
	}

	@Override
	public double precioMasCaro() {
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(ConstantesSQL.OBTENER_PRECIO_MAS_CARO);

		double total = Double.parseDouble(String.valueOf(query.list().get(0)));
		

		return parseaDoble(total);
	}

	@Override
	public double precioMedia() {// media de compra
		SQLQuery query = sessionFactory.getCurrentSession()
				.createSQLQuery(ConstantesSQL.OBTENER_PRECIO_MEDIA_CAMISETAS);

		double total = Double.parseDouble(String.valueOf(query.list().get(0)));


		return parseaDoble(total);
	}

	@Override
	public double precioMasBajo() {// camiseta mas barata
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(ConstantesSQL.OBTENER_PRECIO_MAS_BAJO);

		double total = Double.parseDouble(String.valueOf(query.list().get(0)));
		


		return parseaDoble(total);
	}

	@Override
	public List<Double> listaVentas() {
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(ConstantesSQL.OBTENER_LISTADO_VENTAS);
		List<Double> listaVentas= query.list();
		return listaVentas;
	}

	@Override
	public List<Integer> listaDias() {
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(ConstantesSQL.OBTENER_LISTADO_FECHAS_VENTAS);
		List<Integer> listaDias= query.list();
		
		return listaDias;
	}

	@Override
	public List<Integer> ventaEnProceso() {
		
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(ConstantesSQL.SQL_OBTENER_VENTA_PROCESO);
		List<Integer> ventaEnProceso= query.list();
		
		return ventaEnProceso;
	}
	
	
	public static double parseaDoble(double num) {
		
		double total=0;
		
		BigDecimal formatNumber = new BigDecimal(total);
		formatNumber = formatNumber.setScale(2, RoundingMode.DOWN);
		
		total= Double.parseDouble(String.valueOf(formatNumber)) ;
		
		return total;
	}

}


