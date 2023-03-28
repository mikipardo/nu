package modelo;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Usuario {

	@Id
	@GeneratedValue
	private int id;

	@OneToOne
	private Carrito carrito;

	
	@Email(message = "Email no puede repetirse")
	@NotEmpty(message = "Email no puede estar vacio")
	@Size(min = 1, max = 50, message = "email debe tener entre 1 y 50 caracteres")
	@Pattern(regexp ="^[a-zA-Z @.-9]{1,50}$", message = "formato de email no valido")
	@Column(unique = true)
	private String email;

	@NotEmpty(message = "Nombre no puede estar vacio")
	@Size(min = 1, max = 20, message = "descripcion debe tener entre 1 y 2000 caracteres")
	@Pattern(regexp = "^[a-zA-Z áéíóúÁÉÍÓÚñÑ0-9]{1,20}$", message = "solo letras, numeros y espacios")
	private String nombre;

	@NotEmpty(message = "Pass no puede estar vacio")
	@Size(min = 1, max = 70, message = "Pass debe tener entre 1 y 20 caracteres")
	@Pattern(regexp = "^[a-zA-Z =.@! áéíóúÁÉÍÓÚñÑ0-9]{1,70}$", message = "solo letras, numeros")
	private String pass;

	private String telefono;

	private String dni;

	public Usuario() {
		// TODO Auto-generated constructor stub
	}

	public Usuario(int id, String nombre, String email, String pass, String telefono, String dni) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.pass = pass;
		this.telefono = telefono;
		this.dni = dni;
	}

	public Usuario(String nombre, String email, String pass, String telefono, String dni) {
		super();
		this.nombre = nombre;
		this.email = email;
		this.pass = pass;
		this.telefono = telefono;
		this.dni = dni;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dni, email, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(dni, other.dni) && Objects.equals(email, other.email) && id == other.id;
	}

	public Carrito getCarrito() {
		return carrito;
	}

	public void setCarrito(Carrito carrito) {
		this.carrito = carrito;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", email=" + email + ", pass=" + pass + ", telefono="
				+ telefono + ", dni=" + dni + "]";
	}

}
