package io.github.lbevan.sentiment.service.impl;

import io.github.lbevan.rabbitmq.service.impl.RabbitMQService;
import io.github.lbevan.sentiment.repository.impl.AnalysisRequestRepository;
import io.github.lbevan.sentiment.service.domain.misc.DocumentType;
import io.github.lbevan.twitter.service.domain.Tweet;
import io.github.lbevan.twitter.service.impl.TwitterService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    @MockBean
    private TwitterService twitterService;

    @Autowired
    private MockMvc mockMvc;


    // region getAnalysisRequest() tests

    @Test
    public void whenRequestIdIsMissing_thenReturnHttp404() throws Exception {
        this.mockMvc.perform(
                get(BASE_API + "/"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void whenRequestNotFound_thenReturnHttp404() throws Exception {
        this.mockMvc.perform(
                get(BASE_API + "/94482c22-3240-471b-8b27-ffe8755ba4e3"))
                .andExpect(status().isNotFound());
    }

    // endregion


    // region createTextAnalysisRequest()

    @Test
    public void whenValidTextAnalysisRequestIsSent_thenReturnHttp200() throws Exception {
        this.mockMvc.perform(
                post(BASE_API + "/text")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"text\": \"Test text\" }"))
                .andExpect(status().isOk());
    }

    @Test
    public void whenTextIsNull_thenReturnHttp400() throws Exception {
        this.mockMvc.perform(
                post(BASE_API + "/text")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"text\": null }"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenTextIsEmpty_thenReturnHttp400() throws Exception {
        this.mockMvc.perform(
                post(BASE_API + "/text")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"text\": \"\" }"))
                .andExpect(status().isBadRequest());
    }

    // endregion


    // region createTweetAnalysisRequest()

    @Test
    public void whenValidTweetAnalysisRequestIsSent_thenReturnHttp200() throws Exception {
        when(twitterService.getTweetById(any()))
                .thenReturn(new Tweet(Long.valueOf("2131232131231231"), "Text"));

        this.mockMvc.perform(
                post(BASE_API + "/tweet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"tweetLink\": \"https://twiter.com/status/example/2131232131231231\" }"))
                .andExpect(status().isOk());
    }

    @Test
    public void whenTweetLinkIsNull_thenReturnHttp400() throws Exception {
        this.mockMvc.perform(
                post(BASE_API + "/tweet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"tweetLink\": null }"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenTweetLinkIsEmpty_thenReturnHttp400() throws Exception {
        this.mockMvc.perform(
                post(BASE_API + "/tweet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"tweetLink\": \"\" }"))
                .andExpect(status().isBadRequest());
    }

    // endregion


    // region createHashtagAnalysisRequest()

    @Test
    public void whenValidHashtagAnalysisRequestIsSent_thenReturnHttp200() throws Exception {
        this.mockMvc.perform(
                post(BASE_API + "/hashtag")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"hashtag\": \"#example\" }"))
                .andExpect(status().isOk());
    }

    @Test
    public void whenHashtagIsNull_thenReturnHttp400() throws Exception {
        this.mockMvc.perform(
                post(BASE_API + "/hashtag")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"hashtag\": null }"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenHashtagIsEmpty_thenReturnHttp400() throws Exception {
        this.mockMvc.perform(
                post(BASE_API + "/hashtag")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"hashtag\": \"\" }"))
                .andExpect(status().isBadRequest());
    }

    // endregion


    // region createDocumentAnalysisRequest()

    @Test
    public void whenTxtDocumentIsSent_thenReturnHttp200() throws Exception {
        MockMultipartFile mockMultipartFile = new MockMultipartFile(
                "document",
                "mydoc.txt",
                DocumentType.TXT.getContentType(),
                "txt document".getBytes());

        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .multipart(BASE_API + "/document")
                        .file(mockMultipartFile))
                .andExpect(status().isOk());
    }

    @Test
    public void whenInvalidContentTypeIsSent_thenReturnHttp400() throws Exception {
        MockMultipartFile mockMultipartFile = new MockMultipartFile(
                "document",
                "mydoc.txt",
                "text/html",
                "txt document".getBytes());

        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .multipart(BASE_API + "/document")
                        .file(mockMultipartFile))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenDocumentIsEmpty_thenReturnHttp400() throws Exception {
        MockMultipartFile mockMultipartFile = new MockMultipartFile(
                "document",
                "mydoc.txt",
                DocumentType.TXT.getContentType(),
                new byte[0]);

        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .multipart(BASE_API + "/document")
                        .file(mockMultipartFile))
                .andExpect(status().isBadRequest());
    }

    // endregion
}
