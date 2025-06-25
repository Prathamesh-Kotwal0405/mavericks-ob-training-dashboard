package com.hexa.trainingdashboard.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hexa.trainingdashboard.model.Assessment;

public interface AssessmentRepository extends JpaRepository<Assessment, Long> {
	@Query(value = "SELECT * FROM assessment WHERE fresher_id = :fresherId", nativeQuery = true)
	public Optional<Assessment> findByFresherId(@Param("fresherId") long fresherId);

}
