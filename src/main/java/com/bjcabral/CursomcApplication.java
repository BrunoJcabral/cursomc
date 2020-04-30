package com.bjcabral;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bjcabral.domain.Categoria;
import com.bjcabral.domain.Produto;
import com.bjcabral.repositories.CategoriaRepository;
import com.bjcabral.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	
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
	}

}
