package com.bjcabral.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bjcabral.domain.Categoria;
import com.bjcabral.repositories.CategoriaRepository;
import com.bjcabral.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	 
	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrato! | ID: " + id
				+ "| Tipo: " + Categoria.class.getName()
				));
	}

}
