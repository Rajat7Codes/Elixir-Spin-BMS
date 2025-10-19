package co.in.nextgencoder.clashhub.service;

import co.in.nextgencoder.clashhub.service.dto.clash.ClashCardResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ClashRoyaleApiService {

    private final RestTemplate restTemplate;
    private final String baseUrl;
    private final String apiToken;
    private final Logger logger = LoggerFactory.getLogger(getClass().getName());

    public ClashRoyaleApiService(
            RestTemplate restTemplate,
            @Value("${clashroyale.api.base-url}") String baseUrl,
            @Value("${clashroyale.api.token}") String apiToken) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
        this.apiToken = apiToken;
    }

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer ".concat(apiToken));
        return headers;
    }

    public ClashCardResponse getCards() {
        logger.info( "In get cards =======");
        String url = baseUrl + "/cards";
        ResponseEntity<ClashCardResponse> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(createHeaders()),
                ClashCardResponse.class
        );
        logger.info( "After get cards =======");
        logger.info( response.toString());
        return response.getBody();
    }

    public String getPlayer(String playerTag) {
        String url = baseUrl + "/players/%23" + playerTag; // '#' must be encoded
        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(createHeaders()),
                String.class
        );
        return response.getBody();
    }

    public String getBattleLog(String playerTag) {
        String url = baseUrl + "/players/%23" + playerTag + "/battlelog";
        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(createHeaders()),
                String.class
        );
        return response.getBody();
    }
}

