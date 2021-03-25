package com.marzio.curso.boot.domain;

import java.util.List;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
@Entity
@Table(name = "DEPARTAMENTOS")
public class Departamento extends AbstractEntity<Long> {
	
	@Column(name = "nome" , nullable = false, unique = true, length = 60)
	private String nome;
	
	@OneToMany(mappedBy  = "departamento")
	private List<Cargo> cargos;

}
