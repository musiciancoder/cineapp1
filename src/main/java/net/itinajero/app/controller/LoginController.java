package net.itinajero.app.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class LoginController {
	
	//AL HACER CLICK EN ENTRAR Y LOGEARNOS EXITOSAMENTE
	@GetMapping(value="/index")
	public String mostrarPrincipalAdmin(Authentication authentication) {
		System.out.println("Username: " + authentication.getName() ); //para obtener nombre de usuario en java
		
		for(GrantedAuthority rol: authentication.getAuthorities()) { //para recuperar los roles respectivos a cada usuario
		System.out.println("Rol: " + rol.getAuthority());
		}
		return "admin";
	}
	
	//AL HACER CLICK EN "SALIR" (PARA CERRAR SESION DE USUARIO)
	@GetMapping(value = "/logout")
	public String logout(HttpServletRequest request) {
		
		SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
		
		logoutHandler.logout(request, null, null);
		
		return "redirect:/formLogin";
	}

}
