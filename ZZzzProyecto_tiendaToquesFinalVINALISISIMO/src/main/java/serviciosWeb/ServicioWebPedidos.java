package serviciosWeb;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

import datos.servicioWeb.ResumenPedido;
import modelo.Usuario;
import servicios.ServicioPedidos;

@Controller
@RequestMapping("ServicioWebPedidos/")
public class ServicioWebPedidos {

	@Autowired
	private ServicioPedidos servicioPedidos;
	
	public ServicioWebPedidos() {
		// TODO Auto-generated constructor stub
	}

	@RequestMapping("paso1")
	public ResponseEntity<String> paso1(String nombre, String direccion, String provincia,String fecha,String horario, HttpServletRequest request){
		String respuesta = "";
		servicioPedidos.procesarPaso1(nombre, direccion, provincia, fecha,horario,
				(Usuario)request.getSession().getAttribute("usuario") );
		respuesta = "ok";
		return new ResponseEntity<String>(respuesta,HttpStatus.OK);
	}
	
	
	
	@RequestMapping("paso2")//metodo de confirmacion del pedido
	public ResponseEntity<String> paso2(String titular, String numero,String fechaCaducida,HttpServletRequest request){

		String respuesta = "";
		//En caso de todo OK sacamos un objeto de  resumenPedido y lo pasamos por JSON
		Usuario u = (Usuario)request.getSession().getAttribute("usuario");
		servicioPedidos.procesarPaso2(titular, numero,fechaCaducida, //"Usuario" es un atributo lanzado a la red Cahe y recogido por nosotros
				u
				);
	
		respuesta = "ok";
		
		return new ResponseEntity<String>(respuesta,HttpStatus.OK);
	}
	
	@RequestMapping("paso2BIS")//metodo de confirmacion del pedido
	public ResponseEntity<String> paso2BIS(String comentario,HttpServletRequest request){
		String respuesta = "";
		//En caso de todo OK sacamos un objeto de  resumenPedido y lo pasamos por JSON
		Usuario u = (Usuario)request.getSession().getAttribute("usuario");
		servicioPedidos.procesarPaso2BIS(comentario, //"Usuario" es un atributo lanzado a la red Cahe y recogido por nosotros
				u
				);
		ResumenPedido resumen = servicioPedidos.obtenerResumenDelPedido(
				u
				);
		respuesta = "ok:"+new Gson().toJson(resumen);	
		
		return new ResponseEntity<String>(respuesta,HttpStatus.OK);
	}
	
	@RequestMapping("confirmarPedido")
	public ResponseEntity<String> confirmarPedido(HttpServletRequest request){
		String respuesta = "";
		servicioPedidos.confirmarPedido(
				(Usuario)request.getSession().getAttribute("usuario")
				);
		respuesta = "pedido completado, puedes seguir comprando";
		return new ResponseEntity<String>(respuesta,HttpStatus.OK);
	}//end confirmar
	
}
