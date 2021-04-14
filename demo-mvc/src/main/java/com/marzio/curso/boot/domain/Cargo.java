package com.marzio.curso.boot.domain;

import javax.persistence.Column;

import java.util.List;

import javax.persistence.*;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;


@SuppressWarnings("serial")
@Getter
@Setter
@Entity
@Table(name = "CARGOS")
public class Cargo extends AbstractEntity<Long> {

	@NotBlank(message = "O nome do cargo é obrigatório. ")
	@Size(message = "O nome do cargo deve conter no máximo 60 caracteres. ")
	@Column(name = "nome" , nullable = false, unique = true, length = 60)
	private String nome;

	@NotNull(message = "Selecione o departamento relativo ao cargo. ")
	@ManyToOne
	@JoinColumn(name = "id_departamento_fk")
	private Departamento departamento;
	
	@OneToMany(mappedBy = "cargo")//a classe cargo é o lado fraco do relacionamento
	private List<Funcionario> funcionarios;

}
