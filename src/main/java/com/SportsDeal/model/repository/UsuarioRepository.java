package com.SportsDeal.model.repository;

import org.springframework.data.repository.CrudRepository;

import com.SportsDeal.model.entity.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {
	
	public Usuario findByEmail(String email);
    public Usuario findByEmailAndPassword(String name,String password);

}
