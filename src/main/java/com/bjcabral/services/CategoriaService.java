package com.bjcabral.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bjcabral.domain.Categoria;
import com.bjcabral.repositories.CategoriaRepository;
import com.bjcabral.resources.exception.MsgException;
import com.bjcabral.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	 
	public Categoria findById(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException(MsgException.NOT_FOUND + "ID: " + id
				+ "| Tipo: " + Categoria.class.getName()
				));
	}
	
	public Categoria insert(Categoria categoria) {
		
		return repo.save(categoria);
	}

	public Categoria update(Categoria categoria) {
		findById(categoria.getId());
		return repo.save(categoria);
	}

}
