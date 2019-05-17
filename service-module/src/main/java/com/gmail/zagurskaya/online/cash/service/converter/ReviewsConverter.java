package com.gmail.zagurskaya.online.cash.service.converter;

import com.gmail.zagurskaya.online.cash.repository.model.Reviews;
import com.gmail.zagurskaya.online.cash.service.model.ReviewsDTO;

public interface ReviewsConverter {

    ReviewsDTO toDTO(Reviews reviews);

    Reviews toEntity(ReviewsDTO reviewsDTO);
}
