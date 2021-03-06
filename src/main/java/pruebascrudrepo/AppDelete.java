package pruebascrudrepo;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import net.itinajero.app.repository.NoticiasRepository;

public class AppDelete {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
		NoticiasRepository repo = context.getBean("noticiasRepository", NoticiasRepository.class);
		
		// Operacion CRUD - Delete [metodo deleteById del repositorio]
		int idNoticia = 50;
		//repo.deleteById(idNoticia);
		
		
		 if(repo.existsById(idNoticia)) { //con esto nos aseguramos que si no existe ese registro no se intenta el borrado (y por lo tanto no se genera una excepcion)
			 repo.deleteById(idNoticia); 
			 }
		 
				
		context.close();
	}

}
