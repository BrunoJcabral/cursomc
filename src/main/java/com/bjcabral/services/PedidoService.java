package com.bjcabral.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bjcabral.domain.ItemPedido;
import com.bjcabral.domain.PagamentoBoleto;
import com.bjcabral.domain.Pedido;
import com.bjcabral.domain.enums.EstadoPagamento;
import com.bjcabral.repositories.ItemPedidoRepository;
import com.bjcabral.repositories.PedidoRepository;
import com.bjcabral.resources.exception.MsgException;
import com.bjcabral.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	@Autowired
	private BoletoService boletoService;
	 
	@Autowired
	private PagamentoService pagamentoService;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepo;
	
	public Pedido buscar(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException(MsgException.NOT_FOUND + "ID: " + id
				+ "| Tipo: " + Pedido.class.getName()
				));
	}

	@Transactional
	public Pedido insert(Pedido pedidoRequest) {

		pedidoRequest.setId(null);
		pedidoRequest.setInstante(new Date());
		pedidoRequest.getPagamento().setTipoPagamento(EstadoPagamento.PENDENTE);
		pedidoRequest.getPagamento().setPedido(pedidoRequest);
		
		if(pedidoRequest.getPagamento() instanceof PagamentoBoleto) {
			PagamentoBoleto pagamentoBoleto = (PagamentoBoleto) pedidoRequest.getPagamento();
			boletoService.preencherPagamentoBoleto(pagamentoBoleto, pedidoRequest.getInstante());
		}
		
		pedidoRequest = repo.save(pedidoRequest);
		pagamentoService.insert(pedidoRequest.getPagamento());
		
		for(ItemPedido itemPedido : pedidoRequest.getItens()) {
			itemPedido.setDesconto(0.0);
			itemPedido.setPreco(produtoService.buscar(itemPedido.getProduto().getId()).getValor());
			itemPedido.setPedido(pedidoRequest);
		}
		itemPedidoRepo.saveAll((pedidoRequest.getItens()));
		return pedidoRequest;
	}

}
