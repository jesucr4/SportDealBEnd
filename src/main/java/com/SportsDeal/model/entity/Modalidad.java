package com.SportsDeal.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the modalidad database table.
 * 
 */
@Entity
@NamedQuery(name="Modalidad.findAll", query="SELECT m FROM Modalidad m")
public class Modalidad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String descripcion;

	//bi-directional many-to-one association to Competicion
	@OneToMany(mappedBy="modalidad")
	private List<Competicion> competicions;

	//bi-directional many-to-one association to Instalacion
	@OneToMany(mappedBy="modalidad")
	private List<Instalacion> instalacions;

	public Modalidad() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Competicion> getCompeticions() {
		return this.competicions;
	}

	public void setCompeticions(List<Competicion> competicions) {
		this.competicions = competicions;
	}

	public Competicion addCompeticion(Competicion competicion) {
		getCompeticions().add(competicion);
		competicion.setModalidad(this);

		return competicion;
	}

	public Competicion removeCompeticion(Competicion competicion) {
		getCompeticions().remove(competicion);
		competicion.setModalidad(null);

		return competicion;
	}

	public List<Instalacion> getInstalacions() {
		return this.instalacions;
	}

	public void setInstalacions(List<Instalacion> instalacions) {
		this.instalacions = instalacions;
	}

	public Instalacion addInstalacion(Instalacion instalacion) {
		getInstalacions().add(instalacion);
		instalacion.setModalidad(this);

		return instalacion;
	}

	public Instalacion removeInstalacion(Instalacion instalacion) {
		getInstalacions().remove(instalacion);
		instalacion.setModalidad(null);

		return instalacion;
	}

}