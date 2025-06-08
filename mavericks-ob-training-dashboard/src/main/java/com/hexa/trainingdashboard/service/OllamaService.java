package com.hexa.trainingdashboard.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hexa.trainingdashboard.dto.OllamaRequest;

@Service
public class OllamaService {
	private final RestTemplate restTemplate = new RestTemplate();
    private final String OLLAMA_URL = "http://localhost:11434/api/generate";

    public String getResponseFromModel(String prompt) {
        OllamaRequest request = new OllamaRequest();
        request.setModel("mistral");
        request.setPrompt(prompt);
        request.setStream(false);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<OllamaRequest> entity = new HttpEntity<>(request, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                OLLAMA_URL, HttpMethod.POST, entity, String.class);

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());
            return root.path("response").asText(); // or handle full response structure
        } catch (Exception e) {
            return "Error parsing response: " + e.getMessage();
        }
    }
}
