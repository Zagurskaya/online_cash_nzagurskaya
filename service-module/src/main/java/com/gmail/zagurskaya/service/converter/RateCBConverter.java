package com.gmail.zagurskaya.service.converter;

import com.gmail.zagurskaya.repository.model.RateCB;
import com.gmail.zagurskaya.service.model.RateCBDTO;

public interface RateCBConverter {

    RateCBDTO toDTO(RateCB rateCB);

    RateCB toEntity(RateCBDTO rateCBDTO);
}
