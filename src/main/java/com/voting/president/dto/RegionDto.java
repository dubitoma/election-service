package com.voting.president.dto;

import java.util.List;

/**
 * Data Transfer Object for votes by region.
 *
 */
public class RegionDto {

    private String region;
    private List<VoteDto> votes;

    /**
     * Constructor to fully initialise the CandidateDto
     *
     * @param region area of whre vote happened
     * @param votes data transfer object for collected votes of a region
     */
    public RegionDto(String region, List<VoteDto> votes) {
        this.region = region;
        this.votes = votes;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public List<VoteDto> getVotes() {
        return votes;
    }

    public void setVotes(List<VoteDto> votes) {
        this.votes = votes;
    }
}
