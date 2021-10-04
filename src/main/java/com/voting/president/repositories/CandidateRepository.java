package com.voting.president.repositories;


import com.voting.president.entities.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * CandidateRepository interface
 */
public interface CandidateRepository extends JpaRepository<Candidate, Integer> {
}
