package pruebascrudrepo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.itinajero.app.model.Noticia;
import net.itinajero.app.repository.NoticiasRepository;

public class AppFindAll {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
		NoticiasRepository repo = context.getBean("noticiasRepository", NoticiasRepository.class);
		
		// Recuperar todos los registros [metodo findAll del repositorio] con repo.findAll()
		Iterable<Noticia> it = repo.findAll(); //Iterable es un tipo de colecciones factibles de ser iteradas por un bloque foreach
		for (Noticia n : it) {
			System.out.println(n); //imprime n de acuerdo a nuestra sobreescritura de metodo toString
		}
		
		context.close();
	}

}
