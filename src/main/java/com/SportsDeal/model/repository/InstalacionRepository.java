package com.SportsDeal.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.SportsDeal.model.entity.Instalacion;
import com.SportsDeal.model.entity.Modalidad;

@Repository
public interface InstalacionRepository extends CrudRepository<Instalacion, Integer> {

	public Instalacion findByNombre(String nombre);
	public Instalacion findById(int id);
	public List<Instalacion> findByModalidad(Modalidad modalidad);
	
	@Query(value = "Select * from instalacion as i, modalidad as m where i.idModalidad=m.id "
			+ "and m.descripcion = 'fútbol' ", nativeQuery = true)
	public List<Instalacion> getInstalacionFutbol( );
	
	@Query(value = "Select * from instalacion as i, modalidad as m where i.idModalidad=m.id "
			+ "and m.descripcion = 'baloncesto' ", nativeQuery = true)
	public List<Instalacion> getInstalacionBaloncesto();
	
	@Query(value = "Select * from instalacion as i, modalidad as m where i.idModalidad=m.id "
			+ "and m.descripcion = 'tenis' ", nativeQuery = true)
	public List<Instalacion> getInstalacionTenis();
	
	@Query(value = "Select * from instalacion as i, modalidad as m where i.idModalidad=m.id "
			+ "and m.descripcion = 'motor'", nativeQuery = true)
	public List<Instalacion> getInstalacionMotor();
	
	
	
	
}
