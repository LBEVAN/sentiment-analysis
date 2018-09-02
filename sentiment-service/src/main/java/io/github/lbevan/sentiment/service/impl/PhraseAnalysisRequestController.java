package io.github.lbevan.sentiment.service.impl;

import io.github.lbevan.rabbitmq.service.impl.RabbitMQService;
import io.github.lbevan.sentiment.repository.impl.PhraseAnalysisRequestRepository;
import io.github.lbevan.sentiment.service.Util.UUIDGenerator;
import io.github.lbevan.sentiment.service.domain.SimpleAnalysisApiRequest;
import io.github.lbevan.sentiment.service.domain.dto.PhraseAnalysisRequest;
import io.github.lbevan.sentiment.service.domain.entity.PhraseAnalysisRequestEntity;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final PhraseAnalysisRequestRepository repository;
    private final RabbitMQService rabbitMQService;


    @Autowired
    public PhraseAnalysisRequestController(PhraseAnalysisRequestRepository repository,
                                           RabbitMQService rabbitMQService) {
        this.repository = repository;
        this.rabbitMQService = rabbitMQService;
    }

    /**
     * REST API Endpoint. Request analysis of a phrase.
     *
     * @return ResponseEntity<UUID>
     */
    @PostMapping(value = "/phrase", headers = { "accept=application/json", "content-type=application/json" })
    public ResponseEntity<UUID> createRequest(@RequestBody SimpleAnalysisApiRequest request) {
        // todo: cleanup dto usage here!
        UUID uuid = UUIDGenerator.generateUUID();

        try {
            repository.insert(new PhraseAnalysisRequestEntity(uuid.toString(), request.getData()));
        } catch (Exception e) {
            System.out.println(e);
        }

        rabbitMQService.sendPhraseAnalysisRequest(new PhraseAnalysisRequest(request.getData(), uuid.toString()));

        return new ResponseEntity<>(uuid, HttpStatus.OK);
    }
}
