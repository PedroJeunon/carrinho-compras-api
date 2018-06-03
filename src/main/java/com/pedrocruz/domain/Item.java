package com.pedrocruz.domain;

import java.math.BigDecimal;

/**
 * Classe que representa um item no carrinho de compras.
 */
public class Item {

	private Produto produto;
	private BigDecimal valorUnitario;
	private int quantidade;

	/**
	 * Construtor da classe Item.
	 * 
	 * @param produto
	 * @param valorUnitario
	 * @param quantidade
	 */
	public Item(Produto produto, BigDecimal valorUnitario, int quantidade) {
		this.produto = produto;
		this.valorUnitario = valorUnitario;
		this.quantidade = quantidade;
	}

	/**
	 * Retorna o produto.
	 *
	 * @return Produto
	 */
	public Produto getProduto() {
		return this.produto;
	}

	/**
	 * Retorna o valor unitário do item.
	 *
	 * @return BigDecimal
	 */
	public BigDecimal getValorUnitario() {
		return this.valorUnitario;
	}

	/**
	 * Retorna a quantidade dos item.
	 *
	 * @return int
	 */
	public int getQuantidade() {
		return this.quantidade;
	}

	/**
	 * Retorna o valor total do item.
	 *
	 * @return BigDecimal
	 */
	public BigDecimal getValorTotal() {
		return this.valorUnitario.multiply(new BigDecimal(this.quantidade));
	}

	/**
	 * Metodo que incrementa a quantidade de itens.
	 * 
	 * @param quantidade
	 */
	public void setNovaQuantidade(int novaQuantidade) {
		this.quantidade += novaQuantidade;
	}

	/**
	 * Metodo que atualiza o valor unitario do item.
	 */
	public void setNovoValorUnitario(BigDecimal novoValor) {
		this.valorUnitario = novoValor;
	}
}