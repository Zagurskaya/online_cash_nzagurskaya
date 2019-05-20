package com.gmail.zagurskaya.online.cash.service.converter;

import com.gmail.zagurskaya.online.cash.repository.model.RateCB;
import com.gmail.zagurskaya.online.cash.service.model.RateCBDTO;

public interface RateCBConverter {

    RateCBDTO toDTO(RateCB rateCB);

    RateCB toEntity(RateCBDTO rateCBDTO);
}
