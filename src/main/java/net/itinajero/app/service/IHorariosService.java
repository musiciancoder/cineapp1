package net.itinajero.app.service;

import java.util.Date;
import java.util.List;

import net.itinajero.app.model.Horario;

//Clase de servicio que contiene los metodos para buscar los horarios disponibles para una pelicula y una fecha
public interface IHorariosService {
	List<Horario>buscarPorIdPelicula(int idPelicula, Date fecha);

}