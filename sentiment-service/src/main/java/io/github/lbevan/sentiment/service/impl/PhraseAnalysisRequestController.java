package io.github.lbevan.sentiment.service.impl;

import io.github.lbevan.sentiment.service.Util.UUIDGenerator;
import io.github.lbevan.sentiment.service.domain.SimpleAnalysisApiRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * REST API for Phrase Analysis Requests.
 */
@RestController()
@RequestMapping(value = "/api/requests",
        headers = { "accept=application/json" },
        produces = "application/json")
@CrossOrigin
public class PhraseAnalysisRequestController {

//    private final RabbitTemplate rabbitTemplate;
//
//    @Autowired
//    public PhraseAnalysisRequestController(RabbitTemplate rabbitTemplate) {
//        this.rabbitTemplate = rabbitTemplate;
//    }

    /**
     * REST API Endpoint. Request analysis of a phrase.
     *
     * @return ResponseEntity<UUID>
     */
    @PostMapping(value = "/phrase", headers = { "accept=application/json", "content-type=application/json" })
    public ResponseEntity<UUID> createRequest(@RequestBody SimpleAnalysisApiRequest request) {
        UUID uuid = UUIDGenerator.generateUUID();

        // todo: save the request

        // todo: send message to queue
        // rabbitTemplate.convertAndSend("twitter_tweet_request", sentimentRequest);

        return new ResponseEntity<>(uuid, HttpStatus.OK);
    }
}
