package com.pichincha.productos.atm.servicio;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pichincha.productos.atm.exepciones.TarjetaNoExisteExepcion;
import com.pichincha.productos.atm.modelo.Tarjeta;
import com.pichincha.productos.atm.repositorio.TarjetaRepositorio;

@Service
public class TarjetaServicio {

	@Autowired
	private TarjetaRepositorio tarjetaRepositorio;

	public boolean validarTarjetaActiva(String numeroTarjeta) throws TarjetaNoExisteExepcion {

		Tarjeta tarjeta = tarjetaRepositorio.buscarTarjeta(numeroTarjeta);
		boolean esTarjetaActiva = false;
		if (tarjeta != null && "Activa".equals(tarjeta.getEstado())) {
			esTarjetaActiva = true;
		}
		return esTarjetaActiva;
	}

	public boolean validarTarjetaEsVigente(String numeroTarjeta) throws TarjetaNoExisteExepcion {
		Tarjeta tarjeta = tarjetaRepositorio.buscarTarjeta(numeroTarjeta);
		Date fechaActual = new Date();
		boolean esTarjetaVigente = false;		
		if (fechaActual.before(tarjeta.getFechaCaducidad())) {
			esTarjetaVigente = true;
		}
		return esTarjetaVigente;
	}

	public boolean validarPinTarjeta(String numeroTarjeta, String pinTarjeta) throws TarjetaNoExisteExepcion {
		Tarjeta tarjeta = tarjetaRepositorio.buscarTarjeta(numeroTarjeta);
		boolean esPinValido = false;
		
		if(pinTarjeta.equals(tarjeta.getPin())) {
			esPinValido = true;
		}
		return esPinValido;
	}

}
