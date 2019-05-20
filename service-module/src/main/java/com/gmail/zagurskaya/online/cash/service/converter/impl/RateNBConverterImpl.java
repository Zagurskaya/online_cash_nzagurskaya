package com.gmail.zagurskaya.online.cash.service.converter.impl;

import com.gmail.zagurskaya.online.cash.repository.model.Currency;
import com.gmail.zagurskaya.online.cash.repository.model.RateNB;
import com.gmail.zagurskaya.online.cash.service.converter.RateNBConverter;
import com.gmail.zagurskaya.online.cash.service.model.RateNBDTO;
import org.springframework.stereotype.Component;

@Component
public class RateNBConverterImpl implements RateNBConverter {

    @Override
    public RateNBDTO toDTO(RateNB rateNB) {
        RateNBDTO rateNBDTO = new RateNBDTO();
        rateNBDTO.setId(rateNB.getId());
        rateNBDTO.setCurrencyId(rateNB.getCurrency().getId());
        rateNBDTO.setDate(rateNB.getDate());
        rateNBDTO.setSum(rateNB.getSum());
        return rateNBDTO;
    }

    @Override
    public RateNB toEntity(RateNBDTO rateNBDTO) {
        RateNB rateNB = new RateNB();
        rateNB.setId(rateNBDTO.getId());
        Currency currency = new Currency();
        currency.setId(rateNBDTO.getCurrencyId());
        rateNB.setCurrency(currency);
        rateNB.setDate(rateNBDTO.getDate());
        rateNB.setSum(rateNBDTO.getSum());
        return rateNB;
    }
}
