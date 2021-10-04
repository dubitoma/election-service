package com.voting.president.repositories;

import com.voting.president.entities.Voter;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * VoterRepository interface
 */
public interface VoterRepository extends JpaRepository<Voter, Integer> {
}
