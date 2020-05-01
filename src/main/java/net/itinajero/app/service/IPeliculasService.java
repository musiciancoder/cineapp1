package net.itinajero.app.service;

import java.util.Date;
import java.util.List;

//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;

import net.itinajero.app.model.Pelicula;

public interface IPeliculasService {
	
	void insertar (Pelicula pelicula);//metodo3
	
	List <Pelicula> buscarTodas (); //metodo1
	
	Pelicula buscarPorId(int idPelicula);//metodo2
	
	List <String> buscarGeneros();
	
	void eliminar (int idPelicula);

	//List<Pelicula> buscarPorFecha(Date fecha);
	
	// Page<Pelicula> buscarTodas(Pageable page);
	
	

}
