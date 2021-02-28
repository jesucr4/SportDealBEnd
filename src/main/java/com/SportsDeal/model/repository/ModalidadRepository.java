package com.SportsDeal.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.SportsDeal.model.entity.Modalidad;
@Repository
public interface ModalidadRepository extends CrudRepository<Modalidad, Integer> {

	public Modalidad findByDescripcion(String descripcion);
	public Modalidad findById(int id);
}
