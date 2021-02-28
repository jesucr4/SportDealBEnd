package com.SportsDeal.model.repository;

import java.util.List;

import org.hibernate.query.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.SportsDeal.model.entity.Competicion;

public interface CompeticionRepository extends CrudRepository<Competicion, Integer> {

	@Query(value= "select c.* from competicion as c, modalidad as m where "
			+ "c.idModalidad=m.id and m.descripcion='futbol'", nativeQuery = true)
	public List<Competicion> getCompeticionFutbol();
	@Query(value= "select * from competicion as c, modalidad as m where "
			+ "c.idModalidad=m.id and m.descripcion='tenis'", nativeQuery = true)
	public List<Competicion> getCompeticionTenis();
	@Query(value= "select * from competicion as c, modalidad as m where "
			+ "c.idModalidad=m.id and m.descripcion='baloncesto'", nativeQuery = true)
	public List<Competicion> getCompeticionBaloncesto();
	@Query(value= "select * from competicion as c, modalidad as m where "
			+ "c.idModalidad=m.id and m.descripcion='motor'", nativeQuery = true)
	public List<Competicion> getCompeticionMotor();
	
	public abstract void deleteById(int id);
	public Competicion findById(int id);
	
}
