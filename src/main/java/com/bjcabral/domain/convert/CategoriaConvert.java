package com.bjcabral.domain.convert;


import org.springframework.stereotype.Component;

import com.bjcabral.domain.Categoria;
import com.bjcabral.dto.CategoriaDTO;

@Component
public class CategoriaConvert implements IDTOConvert<Categoria, CategoriaDTO> {

	public CategoriaConvert() {}
	
	@Override
	public Categoria dtoToClass(CategoriaDTO objDto) {
		Categoria categoria = new Categoria(objDto.getId(), objDto.getNome());
		return categoria;
	}

	@Override
	public CategoriaDTO classToDto(Categoria objClass) {
		CategoriaDTO categoriaDto =  new CategoriaDTO(objClass);
		return categoriaDto;
	}

	
}
