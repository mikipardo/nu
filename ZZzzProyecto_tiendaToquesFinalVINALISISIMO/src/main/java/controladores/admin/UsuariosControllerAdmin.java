package controladores.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import constantes.Paginacion;
import modelo.Usuario;
import servicios.ServicioUsuarios;
import utilsFile.CryptoData;

@Controller
public class UsuariosControllerAdmin {

	@Autowired
	private ServicioUsuarios usuariosDAO;

	@RequestMapping("/admin/gestionarUsuarios")
	public String gestionarUsuarios(Model model, @RequestParam(defaultValue = "0") String comienzo,
			@RequestParam(defaultValue = "") String nombre) {
		int comienzo_int = Integer.parseInt(comienzo);
		model.addAttribute("todosUsuarios", usuariosDAO.obtenerUsuario(comienzo_int, nombre));
		model.addAttribute("nombre", nombre);
		model.addAttribute("siguiente", comienzo_int + Paginacion.RESULTADOS_POR_PAGINA_ADMIN_USER);
		model.addAttribute("anterior", comienzo_int - Paginacion.RESULTADOS_POR_PAGINA_ADMIN_USER);
		model.addAttribute("totalUSer", usuariosDAO.obtenerTotalDeUsuarios(nombre));
		return "/admin/gestionarUsuarios";
	}

	@RequestMapping("/admin/borrarUsuario")
	public String borrarUsuario(String idBorrar, Model model) {
		usuariosDAO.borrarUsuarioPorID(Integer.parseInt(idBorrar));
		return gestionarUsuarios(model, "0", "");
	}

	@RequestMapping("/admin/editarUsuario")
	public String editarUsuario(Model model, String idEditar) {
		Usuario usuarioEditar = usuariosDAO.obtenerUsuarioPorId(Integer.parseInt(idEditar));
		model.addAttribute("usuario", usuarioEditar);
		return "/admin/formEditarUsuario";
	}

	@RequestMapping("/admin/actualizaUsuario")
	public String actualizaUsuario(@Valid Usuario usuario , BindingResult br, Model model) {
		if (!br.hasErrors()) {
		String key = CryptoData.encriptacionMD5(usuario.getPass());
		usuario.setPass(key);
		usuariosDAO.guardarCambiosUsuario(usuario);
		return gestionarUsuarios(model, "0", "");
		}else {// si la validacion es mala
			model.addAttribute("usuario", usuario);
		
			return "admin/formEditarUsuario";
		}
	}

	@RequestMapping("/admin/nuevoUsuario")
	public String nuevoUsuario(Model model, Usuario usuario) {

		model.addAttribute("usuario", usuario);
		return "/admin/nuevoUsuario";
	}

	@RequestMapping("/admin/guardarNuevoUsuario")
										//validador de expresiones
	public String guardarNuevoUsuario( @Valid Usuario usuario,BindingResult br, Model model) {
		if (!br.hasErrors()) {
		String key = CryptoData.encriptacionMD5(usuario.getPass());
		usuario.setPass(key);
		usuariosDAO.registrarUsuario(usuario);
		return gestionarUsuarios(model, "0", "");
		} else {// si la validacion es mala
			model.addAttribute("usuario", usuario);
			return "admin/nuevoUsuario";
		}
	}
}
