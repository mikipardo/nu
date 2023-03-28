package controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import servicios.ServicioInterfazSetUp;

@Controller
public class InicioController {public InicioController() {
	// TODO Auto-generated constructor stub
}
@Autowired
private ServicioInterfazSetUp servicioSetUp;


@RequestMapping("/inicio")
public String inicio() {
	servicioSetUp.setUp();
	return"inicio";
}

}
