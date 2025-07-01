package com.hexa.trainingdashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexa.trainingdashboard.model.TrainingProgress;

public interface TrainingProgressRepository extends JpaRepository<TrainingProgress, Long> {
}
