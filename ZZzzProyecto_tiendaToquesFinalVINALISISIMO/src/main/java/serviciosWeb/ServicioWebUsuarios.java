package serviciosWeb;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import modelo.Usuario;
import servicios.ServicioUsuarios;
import utilsFile.CryptoData;
import utilsFile.GestorDeFotos;

@Controller
@RequestMapping("ServicioWebUsuarios/")
public class ServicioWebUsuarios {
	
	@Autowired
	private ServicioUsuarios servicioUsuarios;
	
	@RequestMapping("registrarUsuario")
	public ResponseEntity<String> registrarUsuario(@RequestParam Map<String, Object> formData,
			@RequestParam("foto") CommonsMultipartFile foto,
			HttpServletRequest request){
		String respuesta = "ok";

		Gson gson = new Gson();
		JsonElement json = gson.toJsonTree(formData);
		Usuario u = gson.fromJson(json, Usuario.class);
		System.out.println("usuario a registrar: " + u.toString());
		String rutaRealDelProyecto = 
				request.getServletContext().getRealPath("");
		
		String key;
		try {
			key = CryptoData.encriptacionMD5(u.getPass());
			u.setPass(key);
			System.out.println(key+ " esta es la clave encriptada");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error e encripatcion en guardarNuevoUsuario usuario registrado sin encriptacion de clave");
		}
		//comprobamos si no existe el email le dejamos registrar
		if( servicioUsuarios.obtenerUsuarioPorEmail(u.getEmail()) == null ) {
		servicioUsuarios.registrarUsuario(u);
		GestorDeFotos.guardarFotoUsuario(u, foto, rutaRealDelProyecto);
		}else {
			respuesta = "email ya registrado";
		}
		System.out.println("la resouesta es:" +respuesta);
		return new ResponseEntity<String>(respuesta,HttpStatus.OK);
	}
	
	@RequestMapping("identificarUsuario")
	public ResponseEntity<String> 
		identificarUsuario(String email, String pass, HttpServletRequest request){
		String key = CryptoData.encriptacionMD5(pass); 
		String respuesta = "";
		//comprobar en bd si el conjunto email pass es correcto
		Usuario u = servicioUsuarios.obtenerUsuarioPorEmailYPass(email, key);
		
		if( u != null ) {
			request.getSession().setAttribute("usuario", u);
			respuesta = "ok," + u.getNombre();
		}else {
			respuesta = "error, email o pass incorrectos";
		}
		return new ResponseEntity<String>(respuesta,HttpStatus.OK);
	}
	
	@RequestMapping("logout")
	public ResponseEntity<String> logout(HttpServletRequest request){
		request.getSession().invalidate();
		return new ResponseEntity<String>("ok",HttpStatus.OK);
	}//end logout
	
}//end class






