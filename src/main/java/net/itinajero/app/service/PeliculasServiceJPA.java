package net.itinajero.app.service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.itinajero.app.model.Horario;
import net.itinajero.app.model.Pelicula;
import net.itinajero.app.repository.HorariosRepository;
import net.itinajero.app.repository.PeliculasRepository;

@Service
public class PeliculasServiceJPA implements IPeliculasService {
	
	@Autowired//injeccion de dependencias. Se crea una instancia de la interfaz PeliculasRepository
	//(cont) Estamos inyecyando desde nuestro Root ApplicationContext una instancia de nuestro repositorio de JPA en una clase de servicio
	//notar que al integrar Spring JPA con Spring MVC, se realiza la inyeccion de dependencias en clase de servicio (PeliculasServiceJPA (aca mismo), a diferencia de Spring MVC solo, donde se realiza la inyeccion de dependencias en el controlador (PeliculasController, en nuestro caso)
	private PeliculasRepository peliculasRepo; //este campo contiene todos los metodos de JPA

	 @Autowired	
		private HorariosRepository horariosRepo;
	
	@Override
	public void insertar(Pelicula pelicula) {
		peliculasRepo.save(pelicula); //metodo para agregar una pelicula. save viene de JPA
		
	}

	@Override
	public List<Pelicula> buscarTodas() {//metodo para mostrar todas las peliculas
		return peliculasRepo.findAll(); //metodo de JPA
	}

	@Override
	public Pelicula buscarPorId(int idPelicula) { //metodo para buscar una pelicula en particular. Es llamado en HomeController
		Optional <Pelicula> optional = peliculasRepo.findById(idPelicula);  //.findById metodo de JPA
		if(optional.isPresent()) { //en caso que se encuentre
			return optional.get();
		}
		return null;//sino encuentra nada retorna null
	}

	@Override
	public List<String> buscarGeneros() {
		
		// Nota: Esta lista podria ser obtenida de una BD
				List<String> generos = new LinkedList<>();
				generos.add("Accion");
				generos.add("Aventura");
				generos.add("Clasicas");
				generos.add("Comedia Romantica");
				generos.add("Drama");
				generos.add("Terror");
				generos.add("Infantil");
				generos.add("Accion y Aventura");
				generos.add("Romantica");
				generos.add("Ciencia Ficcion");
						
				return generos;
	}

	//para eliminar peliculas
	@Override
	public void eliminar(int idPelicula) {
		peliculasRepo.deleteById(idPelicula);
		
	}
	


	//paginacion
	/*@Override
	public Page<Pelicula> buscarTodas(Pageable page) {
		return peliculasRepo.findAll(page);
}  */

}
