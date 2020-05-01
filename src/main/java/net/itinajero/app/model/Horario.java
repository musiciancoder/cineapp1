package net.itinajero.app.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity //La entidad es la clase java que queremos enlazar con la BBDD
@Table(name="horarios") //name="horarios" nombre de nuestra tabla en la BBDD 
public class Horario {
	
	@Id //con esto declaramos el identificador de nuestra entidad, es decir la llave primaria de nuestra tabla. Deberá ser unico para cada entidad
	@GeneratedValue(strategy=GenerationType.IDENTITY)//indicamos que el valor de la llave primaria se genera automaticamente
	private int id;
	private Date fecha;
	private String hora; // HH:mm
	private String sala; 
	private double precio;
	
	//@Transient
	@ManyToOne //Distintos horarios pueden tener la misma pelicula
	@JoinColumn (name="idPelicula") //name="idPelicula" nomble de la llave foranea. //Distintos horarios pueden tener la misma pelicula
	private Pelicula pelicula;

	
	
	public Horario() { }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getSala() {
		return sala;
	}

	public void setSala(String sala) {
		this.sala = sala;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Pelicula getPelicula() {
		return pelicula;
	}

	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}

	@Override
	public String toString() {
		return "Horario [id=" + id + ", fecha=" + fecha + ", hora=" + hora + ", sala=" + sala + ", precio=" + precio
				+ ", pelicula=" + pelicula + "]";
	}
		
}
