package net.itinajero.app.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//Todas las java anotations en esta clase configuran nuestra clase de modelo pueda ser administrada o persistida por un repositorio de SpringData JPA

@Entity //La entidad es la clase java que queremos enlazar con la BBDD
@Table(name="noticias") //name="noticias" nombre de nuestra tabla en la BBDD 
public class Noticia {
	
	@Id //con esto declaramos el identificador de nuestra entidad, es decir la llave primaria de nuestra tabla. Deberá ser unico para cada entidad
	@GeneratedValue(strategy=GenerationType.IDENTITY)//indicamos que el valor de la llave primaria se genera automaticamente
	
	//@Column si el nombre de nuestro atributo en nuestra entidad, mapea con un campo de una tabla y tienen el mismo nombre, en este caso se puede omitir la anotación @Column. Esa es la razón por la cual en nuestra clase de modelo Noticia no hemos utilizado la anotación @Column en los atributos de la clase.
	private int id;
	private String titulo;
	private Date fecha;
	private String detalle;
	private String estatus;
	
	public Noticia() {
		
		this.fecha = new Date();
		this.estatus="Activa";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		
		this.titulo = titulo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
	
		this.detalle = detalle;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		
		this.estatus = estatus;
	}

	@Override
	public String toString() {
		return "Noticia [id=" + id + ", titulo=" + titulo + ", fecha=" + fecha + ", detalle=" + detalle + ", estatus="
				+ estatus + "]";
	}
	
	
	

}
