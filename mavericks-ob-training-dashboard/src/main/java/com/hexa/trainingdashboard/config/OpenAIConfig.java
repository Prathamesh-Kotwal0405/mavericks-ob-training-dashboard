package com.hexa.trainingdashboard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.core.credential.AzureKeyCredential;

@Configuration
public class OpenAIConfig {

    @Bean
    public OpenAIClient openAIClient() {
        return new OpenAIClientBuilder()
            .credential(new AzureKeyCredential("YOUR_API_KEY"))
            .endpoint("https://YOUR_OPENAI_ENDPOINT.azure.com/")
            .buildClient();
    }
}
