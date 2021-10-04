package com.voting.president.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.voting.president.entities.Candidate;
import com.voting.president.service.CandidateService;
import com.voting.president.service.VoteService;
import com.voting.president.dto.CandidateDto;
import com.voting.president.dto.RegionDto;
import com.voting.president.dto.VoteDto;
import com.voting.president.dto.VoteRequestDto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
* Elections controller
*/
@RestController
public class VotingController {

    private static final Logger logger = LoggerFactory.getLogger(VotingController.class);

    private final CandidateService candidateService;
    private final VoteService voteService;

    @Autowired
    public VotingController(CandidateService candidateService, VoteService voteService) {
        this.candidateService = candidateService;
        this.voteService = voteService;
    }

    /**
     * List all candidates
     * @return all available candidates as CandidateDto's
     */
    @RequestMapping(value = "/candidates", method = RequestMethod.GET)
    public List<CandidateDto> getAllCandidates() {
        logger.info("Collecting candidates");
        return candidateService.findAll()
                .stream()
                .map(CandidateDto::new)
                .collect(toList());
    }

    /**
     * Lookup of overall distribution of votes amongst candidates
     * @return votes amongst candidates as VoteDto's
     */
    @RequestMapping(value = "/votes", method = RequestMethod.GET)
    public List<VoteDto> getVotesPerCandidate() {
        logger.info("Calculating votes");
        return candidateService.findAll()
                .stream()
                .map(candidate -> new VoteDto(new CandidateDto(candidate), voteService.getVotesCountPerCandidate(candidate)))
                .collect(Collectors.toList());
    }

    /**
     * Register a new vote
     * @param voteRequest insert voters choice
     */
    @PostMapping(value = "/vote")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerNewVote(@RequestBody @Validated VoteRequestDto voteRequest) {
        logger.info("Registering new vote");
        voteService.createVote(voteRequest.getVoterSsn(), voteRequest.getCandidateNumber());
    }

    /**
     * Lookup of voting result distribution amongst different regions
     * @return result distribution amongst different regions as RegionDto's
     */
    @RequestMapping(value = "/regions", method = RequestMethod.GET)
    public List<RegionDto> getVotesPerRegion() {
        logger.info("Retrieving voting result distribution amongst different regions");
         return getVotesPerRegionResponse(voteService.getVotesPerRegion());
    }

    /**
     * Return a winner cadidate
     * @return single winner as CandidateDto in a list if candidate was votes for by more than 50%,
     * else two most voted candidates as CandidateDto's
     */
    @RequestMapping(value = "/winner", method = RequestMethod.GET)
    public List<CandidateDto> getWinningCandidates() {
        logger.info("retrieving a winner of the elections");
        return candidateService.getWinningCandidates()
                .stream()
                .map(CandidateDto::new)
                .collect(toList());
    }

    /**
     * Create votes per region response
     * @param votesPerRegion regions with candidates and their votes
     * @return response of voting result distribution amongst different regions as RegionDto's
     */
    private List<RegionDto> getVotesPerRegionResponse(Map<String, Map<Candidate, Long>> votesPerRegion) {
        return votesPerRegion.entrySet()
                .stream()
                .map(regionEntry ->
                        new RegionDto(regionEntry.getKey(),    //get region name
                        regionEntry.getValue()
                                .entrySet()
                                .stream()   //build a list of VoteDto
                                .map(voteEntry ->
                                        new VoteDto(
                                        new CandidateDto(voteEntry.getKey()), voteEntry.getValue()))
                                .collect(toList())))
                .collect(toList());
    }
}
