package com.pedrocruz.domain;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;

public class ItemDTO {

	private ProdutoDTO produto;
	private BigDecimal valorUnitario;
	private int quantidade;

	@NotEmpty(message = "O Produto não pode ser vazio")
	public ProdutoDTO getProduto() {
		return produto;
	}

	public void setProduto(ProdutoDTO produto) {
		this.produto = produto;
	}

	@NotEmpty(message = "O valor unitário não pode ser vazio")
	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	@NotEmpty(message = "A quantidade não pode ser vazia")
	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
}
