package com.pedrocruz.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pedrocruz.domain.CarrinhoCompras;
import com.pedrocruz.domain.CarrinhoComprasFactory;

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
}
