package controladores.admin;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import constantes.Paginacion;
import modelo.Camiseta;
import servicios.ServicioCamisetas;
import servicios.ServicioCategorias;

import utilsFile.GestorDeFotos;

@Controller
public class CamisetasController {
	@Autowired
	private ServicioCamisetas camisetasDAO;

	@Autowired
	private ServicioCategorias servicioCategorias;// necesitamos esta clase para las categoras de Camisetas

	@RequestMapping("/admin/gestionarCamisetas") // le metemos default para que sea vacio en vez de nulo
	public String gestionarCamisetas(Model model, @RequestParam(defaultValue = "") String modelo,
			@RequestParam(defaultValue = "0") String comienzo) {
		int comienzo_int = Integer.parseInt(comienzo);
		model.addAttribute("modelo", modelo);
		model.addAttribute("todasCamisetas", camisetasDAO.obtenerCamisetas(modelo, comienzo_int));
		model.addAttribute("siguiente", comienzo_int + Paginacion.RESULTADOS_POR_PAGINA_ADMIN);
		model.addAttribute("anterior", comienzo_int - Paginacion.RESULTADOS_POR_PAGINA_ADMIN);

		model.addAttribute("total", camisetasDAO.obtenerTotalDeCamisetas(modelo));
		return "/admin/gestionarCamisetas";
	}

	@RequestMapping("/admin/borrarCamiseta")
	public String borrarCamiseta(String idBorrar, Model model, HttpServletRequest request) {
		camisetasDAO.borrarCamisetaPorID(Integer.parseInt(idBorrar));
		String rutaRealDelProyecto = request.getServletContext().getRealPath("");
		GestorDeFotos.borrarPortadaLibro(idBorrar, rutaRealDelProyecto);
		return gestionarCamisetas(model, "", "0");
	}

	@RequestMapping("/admin/editarCamiseta")
	public String editarCamiseta(Model model, String idEditar) {
		// creamos un mapa de categorias y lo devolvemos a la vista
		// ademas le seteamos la categoria a la camiseta ya que no sale el idCategoria
		// en BBDD
		Camiseta camisetaEditar = camisetasDAO.obtenerCamisetaPorID(Integer.parseInt(idEditar));
		Map<String, String> mapCategorias = servicioCategorias.obtenerCategoriasParaDesplegable();
		camisetaEditar.setIdCategoria(camisetaEditar.getCategoria().getId());
		model.addAttribute("camiseta", camisetaEditar);
		model.addAttribute("categorias", mapCategorias);
		return "/admin/formEditarCamiseta";
	}


	@RequestMapping("/admin/actualizaCamiseta")
	public String actualizaCamiseta(@Valid Camiseta camiseta, BindingResult br, Model model,
			HttpServletRequest request) {

		if (!br.hasErrors()) {
			if (camiseta.getFotoDelante().getSize() != 0) {
				camiseta.setFechaImagenPortada(new Date());
			}

			String fotoEsJPG1 = camiseta.getFotoDelante().getOriginalFilename();
			String fotoEsJPG2 = camiseta.getFotoAtras().getOriginalFilename();
			String fotoEsJPG3 = camiseta.getFotoOpcional().getOriginalFilename();

			Boolean foto1TerminaJG = GestorDeFotos.comprobarJPG(fotoEsJPG1);
			Boolean foto2TerminaJG = GestorDeFotos.comprobarJPG(fotoEsJPG2);
			Boolean foto3TerminaJG = GestorDeFotos.comprobarJPG(fotoEsJPG3);

			String rutaRealDelProyecto = request.getServletContext().getRealPath("");
			
			if(foto1TerminaJG) {
				GestorDeFotos.guardarFoto(camiseta, rutaRealDelProyecto);
			}else {
				System.out.println("la imagen no puede ser guardada");
			}

			if(foto2TerminaJG) {
				GestorDeFotos.guardarFotoAtras(camiseta, rutaRealDelProyecto);
			}else {
				System.out.println("la imagen no puede ser guardada");
			}

			if(foto3TerminaJG) {
				GestorDeFotos.guardarFotoOpcional(camiseta, rutaRealDelProyecto);
			}else {
				System.out.println("la imagen no puede ser guardada");
			}
				GestorDeFotos.reductorImagenes(rutaRealDelProyecto, camiseta);
			camisetasDAO.guardarCambiosCamiseta(camiseta);

			return gestionarCamisetas(model, "", "0");
		} else {// si la validacion es mala
			model.addAttribute("camiseta", camiseta);
			model.addAttribute("categorias", servicioCategorias.obtenerCategoriasParaDesplegable());

			return "admin/formEditarCamiseta";
		}
	}

	@RequestMapping("/admin/nuevaCamiseta")
	public String nuevaCamiseta(Model model, Camiseta camiseta) {
		model.addAttribute("camiseta", camiseta);
		model.addAttribute("categorias", // este model llama a la BBDD para luego implementar en el select categorias
											// del form de nueva camiseta
				servicioCategorias.obtenerCategoriasParaDesplegable());
		return "/admin/nuevaCamiseta";
	}

	@RequestMapping("/admin/guardarNuevaCamiseta") // BidingResult devuelve True o false si esta bien validado
	public String guardarNuevaCamiseta(@ModelAttribute("camiseta") @Valid Camiseta camiseta, BindingResult br,
			Model model, HttpServletRequest request) {

		String fotoEsJPG1 = camiseta.getFotoDelante().getOriginalFilename();
		String fotoEsJPG2 = camiseta.getFotoAtras().getOriginalFilename();
		String fotoEsJPG3 = camiseta.getFotoOpcional().getOriginalFilename();
		
		System.out.println("/////////////////////////////////////////////////////////");
		System.err.println(fotoEsJPG1+ " "+fotoEsJPG2+" "+fotoEsJPG3);

		Boolean foto1TerminaJG = GestorDeFotos.comprobarJPG(fotoEsJPG1);
		Boolean foto2TerminaJG = GestorDeFotos.comprobarJPG(fotoEsJPG2);
		Boolean foto3TerminaJG = GestorDeFotos.comprobarJPG(fotoEsJPG3);

		String rutaRealDelProyecto = request.getServletContext().getRealPath("");

		// si la validacion=ok registramos
		if (!br.hasErrors()) {
			if (camiseta.getFotoDelante().getSize() != 0) {
				camiseta.setFechaImagenPortada(new Date());
			}
			camisetasDAO.registarCamiseta(camiseta);
			if(foto1TerminaJG) {
				GestorDeFotos.guardarFoto(camiseta, rutaRealDelProyecto);
			}else {
				System.out.println("la imagen no puede ser guardada");
			}

			if(foto2TerminaJG) {
				GestorDeFotos.guardarFotoAtras(camiseta, rutaRealDelProyecto);
			}else {
				System.out.println("la imagen no puede ser guardada");
			}

			if(foto3TerminaJG) {
				GestorDeFotos.guardarFotoOpcional(camiseta, rutaRealDelProyecto);
			}else {
				System.out.println("la imagen no puede ser guardada");
			}
			return gestionarCamisetas(model, "", "0");

		} else {// si la validacion es mala
			model.addAttribute("camiseta", camiseta);
			model.addAttribute("categorias", servicioCategorias.obtenerCategoriasParaDesplegable());

			return "admin/nuevaCamiseta";
		}

	}// end guardarNuevaCamiseta
}
