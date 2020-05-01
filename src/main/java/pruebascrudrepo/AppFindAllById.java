package pruebascrudrepo;

import java.util.LinkedList;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.itinajero.app.model.Noticia;
import net.itinajero.app.repository.NoticiasRepository;

public class AppFindAllById {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
		NoticiasRepository repo = context.getBean("noticiasRepository", NoticiasRepository.class);

		// Recuperar varios registros por Id [metodo findAllById del repositorio]
		
		List<Integer> ids =	new LinkedList<Integer>();//LinkedList implementa List
		ids.add(2);//paso por parametro el id de interes
		//ids.add(5);
		//ids.add(8);
		
		Iterable<Noticia> it = repo.findAllById(ids); //Exactamente igual a AppFindAll. Esto es posible, ya que 
		for(Noticia n : it) {
			System.out.println(n);
		}
		
		context.close();
	}

}
