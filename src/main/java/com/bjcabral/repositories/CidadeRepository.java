package com.bjcabral.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bjcabral.domain.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Integer>{

}
