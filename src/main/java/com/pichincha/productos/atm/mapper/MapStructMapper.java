package com.pichincha.productos.atm.mapper;

import org.mapstruct.Mapper;

import com.pichincha.productos.atm.dto.CardDto;
import com.pichincha.productos.atm.model.Card;

@Mapper(componentModel = "spring")
public interface MapStructMapper {

	Card mapCardDtoToCard(CardDto cardDto);
}
