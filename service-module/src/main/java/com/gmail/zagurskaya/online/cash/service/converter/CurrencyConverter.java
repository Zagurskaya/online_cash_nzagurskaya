package com.gmail.zagurskaya.online.cash.service.converter;

import com.gmail.zagurskaya.online.cash.repository.model.Currency;
import com.gmail.zagurskaya.online.cash.service.model.CurrencyDTO;

public interface CurrencyConverter {

    CurrencyDTO toDTO(Currency currency);

    Currency toEntity(CurrencyDTO currencyDTO);
}
