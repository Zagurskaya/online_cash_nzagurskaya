package com.gmail.zagurskaya.online.cash.service.converter.impl;

import com.gmail.zagurskaya.online.cash.repository.model.Currency;
import com.gmail.zagurskaya.online.cash.service.converter.CurrencyConverter;
import com.gmail.zagurskaya.online.cash.service.model.CurrencyDTO;
import org.springframework.stereotype.Component;

@Component
public class CurrencyConverterImpl implements CurrencyConverter {

    @Override
    public CurrencyDTO toDTO(Currency currency) {
        CurrencyDTO currencyDTO = new CurrencyDTO();
        currencyDTO.setId(currency.getId());
        currencyDTO.setIso(currency.getIso());
        currencyDTO.setName(currency.getName());
        return currencyDTO;
    }

    @Override
    public Currency toEntity(CurrencyDTO currencyDTO) {
        Currency currency = new Currency();
        currency.setId(currencyDTO.getId());
        currency.setIso(currencyDTO.getIso());
        currency.setName(currencyDTO.getName());
        return currency;
    }
}
