package com.bjcabral.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bjcabral.domain.Pagamento;
import com.bjcabral.repositories.PagamentoRepository;

@Service
public class PagamentoService {

	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	public void insert(Pagamento pagamento) {

		pagamentoRepository.save(pagamento);
		
	}

	
	
}
