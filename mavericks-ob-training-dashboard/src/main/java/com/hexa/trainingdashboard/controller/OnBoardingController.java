package com.hexa.trainingdashboard.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexa.trainingdashboard.model.FresherProfile;
import com.hexa.trainingdashboard.repository.FresherProfileRepository;
import com.hexa.trainingdashboard.service.OnboardingService;

@RestController
@RequestMapping("/api/onboarding")
public class OnBoardingController {
	
	private final OnboardingService onboardingService;
	private final FresherProfileRepository fresherProfileRepository;

	public OnBoardingController(OnboardingService onboardingService, FresherProfileRepository fresherProfileRepository) {
		super();
		this.onboardingService = onboardingService;
		this.fresherProfileRepository = fresherProfileRepository;
	}
	
	@PostMapping("/profiles")
	public FresherProfile createProfile(@RequestBody FresherProfile fresherProfile) {
		return onboardingService.saveProfile(fresherProfile);
	}
	
	@GetMapping("/generate-plan/{id}")
	public String generateOnboardingPlan(@PathVariable Long id) {
		Optional<FresherProfile> optionalProfile = fresherProfileRepository.findById(id);
		if(optionalProfile.isEmpty()) {
			throw new RuntimeException("FresherProfile with ID "+ id +" not found.");
		}
		FresherProfile profile = optionalProfile.get();
		return onboardingService.generateOnBoardingPlan(profile);
	}
	
}
