package com.pichincha.productos.atm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pichincha.productos.atm.exepciones.AtmExeption;
import com.pichincha.productos.atm.model.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, String> {

	public Card findByCardNumber(String numeroTarjeta) throws AtmExeption;

}
