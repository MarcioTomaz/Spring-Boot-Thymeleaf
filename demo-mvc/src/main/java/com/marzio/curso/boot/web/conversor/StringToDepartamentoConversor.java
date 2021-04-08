package com.marzio.curso.boot.web.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.marzio.curso.boot.domain.Departamento;
import com.marzio.curso.boot.service.DepartamentoService;

@Component
public class StringToDepartamentoConversor implements Converter<String, Departamento>{
	
	@Autowired
	private DepartamentoService departamentoService;

	@Override
	public Departamento convert(String text) {
		
		if(text.isEmpty()) {
			return null;
		}
		
		Long id = Long.valueOf(text);
		return departamentoService.buscarPorId(id);
	}

}
