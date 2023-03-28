package servicios;

import java.util.List;

import modelo.Usuario;

public interface ServicioUsuarios {

	public List<Usuario>obtenerUsuario(int comienzo,String nombre);
	public void registrarUsuario(Usuario c);
	public void borrarUsuarioPorID(int id);
	public Usuario obtenerUsuarioPorId(int id);
	public void guardarCambiosUsuario(Usuario u);
	public Usuario obtenerUsuarioPorEmailYPass(String email, String pass);
	public int obtenerTotalDeUsuarios(String nombre);
	public Usuario obtenerUsuarioPorEmail(String email);
 }
