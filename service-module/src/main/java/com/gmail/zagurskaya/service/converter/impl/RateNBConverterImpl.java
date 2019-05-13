package com.gmail.zagurskaya.service.converter.impl;

import com.gmail.zagurskaya.repository.model.RateNB;
import com.gmail.zagurskaya.service.converter.RateNBConverter;
import com.gmail.zagurskaya.service.model.RateNBDTO;
import org.springframework.stereotype.Component;

@Component
public class RateNBConverterImpl implements RateNBConverter {

    @Override
    public RateNBDTO toDTO(RateNB rateNB) {
        RateNBDTO rateNBDTO = new RateNBDTO();
        rateNBDTO.setId(rateNB.getId());
        rateNBDTO.setCurrencyId(rateNB.getCurrencyId());
        rateNBDTO.setDate(rateNB.getDate());
        rateNBDTO.setSum(rateNB.getSum());
        return rateNBDTO;
    }

    @Override
    public RateNB toEntity(RateNBDTO rateNBDTO) {
        RateNB rateNB = new RateNB();
        rateNB.setId(rateNBDTO.getId());
        rateNB.setCurrencyId(rateNBDTO.getCurrencyId());
        rateNB.setDate(rateNBDTO.getDate());
        rateNB.setSum(rateNBDTO.getSum());
        return rateNB;
    }
}
