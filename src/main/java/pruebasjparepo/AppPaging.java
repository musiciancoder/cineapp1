package pruebasjparepo;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import net.itinajero.app.model.Noticia;
import net.itinajero.app.repository.NoticiasRepository;

public class AppPaging {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
		NoticiasRepository repo = context.getBean("noticiasRepository", NoticiasRepository.class);	
		
		// Obtener todas las entidades por paginacion.  La primera pagina es 0 siempre!!!!
		//Page<Noticia> page = repo.findAll(PageRequest.of(0, 5));//Aca tenemos polimorfismo, ya que PageRequest es una clase q implementa la interfaz Pageable, que a su vez extiende la intefaz Page. La primera pagina es 0 siempre.
		
		// Obtener todas las entidades por paginacion y ordenamiento
		Page<Noticia> page = repo.findAll(PageRequest.of(1, 10, Sort.by("titulo")));
		
		for(Noticia n: page) {
			System.out.println(n);
		}		
		
		context.close();

	}

}
