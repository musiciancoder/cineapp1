package pruebascrudrepo;

//IMPORTANTE: HACERLO CORRER NO CON RUN ON SERVER, SINO CON JAVA APPLICATION

import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.itinajero.app.model.Noticia;
import net.itinajero.app.repository.NoticiasRepository;

// Aplicacion para persistir (Crear) en la tabla Noticias un objeto de tipo Noticia
public class AppCreate {

	public static void main(String[] args) {
		
		Noticia noticia = new Noticia();
		//noticia.setId(1), esto no va, porque nuestra BBDD la definimos como autoincrement
		noticia.setTitulo("Proximo Estreno: Juego Macabro 8");
		noticia.setDetalle("El mes de septiembre se estrena la nueva entrega de SAW 8");
		
		Noticia noticia5 = new Noticia();
		//noticia.setId(1), esto no va, porque nuestra BBDD la definimos como autoincrement
		noticia5.setTitulo("Titulo10");
		noticia5.setDetalle("Detalle10");
		
		Noticia noticia6 = new Noticia();
		noticia6.setTitulo("Titulo60");
		noticia6.setDetalle("Detalle60");
		
		Noticia noticia7 = new Noticia();
		noticia7.setTitulo("Titulo70");
		noticia7.setDetalle("Detalle70");
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");//pasamos nuestro archivo root-context de nuestro directorio spring como parametro 
		//(cont) por lo que context es una instancia que contiene todos los beans presentes en este archivo
		NoticiasRepository repo = context.getBean("noticiasRepository", NoticiasRepository.class);//"noticiasRepository" es una instancia de clase NoticiasRepository; en JPA no es necesario hacer explicita esta instanciacion. 
		//(cont) segundo parametro NoticiasRepository.class es el nombre de la clase NoticiasRepository
		// Operacion CRUD - Create [metodo save del repositorio]
		repo.save(noticia);
		repo.save(noticia5);
		repo.save(noticia6);
		repo.save(noticia7);
		
		context.close();

	}

}
