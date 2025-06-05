package com.hexa.trainingdashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexa.trainingdashboard.model.Assessment;

public interface AssessmentRepository extends JpaRepository<Assessment, Long> {

}
