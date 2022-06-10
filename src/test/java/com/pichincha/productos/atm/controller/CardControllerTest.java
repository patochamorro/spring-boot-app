package com.pichincha.productos.atm.controller;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.RestTemplate;

import com.pichincha.productos.atm.model.Card;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CardControllerTest {

	@LocalServerPort
	private int port;

	private String baseUrl = "http://localhost";
	private static RestTemplate restTemplate = null;

	@BeforeAll
	public static void init() {
		restTemplate = new RestTemplate();
	}

	@BeforeEach
	public void setUp() {
		baseUrl = baseUrl.concat(":").concat(port + "").concat("/card");
	}

	@BeforeEach
	public void inicializar() {

	}

	@Test
	@Sql(statements = "insert into card (card_number , expire_date , pin, state)values ('12344556556546', sysdate+20,'1234','A')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM card", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void returnAPersonWithIdOne() {
		Card card = new Card();
		card.setCardNumber("12344556556546");
		card.setPin("1234");
		Boolean isCardValid = restTemplate.postForObject(baseUrl.concat("/validate"), card, Boolean.class);
		assertAll(() -> assertNotNull(isCardValid,"Object was null"), () -> assertEquals(true, isCardValid,"PIN invalid"));
	}
}
