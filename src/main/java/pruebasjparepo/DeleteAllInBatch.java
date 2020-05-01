package pruebasjparepo;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import net.itinajero.app.repository.NoticiasRepository;

public class DeleteAllInBatch {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
		NoticiasRepository repo = context.getBean("noticiasRepository", NoticiasRepository.class);

		// Se usa este metodo cuando tengo muchos registros. Usar con cuidado, los registors no se pueden recuperar
		repo.deleteAllInBatch();			
		
		context.close();
	}

}
