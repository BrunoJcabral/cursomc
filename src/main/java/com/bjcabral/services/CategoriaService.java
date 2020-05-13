package com.bjcabral.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.bjcabral.domain.Categoria;
import com.bjcabral.domain.Cliente;
import com.bjcabral.repositories.CategoriaRepository;
import com.bjcabral.resources.exception.MsgException;
import com.bjcabral.services.exceptions.DataIntegrityException;
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

		Categoria categoriaUpdated = findById(categoria.getId());
		categoriaUpdated.setNome(categoria.getNome());
		return repo.save(categoriaUpdated);
	}

	public void delete(Integer id) {
		findById(id);
		try {
			repo.deleteById(id);
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel deletar uma categoria que tem produtos associados!");
		}
		
		
	}

	public List<Categoria> findAll() {
		List<Categoria> obj = repo.findAll();
		
		return obj;
	}
	
	public Page<Categoria> findByPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		 PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		 
		 return repo.findAll(pageRequest);
		 }
	

}
