package com.hexa.trainingdashboard.model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
@Entity
@Table(name = "training_progress")
public class TrainingProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "fresher_id", nullable = false, unique = true)
    @JsonIgnore
    private FresherProfile fresher;

    @Enumerated(EnumType.STRING)
    @Column(name = "profile_updated", nullable = false)
    private ProgressStatus profileUpdated = ProgressStatus.NOT_COMPLETED;

    @Enumerated(EnumType.STRING)
    @Column(name = "quiz_completed", nullable = false)
    private ProgressStatus quizCompleted = ProgressStatus.NOT_COMPLETED;

    @Enumerated(EnumType.STRING)
    @Column(name = "coding_challenge", nullable = false)
    private ProgressStatus codingChallenge = ProgressStatus.NOT_COMPLETED;

    @Enumerated(EnumType.STRING)
    @Column(name = "assignment_submitted", nullable = false)
    private ProgressStatus assignmentSubmitted = ProgressStatus.NOT_COMPLETED;

    @Enumerated(EnumType.STRING)
    @Column(name = "certification_completed", nullable = false)
    private ProgressStatus certificationCompleted = ProgressStatus.NOT_COMPLETED;

	public FresherProfile getFresher() {
		return fresher;
	}

	public void setFresher(FresherProfile fresher) {
		this.fresher = fresher;
	}

	public ProgressStatus getProfileUpdated() {
		return profileUpdated;
	}

	public void setProfileUpdated(ProgressStatus profileUpdated) {
		this.profileUpdated = profileUpdated;
	}

	public ProgressStatus getQuizCompleted() {
		return quizCompleted;
	}

	public void setQuizCompleted(ProgressStatus quizCompleted) {
		this.quizCompleted = quizCompleted;
	}

	public ProgressStatus getCodingChallenge() {
		return codingChallenge;
	}

	public void setCodingChallenge(ProgressStatus codingChallenge) {
		this.codingChallenge = codingChallenge;
	}

	public ProgressStatus getAssignmentSubmitted() {
		return assignmentSubmitted;
	}

	public void setAssignmentSubmitted(ProgressStatus assignmentSubmitted) {
		this.assignmentSubmitted = assignmentSubmitted;
	}

	public ProgressStatus getCertificationCompleted() {
		return certificationCompleted;
	}

	public void setCertificationCompleted(ProgressStatus certificationCompleted) {
		this.certificationCompleted = certificationCompleted;
	}

    // Getters & setters
    
    
}

