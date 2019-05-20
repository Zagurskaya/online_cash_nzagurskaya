package com.gmail.zagurskaya.online.cash.service;

import com.gmail.zagurskaya.online.cash.service.model.CurrencyDTO;

import java.util.List;

public interface CurrencyService {

    List<CurrencyDTO> getCurrencies();

    void add(CurrencyDTO user);

    void update(CurrencyDTO user);

    CurrencyDTO getUserById(Long id);

}
