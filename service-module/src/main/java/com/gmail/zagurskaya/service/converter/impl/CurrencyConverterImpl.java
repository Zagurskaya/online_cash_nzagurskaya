package com.gmail.zagurskaya.service.converter.impl;

import com.gmail.zagurskaya.repository.model.Currency;
import com.gmail.zagurskaya.service.converter.CurrencyConverter;
import com.gmail.zagurskaya.service.model.CurrencyDTO;
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
