package com.hexa.trainingdashboard.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hexa.trainingdashboard.dto.LoginRequest;
import com.hexa.trainingdashboard.model.FresherProfile;
import com.hexa.trainingdashboard.repository.FresherProfileRepository;
import com.hexa.trainingdashboard.service.OnboardingService;

@RestController
@RequestMapping("/api/onboarding")
@CrossOrigin(origins = "*",methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
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
	public String generateOnboardingPlan(@PathVariable("id") Long id) throws JsonProcessingException {
		Optional<FresherProfile> optionalProfile = fresherProfileRepository.findById(id);
		if(optionalProfile.isEmpty()) {
			throw new RuntimeException("FresherProfile with ID "+ id +" not found.");
		}
		FresherProfile profile = optionalProfile.get();
		String plan = onboardingService.generateOnBoardingPlan(profile);
		onboardingService.setTrainingSchedule(profile, plan);
		return "Successfully generated onboarding plan for required fresher id";
	}
	
	@GetMapping("/generate-cert-plan/{id}")
	public String generateCertificationPlan(@PathVariable("id") Long id) throws JsonProcessingException {
		Optional<FresherProfile> optionalProfile = fresherProfileRepository.findById(id);
		if(optionalProfile.isEmpty()) {
			throw new RuntimeException("FresherProfile with ID "+ id +" not found.");
		}
		FresherProfile profile = optionalProfile.get();
		String plan = onboardingService.generateCertificationPlan(profile);
		return plan;
	}
	
	@GetMapping("/getProfile/{id}")
	public String getFresherProfileById(@PathVariable("id") Long id) throws JsonProcessingException {
		Optional<FresherProfile> optionalProfile = fresherProfileRepository.findById(id);
		if(optionalProfile.isPresent()) {
			ObjectMapper mapper = new ObjectMapper();
			String prettyJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(optionalProfile.get());
			return prettyJson;
		}
		return "No data found for given fresher id";
	}
	
	@PostMapping("/login")
	public FresherProfile login(@RequestBody LoginRequest request) {
	    return onboardingService.login(request.getEmail(), request.getPassword());
	}
	
}
