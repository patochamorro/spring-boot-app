package com.pichincha.productos.atm.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pichincha.productos.atm.exepciones.TarjetaNoExisteExepcion;
import com.pichincha.productos.atm.modelo.Tarjeta;

@Repository
public interface TarjetaRepositorio extends JpaRepository<Tarjeta, String> {

	public Tarjeta buscarTarjeta(String numeroTarjeta) throws TarjetaNoExisteExepcion;

}
