package net.itinajero.app.controller;

import java.awt.List;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.itinajero.app.model.Horario;
import net.itinajero.app.model.Pelicula;
import net.itinajero.app.service.IPeliculasService;

@Controller
@RequestMapping(value="/horarios")
public class HorariosController {
	
	@Autowired //injeccion de dependencias
	IPeliculasService servicePeliculas;
		
	/**
	 * Metodo para mostrar el formulario para crear un nuevo horario
	 * @return
	 */
	@GetMapping(value = "/create")
	public String crear(@ModelAttribute Horario horario, Model model) {
		
		
	
		model.addAttribute("peliculas", servicePeliculas.buscarTodas());
		
		
		// Ejercicio: Recuperar lista de peliculas para poblar <select>
				
		// Ejercicio: agregar al modelo listado de peliculas
		
		// Ejercicio: crear archivo formHorario.jsp y configurar el diseño utilizando el codigo HTML
		// del archivo formHorario.html de la plantilla (utilizar Form Tag Library)
		
		return "horarios/formHorario";
	}
		
	/**
	 * Metodo para guardar el registro del Horario
	 * @param horario
	 * @param model
	 * @return
	 */
	@PostMapping(value = "/save")
	public String guardar(@ModelAttribute Horario horario, BindingResult result, Model model) {				
		
		if(result.hasErrors()) { //para probar
			System.out.println("Hay errores");
			
		}else {
			System.out.println("Horario salvado exitosamente   " + horario.toString());
			
		}
		// Ejercicio: Verificar si hay errores en el Data Binding
		
		// Ejercicio: En caso de no haber errores, imprimir en consola el objeto de model Horario 
		// que ya debera de tener los datos del formulario
				
		// De momento, hacemos un redirect al mismo formulario 
		return "redirect:/horarios/create";
	}
	
	// Ejercicio: Crear metodo para personalizar el Data Binding para las todas las propiedades de tipo Date	
	//Esto es para que el formato especificado coincida con el formato del sistema operativo, en este caso la fecha del sistema oper. es dd//MM/aaaa y nosotros debemos forzarla a que sea dd-MM-yyyy
	@InitBinder("horario")
	public void initBinder(WebDataBinder binder) {
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
}
