package com.pedrocruz.service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

import com.pedrocruz.domain.CarrinhoCompras;
import com.pedrocruz.domain.Produto;

public interface CarrinhoComprasService {

	CarrinhoCompras criarCarrinho(String cpf);

	boolean invalidarCarrinho(String cpf);

	Optional<CarrinhoCompras> buscarCarrinho(String cpf);

	Optional<Map<String, CarrinhoCompras>> buscarCarrinhos();

	boolean removerItem(String cpf, Produto produto);

	boolean removerItem(String cpf, int posicao);

	BigDecimal getValorTicketMedio();

	void adicionarItem(String cpf, Produto produto, BigDecimal valorUnitario, int quantidade);
}
