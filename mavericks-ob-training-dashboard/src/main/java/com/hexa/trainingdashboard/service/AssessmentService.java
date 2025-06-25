package com.hexa.trainingdashboard.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hexa.trainingdashboard.dto.AssessmentDto;
import com.hexa.trainingdashboard.model.Assessment;
import com.hexa.trainingdashboard.repository.AssessmentRepository;

@Service
public class AssessmentService {
	
	private final AssessmentRepository assessmentRepository;
	private final AzureOpenAIService azureOpenAIService;
	public AssessmentService(AssessmentRepository assessmentRepository, AzureOpenAIService azureOpenAIService) {
		super();
		this.assessmentRepository = assessmentRepository;
		this.azureOpenAIService = azureOpenAIService;
	}
	

	public String evaluateAssessment(Assessment assessment) throws JsonProcessingException {
		AssessmentDto dto = new AssessmentDto();
		dto.setFresherId(assessment.getFresherId());
		dto.setType(assessment.getType());
		dto.setAttempts(assessment.getAttempts());
		dto.setSubjectMarks(assessment.getSubjectMarks());
		ObjectMapper mapper = new ObjectMapper();
		String assessmentJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(dto);
		String prompt = """
				You are a Subject Matter Expert at a software company. Based on the fresher's assessment score below, generate a **customized Improvement plan** tailored to their specific score for specific subject.

				Assessment:
				%s

				Instructions:
				- Understand the candidate's **marks for specific skills**
				- Customize the feedback to match the expectations and learning curve of that role
				- Break the feedback into subject wise topics and areas of imporvement.
				- Each feedback should include:
				  - Topics to study or master
				  - Tools or systems to get familiar with
				  - Tasks or shadowing opportunities
				  - Assignments or assessments
				- The feedback should be practical for a **new college graduate** and provided marks are out of 100 

				Output Format:
				- Title: Feedback for <role>
				- Topic-by-topic breakdown as bullet points
				- Complete all these in 10 lines.
				"""
				.formatted(assessmentJson);
		String assessmentResponse = azureOpenAIService.getResponseFromModel(prompt);
		assessment.setFeedback(assessmentResponse);
		assessmentRepository.save(assessment);
		return assessmentResponse;
		
	}
	
	public String createAssessmentData(AssessmentDto assessmentDto) {
		Assessment assessment = new Assessment();
		assessment.setFresherId(assessmentDto.getFresherId());
		assessment.setType(assessmentDto.getType());
		assessment.setAttempts(assessment.getAttempts()+1);
		assessment.setSubjectMarks(assessmentDto.getSubjectMarks());
		assessment.setLastAttempted(LocalDateTime.now());
		assessmentRepository.save(assessment);
		return "Assessment data created for id : " + assessment.getFresherId();
	}

}
