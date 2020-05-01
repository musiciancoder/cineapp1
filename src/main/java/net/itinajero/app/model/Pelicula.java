package net.itinajero.app.model;

import java.util.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="peliculas") //nombre de la tabla en mysql
public class Pelicula { //ESTA ES NUESTRA CLASE DE MODELO 
	
	//notar que las variables aca declaradas se llaman igual que los names (o paths) de los inputs 
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String titulo;
	private int duracion=100;
	private String clasificacion="B";
	private String genero;
	private String imagen = "cinema.png"; //imagen por defecto
	private Date fechaEstreno;
	private String estatus="Activa";
	
	//@Transient //anotacion que le señala a jpa que un atributo de una entidad (en este caso Detalle detalle) no debe ser persistente, de esta manera el atributo no es tomado en cuenta al persistir en la BBDD. En el resultado deja detalle=null
	@OneToOne
	@JoinColumn(name="idDetalle")//con esto seteamos la llave primaria
	private Detalle detalle;//objeto compuesto
	
	@OneToMany (mappedBy="pelicula", fetch=FetchType.EAGER )// La constante Eager significa q cada vez que consultemos un registro de tipo pelicula queremos q tambien se ejecute una consulta en la tabla horarios para traernos todos los horarios que pertenezcan a la pelicula que estamos consultando
	private List<Horario> horarios;
	
	
	
	public Pelicula() { //constructor por defecto. Si no se encuentra un objeto de tipo pelicula en el modelo (porque aun no hemos llenado el formulario), el constructor por defecto es llamado, asignando los valores por defecto que hemos dado si es que inicializamos alguna variable al inicializarla, como por ejemplo private int duracion=100;
		//System.out.println("Constructor Pelicula");
		//System.out.println("Pelicula: " + this.toString());
	}
	
	
	
	public Detalle getDetalle() {
		return detalle;
	}



	public void setDetalle(Detalle detalle) {
		this.detalle = detalle;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id; //el id en azul es propiedad, el id negro viene de la instanciacion (de afuera)
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public int getDuracion() {
		return duracion;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	public String getClasificacion() {
		return clasificacion;
	}
	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public Date getFechaEstreno() {
		return fechaEstreno;
	}
	public void setFechaEstreno(Date fechaEstreno) {
		this.fechaEstreno = fechaEstreno;
	}
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	
	
	public List<Horario> getHorarios() {
		return horarios;
	}

	public void setHorarios(List<Horario> horarios) {
		this.horarios = horarios;
	}



	@Override
	public String toString() {
		return "Pelicula [id=" + id + ", titulo=" + titulo + ", duracion=" + duracion + ", clasificacion="
				+ clasificacion + ", genero=" + genero + ", imagen=" + imagen + ", fechaEstreno=" + fechaEstreno
				+ ", estatus=" + estatus + ", detalle=" + detalle + "]";
	}

	
	

}
