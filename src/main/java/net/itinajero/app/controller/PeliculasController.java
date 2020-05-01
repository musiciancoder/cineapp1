package net.itinajero.app.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.itinajero.app.model.Pelicula;
import net.itinajero.app.service.IDetallesService;
import net.itinajero.app.service.IPeliculasService;
import net.itinajero.app.util.Utileria;

@Controller
@RequestMapping("/peliculas")
public class PeliculasController {
	
	@Autowired
	private IDetallesService serviceDetalles;// inyeccion de dependencias
	
	@Autowired
	private IPeliculasService servicePeliculas; // inyeccion de dependencias
	
	
	//Al HACER CLICK EN PELICULAS
	@GetMapping("/index") 
	public String mostrarIndex (Model model) { //metodo para agregar la lista de peliculas al modelo
		List <Pelicula> lista = servicePeliculas.buscarTodas(); //exactamente lo mismo que en metodo mostrarPrincipal de homecontroller, pero aca le estamos dando otra url y va a renderizar "/peliculas/listaPeliculas" en vez de "/home"
		model.addAttribute("peliculas", lista); //peliculas se usa en  <c:forEach var="pelicula" items="${peliculas}"> en listaPeliculas.jsp  
		return "peliculas/listaPeliculas";
	}
	
	//AL HACER CLICK EN NUEVA (este boton esta en listaPeliculas.jsp). 
	//Notese que en este ejemplo se ocupa data binding entre lo que se esta escribiendo en el formulario y la clase modelo, por eso acá esta presente @ModelAttribute, que se encarga de realizar el databinding, que aun no esta completo hasta que le demos click en guardar (ahi se ejecuta el siguiente metodo:guardar)
	//es mejor usar @ModelAttribute y <form:form> en la vista (formPeliculas en nuestro caso) cuando existe una clase de modelo (en nuestro caso la clase Pelicula). Sino, ocupar model.addatribute como en el resto de los metodos aca descritos
	@GetMapping("/create")
	public String crear(@ModelAttribute Pelicula pelicula, Model model) { // @ModelAttribute se usa para vincular lo que se escribe en los inputs de las vistas (en nuestro caso formPeliculas.jsp) con el modelo a traves de la clase modelo ( en nuestro caso Pelicula)
		//model.addAttribute("generos", servicePeliculas.buscarGeneros());//aqui se agrega la lista de generos al modelo. "generos" se llama en la cista con items=generos y  servicePeliculas.buscarGeneros() retorna la lista de generos desde la clase de servicio
		return "peliculas/formPelicula";
	}
	
	//AL HACER CLICK EN GUARDAR
	//aca se completa el databinding
	@PostMapping("/save")
	public String guardar (@ModelAttribute Pelicula pelicula, BindingResult result, RedirectAttributes attributes, //Aca tenemos databinding para peliculas. el tipo BindingResult es usado es usado por el servidor para dar un informe detallado en caso de errores en el dataBinding
		 @RequestParam("archivoImagen") MultipartFile multiPart, HttpServletRequest request) { //Multipart is a container that holds multiple body parts. Multipart provides methods to retrieve and set its subparts.  A ServletRequest object provides data including parameter name and values, attributes, and an input stream. 
		
		
		if(result.hasErrors()) { //para probar
			System.out.println("Existieron errores");
			return "peliculas/formPelicula";	
		}
		
		if (!multiPart.isEmpty()) { //si el contenedor no esta vacio
			String nombreImagen = Utileria.guardarImagen(multiPart,request);
			pelicula.setImagen(nombreImagen); //pelicula en este punto es lo que lo que el usuario ha escrito en el formulario. Esto es posible gracias a @ModelAtribute que ha unido lo que se escribe en el formulario create con la clase modelo
			}
		
		System.out.println("Antes: " + pelicula.getDetalle());
		
		serviceDetalles.insertar(pelicula.getDetalle());////por inyeccion de dependencias, para insertar el detalle de una nueva pelicula escrita en el formulario a la lista de peliculas. insertar esta sobreescrito en DetallesService
		
		System.out.println("Despues: " + pelicula.getDetalle());
	
		
		servicePeliculas.insertar(pelicula); //por inyeccion de dependencias, para insertar una nueva pelicula escrita en el formulario a la lista de peliculas
		
		
		attributes.addFlashAttribute("mensaje", "El registro fue guardado"); //agregamos un mensaje con flashatributes, sera visible en pantalla solo cuando terminamos de agregar una pelicula y se redirecciona a redirect:/peliculas/index
		
		
		return "redirect:/peliculas/index"; //redireccionamiento. el redireccionamiento es siempre tipo get, y aca estamos ocupando post en metodo guardar. addFlashatributes se ocupa para que no se produzcan conflicos .
	}
	
//AL HACER CLICK EN BOTON EDITAR (al lado de cada pelicula)
	@GetMapping(value="/edit/{id}")
	public String editar (@PathVariable("id") int idPelicula, Model model) {//Model model porque en el formulario formPelicula aparece ModelAtributte="pelicula"
		//model.addAttribute("generos", servicePeliculas.buscarGeneros());//para que al renderizar reconozca el genero que estaba
		Pelicula pelicula = servicePeliculas.buscarPorId(idPelicula);
		model.addAttribute("pelicula", pelicula);
		return "peliculas/formPelicula";
	}
	
	
	//AL HACER CLICK EN BOTON ELIMINAR (al lado de cada pelicula)
	@GetMapping(value="/delete/{id}")
	public String eliminar (@PathVariable ("id") int idPelicula, RedirectAttributes attributes) {
		
		Pelicula pelicula = servicePeliculas.buscarPorId(idPelicula);
		
		//Primero eliminar pelicula
		servicePeliculas.eliminar(idPelicula); // OJO!! este eliminar es el metodo sobreescrito en PeliculasService JPA. LOS DOS METODOS SE LLAMAN IGUAL!!!
		
		//luego eliminar detalle
		serviceDetalles.eliminar(pelicula.getDetalle().getId());//
		
		attributes.addFlashAttribute("mensaje", "La pelicula fue eliminada"); //mensaje aparece como expression language en listaPeliculas.jsp
		return "redirect:/peliculas/index";
	}
	
	
	@ModelAttribute("generos") //anotacion @ModelAttribute antes de un metodo (no como argumento) tiene otro sentido: ejecutar este metodo en cada metodo de esta clase. Con esto evitamos tener que escribir /model.addAttribute("generos", servicePeliculas.buscarGeneros()) dos veces en los metodos editar y crear
	public List <String> getGeneros(){
		return servicePeliculas.buscarGeneros();
	}

	
	//Esto es para que el formato especificado coincida con el formato del sistema operativo, en este caso la fecha del sistema oper. es dd//MM/aaaa y nosotros debemos forzarla a que sea dd-MM-yyyy
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
	//METODO DE PAGINACION, AL HACER CLICK EN 'SIGUIENTE' O 'ATRAS'
	/*@GetMapping(value = "/indexPaginate")
	public String mostrarIndexPaginado(Model model, Pageable page) {
	Page<Pelicula> lista = servicePeliculas.buscarTodas(page);
	model.addAttribute("peliculas", lista);
	return "peliculas/listPeliculas";
	}*/

}
