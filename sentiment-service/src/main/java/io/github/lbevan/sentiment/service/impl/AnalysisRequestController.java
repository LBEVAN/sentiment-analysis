package io.github.lbevan.sentiment.service.impl;

import com.google.common.collect.ImmutableMap;
import io.github.lbevan.rabbitmq.service.impl.RabbitMQService;
import io.github.lbevan.sentiment.repository.impl.AnalysisRequestRepository;
import io.github.lbevan.sentiment.service.Util.UUIDGenerator;
import io.github.lbevan.sentiment.service.domain.AnalysisType;
import io.github.lbevan.sentiment.service.domain.dto.AnalysisRequestDto;
import io.github.lbevan.sentiment.service.domain.dto.AnalysisRequestResponseDto;
import io.github.lbevan.sentiment.service.domain.dto.TextAnalysisRequestDto;
import io.github.lbevan.sentiment.service.domain.entity.AnalysisRequestEntity;
import io.github.lbevan.sentiment.service.domain.misc.RequestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.UUID;

/**
 * REST API for Analysis Requests.
 */
@RestController()
@RequestMapping(value = "/api/request",
        headers = { "accept=application/json" },
        produces = "application/json")
@CrossOrigin
public class AnalysisRequestController {

    private final AnalysisRequestRepository analysisRequestRepository;
    private final RabbitMQService rabbitMQService;

    /**
     * Constructor.
     *
     * @param analysisRequestRepository
     * @param rabbitMQService
     */
    @Autowired
    public AnalysisRequestController(AnalysisRequestRepository analysisRequestRepository,
                                     RabbitMQService rabbitMQService) {
        this.analysisRequestRepository = analysisRequestRepository;
        this.rabbitMQService = rabbitMQService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnalysisRequestDto> getAnalysisRequest(@PathVariable String id) {
        AnalysisRequestEntity analysisRequestEntity = analysisRequestRepository.findByRequestId(id);

        // entity not found - return 404
        if(analysisRequestEntity == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new AnalysisRequestDto(analysisRequestEntity), HttpStatus.OK);
    }

    /**
     * REST API Endpoint. Request analysis of a of piece of text.
     *
     * @return ResponseEntity<UUID>
     */
    @PostMapping(value = "/text", headers = { "accept=application/json", "content-type=application/json" })
    public ResponseEntity<AnalysisRequestResponseDto> createTextAnalysisRequest(
            @RequestBody TextAnalysisRequestDto request) {
        UUID requestId = UUIDGenerator.generateUUID();

        request.setRequestId(requestId.toString());

        AnalysisRequestEntity analysisRequestEntity = new AnalysisRequestEntity(
                requestId.toString(),
                RequestStatus.REQUESTED,
                Instant.now(),
                ImmutableMap.of("Text", request.getText()));

        try {
            analysisRequestEntity = analysisRequestRepository.save(analysisRequestEntity);
        } catch(Exception e) {
            System.out.println(e);
        }

        rabbitMQService.sendTextAnalysisRequest(request);

        return new ResponseEntity<>(new AnalysisRequestResponseDto(requestId.toString()), HttpStatus.OK);
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
