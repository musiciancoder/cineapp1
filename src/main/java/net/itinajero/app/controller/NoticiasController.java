package net.itinajero.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.itinajero.app.model.Noticia;
import net.itinajero.app.service.INoticiasService;

@Controller

@RequestMapping("/noticias") //con esto le damos una url a la clase
public class NoticiasController {
	
	@Autowired
	private INoticiasService serviceNoticias;//declaramos variable de la interfaz, para hacer inyeccion de dependencias
	
	@GetMapping(value="/create") //con esto le damos una url al metodo. En la barra de direcciones, entoces es /noticias/create
	public String crear() {
		
		return "noticias/formNoticia"; //renderiza el jsp formNoticia
		
	}
	
	@PostMapping(value="/save") 
	public String guardar(Noticia noticia){//en el argumento se instancia la clase modelo, para hacer data binding, es decir unir vista con modelo 
		// noticia.setTitulo(titulo); esto  ya no es necesario, porque por cada propiedad descrita en la clase que esta en el  modelo (en nuestro caso la clase Noticia)  que coincidaa con los names en los inputs de nuestra vista (formNoticia), con data binding van a ser pedidos automaticamente
		
		
		//TODO: GUARDAR EL OBJETO NOTICIA EN LA BBDD
		
		//System.out.println(noticia); //toma el metodo toString sobreescrito en noticia
		
		serviceNoticias.guardar(noticia);
		
		
		return "noticias/formNoticia";
		
	}

}
