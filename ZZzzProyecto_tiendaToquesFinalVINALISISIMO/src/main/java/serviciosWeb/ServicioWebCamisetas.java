package serviciosWeb;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;

import datos.servicioWeb.RespuestaCamisetas;
import modelo.Camiseta;
import servicios.ServicioCamisetas;

@Controller
@RequestMapping("ServicioCamisetas/")
public class ServicioWebCamisetas {

	@Autowired
	private ServicioCamisetas camisetasDAO;
	
	@RequestMapping("obtenerCamisetas")
	public ResponseEntity<String> obtenerCamisetas(@RequestParam(defaultValue = "") String modelo,@RequestParam(defaultValue = "0" ) String comienzo){
		//Devolvemos un JSON para que pinte en la parte cliente

		RespuestaCamisetas rC = new RespuestaCamisetas();
		rC.setCamisetas(camisetasDAO.obtenerCamisetas(modelo,Integer.parseInt(comienzo)));
		rC.setTotal(camisetasDAO.obtenerTotalDeCamisetas(modelo));		
		String respuesta = new Gson().toJson(rC);
		
		
		return new ResponseEntity<String>(respuesta,HttpStatus.OK);
		
		
	}
	
	@RequestMapping("obtenerRecomendados")
	public ResponseEntity<String> obtenerRecomendados(String idProducto){
		//Devolvemos un JSON para que pinte en la parte cliente
		int idCami = Integer.parseInt(idProducto);
		RespuestaCamisetas rC = new RespuestaCamisetas();
		Camiseta camiseta = camisetasDAO.obtenerCamisetaPorID(idCami);
		rC.setCamisetas(camisetasDAO.recomendados(camiseta.getPrecio()));
		String respuesta = new Gson().toJson(rC);

		return new ResponseEntity<String>(respuesta,HttpStatus.OK);
		
		
	}
	
	@RequestMapping("destacadosSemana")
	public ResponseEntity<String> destacadosSemana(){
		//Devolvemos un JSON para que pinte en la parte cliente

		RespuestaCamisetas rC = new RespuestaCamisetas();
		
		rC.setCamisetas(camisetasDAO.destacadosSemana());
	
		String respuesta = new Gson().toJson(rC);

		
		return new ResponseEntity<String>(respuesta,HttpStatus.OK);
		
		
	}
	
	
		@RequestMapping("obtenerDetalleCamiseta")
	public ResponseEntity<String> obtenerDetalleCamiseta(String idDetalleProducto){
		//Devolvemos un JSON para que pinte en la parte cliente
		Camiseta camiseta = new Camiseta();
		System.out.println("Este es el id de detalleProducto"+idDetalleProducto);
		camiseta= camisetasDAO.obtenerCamisetaPorID(Integer.parseInt(idDetalleProducto));
		
		String respuesta = new Gson().toJson(camiseta);
		System.out.println("Este es el id de res de metodo"+respuesta);
		return new ResponseEntity<String>(respuesta,HttpStatus.OK);
		
		
	}
	
}
