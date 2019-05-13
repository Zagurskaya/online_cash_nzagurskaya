package com.gmail.zagurskaya.service;

import com.gmail.zagurskaya.service.model.CurrencyDTO;

import java.util.List;

public interface CurrencyService {

    List<CurrencyDTO> getCurrency();

    CurrencyDTO add(CurrencyDTO currencyDTO);

}
