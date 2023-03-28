package servicios;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface ServicioEstadisticas {


	public int totalClientes();
	public int totalCamisetas();
	public int totalPedidos();
	public int totalVentas();
	public int totalAltas();
	public int totalBajas();
	public double totalTotales();
	
	public double precioTotalStock();
	public double porcentajeVentas();
	public double precioMasCaro();
	public double precioMedia();
	public double precioMasBajo();
	
	public List<Double> listaVentas();
	public List<Integer> listaDias();
	public List<Integer> ventaEnProceso();
}
