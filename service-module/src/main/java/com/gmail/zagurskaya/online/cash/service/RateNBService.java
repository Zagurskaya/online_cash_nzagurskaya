package com.gmail.zagurskaya.online.cash.service;


import com.gmail.zagurskaya.online.cash.service.model.RateNBDTO;

import java.util.List;

public interface RateNBService {

    List<RateNBDTO> getRatesNB();

    void add(RateNBDTO user);

    void update(RateNBDTO user);

    RateNBDTO getUserById(Long id);
}
