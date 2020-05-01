package net.itinajero.app.controller;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.swing.Spring;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.itinajero.app.model.Horario;
import net.itinajero.app.model.Pelicula;
import net.itinajero.app.service.IHorariosService;
import net.itinajero.app.service.IPeliculasService;
import net.itinajero.app.util.Utileria;



@Controller //señala que es un controlador
	public class HomeController {
	
	
	@Autowired //se usa Autowired para insertar una instancia de nuestra interfaz de servicio en nuestra variable de forma automatica 
	private IPeliculasService servicePeliculas; // servicePeliculas instancia de interfaz IPeliculasService
	
	@Autowired
	private IHorariosService serviceHorarios; //iny de dependencias 
	
	 private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

	//AL ESCRIBIR HOME EN BARRA DE DIRECCIONES HOME O HACER CLICK EN MyCINESITE (esta era la prueba del principio)
	@RequestMapping(value="/home", method=RequestMethod.GET)  //url en la barra de direcciones
	public String goHome(){
		return "home"; // devuelve la vista (en este caso home.jsp). Es decir de la barra de direcciones pasa al controlador, quien devuelve la vista
	}
	
	
	//AL HACER CLICK EN BOTON FILTRAR (AL LADO DE LAS FECHAS)
	@RequestMapping(value="/search", method=RequestMethod.POST)  
	public String buscar(@RequestParam ("fecha") String fecha, Model model){ //metodo que recibe la fecha que selecciona el usuario en name=fecha y por medio de @RequestParam lo asocia a String fecha, que es lo que se usa en el metodo
		
		List<String> listaFechas = Utileria.getNextDays(4);
		
/*EN LA SIGUIENTE LINEA SE VE EN ACCION LA INYECCION DE DEPENDENCIAS EN Spring.class En ningun momento utilizamos el operador new, por ejemplo: servicePeliculas =new PeliculasServiceImp(), y
(cont) sin embargo Spring creo una instancia de nuestra clase de servicio de forma automatica y la inyecto en nuestro controlador con la anotacion @autowired en nuestra variable servicePeliculas*/
		List<Pelicula>peliculas = servicePeliculas.buscarTodas(); 
		
		model.addAttribute("fechas", listaFechas);//"fechas" se ocupa en el home.jsp
		model.addAttribute("fechaBusqueda", fecha);// "fechaBusqueda" es lo que se utiliza en el jsp para invocar a  dateFormat.format(new Date())); new Date() da la fecha de hoy 
		model.addAttribute("peliculas", peliculas);
		
		System.out.println("Buscando peliculas en exibicion para la fecha: " + fecha );
		return "home"; 
	}
	
	
	//AL COMIENZO DE LA APP
	@RequestMapping(value="/", method=RequestMethod.GET) 
	public String mostrarPrincipal(Model model){
		
		List<String> listaFechas = Utileria.getNextDays(4); //Utileria (en paquete util) es  static
		//System.out.println(listaFechas); //para probar en consola
		
		//LinkedList<String>peliculas = new LinkedList<>(); //Esto lo probé yo e igual funciona TODO:PREGUNTAR AL PROFE. 
		
		List<Pelicula>peliculas = servicePeliculas.buscarTodas(); //muestra la lista de todas las peliculas de la base de datos
		
		model.addAttribute("fechas", listaFechas);//"fechas" se ocupa en el home.jsp
		model.addAttribute("fechaBusqueda", dateFormat.format(new Date()));// "fechaBusqueda" es lo que se utiliza en el jsp para invocar a  dateFormat.format(new Date())); new Date() da la fecha de hoy 
		model.addAttribute("peliculas", peliculas);//clave, valor. Despues se utiliza la clave "peliculas" en el item de jsp, en nuestro caso en el archivo home.jsp y peliculas (en negro) es la lista. No embolarse conque addAtribute es un atributo que se agrega al modelo ni nada de eso.
		
		return "home";
	}
	
	
	//AL HACER CLICK EN BOTON CONSULTAR HORARIOS
	//@RequestMapping(value="/detail/{id}/{fecha}",method=RequestMethod.GET) //aca se reconoce la url dinamica, que es escrita en la barra de direcciones al presionar el boton del ConsultaHorario archivo jsp
	@RequestMapping(value="/detail/{id}/{fecha}",method=RequestMethod.GET)
	public String mostrarDetalle(Model model, @PathVariable("id") int idPelicula, @PathVariable("fecha") Date fecha){ //@PathVariable une los parametros dinamicos en en argumento de RequestMapping a la variable local, es decir une id con int idPelicula
	//public String mostrarDetalle(Model model, @RequestParam("idMovie") int idPelicula, @RequestParam("fecha") Date fecha) { //idMovie y fecha en azul vienen del home.jsp en la linea href="detail?idMovie=${pelicula.id }&fecha=${fechaBusqueda}
		List<Horario>horarios=serviceHorarios.buscarPorIdPelicula(idPelicula, fecha);
		model.addAttribute("fechaBusqueda", dateFormat.format(fecha));//fecha que habia seleccionado el usuario
		model.addAttribute("horarios", horarios);//horarios para la pelicula y fecha que selecciona el usuario
		model.addAttribute("pelicula", servicePeliculas.buscarPorId(idPelicula));// pelicula que selecciona el usuario;  servicePeliculas.buscarPorId(idPelicula) funciona por inyeccion de dependencias; buscarPorId es metodo de clase de servico PeliculasServiceJPA	

		
		return "detalle";
		
		//PODEMOS CONCLUIR QUE SE RECIBE idMovie DESDSE LA VISTA (HOME.JSP EN NUESTRO CASO), QUE ACÁ EN EL CONTROLADOR SE TRANSFORMA EN idPelicula QUE SE PASA AL MODELO POR MEDIO DE LA CLASE DE SERVICIO (CON servicePeliculas.buscarPorId(idPelicula)EN NUESTRO CASO, la clase de servicio es PeliculasService) 
		
	}
	
	
	@RequestMapping(value="/formLogin", method=RequestMethod.GET)  //formLogin viene de security.xml
	public String mostrarLogin() {
		return "formLogin";
	}
	
	
	//Esto es para que el formato especificado coincida con el formato del sistema operativo, en este caso la fecha del sistema oper. es dd//MM/aaaa y nosotros debemos forzarla a que sea dd-MM-yyyy
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
		
	
}
	
	
	


