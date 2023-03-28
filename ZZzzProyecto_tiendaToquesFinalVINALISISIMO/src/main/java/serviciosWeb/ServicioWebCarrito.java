package serviciosWeb;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

import modelo.Usuario;
import servicios.ServicioCarrito;

@Controller
@RequestMapping("ServicioWebCarrito/")
public class ServicioWebCarrito {

	@Autowired
	private ServicioCarrito servicioCarrito;

	@RequestMapping("agregarCamiseta")
	public ResponseEntity<String> agregarCamiseta(String idProducto, String cantidad, HttpServletRequest request) {
		String respuesta = "";
		int cantidadMenor = Integer.parseInt(cantidad);
		
		// Comprueba si la cantidad es negativa o cero 3/3 en comprobacion
		if (cantidadMenor > 0) {
			respuesta = "agregar el producto de id: " + idProducto + " al carrito del usuario: "
					+ ((Usuario) request.getSession().getAttribute("usuario")).getNombre();
			servicioCarrito.agregarProducto((Usuario) request.getSession().getAttribute("usuario"),
					Integer.parseInt(idProducto), Integer.parseInt(cantidad));
			respuesta = "producto agregado al carrito correctamente";
		} else {
			respuesta = "cantidadError";
		}
		return new ResponseEntity<String>(respuesta, HttpStatus.OK);
	}
	
	@RequestMapping("obtenerProductosCarrito")
	public ResponseEntity<String> obtenerProductosCarrito(HttpServletRequest request) {
		String respuesta = "";

		respuesta = new Gson()
				.toJson(servicioCarrito.obtenerProductoCarrito((Usuario) request.getSession().getAttribute("usuario")));

		return new ResponseEntity<String>(respuesta, HttpStatus.OK);
	}


	@RequestMapping("actualizaCantiddadCamiseta")
	public ResponseEntity<String> actualizaCantiddadCamiseta(String idProducto, String cantidad, HttpServletRequest request) {
		String respuesta = "";
		int cantidadMenor = Integer.parseInt(cantidad);
		int idCamiseta = Integer.parseInt(idProducto);
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
		// Comprueba si la cantidad es negativa o cero 3/3 en comprobacion
		if (cantidadMenor > 0) {
			servicioCarrito.actualizaProducto(usuario, idCamiseta, cantidadMenor);
			respuesta = new Gson()//devuelve un hasMap pero al pasarlo aJson no hay que castear ni mapear
					.toJson(servicioCarrito.obtenerProductoCarrito((Usuario) request.getSession().getAttribute("usuario")));

		} else {
			respuesta = "cantidadError";
		}
		return new ResponseEntity<String>(respuesta, HttpStatus.OK);
	}

	@RequestMapping("borrarCamisetaCarrito")
	public ResponseEntity<String> borrarCamisetaCarrito( String idProducto,HttpServletRequest request) {
		String respuesta = "";
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

		servicioCarrito.borrarCamisetaCarrito(usuario,Integer.parseInt(idProducto));
		respuesta = new Gson()//devuelve un hasMap pero al pasarlo aJson no hay que castear ni mapear
				.toJson(servicioCarrito.obtenerProductoCarrito((Usuario) request.getSession().getAttribute("usuario")));
	
		return new ResponseEntity<String>(respuesta, HttpStatus.OK);
	}

}
