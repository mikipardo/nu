package datos.servicioWeb;

import java.util.List;

import modelo.Camiseta;

public class RespuestaCamisetas {

	private int total;
	private List<Camiseta> Camiseta;
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<Camiseta> getCamisetas() {
		return Camiseta;
	}
	public void setCamisetas(List<Camiseta> Camiseta) {
		this.Camiseta = Camiseta;
	}
}
