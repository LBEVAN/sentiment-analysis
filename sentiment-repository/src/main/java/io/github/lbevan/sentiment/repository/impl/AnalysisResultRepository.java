package io.github.lbevan.sentiment.repository.impl;

import io.github.lbevan.sentiment.service.domain.entity.AnalysisResult;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;
import java.util.List;

public interface AnalysisResultRepository extends MongoRepository<AnalysisResult, BigInteger> {

    /**
     *
     * @param entities
     * @param <S>
     * @return List<AnalysisResult>
     */
    @Override
    <S extends AnalysisResult> List<S> saveAll(Iterable<S> entities);
}
