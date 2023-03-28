package serviciosSetUp;

import java.util.Iterator;
import java.util.Random;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import modelo.Camiseta;
import modelo.Categoria;
import modelo.SetUp;
import modelo.Usuario;
import servicios.ServicioInterfazSetUp;
import utilsFile.CryptoData;

@Service
@Transactional
public class ServiciosSetUp implements ServicioInterfazSetUp {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void setUp() {
		// TODO Auto-generated method stub+

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SetUp.class);
		String key = CryptoData.encriptacionMD5("1234");
		// si de la tabla setUp no hay objeto seteamos los nuestros
		if (criteria.list().size() == 0) {
			Random rnd = new Random();
			// preparamos unos registros
			sessionFactory.getCurrentSession()
					.save(new Usuario("Miki", "miki@gmail.com", key, "646601809", "47534376T"));
			sessionFactory.getCurrentSession()
					.save(new Usuario("Ana", "ana@gmail.com", key, "619383061", "43789645W"));
			sessionFactory.getCurrentSession()
					.save(new Usuario("Richard", "richard@gmail.com", key, "689378541", "256893L"));
			// categorias
			
			for (int i = 0; i < 20; i++) {
				//aleatorio para dni + telefono
				int number = rnd. nextInt(99999999);
			
				
				sessionFactory.getCurrentSession()
				.save(new Usuario("User"+i, "user"+i+"@gmail.com", key, "6"+number, "0"+number+"X"));;
			}

			Categoria clasic = new Categoria("Clasic", "categoria moda clasica");
			sessionFactory.getCurrentSession().save(clasic);

			Categoria newColection = new Categoria("New Collection", "categoria moda clasica");
			sessionFactory.getCurrentSession().save(newColection);

			Categoria luxury = new Categoria("Luxury", "categoria luxury");
			sessionFactory.getCurrentSession().save(luxury);

			// camisetas
			sessionFactory.getCurrentSession()
					.save(new Camiseta("BetFl", 2022, "L", "Camiseta slip para el día a día", 989.90, "ROJO", clasic,true));
			sessionFactory.getCurrentSession()
					.save(new Camiseta("Rigel", 2021, "M", "Materiales reciclados", 793.90, "AZUL", newColection,true));
			sessionFactory.getCurrentSession()
					.save(new Camiseta("Aldebaran", 2019, "XL", "Tu confort y transpiración", 899.90, "VERDE", luxury,true));
			
			for (int i = 0; i < 30; i++) {
				int precio = (rnd. nextInt(99)+1); 
				String money = precio+".00";
				sessionFactory.getCurrentSession()
				.save(new Camiseta("Test"+i, 2021, "M", "Camiseta por defecto para testeo "+ i, Double.parseDouble(money), "NEGRA", clasic,true));
			}
			
		}
		// le seteamos un boleano para terminar y por si queremos ver la valoracion
		SetUp setUp = new SetUp();
		setUp.setCompleto(true);
		sessionFactory.getCurrentSession().save(setUp);
	}

}
