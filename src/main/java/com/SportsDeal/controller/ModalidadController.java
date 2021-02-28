package com.SportsDeal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SportsDeal.model.entity.Modalidad;
import com.SportsDeal.model.repository.ModalidadRepository;


@RestController
public class ModalidadController {

	@Autowired 
	ModalidadRepository modalidadrepo;
	
	@GetMapping("modalidad/all")
	public Iterable<Modalidad> getTodasModalidades(){
		return this.modalidadrepo.findAll();
	}
	
	@GetMapping("/modalidad/filterByDescripcion")
	public Modalidad filterByDescripcion (String descripcion){
		return modalidadrepo.findByDescripcion(descripcion);
	}
	
	@GetMapping("/modalidad/filterById")
	public Modalidad filterByIModalidad (int id){
		return modalidadrepo.findById(id);
	}
	
	
	
}
