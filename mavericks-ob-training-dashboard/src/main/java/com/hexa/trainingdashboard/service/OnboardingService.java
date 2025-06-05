package com.hexa.trainingdashboard.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.models.ChatCompletionsOptions;
import com.azure.ai.openai.models.ChatMessage;
import com.azure.ai.openai.models.ChatRole;
import com.hexa.trainingdashboard.model.FresherProfile;
import com.hexa.trainingdashboard.repository.FresherProfileRepository;

@Service
public class OnboardingService {
	
	private final FresherProfileRepository fresherProfileRepository;
	private final OpenAIClient aiClient;
	
	public OnboardingService(FresherProfileRepository fresherProfileRepository, OpenAIClient aiClient) {
		super();
		this.fresherProfileRepository = fresherProfileRepository;
		this.aiClient = aiClient;
	}
	
	public String generateOnBoardingPlan(FresherProfile fresherProfile) {
		ChatMessage systemMessage  = new ChatMessage(ChatRole.SYSTEM).setContent("You are an AI assistant that generates personal onboarding plans in IT company");
		ChatMessage userMessage = new ChatMessage(ChatRole.USER).setContent("Create a personalized onboarding plan for the following profile: "+fresherProfile);
		ChatCompletionsOptions options = new ChatCompletionsOptions(List.of(systemMessage,userMessage)).setMaxTokens(500);
		return aiClient.getChatCompletions("gpt-4", options).getChoices().get(0).getMessage().getContent();
	}
	
	public FresherProfile saveProfile(FresherProfile fresherProfile) {
		return fresherProfileRepository.save(fresherProfile);
	}
	
	
}
