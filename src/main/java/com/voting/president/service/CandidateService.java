package com.voting.president.service;

import com.voting.president.entities.Candidate;

import java.util.List;

/**
 * CandidateService interface
 */
public interface CandidateService {

    /**
     * Find all candidates participating in an election
     */
    List<Candidate> findAll();

    /**
     * Find candidate who has been voted by more than 50%, or return two most voted candidates
     */
    List<Candidate> getWinningCandidates();
}
