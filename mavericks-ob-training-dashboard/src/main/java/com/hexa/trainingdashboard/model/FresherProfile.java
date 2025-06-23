package com.hexa.trainingdashboard.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

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
	public FresherProfile() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
