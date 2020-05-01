package pruebasjparepo;

import java.util.List;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Sort;
import net.itinajero.app.model.Noticia;
import net.itinajero.app.repository.NoticiasRepository;

public class AppSorting {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
		NoticiasRepository repo = context.getBean("noticiasRepository", NoticiasRepository.class);	
		// Obtener todas las entidades ordenadas por un campo ("titulo").
		//List<Noticia> lista = repo.findAll(Sort.by("titulo").descending());//quitar descending() para obtener lista ordenada ascendentemente
		
		// Obtener todas las entidades ordenadas por 2 campos.
		List<Noticia> lista = repo.findAll(Sort.by("fecha").descending().and(Sort.by("titulo").ascending()));//doble ordenamiento descendente para fecha y ascendente para titulo
		
		for(Noticia n : lista) {
			System.out.println(n);
		}
		
		context.close();

	}

}
