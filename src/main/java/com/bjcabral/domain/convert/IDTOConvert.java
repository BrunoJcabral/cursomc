package com.bjcabral.domain.convert;


public interface IDTOConvert<C, DTO>{

	public C dtoToClass(final DTO objDto); 
	public DTO classToDto(final C objClass);
}
