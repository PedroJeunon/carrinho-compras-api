package com.pedrocruz.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/rest/carrinho")
public class CarrinhoComprasController {

	@RequestMapping(path = "/listarCarrinhos", method = RequestMethod.GET)
	public ResponseEntity<Object> listarCarrinhos() {
		return new ResponseEntity<Object>("Listando Carrinho", HttpStatus.OK);
	}

}
