package modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SetUp {
	// facilidad, para cuando se cree la base de datos
	// de nuevo, tener algunos registros con los
	// que poder probar cosas

	private boolean completo;

	@Id
	@GeneratedValue
	private int id;

	public boolean isCompleto() {
		return completo;
	}

	public void setCompleto(boolean completo) {
		this.completo = completo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
