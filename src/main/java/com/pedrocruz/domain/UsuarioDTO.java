package com.pedrocruz.domain;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

public class UsuarioDTO {

	private String cpf;

	@NotEmpty(message = "CPF não pode ser vazio")
	@CPF
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}
