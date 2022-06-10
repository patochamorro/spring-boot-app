package com.pichincha.productos.atm.service;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.util.Assert;

import com.pichincha.productos.atm.exepciones.AtmExeption;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class CardServiceIntegrationTest {


	@Autowired
	CardService tarjetaServicio;
	
//	@BeforeAll
//	@Sql(statements = "insert into card (card_number , expire_date , pin, state)values ('1234567891234564', sysdate+20,'1234','Activa')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//	
//	public void inicializar() {
//		
//	}

	@Test
	@Sql(statements = "insert into card (card_number , expire_date , pin, state)values ('1234567891234564', sysdate+20,'1234','Activa')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM card", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void deberiaValidarEstadoTarjetaActiva() throws AtmExeption {

		String numeroTarjeta = "1234567891234564";
		
		boolean esTarjetaActiva = tarjetaServicio.validarTarjetaActiva(numeroTarjeta);
		Assert.isTrue(esTarjetaActiva, "La tarjeta no está ACTIVA");
	}

	@Test
	public void deberiaValidarEstadoTarjetaInactiva() throws AtmExeption {

		String numeroTarjeta = "1234567891234564";		
		boolean esTarjetaActiva = tarjetaServicio.validarTarjetaActiva(numeroTarjeta);
		Assert.isTrue(!esTarjetaActiva, "La tarjeta no está INACTIVA");
	}

	@Test
	@Sql(statements = "insert into card (card_number , expire_date , pin, state)values ('1234567891234564', sysdate-20,'1234','Activa')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM card", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void deberiaValidarCaducidadTarjetaCuandoEsteExpirada() throws AtmExeption {
		String numeroTarjeta = "1234567891234564";
		Calendar calendario = new GregorianCalendar();
		calendario.add(Calendar.YEAR, -1);
		
		boolean esTarjetaVigente = tarjetaServicio.validarTarjetaEsVigente(numeroTarjeta);

		Assert.isTrue(!esTarjetaVigente, "La tarjeta está vigente aún");
	}

	@Test
	@Sql(statements = "insert into card (card_number , expire_date , pin, state)values ('1234567891234564', sysdate+20,'1234','Activa')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM card", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void deberiaValidarCaducidadTarjetaCuandoEsteVigente() throws AtmExeption {
		String numeroTarjeta = "1234567891234564";
		Calendar calendario = new GregorianCalendar();
		calendario.add(Calendar.DAY_OF_MONTH, 1);
		
		boolean esTarjetaVigente = tarjetaServicio.validarTarjetaEsVigente(numeroTarjeta);

		Assert.isTrue(esTarjetaVigente, "La tarjeta no está vigente ");
	}

	@Test
	@Sql(statements = "insert into card (card_number , expire_date , pin, state)values ('1234567891234564', sysdate+20,'1234','Activa')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM card", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void deberiaValidarPinTarjeta() throws AtmExeption {

		String numeroTarjeta = "1234567891234564";
		String pinTarjeta = "1234";		
		boolean esPinValido = tarjetaServicio.validarPinTarjeta(numeroTarjeta, pinTarjeta);

		Assert.isTrue(esPinValido, "El PIN de la tarjeta no es válido ");
	}

	@Test
	public void deberiaValidarCuandoTarjetaNoExiste() throws AtmExeption {
		String numeroTarjeta = "1234567891234564";
		String pinTarjeta = "123";
			
		boolean seProdujoExepcion = false;
		try {
			tarjetaServicio.validarPinTarjeta(numeroTarjeta, pinTarjeta);
		} catch (AtmExeption e) {
			seProdujoExepcion = true;
		}

		Assert.isTrue(seProdujoExepcion, "La tarjeta si existe");
	}
	
}
