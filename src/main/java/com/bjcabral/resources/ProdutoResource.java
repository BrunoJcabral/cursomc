package com.bjcabral.resources;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bjcabral.domain.Produto;
import com.bjcabral.dto.ProdutoDTO;
import com.bjcabral.services.ProdutoService;
import com.bjcabral.util.UrlUtil;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Produto> find(@PathVariable Integer id) {
		
		Produto produto = service.buscar(id);
		
		return ResponseEntity.ok(produto);
	}
	
	@RequestMapping( method = RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			@RequestParam(value = "nome", defaultValue = "") String nome,
			@RequestParam(value = "categorias", defaultValue = "0") String categoria,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		
		String nomeDecoded = UrlUtil.decodeParam(nome);
		List<Integer> listCategoria =  UrlUtil.decodeIntList(categoria);
		Page<Produto> listaProduto = service.search(nomeDecoded, listCategoria, page, linesPerPage, orderBy, direction);
		Page<ProdutoDTO> listaProdutoDTO = listaProduto
			.map(obj -> new ProdutoDTO(obj));
		return ResponseEntity.ok().body(listaProdutoDTO);
	}
	
}
