package com.gmail.zagurskaya.repository.impl;

import com.gmail.zagurskaya.repository.ReviewsRepository;
import com.gmail.zagurskaya.repository.model.Reviews;
import org.springframework.stereotype.Repository;

@Repository
public class ReviewsRepositoryImpl extends GenericRepositoryImpl<Long, Reviews> implements ReviewsRepository {

}
