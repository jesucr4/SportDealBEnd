package com.SportsDeal.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SportsDeal.jwtSecurity.AutenticadorJWT;
import com.SportsDeal.model.entity.Competicion;

import com.SportsDeal.model.repository.CompeticionRepository;
import com.SportsDeal.model.repository.InstalacionRepository;
import com.SportsDeal.model.repository.ModalidadRepository;

@RestController
@CrossOrigin
public class CompeticionController {
	public static final int FUTBOL = 1;
	public static final int BALONCESTO = 3;
	public static final int TENIS = 2;
	public static final int MOTOR = 4;
	
	@Autowired
	CompeticionRepository competicionrepo;
	@Autowired
	InstalacionRepository instalacionrepo;
	@Autowired
	ModalidadRepository modalidadrepo;
	
	@GetMapping ("/torneo/id")
	public DTO torneoEncontrado (int id, HttpServletRequest request) {
		Competicion competicion = competicionrepo.findById(id);
		
		return getDtoFromCompeticion(competicion);
	}
	
	
	@GetMapping("torneo/deporte")
	public DTO torneos (int mod, HttpServletRequest request) {
		int idUsuariot = AutenticadorJWT.getIdUsuarioDesdeJwtIncrustadoEnRequest(request);
		List<DTO> listaTorneoDTO = new ArrayList<DTO>();
		DTO dtoResultado = new DTO();
		try {
			List<Competicion> competiciones = new ArrayList<Competicion>();
			switch (mod) {
			case FUTBOL: 
				competiciones = this.competicionrepo.getCompeticionFutbol();
				break;
			case BALONCESTO: 
				competiciones = this.competicionrepo.getCompeticionBaloncesto();
				break;
			case TENIS: 
				competiciones = this.competicionrepo.getCompeticionTenis();
				break;
			case MOTOR: 
				competiciones = this.competicionrepo.getCompeticionMotor();
				break;
			}			
			for (Competicion c : competiciones) {
				listaTorneoDTO.add(getDtoFromCompeticion(c));
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		
		dtoResultado.put("competiciones", listaTorneoDTO);
		return dtoResultado;
		
	}
	
	
	@DeleteMapping("torneo/delete/{id}")
	public void delete(@PathVariable("id") int id, HttpServletRequest request) {
		System.out.println("hola");
		competicionrepo.deleteById(id);
		
	}
	
	@PutMapping("torneo/nuevo")
	private DTO nuevoTorneo (@RequestBody DatosNuevoTorneo nDat, HttpServletRequest request) {
		DTO dto = new DTO();
		dto.put("result", "fail");
		Competicion com = new Competicion();
		com.setNombre(nDat.nombre);
		com.setModalidad(this.modalidadrepo.findById(nDat.idModalidad));
		com.setInstalacion(this.instalacionrepo.findById(nDat.idInstalacion));
		this.competicionrepo.save(com);
		dto.put("result", "ok");
		return dto;
	}
	
	@PostMapping("/torneo/actualizar")
	public DTO actualizadDatosTorneo (int id, @RequestBody DTO dtorecibido,  HttpServletRequest request) {
		DTO dto = new DTO();
		dto.put("result", "fail");
		try {
			Competicion competicion = competicionrepo.findById(id);
			competicion.setNombre((String) dtorecibido.get("nombre"));
			competicion.setInstalacion(this.instalacionrepo.findById((int) dtorecibido.get("instalacion")));
			competicionrepo.save(competicion);
			dto.put("result", "ok");
		}catch ( Exception ex){
			ex.printStackTrace();
		}
		
		return dto;
	}
	
	
private DTO getDtoFromCompeticion (Competicion c) {
		
		DTO dto = new DTO();
		dto.put("id", c.getId());
		dto.put("nombre", c.getNombre());	
		dto.put("modalidad", c.getModalidad());
		dto.put("instalacion", c.getInstalacion().getId());
		return dto;
	}
}

class DatosNuevoTorneo {
	String nombre;
	int idModalidad;
	int idInstalacion;
	public DatosNuevoTorneo(String nombre, int idModalidad, int idInstalacion) {
		super();
		this.nombre = nombre;
		this.idModalidad = idModalidad;
		this.idInstalacion = idInstalacion;
	}
	
}
