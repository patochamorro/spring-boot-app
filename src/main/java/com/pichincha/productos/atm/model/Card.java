package com.pichincha.productos.atm.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "cardNumber" }) })
public class Card {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String state;
	private String cardNumber;
	private Date expireDate;
	private String pin;

	public Card(String state, String cardNumber, Date expireDate, String pin) {
		super();
		this.state = state;
		this.cardNumber = cardNumber;
		this.expireDate = expireDate;
		this.pin = pin;
	}

}
