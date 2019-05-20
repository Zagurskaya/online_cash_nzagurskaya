package com.gmail.zagurskaya.online.cash.service;

import com.gmail.zagurskaya.online.cash.service.model.RateCBDTO;

import java.util.List;

public interface RateCBService {

    List<RateCBDTO> getRatesCB();

    void add(RateCBDTO user);

    void update(RateCBDTO user);

    RateCBDTO getUserById(Long id);

}
