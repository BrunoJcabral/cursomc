package com.bjcabral;

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
import com.bjcabral.domain.Produto;
import com.bjcabral.domain.enums.TipoCliente;
import com.bjcabral.repositories.CategoriaRepository;
import com.bjcabral.repositories.CidadeRepository;
import com.bjcabral.repositories.ClienteRepository;
import com.bjcabral.repositories.EnderecoRepository;
import com.bjcabral.repositories.EstadoRepository;
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
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Categoria categoria1 = new Categoria(null, "informatica"); 
		Categoria categoria2 = new Categoria(null, "escritorio"); 
		
		Produto p1 = new Produto(null,"Computador", 2000.00);
		Produto p2 = new Produto(null,"Impressora", 800.00);
		Produto p3 = new Produto(null,"Mouse", 80.00);

		categoria1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		categoria2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().add(categoria1);
		p2.getCategorias().addAll(Arrays.asList(categoria1,categoria2));
		p3.getCategorias().add(categoria1);
				
		categoriaRepository.saveAll(Arrays.asList(categoria1,categoria2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade cidade1 = new Cidade(null, "Uberlandia", est1);
		Cidade cidade2 = new Cidade(null, "São paulo", est2);
		Cidade cidade3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().add(cidade1);
		est2.getCidades().addAll(Arrays.asList(cidade2,cidade3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(cidade1, cidade2,cidade3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "Maria@gmail.com", "91234567890", TipoCliente.PESSOA_FISICA);
		cli1.getTelefone().addAll(Arrays.asList("34271667", "31310023"));
		
		Endereco end1 = new Endereco(null, "Rua vasco cirquine", 25, "casa", "Sitio novo", cidade1, cli1);
		Endereco end2 = new Endereco(null, "Rua Joao de Barros", 13, "casa", "Sitio novo", cidade2, cli1);
		
		cli1.getEnderecos().addAll(Arrays.asList(end1,end2));
		
		clienteRepository.save(cli1);
		enderecoRepository.saveAll(Arrays.asList(end1,end1));
		
		
	}

}
