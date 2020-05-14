package com.bjcabral.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bjcabral.domain.Cliente;
import com.bjcabral.repositories.ClienteRepository;
import com.bjcabral.repositories.EnderecoRepository;
import com.bjcabral.resources.exception.MsgException;
import com.bjcabral.services.exceptions.DataIntegrityException;
import com.bjcabral.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	@Autowired
	private EnderecoRepository enderecoRepositorio;

	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				MsgException.NOT_FOUND + "ID: " + id + "| Tipo: " + Cliente.class.getName()));
	}

	@Transactional
	public Cliente insert(Cliente cliente) {
		 repo.save(cliente);
		 enderecoRepositorio.saveAll(cliente.getEnderecos());
		 return cliente;
	}

	public Cliente update(Cliente cliente) {

		Cliente clienteUpdated = findById(cliente.getId());
		updateDataInfoCliente(clienteUpdated, cliente);
		return repo.save(clienteUpdated);
	}

	public void delete(Integer id) {
		findById(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException(MsgException.RELATED_ENTITY);
		}

	}

	public List<Cliente> findAll() {
		List<Cliente> obj = repo.findAll();

		return obj;
	}

	public Page<Cliente> findByPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

		return repo.findAll(pageRequest);
	}
	
	private void updateDataInfoCliente(Cliente clienteUpdated, Cliente cliente) {
		// TODO Auto-generated method stub
		clienteUpdated.setNome(cliente.getNome());
		clienteUpdated.setEmail(cliente.getEmail());
	}
	
}
