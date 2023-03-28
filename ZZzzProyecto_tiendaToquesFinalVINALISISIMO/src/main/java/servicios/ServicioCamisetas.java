package servicios;

import java.util.List;

import modelo.Camiseta;
import modelo.Usuario;

public interface ServicioCamisetas {
	
	public List<Camiseta> obtenerCamisetas(String modelo,int comienzo);
	public void registarCamiseta(Camiseta c);	
	public void borrarCamisetaPorID(int id);
	public Camiseta obtenerCamisetaPorID(int id);
	public void guardarCambiosCamiseta(Camiseta c);
	public int obtenerTotalDeCamisetas(String modelo);
	public void eliminarRatroCamisetaPorID(int id);
	public List<Camiseta>destacadosSemana();
	public List<Camiseta>recomendados(double precio);
	
}
