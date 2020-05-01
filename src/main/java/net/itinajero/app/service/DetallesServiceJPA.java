package net.itinajero.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.itinajero.app.model.Detalle;
import net.itinajero.app.repository.DetallesRepository;

@Service
public class DetallesServiceJPA implements IDetallesService {
	
	@Autowired
	private DetallesRepository detallesRepo; //inyeccion de dependencias para poder usar los metodos de JPA al sobreescribir metodos de la interfaz

	@Override
	public void insertar(Detalle detalle) { //se ocua en el controlador (en nuestro caso Peliculas Controller, ya que la clase peliculas contiene a la clase detalles)
		detallesRepo.save(detalle); 
		
	}

	@Override
	public void eliminar(int idDetalle) {
		detallesRepo.deleteById(idDetalle);
		
	}

}
