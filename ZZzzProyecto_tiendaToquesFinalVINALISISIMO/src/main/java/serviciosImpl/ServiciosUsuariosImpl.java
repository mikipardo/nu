package serviciosImpl;

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
import modelo.Usuario;
import servicios.ServicioUsuarios;

@Service
@Transactional
public class ServiciosUsuariosImpl implements ServicioUsuarios {//el restrictions es que haga macht
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Usuario> obtenerUsuario(int comienzo,String nombre) {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(Usuario.class);
		//el restrictions es que haga macht
		c.add(Restrictions.like("nombre", "%"+nombre+"%"));
		c.addOrder(Order.desc("id"));
		c.setFirstResult(comienzo);
		c.setMaxResults(Paginacion.RESULTADOS_POR_PAGINA_ADMIN_USER);
		return c.list();
	}
	
	@Override
	public int obtenerTotalDeUsuarios(String nombre) {
	
		SQLQuery query = sessionFactory.getCurrentSession().
				createSQLQuery(ConstantesSQL.OBTENER_TOTAL_USUARIOS);
		query.setParameter("nombre", "%"+nombre+"%");
		return Integer.parseInt(query.list().get(0).toString());
	}

	@Override
	public void registrarUsuario(Usuario c) {
		sessionFactory.getCurrentSession().save(c);
		
	}

	@Override
	public void borrarUsuarioPorID(int id) {
		Usuario u = 
				(Usuario)sessionFactory.getCurrentSession().get(Usuario.class, id);
		sessionFactory.getCurrentSession().delete(u);
		
	}

	@Override
	public Usuario obtenerUsuarioPorId(int id) {
		return (Usuario)
				sessionFactory.getCurrentSession().get(Usuario.class, id);
	}

	@Override
	public void guardarCambiosUsuario(Usuario u) {
		sessionFactory.getCurrentSession().merge(u);
	}


	@Override
	public Usuario obtenerUsuarioPorEmailYPass(String email, String pass) {
		Criteria c = //el restrictions es que haga macht
				sessionFactory.getCurrentSession().createCriteria(Usuario.class);
		c.add(Restrictions.eq("email", email));
		c.add(Restrictions.eq("pass", pass));
		Usuario u = null;
		if(c.uniqueResult()!=null) {
			u = (Usuario)c.uniqueResult();
		}
		return u;
	}

	@Override
	public Usuario obtenerUsuarioPorEmail(String email) {
		
		Criteria c = 
				sessionFactory.getCurrentSession().createCriteria(Usuario.class);
		c.add(Restrictions.eq("email", email));
		Usuario u = null;
		if(c.uniqueResult()!=null) {
			u = (Usuario)c.uniqueResult();
		}
		return u;

	}



}
