package com.SportsDeal.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SportsDeal.jwtSecurity.AutenticadorJWT;
import com.SportsDeal.model.entity.Instalacion;
import com.SportsDeal.model.repository.InstalacionRepository;


@RestController
@CrossOrigin
public class InstalacionController {

	@Autowired
	InstalacionRepository instalacionrepo;
	
	public static final int FUTBOL = 1;
	public static final int BALONCESTO = 2;
	public static final int TENIS = 3;
	public static final int MOTOR = 4;
	
	@GetMapping("instalacion/all")
	public Iterable<Instalacion> getAllInstalaciones(){
		return this.instalacionrepo.findAll();
	}
	
	@GetMapping("instalacion/deporte")
	public DTO instalacionesFútbol(int mod, HttpServletRequest request){
		int idUsuautenticado = AutenticadorJWT.getIdUsuarioDesdeJwtIncrustadoEnRequest(request);
		List<DTO> listaInstalacionesEnDTO = new ArrayList<DTO>();
		
		DTO dtoResultado = new DTO();
		try {
			List<Instalacion> instalaciones = new ArrayList<Instalacion>();
			switch (mod) {
			case FUTBOL: {
					instalaciones = this.instalacionrepo.getInstalacionFutbol();			
				break;
			}
			case BALONCESTO: {
				instalaciones = this.instalacionrepo.getInstalacionBaloncesto();
				break;
			}
			case TENIS: {
				instalaciones = this.instalacionrepo.getInstalacionTenis();
				break;
			}
			case MOTOR: {
				instalaciones = this.instalacionrepo.getInstalacionMotor();
				break;
			}
			}
			for (Instalacion i : instalaciones) {
				listaInstalacionesEnDTO.add(getDtoFromInstalacion(i));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}		
		dtoResultado.put("instalaciones", listaInstalacionesEnDTO);
		return dtoResultado;
	
		
	}
	
	private DTO getDtoFromInstalacion (Instalacion i) {
		
		DTO dto = new DTO();
		dto.put("id", i.getId());
		dto.put("nombre", i.getNombre());
		dto.put("capacidad", i.getCapacidad());
		dto.put("localidad", i.getLocalidad());
		dto.put("fecha_Construccion", i.getF_Construccion());
		dto.put("idModalidad", i.getModalidad());
		dto.put("imagen", i.getImagen());
		
		return dto;
	}
	
	
}
