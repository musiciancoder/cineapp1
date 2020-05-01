package net.itinajero.app.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import net.itinajero.app.model.Contacto;
import net.itinajero.app.service.IPeliculasService;

@Controller
public class ContactoController {
	
	@Autowired //injeccion de dependencias
	IPeliculasService servicePeliculas;
	
	@GetMapping("/contacto")
	public String mostrarFormulario(@ModelAttribute ("instanciaContacto") Contacto contacto, Model model) {//@ModelAttribute automaticamente crea una instancia "instanciaContacto" con el constructor por defecto de la clase modelo contacto y la agrega al modelo. 
		//si no le damos un nombre a la instancia contacto (en este caso sí lo hicimos con "instanciaContacto"), por defecto esta instancia toma el nombre de la clase modelo en minuscula (en nuestro caso seria contacto)
		model.addAttribute("generos", servicePeliculas.buscarGeneros()); //para seleccion multiple de generos. servicePeliculas.buscarGeneros() es la lista de generos, que es lo que estamos agreganso el modelo.  @ModelAttribute se usa con clases de modelo es decir con clases creadas para recibir lo que el usuario ingresa (En nuesto caso la clase Contacto es nuestra clase de modelo)
		model.addAttribute("tipos", tiposNotificaciones()); //para los checkboxes
	//	notese la ausencia de items=${} para radiobuttons, a diferencia de checkboxes que sí tienen items en formContacto.jsp. Es por ello que en el controlador hay una lista para checkboxes, a diferencia de radiobuttons 
		return "formContacto";
	}
	
	
	@PostMapping("/contacto")
	public String guardar (@ModelAttribute ("instanciaContacto") Contacto contacto) { //metodo que se activa cuando guardamos los datos del formulario, puede tener la misma url que mostrarFormulario porque una es get y la otra es pot
		System.out.println(contacto);
		return "redirect:/contacto"; //al redireccionar a "/contacto" llamamos al metodo mostrarFormulario() que es el que en realidad guarda al modelo con model.Attributes
	}
	
	private  List <String> tiposNotificaciones(){ //metodo para checkboxes en formContacto
			// Nota: La lista se podria generar apartir de una BD
			List<String> tipos = new LinkedList<>();
			tipos.add("Estrenos");
			tipos.add("Promociones");
			tipos.add("Noticias");
			tipos.add("Preventas");
			return tipos; //devuelve la lista
		}
		
	}


