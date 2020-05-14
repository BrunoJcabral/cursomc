package com.bjcabral.domain.convert;

import java.util.Arrays;

import org.springframework.stereotype.Component;

import com.bjcabral.domain.Cidade;
import com.bjcabral.domain.Cliente;
import com.bjcabral.domain.Endereco;
import com.bjcabral.domain.enums.TipoCliente;
import com.bjcabral.dto.ClienteDTO;
import com.bjcabral.dto.ClienteNewDTO;
import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

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
	
	public Cliente dtoToClass(ClienteNewDTO objDto) {
		Cliente cliente = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipoCliente()));
		Cidade cidade = new Cidade(objDto.getCidadeId(),null,null);
		Endereco endereco= new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cliente, cidade);
		cliente.getEnderecos().add(endereco);
		cliente.getTelefone().add(objDto.getTelefone1());
		
		if(objDto.getTelefone2()!=null) {
			cliente.getTelefone().add(objDto.getTelefone2());
		}
		if(objDto.getTelefone3()!=null) {
			cliente.getTelefone().add(objDto.getTelefone3());
		}
		return cliente;
	}

}
