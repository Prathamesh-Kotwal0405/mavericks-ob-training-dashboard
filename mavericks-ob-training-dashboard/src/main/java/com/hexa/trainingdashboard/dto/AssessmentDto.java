package com.hexa.trainingdashboard.dto;

import java.util.Map;

public class AssessmentDto {
	
	private Long fresherId;
	private String type;
	private int attempts;
	private String feedback;
	private Map<String, Integer> subjectMarks;
	public Long getFresherId() {
		return fresherId;
	}
	public void setFresherId(Long fresherId) {
		this.fresherId = fresherId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getAttempts() {
		return attempts;
	}
	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	public Map<String, Integer> getSubjectMarks() {
		return subjectMarks;
	}
	public void setSubjectMarks(Map<String, Integer> subjectMarks) {
		this.subjectMarks = subjectMarks;
	}
	
	

}
