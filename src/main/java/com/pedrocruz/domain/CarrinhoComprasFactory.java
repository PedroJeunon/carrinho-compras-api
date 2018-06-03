package com.pedrocruz.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe responsável pela criação e recuperação dos carrinhos de compras.
 */
public class CarrinhoComprasFactory {

	private Map<String, CarrinhoCompras> carrinhos = new HashMap<>();

	/**
	 * Cria e retorna um novo carrinho de compras para o cliente passado como
	 * parâmetro.
	 *
	 * Caso já exista um carrinho de compras para o cliente passado como parâmetro,
	 * este carrinho deverá ser retornado.
	 *
	 * @param identificacaoCliente
	 * @return CarrinhoCompras
	 */
	public CarrinhoCompras criar(String identificacaoCliente) {
		if (this.carrinhos != null && this.carrinhos.containsKey(identificacaoCliente)) {
			return this.carrinhos.get(identificacaoCliente);
		}

		CarrinhoCompras carrinho = new CarrinhoCompras();
		carrinho.setItens(new ArrayList<>());
		this.carrinhos.put(identificacaoCliente, carrinho);

		return carrinho;
	}

	/**
	 * Retorna o valor do ticket médio no momento da chamada ao método. O valor do
	 * ticket médio é a soma do valor total de todos os carrinhos de compra dividido
	 * pela quantidade de carrinhos de compra. O valor retornado deverá ser
	 * arredondado com duas casas decimais, seguindo a regra: 0-4 deve ser
	 * arredondado para baixo e 5-9 deve ser arredondado para cima.
	 *
	 * @return BigDecimal
	 */
	public BigDecimal getValorTicketMedio() {
		return null;
	}

	/**
	 * Invalida um carrinho de compras quando o cliente faz um checkout ou sua
	 * sessão expirar. Deve ser efetuada a remoção do carrinho do cliente passado
	 * como parâmetro da listagem de carrinhos de compras.
	 *
	 * @param identificacaoCliente
	 * @return Retorna um boolean, tendo o valor true caso o cliente passado como
	 *         parämetro tenha um carrinho de compras e e false caso o cliente não
	 *         possua um carrinho.
	 */
	public boolean invalidar(String identificacaoCliente) {
		if (this.carrinhos != null && this.carrinhos.containsKey(identificacaoCliente)) {
			this.carrinhos.remove(identificacaoCliente);
			return true;
		}
		return false;
	}

	/**
	 * Metodo que busca o carrinho por identificacao por cliente.
	 * 
	 * @param identificacaoCliente
	 * @return CarrinhoCompras or null
	 */
	public CarrinhoCompras buscarCarrinhoPorIdentificacao(String identificacaoCliente) {
		if (this.carrinhos != null) {
			return this.carrinhos.get(identificacaoCliente);
		}
		return null;
	}

	public Map<String, CarrinhoCompras> buscarCarrinhos() {
		return this.carrinhos;
	}
}
