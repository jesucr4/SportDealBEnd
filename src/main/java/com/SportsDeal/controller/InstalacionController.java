package com.SportsDeal.controller;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.SportsDeal.jwtSecurity.AutenticadorJWT;
import com.SportsDeal.model.entity.Instalacion;
import com.SportsDeal.model.repository.InstalacionRepository;
import com.SportsDeal.model.repository.ModalidadRepository;


@RestController
@CrossOrigin
public class InstalacionController {

	@Autowired
	InstalacionRepository instalacionrepo;
	@Autowired
	ModalidadRepository modalidadrepo;
	
	public static final int FUTBOL = 1;
	public static final int BALONCESTO = 3;
	public static final int TENIS = 2;
	public static final int MOTOR = 4;
	
	@GetMapping("instalacion/all")
	public Iterable<Instalacion> getAllInstalaciones(){
		return this.instalacionrepo.findAll();
	}
	
	@GetMapping("instalacion/nombres")
	public Iterable<Instalacion> getNombreInstalacion(int mod, HttpServletRequest request){
		return this.instalacionrepo.findByModalidad(modalidadrepo.findById(mod));
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
	
	@PutMapping("instalacion/nueva")
	private DTO nuevaInstalacion (@RequestBody DatosNuevaInstalacion newIns, HttpServletRequest request){
		
		//parseamos fecha que recibimos como tiempo unix
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String date = format.format(newIns.f_construccion);
		DTO dto = new DTO();
		dto.put("result", "fail");
		//obtenemos todos los datos de la instalación
		//creamos un nuevo objeto instalación
		Instalacion ins = new Instalacion();
		
		ins.setNombre(newIns.nombre);
		ins.setLocalidad(newIns.localidad);
		ins.setCapacidad(newIns.capacidad);
		ins.setF_Construccion(new Date(newIns.f_construccion));
		ins.setModalidad(this.modalidadrepo.findById(newIns.idModalidad));
		ins.setImagen(newIns.imagen);
		//System.out.println(ins);
		//guardamos nueva instalación en BBDD
		this.instalacionrepo.save(ins);
		
		dto.put("result", "ok");
		return dto;
		
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

class DatosNuevaInstalacion {
	String nombre;
	
	String localidad;
	int capacidad;
	long f_construccion;
	int idModalidad;
	byte[] imagen;
	
	public DatosNuevaInstalacion(String nombre, String localidad, int capacidad, long f_construccion, int idModalidad,
			byte[] imagen) {
		super();
		this.nombre = nombre;
		this.localidad = localidad;
		this.capacidad = capacidad;
		this.f_construccion = f_construccion;
		this.idModalidad = idModalidad;
		this.imagen = imagen;
	}

	public long getF_construccion() {
		return f_construccion;
	}

	public void setF_construccion(long f_construccion) {
		this.f_construccion = f_construccion;
	}
	
	
		
	
	
}