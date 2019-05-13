package com.gmail.zagurskaya.service;

import com.gmail.zagurskaya.service.model.RateCBDTO;

import java.util.List;

public interface RateCBService {

    List<RateCBDTO> getRateCB();

    RateCBDTO add(RateCBDTO rateCBDTO);

}
