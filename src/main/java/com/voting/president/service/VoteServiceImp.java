package com.voting.president.service;

import com.voting.president.entities.Candidate;
import com.voting.president.entities.Issue;
import com.voting.president.entities.Vote;
import com.voting.president.entities.Voter;
import com.voting.president.repositories.VoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.voting.president.repositories.CandidateRepository;
import com.voting.president.repositories.VoterRepository;
import javax.transaction.Transactional;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Stream;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

/**
 * VoteServiceImpl implementation
 */
@Service("voteService")
@Transactional
public class VoteServiceImp implements VoteService {

    private static final Logger logger = LoggerFactory.getLogger(VoteServiceImp.class);

    private final VoteRepository voteRepository;
    private final CandidateRepository candidateRepository;
    private final VoterRepository voterRepository;

    @Autowired
    public VoteServiceImp(VoteRepository voteRepository, CandidateRepository candidateRepository, VoterRepository voterRepository) {
        this.voteRepository = voteRepository;
        this.candidateRepository = candidateRepository;
        this.voterRepository = voterRepository;
    }

    @Override
    public void createVote(int voterSSN, int candidateNumber) {
        Voter voter = verifyVoter(voterSSN);
        Candidate candidate = verifyCandidate(candidateNumber);
        verifyVote(voter);
        voteRepository.save(new Vote(voter, candidate, Issue.PRESIDENT));
    }

    @Override
    public Long getVotesCountPerCandidate(Candidate candidate) {
        return voteRepository.countVotesByCandidate(candidate);
    }

    @Override
    public Map<String, Map<Candidate, Long>> getVotesPerRegion() {
        return getVotes().collect(groupingBy(
                vote -> vote.getVoter().getRegion().getLabel(), groupingBy(Vote::getCandidate, counting())
        ));
    }

    /**
     * Check if voter entry is present in Database
     *
     * @param  voterSSN social security number
     *
     * @return voter form database
     *
     */
    private Voter verifyVoter(int voterSSN) throws NoSuchElementException{
        return voterRepository.findById(voterSSN).orElseThrow( () -> {
            logger.error("Voter is not registered ", voterSSN);
            return new NoSuchElementException("Voter does not exist " + voterSSN);
        });
    }

    /**
     * Check if candidate entry is present in Database
     *
     * @param  candidateNumber number on the list
     *
     * @return candidate form database
     *
     */
    private Candidate verifyCandidate(int candidateNumber) throws NoSuchElementException{
        return candidateRepository.findById(candidateNumber).orElseThrow( () -> {
            logger.error("Voting for non existing candidate ", candidateNumber);
            return new NoSuchElementException("Candidate does not exist " + candidateNumber);
        });
    }

    /**
     * Check if vote entry is unique and does not exist in the Database
     *
     * @param  voter voter
     *
     */
    private void verifyVote(Voter voter) {
        if(voteRepository.findByVoter(voter) != null) {
            logger.error("A vote has already been cast by the voter ", voter.getSsn());
            throw new IllegalArgumentException("A vote has already been cast by the voter " + voter.getSsn());
        }
    }

    /**
     * Get a list of votes from the Database
     *
     * @return stream of votes
     *
     */
    private Stream<Vote> getVotes() {
        return voteRepository.findAll().stream();
    }
}
