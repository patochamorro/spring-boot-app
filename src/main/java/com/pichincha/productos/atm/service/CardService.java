package com.pichincha.productos.atm.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pichincha.productos.atm.exepciones.AtmExeption;
import com.pichincha.productos.atm.model.Card;
import com.pichincha.productos.atm.repository.CardRepository;

@Service
public class CardService {

	@Autowired
	private CardRepository tarjetaRepositorio;

	public boolean validarTarjetaActiva(String numeroTarjeta) throws AtmExeption {

		Card tarjeta = tarjetaRepositorio.findByCardNumber(numeroTarjeta);
		boolean esTarjetaActiva = false;
		if (tarjeta != null && "Activa".equals(tarjeta.getState())) {
			esTarjetaActiva = true;
		}
		return esTarjetaActiva;
	}

	public boolean validarTarjetaEsVigente(String numeroTarjeta) throws AtmExeption {
		Card tarjeta = tarjetaRepositorio.findByCardNumber(numeroTarjeta);
		Date fechaActual = new Date();
		boolean esTarjetaVigente = false;		
		if (fechaActual.before(tarjeta.getExpireDate())) {
			esTarjetaVigente = true;
		}
		return esTarjetaVigente;
	}

	public boolean validarPinTarjeta(String numeroTarjeta, String pinTarjeta) throws AtmExeption {
		Card tarjeta = tarjetaRepositorio.findByCardNumber(numeroTarjeta);
		boolean esPinValido = false;
		
		if(tarjeta == null) {
			throw new AtmExeption("La tarjeta no existe");
		}
		
		if(pinTarjeta.equals(tarjeta.getPin())) {
			esPinValido = true;
		}
		return esPinValido;
	}

}
