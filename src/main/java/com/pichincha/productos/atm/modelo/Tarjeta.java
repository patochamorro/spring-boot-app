package com.pichincha.productos.atm.modelo;

import java.util.Date;

public class Tarjeta {

	private String estado;
	private String numeroTarjeta;
	private Date fechaCaducidad;
	private String pin;
	
	public Tarjeta(String estado, String numeroTarjeta, Date fechaCaducidad, String pin) {
		super();
		this.estado = estado;
		this.numeroTarjeta = numeroTarjeta;
		this.fechaCaducidad = fechaCaducidad;
		this.pin = pin;
	}


	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public Date getFechaCaducidad() {
		return fechaCaducidad;
	}

	public void setFechaCaducidad(Date fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}


	public String getPin() {
		return pin;
	}


	public void setPin(String pin) {
		this.pin = pin;
	}

	
	
}
