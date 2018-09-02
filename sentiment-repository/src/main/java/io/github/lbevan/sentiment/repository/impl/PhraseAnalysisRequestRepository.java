package io.github.lbevan.sentiment.repository.impl;

import io.github.lbevan.sentiment.service.domain.entity.PhraseAnalysisRequestEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;

public interface PhraseAnalysisRequestRepository extends MongoRepository<PhraseAnalysisRequestEntity, BigInteger> {

    /**
     * Insert a new Phrase Analysis Request into the database.
     *
     * @param entity
     * @param <S>
     * @return PhraseAnalysisRequestEntity
     */
    @Override
    <S extends PhraseAnalysisRequestEntity> S insert(S entity);
}
