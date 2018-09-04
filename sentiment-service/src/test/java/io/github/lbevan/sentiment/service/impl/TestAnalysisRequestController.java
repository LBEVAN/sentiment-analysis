package io.github.lbevan.sentiment.service.impl;

import io.github.lbevan.rabbitmq.service.impl.RabbitMQService;
import io.github.lbevan.sentiment.repository.impl.AnalysisRequestRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test class for {@link AnalysisRequestController}.
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(AnalysisRequestController.class)
public class TestAnalysisRequestController {

    private static final String BASE_API = "/api/request";

    @MockBean
    private AnalysisRequestRepository analysisRequestRepository;

    @MockBean
    private RabbitMQService rabbitMQService;

    @Autowired
    private MockMvc mockMvc;

    // region getAnalysisRequest() tests

    @Test
    public void whenRequestIdIsMissing_thenReturnHttp404() throws Exception {
        this.mockMvc.perform(get(BASE_API + "/")).andExpect(status().isNotFound());
    }

    @Test
    public void whenRequestNotFound_thenReturnHttp404() throws Exception {
        this.mockMvc.perform(get(BASE_API + "/94482c22-3240-471b-8b27-ffe8755ba4e3")).andExpect(status().isNotFound());
    }

    // endregion
}
