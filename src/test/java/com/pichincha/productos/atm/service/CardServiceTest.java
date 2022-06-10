package com.pichincha.productos.atm.service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

import com.pichincha.productos.atm.exepciones.AtmExeption;
import com.pichincha.productos.atm.model.Card;
import com.pichincha.productos.atm.repository.CardRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class CardServiceTest {

	@MockBean
	CardRepository tarjetaRepositorio;

	@InjectMocks
	CardService tarjetaServicio;

	@Test
	public void deberiaValidarEstadoTarjetaActiva() throws AtmExeption {

		String numeroTarjeta = "1234567891234565";
		Mockito.when(tarjetaRepositorio.findByCardNumber("1234567891234565"))
				.thenReturn(new Card("Activa", numeroTarjeta, new Date(), ""));
		boolean esTarjetaActiva = tarjetaServicio.validarTarjetaActiva(numeroTarjeta);
		Assert.isTrue(esTarjetaActiva, "La tarjeta no está ACTIVA");
	}

	@Test
	public void deberiaValidarEstadoTarjetaInactiva() throws AtmExeption {

		String numeroTarjeta = "1234567891234564";
		Mockito.when(tarjetaRepositorio.findByCardNumber(numeroTarjeta))
				.thenReturn(new Card("Inactiva", numeroTarjeta, new Date(), ""));
		boolean esTarjetaActiva = tarjetaServicio.validarTarjetaActiva(numeroTarjeta);
		Assert.isTrue(!esTarjetaActiva, "La tarjeta no está INACTIVA");
	}

	@Test
	public void deberiaValidarCaducidadTarjetaCuandoEsteExpirada() throws AtmExeption {
		String numeroTarjeta = "1234567891234564";
		Calendar calendario = new GregorianCalendar();
		calendario.add(Calendar.YEAR, -1);
		System.out.println("deberiaValidarCaducidadTarjetaCuandoEsteExpirada " + calendario.getTime());
		Mockito.when(tarjetaRepositorio.findByCardNumber(numeroTarjeta))
				.thenReturn(new Card("Activa", numeroTarjeta, calendario.getTime(), ""));
		boolean esTarjetaVigente = tarjetaServicio.validarTarjetaEsVigente(numeroTarjeta);

		Assert.isTrue(!esTarjetaVigente, "La tarjeta está vigente aún");
	}

	@Test
	public void deberiaValidarCaducidadTarjetaCuandoEsteVigente() throws AtmExeption {
		String numeroTarjeta = "1234567891234564";
		Calendar calendario = new GregorianCalendar();
		calendario.add(Calendar.DAY_OF_MONTH, 1);
		System.out.println("deberiaValidarCaducidadTarjetaCuandoEsteVigente " + calendario.getTime());
		Mockito.when(tarjetaRepositorio.findByCardNumber(numeroTarjeta))
				.thenReturn(new Card("Activa", numeroTarjeta, calendario.getTime(), ""));
		boolean esTarjetaVigente = tarjetaServicio.validarTarjetaEsVigente(numeroTarjeta);

		Assert.isTrue(esTarjetaVigente, "La tarjeta no está vigente ");
	}

	@Test
	public void deberiaValidarPinTarjeta() throws AtmExeption {

		String numeroTarjeta = "1234567891234564";
		String pinTarjeta = "123";

		Mockito.when(tarjetaRepositorio.findByCardNumber(numeroTarjeta))
				.thenReturn(new Card("Activa", numeroTarjeta, new Date(), pinTarjeta));

		boolean esPinValido = tarjetaServicio.validarPinTarjeta(numeroTarjeta, pinTarjeta);

		Assert.isTrue(esPinValido, "El PIN de la tarjeta no es válido ");
	}

	@Test
	public void deberiaValidarCuandoTarjetaNoExiste() throws AtmExeption {
		String numeroTarjeta = "1234567891234564";
		String pinTarjeta = "123";
			
		Mockito.when(tarjetaRepositorio.findByCardNumber(numeroTarjeta))
				.thenThrow(new AtmExeption("La tarjeta no existe"));
		
		boolean seProdujoExepcion = false;
		try {
			tarjetaServicio.validarPinTarjeta(numeroTarjeta, pinTarjeta);
		} catch (AtmExeption e) {
			seProdujoExepcion = true;
		}

		Assert.isTrue(seProdujoExepcion, "La tarjeta si existe");
	}
	
}
