package com.hexa.trainingdashboard.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hexa.trainingdashboard.dto.AssessmentDto;
import com.hexa.trainingdashboard.model.Assessment;
import com.hexa.trainingdashboard.repository.AssessmentRepository;
import com.hexa.trainingdashboard.service.AssessmentService;

@RestController
@RequestMapping("/api/assessments")
public class AssessmentController {
	
	private final AssessmentService assessmentService;
	private final AssessmentRepository assessmentRepository;

	public AssessmentController(AssessmentService assessmentService, AssessmentRepository assessmentRepository) {
		this.assessmentService = assessmentService;
		this.assessmentRepository = assessmentRepository;
	}
	
	@GetMapping("{id}")
	public String evaluateAssessment(@PathVariable("id") Long id) throws JsonProcessingException {
		Optional<Assessment> optionalAssessment = assessmentRepository.findById(id);
		if(optionalAssessment.isEmpty()) {
			throw new RuntimeException("FresherProfile with ID "+ id +" not found.");
		}
		return assessmentService.evaluateAssessment(optionalAssessment.get());
	}
	
	 @PostMapping
	    public ResponseEntity<String> saveAssessment(@RequestBody AssessmentDto request) {
	        try {
	        	assessmentService.createAssessmentData(request);
	            return ResponseEntity.ok("Assessment saved successfully");
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                                 .body("Error saving assessment: " + e.getMessage());
	        }
	    }
	 
	 @GetMapping("/getAssessmentByProfileId/{id}")
		public String getAssessmentByFresherProfileById(@PathVariable("id") Long id) throws JsonProcessingException {
			Optional<Assessment> optionalAssessment = assessmentRepository.findByFresherId(id);
			if(optionalAssessment.isPresent()) {
				ObjectMapper mapper = new ObjectMapper();
				String prettyJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(optionalAssessment.get());
				return prettyJson;
			}
			return "No data found for given fresher id";
		}
	
	

}
