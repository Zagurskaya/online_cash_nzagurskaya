package com.gmail.zagurskaya.service.converter;

import com.gmail.zagurskaya.repository.model.Reviews;
import com.gmail.zagurskaya.service.model.ReviewsDTO;

public interface ReviewsConverter {

    ReviewsDTO toDTO(Reviews reviews);

    Reviews toEntity(ReviewsDTO reviewsDTO);
}
