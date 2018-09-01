package io.github.lbevan.sentiment.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import io.github.lbevan.sentiment.service.domain.SimpleAnalysisApiRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = { "management.port=0" })
public class TestTweetAnalysisRequestController {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    private String url;
    private SimpleAnalysisApiRequest request;

    @BeforeEach
    public void setUp() {
        url = "http://localhost:" + this.port + "/api/requests/tweet";
        request = new SimpleAnalysisApiRequest("I love Monday Mornings!");
    }

    @Test
    public void whenSendingJsonRequestToRequestEndpoint_thenReturnHttp200Response() {
        ResponseEntity<String> response = this.testRestTemplate.postForEntity(url, request, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void whenSendingNonJsonRequestToRequestEndpoint_thenReturnHttp415Response() throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);

        HttpEntity<String> entity = new HttpEntity<String>(new XmlMapper().writeValueAsString(request), headers);
        ResponseEntity<String> response = this.testRestTemplate.postForEntity(url, entity, String.class);

        assertEquals(HttpStatus.UNSUPPORTED_MEDIA_TYPE, response.getStatusCode());
    }
}
