package io.github.lbevan.sentiment.repository.impl;

import io.github.lbevan.sentiment.service.domain.entity.AnalysisRequestEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;

/**
 * Implicit service for performing MongoDb operations on the {@link AnalysisRequestEntity} entity.
 */
public interface AnalysisRequestRepository extends MongoRepository<AnalysisRequestEntity, BigInteger> {

    /**
     * Save an {@link AnalysisRequestEntity}.
     *
     * @param entity
     * @param <S>
     * @return AnalysisRequestEntity
     */
    @Override
    <S extends AnalysisRequestEntity> S save(S entity);

    /**
     * Retrieve an {@link AnalysisRequestEntity} by its request Id.
     *
     * @param requestId
     * @return AnalysisRequestEntity
     */
    AnalysisRequestEntity findByRequestId(String requestId);
}
