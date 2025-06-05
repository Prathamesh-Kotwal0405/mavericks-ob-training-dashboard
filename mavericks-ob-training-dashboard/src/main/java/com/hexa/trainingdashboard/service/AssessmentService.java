package com.hexa.trainingdashboard.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.models.ChatCompletionsOptions;
import com.azure.ai.openai.models.ChatMessage;
import com.azure.ai.openai.models.ChatRole;
import com.hexa.trainingdashboard.model.Assessment;
import com.hexa.trainingdashboard.repository.AssessmentRepository;

@Service
public class AssessmentService {
	
	private final AssessmentRepository assessmentRepository;
	private final OpenAIClient aiClient;
	public AssessmentService(AssessmentRepository assessmentRepository, OpenAIClient aiClient) {
		super();
		this.assessmentRepository = assessmentRepository;
		this.aiClient = aiClient;
	}
	

	public Assessment evaluateAssessment(Assessment assessment) {
		ChatMessage message = new ChatMessage(ChatRole.USER).setContent("Evaluate this assessment: "+ assessment);
		ChatCompletionsOptions options = new ChatCompletionsOptions(List.of(message));
		String feedback = aiClient.getChatCompletions("gpt-4", options).getChoices().get(0).getMessage().getContent();
		assessment.setFeedback(feedback);
		return assessmentRepository.save(assessment);
	}

}
