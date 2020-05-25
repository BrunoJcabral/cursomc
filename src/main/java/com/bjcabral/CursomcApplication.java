package com.bjcabral;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bjcabral.domain.Categoria;
import com.bjcabral.domain.Cidade;
import com.bjcabral.domain.Cliente;
import com.bjcabral.domain.Endereco;
import com.bjcabral.domain.Estado;
import com.bjcabral.domain.ItemPedido;
import com.bjcabral.domain.Pagamento;
import com.bjcabral.domain.PagamentoBoleto;
import com.bjcabral.domain.PagamentoCartao;
import com.bjcabral.domain.Pedido;
import com.bjcabral.domain.Produto;
import com.bjcabral.domain.enums.EstadoPagamento;
import com.bjcabral.domain.enums.TipoCliente;
import com.bjcabral.repositories.CategoriaRepository;
import com.bjcabral.repositories.CidadeRepository;
import com.bjcabral.repositories.ClienteRepository;
import com.bjcabral.repositories.EnderecoRepository;
import com.bjcabral.repositories.EstadoRepository;
import com.bjcabral.repositories.ItemPedidoRepository;
import com.bjcabral.repositories.PagamentoRepository;
import com.bjcabral.repositories.PedidoRepository;
import com.bjcabral.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Categoria categoria1 = new Categoria(null, "informatica"); 
		Categoria categoria2 = new Categoria(null, "escritorio"); 
		Categoria categoria3 = new Categoria(null, "Cozinha"); 
		Categoria categoria4 = new Categoria(null, "Cama, mesa e banho");
		Categoria categoria5 = new Categoria(null, "Eletronicos"); 
		Categoria categoria6 = new Categoria(null, "Sexyshop");
		Categoria categoria7 = new Categoria(null, "jadinagem"); 
		Categoria categoria8 = new Categoria(null, "lavanderia");
		
		Produto p1 = new Produto(null,"Computador", 2000.00);
		Produto p2 = new Produto(null,"Impressora", 800.00);
		Produto p3 = new Produto(null,"Mouse", 80.00);
		Produto p4 = new Produto(null,"Mesa de escritorio", 300.00);
		Produto p5 = new Produto(null,"Toalha", 50.00);
		Produto p6 = new Produto(null,"Colcha", 200.00);
		Produto p7 = new Produto(null,"Tv true Color", 1200.00);
		Produto p8 = new Produto(null,"Roçadeira", 800.00);
		Produto p9 = new Produto(null,"Abajour", 100.00);
		Produto p10 = new Produto(null,"Pendente", 180.00);
		Produto p11= new Produto(null,"Shampoo", 90.00);

		categoria1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		categoria2.getProdutos().addAll(Arrays.asList(p2, p4));
		categoria3.getProdutos().addAll(Arrays.asList(p5, p6));
		categoria4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p4));
		categoria5.getProdutos().addAll(Arrays.asList(p8));
		categoria6.getProdutos().addAll(Arrays.asList(p9, p10));
		categoria7.getProdutos().addAll(Arrays.asList(p11));
		
		p1.getCategorias().add(categoria1);
		p2.getCategorias().addAll(Arrays.asList(categoria1,categoria2));
		p3.getCategorias().add(categoria1);
		
		p1.getCategorias().addAll(Arrays.asList(categoria1, categoria4));
		p2.getCategorias().addAll(Arrays.asList(categoria1, categoria2, categoria4));
		p3.getCategorias().addAll(Arrays.asList(categoria1, categoria4));
		p4.getCategorias().addAll(Arrays.asList(categoria2));
		p5.getCategorias().addAll(Arrays.asList(categoria3));
		p6.getCategorias().addAll(Arrays.asList(categoria3));
		p7.getCategorias().addAll(Arrays.asList(categoria4));
		p8.getCategorias().addAll(Arrays.asList(categoria5));
		p9.getCategorias().addAll(Arrays.asList(categoria6));
		p10.getCategorias().addAll(Arrays.asList(categoria6));
		p11.getCategorias().addAll(Arrays.asList(categoria7));
				
		categoriaRepository.saveAll(Arrays.asList(categoria1,categoria2,categoria3,categoria4,categoria5,categoria6,categoria7,categoria8));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11));

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade cidade1 = new Cidade(null, "Uberlandia", est1);
		Cidade cidade2 = new Cidade(null, "São paulo", est2);
		Cidade cidade3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().add(cidade1);
		est2.getCidades().addAll(Arrays.asList(cidade2,cidade3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(cidade1, cidade2,cidade3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOA_FISICA);

		cli1.getTelefone().addAll(Arrays.asList("27363323", "93838393"));

		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, cidade1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, cidade2);

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);

		Pagamento pagto1 = new PagamentoCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);

		Pagamento pagto2 = new PagamentoBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00); 
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().add(ip1);
		p2.getItens().add(ip3);
		p3.getItens().add(ip2);
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
		
		
	}

}
