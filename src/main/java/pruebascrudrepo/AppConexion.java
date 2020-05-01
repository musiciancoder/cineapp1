package pruebascrudrepo;

//ESTO ES SOLO UN EJEMPLO DE CONEXION, NO FORMA PARTE DE NUESTRA APLICACION PRINCIPAL

//IMPORTANTE: HACERLO CORRER NO CON RUN ON SERVER, SINO CON JAVA APPLICATION

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppConexion {

	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml"); //pasamos nuestro archivo root-context de nuestro directorio spring como parametro 
		//(cont) por lo que context es una instancia que contiene todos los beans presentes en este archivo

		context.close();
	}

}
