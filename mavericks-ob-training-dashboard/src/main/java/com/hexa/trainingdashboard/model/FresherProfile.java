package com.hexa.trainingdashboard.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;

@Entity
public class FresherProfile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String email;
	private String role;
	@Lob
	private String trainingSchedule;
	
	@OneToOne(mappedBy = "fresher", cascade = CascadeType.ALL)
    private TrainingProgress trainingProgress;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getTrainingSchedule() {
		return trainingSchedule;
	}
	public void setTrainingSchedule(String trainingSchedule) {
		this.trainingSchedule = trainingSchedule;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public FresherProfile() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TrainingProgress getTrainingProgress() {
		return trainingProgress;
	}
	public void setTrainingProgress(TrainingProgress trainingProgress) {
		this.trainingProgress = trainingProgress;
	}
	
	
	
	
	
}
