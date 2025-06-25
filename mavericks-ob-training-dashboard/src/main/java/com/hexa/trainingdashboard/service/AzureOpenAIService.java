package com.hexa.trainingdashboard.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AzureOpenAIService {
	
	private final RestTemplate restTemplate = new RestTemplate();

    // Replace with your actual values
    private final String endpoint = "https://byte-bandits.openai.azure.com/";
    private final String deploymentName = "gpt-4"; // e.g., gpt-4
    private final String apiKey = "9DN59Ar9W5o5gMBf2NBjFMSpd3WqK4OS8UiIlBzhZTsay9dKQb47JQQJ99BFACYeBjFXJ3w3AAABACOGw7Jm";
    private final String apiVersion = "2025-01-01-preview";

    public String getResponseFromModel(String prompt) {
//    	https://byte-bandits.openai.azure.com/openai/deployments/gpt-4/chat/completions?api-version=2025-01-01-preview
        String url = endpoint+"openai/deployments/"+deploymentName+"/chat/completions?api-version="+apiVersion;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("api-key", apiKey);

        // Azure uses Chat Completions, so we structure messages
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("messages", List.of(
            Map.of("role", "user", "content", prompt)
        ));
        requestBody.put("temperature", 0.7);
        requestBody.put("max_tokens", 800);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    url, HttpMethod.POST, entity, String.class);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());
            return root.path("choices").get(0).path("message").path("content").asText();
        } catch (Exception e) {
            return "Error calling Azure OpenAI: " + e.getMessage();
        }
    }
}
