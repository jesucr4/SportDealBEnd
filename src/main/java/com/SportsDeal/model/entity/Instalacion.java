package com.SportsDeal.model.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the instalacion database table.
 * 
 */
@Entity
@NamedQuery(name="Instalacion.findAll", query="SELECT i FROM Instalacion i")
public class Instalacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private int capacidad;

	@Temporal(TemporalType.DATE)
	private Date f_Construccion;

	@Lob
	private byte[] imagen;

	private String localidad;

	private String nombre;

	//bi-directional many-to-one association to Competicion
	@OneToMany(mappedBy="instalacion")
	@JsonIgnore
	private List<Competicion> competicions;

	//bi-directional many-to-one association to Modalidad
	@ManyToOne
	@JoinColumn(name="idModalidad")
	@JsonIgnore
	private Modalidad modalidad;

	public Instalacion() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCapacidad() {
		return this.capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public Date getF_Construccion() {
		return this.f_Construccion;
	}

	public void setF_Construccion(Date f_Construccion) {
		this.f_Construccion = f_Construccion;
	}

	public byte[] getImagen() {
		return this.imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public String getLocalidad() {
		return this.localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Competicion> getCompeticions() {
		return this.competicions;
	}

	public void setCompeticions(List<Competicion> competicions) {
		this.competicions = competicions;
	}

	public Competicion addCompeticion(Competicion competicion) {
		getCompeticions().add(competicion);
		competicion.setInstalacion(this);

		return competicion;
	}

	public Competicion removeCompeticion(Competicion competicion) {
		getCompeticions().remove(competicion);
		competicion.setInstalacion(null);

		return competicion;
	}

	public Modalidad getModalidad() {
		return this.modalidad;
	}

	public void setModalidad(Modalidad modalidad) {
		this.modalidad = modalidad;
	}

}