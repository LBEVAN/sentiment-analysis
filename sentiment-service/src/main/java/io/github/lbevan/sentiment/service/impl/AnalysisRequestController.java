package io.github.lbevan.sentiment.service.impl;

import io.github.lbevan.sentiment.service.domain.AnalysisType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST API for Analysis Requests.
 */
@RestController()
@RequestMapping(value = "/api/requests",
        headers = { "accept=application/json" },
        produces = "application/json")
@CrossOrigin
public class AnalysisRequestController {

    @GetMapping("/{id}")
    public void getRequest() {
        // TODO: this
    }

    /**
     * REST API Endpoint. Retrieve the supported types of analysis.
     *
     * @return ResponseEntity<AnalysisType[]>
     */
    @GetMapping("/options")
    public ResponseEntity<AnalysisType[]> getOptions() {
        return new ResponseEntity<>(AnalysisType.values(), HttpStatus.OK);
    }
}
