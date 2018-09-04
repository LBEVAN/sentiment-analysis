package io.github.lbevan.sentiment.service.impl;

import io.github.lbevan.sentiment.repository.impl.AnalysisResultRepository;
import io.github.lbevan.sentiment.service.domain.entity.AnalysisResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST API for Analysis Results.
 */
@RestController()
@RequestMapping(value = "/api/results",
        headers = { "accept=application/json" },
        produces = "application/json")
@CrossOrigin
public class AnalysisResultController {

    private final AnalysisResultRepository repository;

    /**
     * Constructor.
     *
     * @param repository
     */
    @Autowired
    public AnalysisResultController(AnalysisResultRepository repository) {
        this.repository = repository;
    }

    /**
     * REST API Endpoint. Retrieve the results of a given request.
     *
     * @return ResponseEntity<List<Analysisresult>>
     */
    @GetMapping("/{id}")
    private ResponseEntity<List<AnalysisResult>> getResultsByRequestId(@PathVariable String id) {
        List<AnalysisResult> results = repository.findByRequestId(id);
        return new ResponseEntity<>(results, HttpStatus.OK);
    }
}
