package datos.servicioWeb;

import java.util.List;

public class Estadisticas {

	private int clientes, camisetas,pedidos, ventas,altas,bajas;
	
	private double precio, porcentaje,caro,media,bajo,totales;
	
	private List<Double> listaVentas;
	
	private List<Integer>listaDias;
	
	private List<Integer>listaVentaProceso;
	
	

	public List<Integer> getListaVentaProceso() {
		return listaVentaProceso;
	}

	public void setListaVentaProceso(List<Integer> listaVentaProceso) {
		this.listaVentaProceso = listaVentaProceso;
	}

	public List<Double> getListaVentas() {
		return listaVentas;
	}

	public void setListaVentas(List<Double> listaVentas) {
		this.listaVentas = listaVentas;
	}

	public List<Integer> getListaDias() {
		return listaDias;
	}

	public void setListaDias(List<Integer> listaDias) {
		this.listaDias = listaDias;
	}

	@Override
	public String toString() {
		return "Estadisticas [clientes=" + clientes + ", camisetas=" + camisetas + ", pedidos=" + pedidos + ", ventas="
				+ ventas + ", altas=" + altas + ", bajas=" + bajas + ", totales=" + totales + ", precio=" + precio
				+ ", porcentaje=" + porcentaje + ", caro=" + caro + ", media=" + media + ", bajo=" + bajo + "]";
	}

	public int getClientes() {
		return clientes;
	}

	public void setClientes(int clientes) {
		this.clientes = clientes;
	}

	public int getCamisetas() {
		return camisetas;
	}

	public void setCamisetas(int camisetas) {
		this.camisetas = camisetas;
	}

	public int getPedidos() {
		return pedidos;
	}

	public void setPedidos(int pedidos) {
		this.pedidos = pedidos;
	}

	public int getVentas() {
		return ventas;
	}

	public void setVentas(int ventas) {
		this.ventas = ventas;
	}

	public int getAltas() {
		return altas;
	}

	public void setAltas(int altas) {
		this.altas = altas;
	}

	public int getBajas() {
		return bajas;
	}

	public void setBajas(int bajas) {
		this.bajas = bajas;
	}

	public double getTotales() {
		return totales;
	}

	public void setTotales(double totales) {
		this.totales = totales;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public double getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(double porcentaje) {
		this.porcentaje = porcentaje;
	}

	public double getCaro() {
		return caro;
	}

	public void setCaro(double caro) {
		this.caro = caro;
	}

	public double getMedia() {
		return media;
	}

	public void setMedia(double media) {
		this.media = media;
	}

	public double getBajo() {
		return bajo;
	}

	public void setBajo(double bajo) {
		this.bajo = bajo;
	}
}
