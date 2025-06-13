package com.hexa.trainingdashboard.service;

import org.springframework.stereotype.Service;

import com.hexa.trainingdashboard.model.FresherProfile;
import com.hexa.trainingdashboard.repository.FresherProfileRepository;

@Service
public class OnboardingService {
	
	private final FresherProfileRepository fresherProfileRepository;
	private final OllamaService ollamaService;
	
	public OnboardingService(FresherProfileRepository fresherProfileRepository, OllamaService ollamaService) {
		super();
		this.fresherProfileRepository = fresherProfileRepository;
		this.ollamaService = ollamaService;
	}
	
	public String generateOnBoardingPlan(FresherProfile fresherProfile) {
		return ollamaService.getResponseFromModel("You are an AI assistant that generates personal onboarding plans in IT company."+"Create a personalized onboarding plan for the following fresher profile: "+fresherProfile);
	}
	
	public FresherProfile saveProfile(FresherProfile fresherProfile) {
		return fresherProfileRepository.save(fresherProfile);
	}
	
	public FresherProfile setTrainingSchedule(FresherProfile fresherProfile, String plan) {
		fresherProfile.setTrainingSchedule(plan);
		return fresherProfileRepository.save(fresherProfile);
	}
	
	
}
