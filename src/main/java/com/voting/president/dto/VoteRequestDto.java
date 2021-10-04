package com.voting.president.dto;

/**
 * Data Transfer Object to register new vote.
 *
 */
public class VoteRequestDto {
    private int candidateNumber;
    private int voterSsn;

    protected VoteRequestDto() {
    }

    public int getCandidateNumber() {
        return candidateNumber;
    }

    public void setCandidateNumber(int candidateNumber) {
        this.candidateNumber = candidateNumber;
    }

    public int getVoterSsn() {
        return voterSsn;
    }

    public void setVoterSsn(int voterSsn) {
        this.voterSsn = voterSsn;
    }
}
