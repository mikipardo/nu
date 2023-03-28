package datos.servicioWeb;

import java.util.List;
import java.util.Map;
//Solo es una clase meramente informativa para rellenar peticiones o servicios Respuesta ServidoPedidos
//respuesta desde ServicioWEBPedidos
//indicando el resumen del pedido realizado por el usuario
public class ResumenPedido {

	//Todos los campos han sido copiados directamente de Pedidos
	//para que sea el msimo nobre y no de fallo
	private List<Map<String, Object>> camisetas;
	
	//paso1	
	private String nombreCompleto;
	private String direccion;
	private String provincia;
	private String fecha;
	private String horario;
	
	//paso2
	private String titularTarjeta;
	private String numeroTarjeta;
	private String fechaCaducidad;
	
	private String comentario;
	
	private double totalCompra;
	
	public double getTotalCompra() {
		return totalCompra;
	}
	public void setTotalCompra(double totalCompra) {
		this.totalCompra = totalCompra;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public String getFechaCaducidad() {
		return fechaCaducidad;
	}
	public void setFechaCaducidad(String fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}
	public List<Map<String, Object>> getCamisetas() {
		return camisetas;
	}
	public void setCamisetas(List<Map<String, Object>> camisetas) {
		this.camisetas = camisetas;
	}
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getTitularTarjeta() {
		return titularTarjeta;
	}
	public void setTitularTarjeta(String titularTarjeta) {
		this.titularTarjeta = titularTarjeta;
	}
	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}
	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}
	
	
}
