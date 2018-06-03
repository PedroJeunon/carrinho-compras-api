package com.pedrocruz.service;

import java.util.Map;
import java.util.Optional;

import com.pedrocruz.domain.CarrinhoCompras;

public interface CarrinhoComprasService {

	CarrinhoCompras criarCarrinho(String cpf);

	boolean invalidarCarrinho(String cpf);

	Optional<CarrinhoCompras> buscarCarrinho(String cpf);

	Optional<Map<String, CarrinhoCompras>> buscarCarrinhos();
}
