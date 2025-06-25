package com.hexa.trainingdashboard.model;

import java.time.LocalDateTime;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Transient;

@Entity
public class Assessment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	private Long fresherId;
	private String type;
	private int attempts;
	@Lob
	private String feedback;
	
	@Lob
    @Column(columnDefinition = "TEXT")  // Or "json" for PostgreSQL
    private String subjectMarksJson; // Store Map as JSON
	
	private LocalDateTime lastAttempted;

    @Transient
    private Map<String, Integer> subjectMarks; // Not stored directly

    // ObjectMapper instance for serialization/deserialization
    private static final ObjectMapper mapper = new ObjectMapper();

    @PrePersist
    @PreUpdate
    private void serializeMap() throws Exception {
        if (subjectMarks != null) {
            subjectMarksJson = mapper.writeValueAsString(subjectMarks);
        }
    }

    @PostLoad
    private void deserializeMap() throws Exception {
        if (subjectMarksJson != null && !subjectMarksJson.isEmpty()) {
            subjectMarks = mapper.readValue(subjectMarksJson, new TypeReference<>() {});
        }
    }
	
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
	
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public int getAttempts() {
		return attempts;
	}

	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}

	public Map<String, Integer> getSubjectMarks() {
		return subjectMarks;
	}

	public void setSubjectMarks(Map<String, Integer> subjectMarks) {
		this.subjectMarks = subjectMarks;
	}

	public LocalDateTime getLastAttempted() {
		return lastAttempted;
	}

	public void setLastAttempted(LocalDateTime lastAttempted) {
		this.lastAttempted = lastAttempted;
	}
	
	
	
	
	
	
	

}
