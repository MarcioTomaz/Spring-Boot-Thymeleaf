package com.marzio.curso.boot.dao;

import java.util.List;

import com.marzio.curso.boot.domain.Funcionario;

public interface FuncionarioDao {
	
	void save (Funcionario funcionario);
	
	void update(Funcionario funcionario);
	
	void delete(Long id);
	
	Funcionario findById(Long id);
	
	List<Funcionario> findAll();
}
