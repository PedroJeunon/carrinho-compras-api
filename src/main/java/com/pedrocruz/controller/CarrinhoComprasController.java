package com.pedrocruz.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "API para carrinho de compras")
@RestController
@RequestMapping(path = "/rest/carrinho", produces = "application/json")
public class CarrinhoComprasController {

	@ApiOperation(value = "Metodo de listar todos os carrinhos disponiveis")
	@RequestMapping(path = "/listarcarrinhos", method = RequestMethod.GET)
	public ResponseEntity<Object> listarCarrinhos() {
		return new ResponseEntity<Object>("Listando Carrinho", HttpStatus.OK);
	}

	@ApiOperation(value = "Metodo que lista os produtos de um determinado carrinho por cpf")
	@RequestMapping(path = "/listarcarinho/{cpf}", method = RequestMethod.GET)
	public ResponseEntity<Object> listarCarrinhosPorCPF() {
		return new ResponseEntity<Object>("Listando Carrinho por CPF", HttpStatus.OK);
	}

	@ApiOperation(value = "Metodo que cria um carrinho por cpf")
	@RequestMapping(path = "/criarcarrinho", method = RequestMethod.POST)
	public ResponseEntity<Object> criarCarrinhoPorCPF() {
		return new ResponseEntity<Object>("Criando Carrinho por CPF", HttpStatus.OK);
	}

	@ApiOperation(value = "Metodo de limpa o carrinho por cpf")
	@RequestMapping(path = "/limparcarrinho", method = RequestMethod.DELETE)
	public ResponseEntity<Object> limparCarrinhoPorCPF() {
		return new ResponseEntity<Object>("Limpando Carrinho por CPF", HttpStatus.OK);
	}

	@ApiOperation(value = "Metodo que retorna o valor medio das compras de todos os carrinhos")
	@RequestMapping(path = "/ticketmedio", method = RequestMethod.GET)
	public ResponseEntity<Object> ticketMedioPorCarrinho() {
		return new ResponseEntity<Object>("TicketMedio por Carrinho", HttpStatus.OK);
	}

	@ApiOperation(value = "Metodo que remove um produto baseado na posição no carrinho")
	@RequestMapping(path = "/removeritem/{posicao}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> removerItemPosicao() {
		return new ResponseEntity<Object>("Adicionando item no carrinho", HttpStatus.OK);
	}

	@ApiOperation(value = "Metodo que remove um produto")
	@RequestMapping(path = "/removeritem", method = RequestMethod.DELETE)
	public ResponseEntity<Object> removerItem() {
		return new ResponseEntity<Object>("Adicionando item no carrinho", HttpStatus.OK);
	}

	@ApiOperation(value = "Metodo que retorna o valor total do carrinho")
	@RequestMapping(path = "/valorTotal/{cpf}", method = RequestMethod.GET)
	public ResponseEntity<Object> valorTotalPorCarrinho() {
		return new ResponseEntity<Object>("Adicionando item no carrinho", HttpStatus.OK);
	}

	@ApiOperation(value = "Metodo que adiciona um produto ao carrinho")
	@RequestMapping(path = "/adicionarItem", method = RequestMethod.POST)
	public ResponseEntity<Object> adicionarItem() {
		return new ResponseEntity<Object>("Adicionando item no carrinho", HttpStatus.OK);
	}
}
