package com.hexa.trainingdashboard.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexa.trainingdashboard.model.FresherProfile;

public interface FresherProfileRepository extends JpaRepository<FresherProfile, Long> {
	Optional<FresherProfile> findByEmail(String email);
}
