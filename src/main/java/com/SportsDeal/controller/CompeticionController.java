package com.SportsDeal.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SportsDeal.jwtSecurity.AutenticadorJWT;
import com.SportsDeal.model.entity.Competicion;

import com.SportsDeal.model.repository.CompeticionRepository;

@RestController
@CrossOrigin
public class CompeticionController {
	public static final int FUTBOL = 1;
	public static final int BALONCESTO = 2;
	public static final int TENIS = 3;
	public static final int MOTOR = 4;
	
	@Autowired
	CompeticionRepository competicionrepo;
	
	
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
	
private DTO getDtoFromCompeticion (Competicion c) {
		
		DTO dto = new DTO();
		dto.put("id", c.getId());
		dto.put("nombre", c.getNombre());	
		return dto;
	}
}
