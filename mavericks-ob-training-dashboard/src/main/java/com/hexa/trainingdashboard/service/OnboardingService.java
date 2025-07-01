package com.hexa.trainingdashboard.service;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hexa.trainingdashboard.model.FresherProfile;
import com.hexa.trainingdashboard.model.TrainingProgress;
import com.hexa.trainingdashboard.repository.FresherProfileRepository;
import com.hexa.trainingdashboard.repository.TrainingProgressRepository;

@Service
public class OnboardingService {
	
	private final FresherProfileRepository fresherProfileRepository;
	private final TrainingProgressRepository trainingProgressRepository;
//	private final OllamaService ollamaService;
	private final AzureOpenAIService aiService;
	
	public OnboardingService(FresherProfileRepository fresherProfileRepository, AzureOpenAIService aiService, TrainingProgressRepository trainingProgressRepository ) {
		super();
		this.fresherProfileRepository = fresherProfileRepository;
		this.aiService = aiService;
		this.trainingProgressRepository = trainingProgressRepository;
	}
	
	public String generateOnBoardingPlan(FresherProfile fresherProfile) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String profileJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(fresherProfile);
		String prompt = """
				You are an HR onboarding assistant at a software company. Based on the fresher's profile below, generate a **customized 30-day onboarding plan** tailored to their specific role.

				Fresher Profile:
				%s

				Instructions:
				- Understand the candidate's **role** and **skills**
				- Customize the plan to match the expectations and learning curve of that role
				- Break the plan into 4 weekly segments (Week 1, Week 2, etc.)
				- Each week should include:
				  - Topics to study or master
				  - Tools or systems to get familiar with
				  - Tasks or shadowing opportunities
				  - Assignments or assessments
				- The plan should be practical for a **new college graduate**

				Output Format:
				- Title: Onboarding Plan for <role>
				- Week-by-week breakdown as bullet points
				"""
				.formatted(profileJson);
		return aiService.getResponseFromModel(prompt);
	}
	
	public FresherProfile saveProfile(FresherProfile fresherProfile) {	
		// Create and link TrainingProgress
	    TrainingProgress progress = new TrainingProgress();
	    progress.setFresher(fresherProfile); // Link fresher to progress
	    fresherProfile.setTrainingProgress(progress); // Link progress to fresher

	    // Save fresher only. Hibernate will save progress automatically.
	    return fresherProfileRepository.save(fresherProfile);
	}
	
	public FresherProfile setTrainingSchedule(FresherProfile fresherProfile, String plan) {
		fresherProfile.setTrainingSchedule(plan);
		return fresherProfileRepository.save(fresherProfile);
	}
	
	
}
