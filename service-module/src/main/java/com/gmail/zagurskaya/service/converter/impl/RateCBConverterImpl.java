package com.gmail.zagurskaya.service.converter.impl;

import com.gmail.zagurskaya.repository.model.RateCB;
import com.gmail.zagurskaya.service.converter.RateCBConverter;
import com.gmail.zagurskaya.service.model.RateCBDTO;
import org.springframework.stereotype.Component;

@Component
public class RateCBConverterImpl implements RateCBConverter {

    @Override
    public RateCBDTO toDTO(RateCB rateCB) {
        RateCBDTO rateCBDTO = new RateCBDTO();
        rateCBDTO.setId(rateCB.getId());
        rateCBDTO.setComing(rateCB.getComing());
        rateCBDTO.setSpending(rateCB.getSpending());
        rateCBDTO.setTimestamp(rateCB.getTimestamp());
        rateCBDTO.setSum(rateCB.getSum());
        rateCBDTO.setIsBack(rateCB.getIsBack());
        return rateCBDTO;
    }

    @Override
    public RateCB toEntity(RateCBDTO rateCBDTO) {
        RateCB rateCB = new RateCB();
        rateCB.setId(rateCBDTO.getId());
        rateCB.setComing(rateCBDTO.getComing());
        rateCB.setSpending(rateCBDTO.getSpending());
        rateCB.setTimestamp(rateCBDTO.getTimestamp());
        rateCB.setSum(rateCBDTO.getSum());
        rateCB.setIsBack(rateCBDTO.getIsBack());
        return rateCB;
    }
}
