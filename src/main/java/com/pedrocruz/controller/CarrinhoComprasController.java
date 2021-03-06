package com.pedrocruz.controller;

import java.math.BigDecimal;
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
import com.pedrocruz.domain.ItemDTO;
import com.pedrocruz.domain.Produto;
import com.pedrocruz.domain.ProdutoDTO;
import com.pedrocruz.domain.Response;
import com.pedrocruz.domain.UsuarioDTO;
import com.pedrocruz.service.CarrinhoComprasService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "API para carrinho de compras")
@RestController
@RequestMapping(path = "/rest/carrinho", produces = "application/json")
public class CarrinhoComprasController {

	@Autowired
	private CarrinhoComprasService service;

	@ApiOperation(value = "Metodo de listar todos os carrinhos disponiveis")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna os carrinhos criado"),
			@ApiResponse(code = 204, message = "Caso não exista nenhum carrinho criado") })
	@RequestMapping(path = "/listarcarrinhos", method = RequestMethod.GET)
	public ResponseEntity<Object> listarCarrinhos() {
		Response<Object> response = new Response<Object>();
		Optional<Map<String, CarrinhoCompras>> carrinhos = service.buscarCarrinhos();
		if (carrinhos.isPresent()) {
			response.setResponse(carrinhos.get());
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
		}
	}

	@ApiOperation(value = "Metodo que lista os produtos de um determinado carrinho por cpf")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna o carrinho por cpf"),
			@ApiResponse(code = 204, message = "Caso não exista carrinho para o cpf") })
	@RequestMapping(path = "/listarcarrinho/{cpf}", method = RequestMethod.GET)
	public ResponseEntity<Object> listarCarrinhoPorCPF(@PathVariable("cpf") String cpf) {
		Response<Object> response = new Response<Object>();
		Optional<CarrinhoCompras> carrinho = service.buscarCarrinho(cpf);
		if (carrinho.isPresent()) {
			response.setResponse(carrinho.get());
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
		}
	}

	@ApiOperation(value = "Metodo que cria um carrinho por cpf")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Retorna uma mensagem dizendo que o carrinho esta ok para o cpf"),
			@ApiResponse(code = 400, message = "Caso o body tenha algum erro") })
	@RequestMapping(path = "/criarcarrinho", method = RequestMethod.POST)
	public ResponseEntity<Object> criarCarrinhoPorCPF(@Valid @RequestBody UsuarioDTO usuario, BindingResult result) {
		Response<String> response = new Response<String>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.setResponse(error.getDefaultMessage()));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}

		service.criarCarrinho(usuario.getCpf());
		response.setResponse("Carrinho ok para CPF:" + usuario.getCpf());
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@ApiOperation(value = "Metodo de limpa o carrinho por cpf")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna uma mensagem invalidando ou nao um carrinho criado"),
			@ApiResponse(code = 204, message = "Caso nao encontre nenhum carrinho para o cpf"),
			@ApiResponse(code = 400, message = "Caso o body tenha algum erro") })
	@RequestMapping(path = "/limparcarrinho", method = RequestMethod.DELETE)
	public ResponseEntity<Object> limparCarrinhoPorCPF(@Valid @RequestBody UsuarioDTO usuario, BindingResult result) {
		Response<String> response = new Response<String>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.setResponse(error.getDefaultMessage()));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}

		if (service.invalidarCarrinho(usuario.getCpf())) {
			response.setResponse("Carrinho excluido para CPF: " + usuario.getCpf());
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
		}
	}

	@ApiOperation(value = "Metodo que retorna o valor medio das compras de todos os carrinhos")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna o valor medio dos carrinhos") })
	@RequestMapping(path = "/ticketmedio", method = RequestMethod.GET)
	public ResponseEntity<Object> ticketMedioPorCarrinho() {
		Response<BigDecimal> response = new Response<BigDecimal>();
		response.setResponse(service.getValorTicketMedio());
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@ApiOperation(value = "Metodo que remove um produto baseado na posição no carrinho")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna uma mensagem dizendo se excluiu ou não o item") })
	@RequestMapping(path = "/removeritem/{cpf}/{posicao}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> removerItemPosicao(@PathVariable("cpf") String cpf,
			@PathVariable("posicao") int posicao) {
		Response<String> response = new Response<String>();
		if (service.removerItem(cpf, posicao)) {
			response.setResponse("Item excluido com sucesso");
		} else {
			response.setResponse("Não foi encontrado item para a exclusão");
		}

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@ApiOperation(value = "Metodo que remove um produto")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna uma mensagem dizendo se excluiu ou não o item"),
			@ApiResponse(code = 400, message = "Algum campo nao foi validado corretamente") })
	@RequestMapping(path = "/removeritem/{cpf}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> removerItem(@PathVariable("cpf") String cpf, @RequestBody ProdutoDTO produtoDTO,
			BindingResult result) {
		Response<String> response = new Response<String>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.setResponse(error.getDefaultMessage()));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}

		Produto produto = new Produto(produtoDTO.getCodigo(), produtoDTO.getDescricao());
		if (service.removerItem(cpf, produto)) {
			response.setResponse("Item excluido com sucesso");
		} else {
			response.setResponse("Não foi encontrado item para a exclusão");
		}

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@ApiOperation(value = "Metodo que adiciona um item ao carrinho")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna o carrinho preenchido com o novo item"),
			@ApiResponse(code = 400, message = "Algum campo não foi validado corretamente") })
	@RequestMapping(path = "/adicionarItem/{cpf}", method = RequestMethod.POST)
	public ResponseEntity<Object> adicionarItem(@PathVariable("cpf") String cpf, @RequestBody ItemDTO itemDTO,
			BindingResult result) {
		Response<Object> response = new Response<Object>();
		Produto produto = new Produto(itemDTO.getProduto().getCodigo(), itemDTO.getProduto().getDescricao());

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.setResponse(error.getDefaultMessage()));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}

		service.adicionarItem(cpf, produto, itemDTO.getValorUnitario(), itemDTO.getQuantidade());
		Optional<CarrinhoCompras> carrinho = service.buscarCarrinho(cpf);
		if (carrinho.isPresent()) {
			response.setResponse(carrinho.get());
		}

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
