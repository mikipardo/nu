package controladores.admin;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;

import datos.servicioWeb.Estadisticas;
import datos.servicioWeb.RespuestaCamisetas;
import servicios.ServicioEstadisticas;

@Controller
public class AdminController {
	
	@Autowired
	private ServicioEstadisticas estadisticasDAO;

@RequestMapping("/admin/")
public String inicio(Model model) {
	
	Estadisticas datos = new Estadisticas();
	//int
	datos.setClientes(estadisticasDAO.totalCamisetas());
	datos.setCamisetas(estadisticasDAO.totalCamisetas());
	datos.setPedidos(estadisticasDAO.totalPedidos());
	datos.setVentas(estadisticasDAO.totalVentas());
	datos.setAltas(estadisticasDAO.totalAltas());
	datos.setBajas(estadisticasDAO.totalBajas());
	
	//double
	datos.setPrecio(estadisticasDAO.precioTotalStock());
	datos.setPorcentaje(estadisticasDAO.porcentajeVentas());
	datos.setCaro(estadisticasDAO.precioMasCaro());
	datos.setMedia(estadisticasDAO.precioMedia());
	datos.setBajo(estadisticasDAO.precioMasBajo());

	model.addAttribute("datos", datos);
	
	//pareseamos para dejar a solo dos decimales
	double total=(estadisticasDAO.totalTotales()*100)/estadisticasDAO.totalCamisetas();
	BigDecimal formatNumber = new BigDecimal(total);
	formatNumber = formatNumber.setScale(2, RoundingMode.DOWN);
	
	total= Double.parseDouble(String.valueOf(formatNumber)) ;
	
	datos.setTotales(total);
	
	System.out.println(datos.toString());
	
	return "admin/inicio";
}


@RequestMapping("admin/chartsDias")
public ResponseEntity<String> obtenerChartsDias(){
	//Devolvemos un JSON para que pinte en la parte cliente
	Estadisticas datos = new Estadisticas();
	datos.setListaDias(estadisticasDAO.listaDias());
	System.out.println("**************************************");
	System.out.println(datos.getListaDias().toString());
	String respuesta = new Gson().toJson(datos);
	
	return new ResponseEntity<String>(respuesta,HttpStatus.OK);
	
}


@RequestMapping("admin/chartsVentas")
public ResponseEntity<String> obtenerChartsVentas(){
	//Devolvemos un JSON para que pinte en la parte cliente
	Estadisticas datos = new Estadisticas();
	datos.setListaVentas(estadisticasDAO.listaVentas());

	String respuesta = new Gson().toJson(datos);
	
	return new ResponseEntity<String>(respuesta,HttpStatus.OK);
}


@RequestMapping("admin/ventaEnProceso")
public ResponseEntity<String> ventaEnProceso(){
	//Devolvemos un JSON para que pinte en la parte cliente
	Estadisticas datos = new Estadisticas();
	
	datos.setListaVentaProceso(estadisticasDAO.ventaEnProceso());

	String respuesta = new Gson().toJson(datos);
	
	return new ResponseEntity<String>(respuesta,HttpStatus.OK);
}



}
