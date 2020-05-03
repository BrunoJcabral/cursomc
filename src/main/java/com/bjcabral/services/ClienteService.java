package com.bjcabral.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bjcabral.domain.Cliente;
import com.bjcabral.repositories.ClienteRepository;
import com.bjcabral.resources.exception.MsgException;
import com.bjcabral.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	 
	public Cliente buscar(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException(MsgException.NOT_FOUND + "ID: " + id
				+ "| Tipo: " + Cliente.class.getName()
				));
	}

}
