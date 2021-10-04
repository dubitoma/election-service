package com.voting.president.service;

import com.voting.president.entities.Candidate;

import java.util.Map;

/**
 * VoteService interface
 */
public interface VoteService {

    /**
     * Create a new Vote object
     *
     * @param voterSSN voter's social security number
     * @param candidateNumber candidate's number on the list
     */
    void createVote(int voterSSN, int candidateNumber);

    /**
     * Get Region and associated candidates with their votes
     *
     * @return Map of Map
     */
    Map<String, Map<Candidate, Long>> getVotesPerRegion();

    /**
     * Calculate votes for a given candidate
     *
     * @param candidate
     *
     * @return total candidate votes
     */
    Long getVotesCountPerCandidate(Candidate candidate);
}
