package io.github.lbevan.sentiment.service.impl;

import io.github.lbevan.rabbitmq.service.impl.RabbitMQService;
import io.github.lbevan.sentiment.repository.impl.TweetAnalysisRequestRepository;
import io.github.lbevan.sentiment.service.Util.UUIDGenerator;
import io.github.lbevan.sentiment.service.domain.SimpleAnalysisApiRequest;
import io.github.lbevan.sentiment.service.domain.dto.TweetAnalysisRequest;
import io.github.lbevan.sentiment.service.domain.entity.TweetAnalysisRequestEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * REST API for Tweet Analysis Requests.
 */
@RestController()
@RequestMapping(value = "/api/requests",
        headers = { "accept=application/json" },
        produces = "application/json")
@CrossOrigin
public class TweetAnalysisRequestController {

    private final TweetAnalysisRequestRepository repository;
    private final RabbitMQService rabbitMQService;

    @Autowired
    public TweetAnalysisRequestController(TweetAnalysisRequestRepository repository, RabbitMQService rabbitMQService) {
        this.repository = repository;
        this.rabbitMQService = rabbitMQService;
    }

    /**
     * REST API Endpoint. Request analysis of a single tweet.
     *
     * @return ResponseEntity<UUID>
     */
    @PostMapping(value = "/tweet", headers = { "accept=application/json", "content-type=application/json" })
    public ResponseEntity<UUID> createRequest(@RequestBody SimpleAnalysisApiRequest request) {
        // todo: cleanup dto usage here!
        UUID uuid = UUIDGenerator.generateUUID();

        // todo: save the request
        try {
            repository.insert(new TweetAnalysisRequestEntity(uuid.toString(), request.getData()));
        } catch (Exception e) {
            System.out.println(e);
        }

        rabbitMQService.sendTweetAnalysisRequest(new TweetAnalysisRequest(request.getData(), uuid.toString()));

        return new ResponseEntity<>(uuid, HttpStatus.OK);
    }
}
