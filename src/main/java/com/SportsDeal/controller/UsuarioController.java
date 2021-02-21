package com.SportsDeal.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.SportsDeal.jwtSecurity.AutenticadorJWT;
import com.SportsDeal.model.entity.Usuario;
import com.SportsDeal.model.repository.UsuarioRepository;




//restController para que pueda usar la información hacia fuera

@CrossOrigin
@RestController

public class UsuarioController {
	
	@Autowired
	UsuarioRepository usurepo;
	
	
	@PostMapping("/usuario/datos")
	public DTO autenticaUsuario (@RequestBody DatosAutenticacionUsuario datos) {
		DTO dto = new DTO(); // Voy a devolver un dto

		// Intento localizar un usuario a partir de su nombre de usuario y su password
		Usuario usuAutenticado = usurepo.findByEmailAndPassword(datos.email, datos.password);
		if (usuAutenticado != null) {
			dto.put("jwt", AutenticadorJWT.codificaJWT(usuAutenticado));
		}

		// Finalmente devuelvo el JWT creado, puede estar vacío si la autenticación no ha funcionado
		return dto;
		//DTO dto = new DTO();
		//dto.put("usuario", usurepo.findByEmailAndPassword(datos.email, datos.password));
		//return dto;
	}
	
	@GetMapping("/usuario/getAutenticado")
	public DTO getUsuarioAutenticado (boolean imagen, HttpServletRequest request) {
		int idUsuAutenticado = AutenticadorJWT.getIdUsuarioDesdeJwtIncrustadoEnRequest(request); // Obtengo el usuario autenticado, por su JWT

		// Intento localizar un usuario a partir de su id
		Usuario usuAutenticado = usurepo.findById(idUsuAutenticado).get();

		// Finalmente devuelvo el JWT creado, puede estar vacío si la autenticación no ha funcionado
		return getDTOFromUsuario(usuAutenticado, imagen);
	}
	
	private DTO getDTOFromUsuario (Usuario usu, boolean incluirImagen) {
		DTO dto = new DTO(); // Voy a devolver un dto
		if (usu != null) {
			dto.put("id", usu.getId());
			dto.put("nombre", usu.getNombre());
			dto.put("email", usu.getEmail());
			dto.put("password", usu.getPassword());
			dto.put("imagen", incluirImagen? usu.getImagen() : "");
		}
		return dto;
	}
	
	
}

class DatosAutenticacionUsuario {
	String email;

	String password;

	/**
	 * Constructor
	 */
	public DatosAutenticacionUsuario(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	
}
