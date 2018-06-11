package com.pedrocruz.service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pedrocruz.domain.CarrinhoCompras;
import com.pedrocruz.domain.CarrinhoComprasFactory;
import com.pedrocruz.domain.Produto;

@Service
public class CarrinhoComprasServiceImpl implements CarrinhoComprasService {

	private CarrinhoComprasFactory factory = new CarrinhoComprasFactory();

	public CarrinhoCompras criarCarrinho(String cpf) {
		return factory.criar(cpf);
	}

	public boolean invalidarCarrinho(String cpf) {
		return factory.invalidar(cpf);
	}

	public Optional<CarrinhoCompras> buscarCarrinho(String cpf) {
		return Optional.ofNullable(factory.buscarCarrinhoPorIdentificacao(cpf));
	}

	public Optional<Map<String, CarrinhoCompras>> buscarCarrinhos() {
		return Optional.ofNullable(factory.buscarCarrinhos());
	}

	public boolean removerItem(String cpf, Produto produto) {
		CarrinhoCompras carrinho = factory.buscarCarrinhoPorIdentificacao(cpf);
		if (carrinho != null) {
			return carrinho.removerItem(produto);
		}
		return false;
	}

	public boolean removerItem(String cpf, int posicao) {
		CarrinhoCompras carrinho = factory.buscarCarrinhoPorIdentificacao(cpf);
		if (carrinho != null) {
			return carrinho.removerItem(posicao);
		}
		return false;
	}

	public BigDecimal getValorTicketMedio() {
		return factory.getValorTicketMedio();
	}

	public void adicionarItem(String cpf, Produto produto, BigDecimal valorUnitario, int quantidade) {
		CarrinhoCompras carrinho = factory.buscarCarrinhoPorIdentificacao(cpf);
		if (carrinho != null) {
			carrinho.adicionarItem(produto, valorUnitario, quantidade);
		}
	}
}
