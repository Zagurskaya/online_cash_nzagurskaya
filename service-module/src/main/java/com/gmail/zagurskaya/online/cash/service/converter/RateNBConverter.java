package com.gmail.zagurskaya.online.cash.service.converter;


import com.gmail.zagurskaya.online.cash.repository.model.RateNB;
import com.gmail.zagurskaya.online.cash.service.model.RateNBDTO;

public interface RateNBConverter {

    RateNBDTO toDTO(RateNB rateNB);

    RateNB toEntity(RateNBDTO rateNBDTO);
}
