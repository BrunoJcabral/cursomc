package com.bjcabral.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.bjcabral.domain.Categoria;
import com.bjcabral.domain.Produto;
import com.bjcabral.repositories.CategoriaRepository;
import com.bjcabral.repositories.ProdutoRepository;
import com.bjcabral.resources.exception.MsgException;
import com.bjcabral.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repo;
	
	@Autowired
	private CategoriaRepository categoriaRepo;
	 
	public Produto buscar(Integer id) {
		Optional<Produto> obj = repo.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException(MsgException.NOT_FOUND + "ID: " + id
				+ "| Tipo: " + Produto.class.getName()
				));
	}
	
	public Page<Produto> search(String nome, List<Integer> id, Integer page, Integer linesPerPage, String orderBy, String direction){
		 PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		 List<Categoria> categorias = categoriaRepo.findAllById(id);
		 return repo.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
	}

}
