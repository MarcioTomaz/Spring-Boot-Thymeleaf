package com.marzio.curso.boot.dao;

import org.springframework.stereotype.Repository;

import com.marzio.curso.boot.domain.Cargo;

@Repository
public class CargoDaoImpl extends AbstractDao<Cargo, Long> implements CargoDao {

}
