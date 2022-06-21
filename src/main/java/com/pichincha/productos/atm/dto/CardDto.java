package com.pichincha.productos.atm.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CardDto {

	private Long id;
	private String state;
	private String cardNumber;
	private Date expireDate;
	private String pin;


}
