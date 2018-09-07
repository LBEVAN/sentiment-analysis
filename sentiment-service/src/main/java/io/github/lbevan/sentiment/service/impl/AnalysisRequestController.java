package io.github.lbevan.sentiment.service.impl;

import com.google.common.collect.ImmutableMap;
import io.github.lbevan.rabbitmq.service.impl.RabbitMQService;
import io.github.lbevan.sentiment.repository.impl.AnalysisRequestRepository;
import io.github.lbevan.sentiment.service.Util.UUIDGenerator;
import io.github.lbevan.sentiment.service.domain.AnalysisType;
import io.github.lbevan.sentiment.service.domain.dto.*;
import io.github.lbevan.sentiment.service.domain.entity.AnalysisRequestEntity;
import io.github.lbevan.sentiment.service.domain.misc.RequestStatus;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

    private static final Log LOGGER = LogFactory.getLog(AnalysisRequestController.class);

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
     * @param request the text analysis request
     * @return ResponseEntity<AnalysisRequestResponseDto>
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

        analysisRequestRepository.save(analysisRequestEntity);

        rabbitMQService.sendTextAnalysisRequest(request);

        return new ResponseEntity<>(new AnalysisRequestResponseDto(requestId.toString()), HttpStatus.OK);
    }

    /**
     * REST API Endpoint. Request analysis of a single tweet.
     *
     * @param request the tweet analysis request
     * @return ResponseEntity<AnalysisRequestResponseDto>
     */
    @PostMapping(value = "/tweet", headers = { "accept=application/json", "content-type=application/json" })
    public ResponseEntity<AnalysisRequestResponseDto> createTweetAnalysisRequest(
            @RequestBody TweetAnalysisRequestDto request) {
        UUID requestId = UUIDGenerator.generateUUID();

        request.setRequestId(requestId.toString());

        AnalysisRequestEntity analysisRequestEntity = new AnalysisRequestEntity(
                requestId.toString(),
                RequestStatus.REQUESTED,
                Instant.now(),
                ImmutableMap.of("Tweet Link", request.getTweetLink()));

        analysisRequestRepository.save(analysisRequestEntity);

        rabbitMQService.sendTweetAnalysisRequest(request);

        return new ResponseEntity<>(new AnalysisRequestResponseDto(requestId.toString()), HttpStatus.OK);
    }

    /**
     * REST API Endpoint. Request analysis of a set of tweets that include teh specified hashtag.
     *
     * @param request the hashtag analysis request
     * @return ResponseEntity<AnalysisRequestResponseDto>
     */
    @PostMapping(value = "/hashtag", headers = { "accept=application/json", "content-type=application/json" })
    public ResponseEntity<AnalysisRequestResponseDto> createHashtagAnalysisRequest(
            @RequestBody HashtagAnalysisRequestDto request) {
        UUID requestId = UUIDGenerator.generateUUID();

        request.setRequestId(requestId.toString());

        AnalysisRequestEntity analysisRequestEntity = new AnalysisRequestEntity(
                requestId.toString(),
                RequestStatus.REQUESTED,
                Instant.now(),
                ImmutableMap.of("Hashtag", request.getHashtag()));

        analysisRequestRepository.save(analysisRequestEntity);

        rabbitMQService.sendHashtagAnalysisRequest(request);

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
