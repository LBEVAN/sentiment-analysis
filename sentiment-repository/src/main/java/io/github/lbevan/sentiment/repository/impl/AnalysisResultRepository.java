package io.github.lbevan.sentiment.repository.impl;

import io.github.lbevan.sentiment.service.domain.entity.AnalysisResult;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;
import java.util.List;

public interface AnalysisResultRepository extends MongoRepository<AnalysisResult, BigInteger> {

    /**
     * Find all of the analysis results by request id.
     *
     * @param requestId
     * @return List<AnalysisResult>
     */
    List<AnalysisResult> findByRequestId(String requestId);
}
