package io.github.lbevan.sentiment.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.web.client.RestTemplate;

/**
 * Spring configuration class for the Twitter service.
 */
@Configuration
@PropertySource("classpath:twitter.properties")
public class TwitterConfiguration {

    @Value("${token.uri}")
    private String tokenUri;

    @Value("${client.id}")
    private String clientId;

    @Value("${client.secret}")
    private String clientSecret;

    @Bean
    protected ClientCredentialsResourceDetails oAuthDetails() {
        ClientCredentialsResourceDetails details = new ClientCredentialsResourceDetails();
        details.setId("twitter");
        details.setClientId(clientId);
        details.setClientSecret(clientSecret);
        details.setAccessTokenUri(tokenUri);
        details.setTokenName("token");
        return details;
    }

    @Bean
    @Qualifier("twitterRestTemplate")
    protected RestTemplate restTemplate() {
        return new OAuth2RestTemplate(oAuthDetails());
    }
}
