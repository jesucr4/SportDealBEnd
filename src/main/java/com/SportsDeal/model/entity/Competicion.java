package com.SportsDeal.model.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the competicion database table.
 * 
 */
@Entity
@NamedQuery(name="Competicion.findAll", query="SELECT c FROM Competicion c")
public class Competicion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String nombre;

	//bi-directional many-to-one association to Instalacion
	@ManyToOne
	@JoinColumn(name="idInstalacion")
	@JsonIgnore
	private Instalacion instalacion;

	//bi-directional many-to-one association to Modalidad
	@ManyToOne
	@JoinColumn(name="idModalidad")
	@JsonIgnore
	private Modalidad modalidad;

	public Competicion() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Instalacion getInstalacion() {
		return this.instalacion;
	}

	public void setInstalacion(Instalacion instalacion) {
		this.instalacion = instalacion;
	}

	public Modalidad getModalidad() {
		return this.modalidad;
	}

	public void setModalidad(Modalidad modalidad) {
		this.modalidad = modalidad;
	}

}