package com.gmail.zagurskaya.service;

import com.gmail.zagurskaya.service.model.RateNBDTO;

import java.util.List;

public interface RateNBService {

    List<RateNBDTO> getRateNB();

    RateNBDTO add(RateNBDTO rateNBDTO);

}
