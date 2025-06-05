package com.hexa.trainingdashboard.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexa.trainingdashboard.model.Assessment;
import com.hexa.trainingdashboard.service.AssessmentService;

@RestController
@RequestMapping("/api/assessments")
public class AssessmentController {
	
	private final AssessmentService assessmentService;

	public AssessmentController(AssessmentService assessmentService) {
		this.assessmentService = assessmentService;
	}
	
	@PostMapping
	public Assessment createAssessment(@RequestBody Assessment assessment) {
		return assessmentService.evaluateAssessment(assessment);
	}

}
