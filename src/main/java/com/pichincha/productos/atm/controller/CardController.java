package com.pichincha.productos.atm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pichincha.productos.atm.exepciones.AtmExeption;
import com.pichincha.productos.atm.model.Card;
import com.pichincha.productos.atm.service.CardService;

@RestController
@RequestMapping("/card")
public class CardController {

	@Autowired
	private CardService cardService;

	@PostMapping("/validate")
	public ResponseEntity<Boolean> validarPinTarjeta(@RequestBody Card card) {
		try {
			return ResponseEntity.ok(cardService.validarPinTarjeta(card.getCardNumber(), card.getPin()));
		} catch (AtmExeption e) {
			return ResponseEntity.badRequest().body(null);
		}
	}
}
