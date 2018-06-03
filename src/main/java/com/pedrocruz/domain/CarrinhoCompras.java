package com.pedrocruz.domain;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Optional;

/**
 * Classe que representa o carrinho de compras de um cliente.
 */
public class CarrinhoCompras {

	private Collection<Item> itens;
	private int count = 0;

	/**
	 * Permite a adição de um novo item no carrinho de compras.
	 *
	 * Caso o item já exista no carrinho para este mesmo produto, as seguintes
	 * regras deverão ser seguidas: - A quantidade do item deverá ser a soma da
	 * quantidade atual com a quantidade passada como parâmetro. - Se o valor
	 * unitário informado for diferente do valor unitário atual do item, o novo
	 * valor unitário do item deverá ser o passado como parâmetro.
	 *
	 * Devem ser lançadas subclasses de RuntimeException caso não seja possível
	 * adicionar o item ao carrinho de compras.
	 *
	 * @param produto
	 * @param valorUnitario
	 * @param quantidade
	 */
	public void adicionarItem(Produto produto, BigDecimal valorUnitario, int quantidade) throws RuntimeException {
		// Verifica a existencia do produto na lista. Se existe ja atualiza pelo map
		// conforme
		// especificado no JavaDoc
		Optional<Item> itemOptional = this.getItens().stream().filter(i -> i.getProduto().equals(produto)).map(i -> {
			i.setNovaQuantidade(quantidade);
			if (!i.getValorUnitario().equals(valorUnitario))
				i.setNovoValorUnitario(valorUnitario);
			return i;
		}).findFirst();

		// Caso nao exista, cria um novo produto e inclui na lista.
		if (!itemOptional.isPresent()) {
			try {
				Item item = new Item(produto, valorUnitario, quantidade);
				this.getItens().add(item);
			} catch (UnsupportedOperationException e) {
				throw new UnsupportedOperationException(e.getMessage());
			} catch (ClassCastException e) {
				throw new ClassCastException(e.getMessage());
			} catch (NullPointerException e) {
				throw new NullPointerException(e.getMessage());
			} catch (IllegalArgumentException e) {
				throw new IllegalArgumentException(e.getMessage());
			} catch (IllegalStateException e) {
				throw new IllegalStateException(e.getMessage());
			}
		}
	}

	/**
	 * Permite a remoção do item que representa este produto do carrinho de compras.
	 *
	 * @param produto
	 * @return Retorna um boolean, tendo o valor true caso o produto exista no
	 *         carrinho de compras e false caso o produto não exista no carrinho.
	 */
	public boolean removerItem(Produto produto) {
		try {
			return this.getItens().removeIf(i -> i.getProduto().equals(produto));
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Permite a remoção do item de acordo com a posição. Essa posição deve ser
	 * determinada pela ordem de inclusão do produto na coleção, em que zero
	 * representa o primeiro item.
	 *
	 * @param posicaoItem
	 * @return Retorna um boolean, tendo o valor true caso o produto exista no
	 *         carrinho de compras e false caso o produto não exista no carrinho.
	 */
	public boolean removerItem(int posicaoItem) {
		try {
			this.count = 0;
			this.getItens().stream().forEach(i -> {
				if (this.count == posicaoItem) {
					this.removerItem(i.getProduto());
				}
				this.count++;
			});
			return this.count != 0;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Retorna o valor total do carrinho de compras, que deve ser a soma dos valores
	 * totais de todos os itens que compõem o carrinho.
	 *
	 * @return BigDecimal
	 */
	public BigDecimal getValorTotal() {
		return this.getItens().stream().map(Item::getValorTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	/**
	 * Retorna a lista de itens do carrinho de compras.
	 *
	 * @return itens
	 */
	public Collection<Item> getItens() {
		return this.itens;
	}

	/**
	 * Retorna a lista de itens do carrinho de compras.
	 *
	 * @return itens
	 */
	public void setItens(Collection<Item> itens) {
		this.itens = itens;
	}
}