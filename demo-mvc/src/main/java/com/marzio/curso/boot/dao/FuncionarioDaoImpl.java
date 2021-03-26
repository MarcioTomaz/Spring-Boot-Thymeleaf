package com.marzio.curso.boot.dao;

import org.springframework.stereotype.Repository;

import com.marzio.curso.boot.domain.Funcionario;

@Repository
public class FuncionarioDaoImpl extends AbstractDao<Funcionario, Long> implements FuncionarioDao {

}
