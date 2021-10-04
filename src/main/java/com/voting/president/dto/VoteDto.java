package com.voting.president.dto;

/**
 * Data Transfer Object for Votes.
 *
 */
public class VoteDto {

    private CandidateDto candidate;
    private long numberOfVotes;

    /**
     * Constructor to fully initialise the VoteDto
     *
     * @param candidate data transfer object for candidates
     * @param numberOfVotes number of total votes
     */
    public VoteDto(CandidateDto candidate, long numberOfVotes) {
        this.candidate = candidate;
        this.numberOfVotes = numberOfVotes;
    }

    public CandidateDto getCandidate() {
        return candidate;
    }

    public void setCandidate(CandidateDto candidate) {
        this.candidate = candidate;
    }

    public long getNumberOfVotes() {
        return numberOfVotes;
    }

    public void setNumberOfVotes(long numberOfVotes) {
        this.numberOfVotes = numberOfVotes;
    }
}
