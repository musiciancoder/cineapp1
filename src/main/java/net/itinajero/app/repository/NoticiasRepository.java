package net.itinajero.app.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import net.itinajero.app.model.Noticia;

//Al crear un repositorio, tenemos acceso a las operaciones basicas del CRUD (en este caso este repositorio sirve para las operaciones basicas del CRUD en la tabla noticias 
@Repository
//public interface NoticiasRepository extends CrudRepository<Noticia, Integer> {//para ocupar los metodos de la interfaz CrudRepository
public interface NoticiasRepository extends JpaRepository<Noticia, Integer> {//para ocupar los metodos de la interfaz JpaRepository

	//select * from Noticia;esto seria el equivalente en SQL de la linea siguiente
	List<Noticia>findBy(); //findBy(); con este metodo se recuperan todas las entidades de una tabla
	
	
	//select * from Noticia where estatus =?;esto seria el equivalente en SQL de la linea siguiente
	List<Noticia>findByEstatus(String estatus);//findBy(); con este metodo se recuperan todas las entidades de una tabla de forma filtrada

	//select * from Noticia where fecha=?
	List<Noticia>findByFecha(Date fecha);

	//where estatus and fecha=?; las dos condiciones deben cumplirse al mismo tiempo
	List<Noticia>findByEstatusAndFecha(String estatus, Date fecha);//entidades filtradas por eststus y fecha

	//where estatus or fecha=?; cualquiera de las dos condiciones deben cumplirse 
		List<Noticia>findByEstatusOrFecha(String estatus, Date fecha);//entidades filtradas por eststus y fecha

		//where fecha between ? and ?; buscar entre un rango de valores
				List<Noticia>findByFechaBetween(Date fecha1, Date fecha2);

}
