package interceptores;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


//heredamos de esta clase que nos proporciona este método para que salte elinterceptor
public class InterceptorAdmin extends HandlerInterceptorAdapter {

//Creamos este interceptor, cada vez que haya una llamada a cualquier ruta de admin saltará
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if( request.getParameter("pass")!=null && 
				request.getParameter("pass").equals("admin")){
				//metemos un pequeño token en sesion para indicar que el 
	
				request.getSession().setAttribute("admin", "tokenAdmin");
			}
		
		//si el admin tiene token de admin le dejamos continuar
		if( request.getSession().getAttribute("admin") != null && 
				request.getSession().getAttribute("admin").equals("tokenAdmin")) {
			 
				return true;
			}else {
				//forzar redireccion a loginAdmin.jsp si no hay token de admin
				response.sendRedirect("../loginAdmin.jsp");
				
				return false;
			}

	}

}
