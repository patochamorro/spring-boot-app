package com.pichincha.productos.atm.exepciones;

public class TarjetaNoExisteExepcion extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3558155569337659034L;

	public TarjetaNoExisteExepcion(String mensaje) {
		super(mensaje);
	}
}
