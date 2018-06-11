package com.pedrocruz.domain;

import javax.validation.constraints.NotEmpty;

public class ProdutoDTO {

	private Long codigo;
	private String descricao;

	@NotEmpty(message = "O codigo do produto deve estar preenchido")
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
