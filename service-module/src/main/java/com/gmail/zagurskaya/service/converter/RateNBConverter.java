package com.gmail.zagurskaya.service.converter;

import com.gmail.zagurskaya.repository.model.RateNB;
import com.gmail.zagurskaya.service.model.RateNBDTO;

public interface RateNBConverter {

    RateNBDTO toDTO(RateNB rateNB);

    RateNB toEntity(RateNBDTO rateNBDTO);
}
