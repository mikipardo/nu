package modelo;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;
import org.springframework.web.multipart.MultipartFile;

@Entity
public class Camiseta {

	@Id
	@GeneratedValue
	private int id;
	
	
	@NumberFormat(style = Style.CURRENCY, pattern = "####")
	@Min(value = 2000, message = "El año minimimo es 2000")
	@Max(value = 2024, message = "El año máximo es 2024")
	private int temporada;
	
	private String talla,color;
	
	//validaciones
	@NotEmpty(message = "Modelo no puede estar vacio")
	@Size( min = 1, max = 40, message = "Modelo debe tener entre 1 y 40 caracteres")
	@Pattern(regexp = "^[a-zA-Z áéíóúÁÉÍÓÚñÑ0-9]{1,40}$", message = "solo letras, numeros y espacios")
	private String modelo;
	
	@NotEmpty(message = "descripcion no puede estar vacio")
	@Size( min = 1, max = 2000, message = "descripcion debe tener entre 1 y 2000 caracteres")
	@Pattern(regexp = "^[a-zA-Z ().,áéíóúÁÉÍÓÚñÑ0-9]{1,2000}$", message = "solo letras, numeros, comas, parentesis, puntos y espacios")
	private String descripcion;
	
	@NumberFormat(style = Style.CURRENCY, pattern = "#,###.###")
	@Min(value = 1, message = "el precio minimo es un euro")
	@Max(value = 999, message = "el precio maximo es 999 euros")
	private double precio;
	
	//para realizar actualizacion de foto
	@Column(nullable = true)
	private Date fechaImagenPortada;
	
	@Transient
	private int idCategoria;

	
	private boolean alta;
	

	// ManyToOne muchos a uno Es decir muchas camisetas a una categoria
	// no es opcional obviar este campo
	// pero este campo no se va a guardar en la base de datos su idCateforia solo el
	// nombreCategoria
	// FetchType.EAGER para cargar este campo cuando se cree la entidad NO DESPUES
	// para esto ultimo sería lazy
	@ManyToOne(targetEntity = Categoria.class, optional = false, fetch = FetchType.EAGER)
	private Categoria categoria;


	@Transient // con @Transient, el siguiente campo es ignorado por hibernate para la BBDD
	private MultipartFile fotoDelante;

	@Transient // con @Transient, el siguiente campo es ignorado por hibernate para la BBDD
	private MultipartFile fotoAtras;
	
	@Transient // con @Transient, el siguiente campo es ignorado por hibernate para la BBDD
	private MultipartFile fotoOpcional;

	public Camiseta() {
		// TODO Auto-generated constructor stub
	}
	
	public Camiseta( String modelo, int temporada, String talla, String descripcion, double precio,
			String color,Categoria categoria,boolean alta) {
		super();
		
		this.modelo = modelo;
		this.temporada = temporada;
		this.talla = talla;
		this.descripcion = descripcion;
		this.precio = precio;
		this.color = color;
		this.categoria=categoria;
		this.alta=alta;
	}
	
	public Camiseta( String modelo, int temporada, String talla, String descripcion, double precio,
			String color,Categoria categoria) {
		super();
		
		this.modelo = modelo;
		this.temporada = temporada;
		this.talla = talla;
		this.descripcion = descripcion;
		this.precio = precio;
		this.color = color;
		this.categoria=categoria;
	}
	
	public Camiseta(int id, String modelo, int temporada, String talla, String descripcion, double precio,
			String color) {
		super();
		this.id = id;
		this.modelo = modelo;
		this.temporada = temporada;
		this.talla = talla;
		this.descripcion = descripcion;
		this.precio = precio;
		this.color = color;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTemporada() {
		return temporada;
	}

	public void setTemporada(int year) {
		this.temporada = year;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getTalla() {
		return talla;
	}

	public void setTalla(String talla) {
		this.talla = talla;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Camiseta [id=" + id + ", temporada=" + temporada + ", modelo=" + modelo + ", talla=" + talla
				+ ", descripcion=" + descripcion + ", color=" + color + ", precio=" + precio + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Camiseta other = (Camiseta) obj;
		return id == other.id;
	}

	public MultipartFile getFotoDelante() {
		return fotoDelante;
	}

	public void setFotoDelante(MultipartFile fotoDelante) {
		this.fotoDelante = fotoDelante;
	}

	public MultipartFile getFotoAtras() {
		return fotoAtras;
	}

	public MultipartFile getFotoOpcional() {
		return fotoOpcional;
	}

	public void setFotoOpcional(MultipartFile fotoOpcional) {
		this.fotoOpcional = fotoOpcional;
	}

	public void setFotoAtras(MultipartFile fotoAtras) {
		this.fotoAtras = fotoAtras;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public Date getFechaImagenPortada() {
		return fechaImagenPortada;
	}
	public void setFechaImagenPortada(Date fechaImagenPortada) {
		this.fechaImagenPortada = fechaImagenPortada;
	}
	
	public boolean isAlta() {
		return alta;
	}
	public void setAlta(boolean alta) {
		this.alta = alta;
	}

}
