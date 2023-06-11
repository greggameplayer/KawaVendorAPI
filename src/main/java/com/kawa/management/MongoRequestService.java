package com.kawa.management;

import com.kawa.service.dto.request.mongo.*;
import com.kawa.service.dto.response.mongo.*;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MongoRequestService {

    private final RestTemplate restTemplate;

    private static final String HEADER_API_KEY = "api-key";

    @Value("${mongo.api-key}")
    private String apiKey;

    @Value("${mongo.url}")
    private String mongoUrl;

    private final HttpHeaders headers;

    public MongoRequestService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;

        headers = new HttpHeaders();
        headers.add("Access-Control-Request-Headers", "*");
        headers.add(HEADER_API_KEY, apiKey);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    }

    public <T extends FindOneMongoResponseDTO> T findOne(FindOneMongoRequestDTO requestDTO, Class<T> className) {
        HttpEntity<FindOneMongoRequestDTO> entity = new HttpEntity<>(requestDTO, headers);
        headers.set(HEADER_API_KEY, apiKey);

        return restTemplate.exchange(mongoUrl + "/action/findOne", HttpMethod.POST, entity, className).getBody();
    }

    public <T extends FindAllMongoResponseDTO<?>> T findAll(FindAllMongoRequestDTO requestDTO, Class<T> className) {
        HttpEntity<FindAllMongoRequestDTO> entity = new HttpEntity<>(requestDTO, headers);
        headers.set(HEADER_API_KEY, apiKey);

        return restTemplate.exchange(mongoUrl + "/action/find", HttpMethod.POST, entity, className).getBody();
    }

    public <T extends InsertMongoResponseDTO> T insert(InsertMongoRequestDTO requestDTO, Class<T> className) {
        HttpEntity<InsertMongoRequestDTO> entity = new HttpEntity<>(requestDTO, headers);
        headers.set(HEADER_API_KEY, apiKey);

        return restTemplate.exchange(mongoUrl + "/action/insertOne", HttpMethod.POST, entity, className).getBody();
    }
}
