package com.pichincha.productos.atm;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

import com.pichincha.productos.atm.exepciones.TarjetaNoExisteExepcion;
import com.pichincha.productos.atm.modelo.Tarjeta;
import com.pichincha.productos.atm.repositorio.TarjetaRepositorio;
import com.pichincha.productos.atm.servicio.TarjetaServicio;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class TarjetaRepositorioTest {
	
	@InjectMocks
	TarjetaRepositorio tarjetaRepositorio;

	public void deberiaConsultarTarjetaEistente() throws TarjetaNoExisteExepcion {
		Tarjeta tarjeta =  tarjetaRepositorio.buscarTarjeta("1234567891234565");
		boolean tarjetaExiste = false;
		if(tarjeta!=null) {
			tarjetaExiste = true;
		}
		Assert.isTrue(tarjetaExiste, "La tarjeta no existe");
	}
}
