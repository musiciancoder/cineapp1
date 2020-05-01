package pruebasquery;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.itinajero.app.model.Noticia;
import net.itinajero.app.repository.NoticiasRepository;

public class AppKeywordFindBy {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
		NoticiasRepository repo = context.getBean("noticiasRepository", NoticiasRepository.class);	
		
		// Ejemplo Keyword findBy
		
		//Notese q con Spring JPA ya NO es necesario sobreescribir los metodos definidos en la interfaz NoticiasRepository
		
		//List<Noticia>lista= repo.findBy();
		
		//List<Noticia>lista= repo.findByEstatus("Activa");
		
		
		 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		 List<Noticia> lista=null;
		try {
			lista = repo.findByFecha(format.parse("2017-09-01"));//buscar por fecha. el IDE pide que sea dentro de bloque try catch
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		 for (Noticia n: lista) { System.out.println(n); }
		 
				
		context.close();
	}

}
