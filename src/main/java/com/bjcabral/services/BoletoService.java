package com.bjcabral.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.bjcabral.domain.PagamentoBoleto;

@Service
public class BoletoService {

	public void preencherPagamentoBoleto(PagamentoBoleto pagamentoBoleto, Date instante) {
		Calendar vencimento = Calendar.getInstance();
		vencimento.setTime(instante);
		vencimento.add(Calendar.DAY_OF_MONTH, 7);
		pagamentoBoleto.setDataVencimento(vencimento.getTime());
		
	}

	
	
}
