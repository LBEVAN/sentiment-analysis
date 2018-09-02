package io.github.lbevan.sentiment.repository.impl;

import io.github.lbevan.sentiment.service.domain.entity.TweetAnalysisRequestEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;

public interface TweetAnalysisRequestRepository extends MongoRepository<TweetAnalysisRequestEntity, BigInteger> {

    /**
     * Insert a new Tweet Analysis Request into the database.
     *
     * @param entity
     * @param <S>
     * @return TweetAnalysisRequestEntity
     */
    @Override
    <S extends TweetAnalysisRequestEntity> S insert(S entity);
}
