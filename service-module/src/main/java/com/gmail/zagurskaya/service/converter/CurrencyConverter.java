package com.gmail.zagurskaya.service.converter;

import com.gmail.zagurskaya.repository.model.Currency;
import com.gmail.zagurskaya.service.model.CurrencyDTO;

public interface CurrencyConverter {

    CurrencyDTO toDTO(Currency currency);

    Currency toEntity(CurrencyDTO currencyDTO);
}
