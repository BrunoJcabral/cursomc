package com.bjcabral.domain.convert;

import org.springframework.stereotype.Component;

import com.bjcabral.domain.Categoria;
import com.bjcabral.domain.Cliente;
import com.bjcabral.dto.CategoriaDTO;
import com.bjcabral.dto.ClienteDTO;

@Component
public class ClienteConvert implements IDTOConvert<Cliente, ClienteDTO> {

	public ClienteConvert() {}
	
	@Override
	public Cliente dtoToClass(ClienteDTO objDto) {
		Cliente cliente = new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
		return cliente;
	}

	@Override
	public ClienteDTO classToDto(Cliente objClass) {
		ClienteDTO clienteDto =  new ClienteDTO(objClass);
		return clienteDto;
	}

}
