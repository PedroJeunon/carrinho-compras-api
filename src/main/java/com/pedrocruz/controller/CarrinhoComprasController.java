package com.pedrocruz.controller;

import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pedrocruz.domain.CarrinhoCompras;
import com.pedrocruz.domain.Response;
import com.pedrocruz.domain.UsuarioDTO;
import com.pedrocruz.service.CarrinhoComprasService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "API para carrinho de compras")
@RestController
@RequestMapping(path = "/rest/carrinho", produces = "application/json")
public class CarrinhoComprasController {

	@Autowired
	private CarrinhoComprasService service;

	@ApiOperation(value = "Metodo de listar todos os carrinhos disponiveis")
	@RequestMapping(path = "/listarcarrinhos", method = RequestMethod.GET)
	public ResponseEntity<Object> listarCarrinhos() {
		Response<Map<String, CarrinhoCompras>> response = new Response<Map<String, CarrinhoCompras>>();
		Optional<Map<String, CarrinhoCompras>> carrinhos = service.buscarCarrinhos();
		carrinhos.ifPresent(cars -> response.setResponse(cars));
		return ResponseEntity.ok().body(response);
	}

	@ApiOperation(value = "Metodo que lista os produtos de um determinado carrinho por cpf")
	@RequestMapping(path = "/listarcarrinho/{cpf}", method = RequestMethod.GET)
	public ResponseEntity<Object> listarCarrinhoPorCPF(@PathVariable("cpf") String cpf) {
		Response<CarrinhoCompras> response = new Response<CarrinhoCompras>();
		Optional<CarrinhoCompras> carrinho = service.buscarCarrinho(cpf);
		carrinho.ifPresent(car -> response.setResponse(car));
		return ResponseEntity.ok().body(response);
	}

	@ApiOperation(value = "Metodo que cria um carrinho por cpf")
	@RequestMapping(path = "/criarcarrinho", method = RequestMethod.POST)
	public ResponseEntity<Object> criarCarrinhoPorCPF(@Valid @RequestBody UsuarioDTO usuario, BindingResult result) {
		Response<String> response = new Response<String>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.setResponse(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		service.criarCarrinho(usuario.getCpf());
		response.setResponse("Carrinho ok para CPF:" + usuario.getCpf());
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@ApiOperation(value = "Metodo de limpa o carrinho por cpf")
	@RequestMapping(path = "/limparcarrinho", method = RequestMethod.DELETE)
	public ResponseEntity<Object> limparCarrinhoPorCPF(@Valid @RequestBody UsuarioDTO usuario, BindingResult result) {
		Response<String> response = new Response<String>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.setResponse(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		if (service.invalidarCarrinho(usuario.getCpf())) {
			response.setResponse("Carrinho excluido para CPF: " + usuario.getCpf());
		} else {
			response.setResponse("Não exste carrinho para este CPF: " + usuario.getCpf());
		}
		return ResponseEntity.ok().body(response);
	}

	// TODO - Implementar metodo
	@ApiOperation(value = "Metodo que retorna o valor medio das compras de todos os carrinhos")
	@RequestMapping(path = "/ticketmedio", method = RequestMethod.GET)
	public ResponseEntity<Object> ticketMedioPorCarrinho() {
		return new ResponseEntity<Object>("TicketMedio por Carrinho", HttpStatus.OK);
	}

	// TODO - Implementar metodo
	@ApiOperation(value = "Metodo que remove um produto baseado na posição no carrinho")
	@RequestMapping(path = "/removeritem/{posicao}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> removerItemPosicao() {
		return new ResponseEntity<Object>("Adicionando item no carrinho", HttpStatus.OK);
	}

	// TODO - Implementar metodo
	@ApiOperation(value = "Metodo que remove um produto")
	@RequestMapping(path = "/removeritem", method = RequestMethod.DELETE)
	public ResponseEntity<Object> removerItem() {
		return new ResponseEntity<Object>("Adicionando item no carrinho", HttpStatus.OK);
	}

	// TODO - Implementar metodo
	@ApiOperation(value = "Metodo que retorna o valor total do carrinho")
	@RequestMapping(path = "/valorTotal/{cpf}", method = RequestMethod.GET)
	public ResponseEntity<Object> valorTotalPorCarrinho() {
		return new ResponseEntity<Object>("Adicionando item no carrinho", HttpStatus.OK);
	}

	// TODO - Implementar metodo
	@ApiOperation(value = "Metodo que adiciona um produto ao carrinho")
	@RequestMapping(path = "/adicionarItem", method = RequestMethod.POST)
	public ResponseEntity<Object> adicionarItem() {
		return new ResponseEntity<Object>("Adicionando item no carrinho", HttpStatus.OK);
	}
}
