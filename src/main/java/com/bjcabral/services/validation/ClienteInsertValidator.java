package com.bjcabral.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.bjcabral.domain.Cliente;
import com.bjcabral.domain.enums.TipoCliente;
import com.bjcabral.dto.ClienteNewDTO;
import com.bjcabral.repositories.ClienteRepository;
import com.bjcabral.resources.exception.FieldMessage;
import com.bjcabral.services.validation.Util.UtilBR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		// inclua os testes aqui, inserindo erros na lista
		if(objDto.getTipoCliente().equals(TipoCliente.PESSOA_FISICA.getCod()) && !UtilBR.isValidCPF(objDto.getCpfOuCnpj())) {
				list.add(new FieldMessage("cpfOuCnpj","Numero informado não corresponde ao CPF"));
		}
		
		if(objDto.getTipoCliente().equals(TipoCliente.PESSOA_JURIDICA.getCod()) && !UtilBR.isValidCNPJ(objDto.getCpfOuCnpj())) {			
				list.add(new FieldMessage("cpfOuCnpj","Numero informado não corresponde ao CNPJ"));
		}
		
		Cliente cliente = clienteRepository.findByEmail(objDto.getEmail());
		
		if(cliente!=null) {
			list.add(new FieldMessage("email","Email informado já foi cadastrado"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}


}