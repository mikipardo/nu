package serviciosImpl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import constantes.Paginacion;
import constantesSQL.ConstantesSQL;
import modelo.Camiseta;
import modelo.Categoria;
import modelo.Usuario;
import servicios.ServicioCamisetas;

@Service
@Transactional
public class ServiciosCamisetasImpl implements ServicioCamisetas {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Camiseta> recomendados(double precio) {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(Camiseta.class);
		c.add(Restrictions.between("precio", (precio-precio+10), (precio+20)));
		c.add(Restrictions.ne("precio", precio));
		c.addOrder(Order.desc("precio"));
		c.setMaxResults(3);
		return c.list();
	}

	
	@Override
	public List<Camiseta> destacadosSemana() {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(Camiseta.class);
	
		c.setMaxResults(3);
		c.addOrder(Order.desc("precio"));

		return c.list();
	}

	@Override
	public List<Camiseta> obtenerCamisetas(String modelo, int comienzo) {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(Camiseta.class);
		c.add(Restrictions.like("modelo", "%" + modelo + "%"));
		c.add(Restrictions.eq("alta",true));//solo las que estan de baja
		c.addOrder(Order.asc("id"));
		c.setFirstResult(comienzo);
		c.setMaxResults(Paginacion.RESULTADOS_POR_PAGINA_ADMIN);
		return c.list();
	}

	@Override
	public int obtenerTotalDeCamisetas(String modelo) {
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(ConstantesSQL.OBTENER_TOTAL_CAMISETAS);
		query.setParameter("modelo", "%" + modelo + "%");
		return Integer.parseInt(query.list().get(0).toString());
	}

	@Override
	public void registarCamiseta(Camiseta camiseta) {

		Categoria categoria = (Categoria) sessionFactory.getCurrentSession().get(Categoria.class,
				camiseta.getIdCategoria());
		camiseta.setCategoria(categoria);
		camiseta.setAlta(true);
		sessionFactory.getCurrentSession().save(camiseta);
		// hibernate una vez ha registrado un objeto
		// le asigna al mismo la id generada

	}

	public void borrarCamisetaPorID(int id) {// no las borra/ le da de baja
		Camiseta c = (Camiseta) sessionFactory.getCurrentSession().get(Camiseta.class, id);
		c.setAlta(false);
		sessionFactory.getCurrentSession().update(c);

	}

	@Override//metodo sin usar al ya que ahora no eliminamos del todo solo damos de baja
	public void eliminarRatroCamisetaPorID(int id) {
		Camiseta c = (Camiseta) sessionFactory.getCurrentSession().get(Camiseta.class, id);
		sessionFactory.getCurrentSession().delete(c);

	}

	@Override
	public Camiseta obtenerCamisetaPorID(int id) {
		return (Camiseta) sessionFactory.getCurrentSession().get(Camiseta.class, id);
	}

	@Override
	public void guardarCambiosCamiseta(Camiseta c) {
		Categoria categoria = (Categoria) sessionFactory.getCurrentSession().get(Categoria.class, c.getIdCategoria());
		c.setCategoria(categoria);
		sessionFactory.getCurrentSession().merge(c);
	}




}
