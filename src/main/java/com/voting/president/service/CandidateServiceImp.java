package com.voting.president.service;

import com.voting.president.entities.Candidate;
import com.voting.president.entities.Vote;
import com.voting.president.repositories.VoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.voting.president.repositories.CandidateRepository;

import javax.transaction.Transactional;
import java.util.*;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

/**
 * CandidateService implementation
 */
@Service("candidateService")
@Transactional
public class CandidateServiceImp implements CandidateService {

    private static final Logger logger = LoggerFactory.getLogger(VoteServiceImp.class);

    private final CandidateRepository candidateRepository;
    private final VoteRepository voteRepository;

    @Autowired
    public CandidateServiceImp(CandidateRepository candidateRepository, VoteRepository voteRepository) {
        this.candidateRepository = candidateRepository;
        this.voteRepository = voteRepository;
    }

    /**
     * Retrieve all candidates from the database
     *
     * @return the candidates list
     */
    @Override
    public List<Candidate> findAll() {
        return candidateRepository.findAll();
    }

    @Override
    public List<Candidate> getWinningCandidates() {
        Map<Candidate, Long> sortedCandidates = new LinkedHashMap<>();
        voteRepository.findAll()
                .stream()
                .collect(groupingBy(Vote::getCandidate, counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(entry -> sortedCandidates.put(entry.getKey(), entry.getValue()));
        List<Candidate> candidates = findWinningCandidates(sortedCandidates);
        validateCandidates(candidates);
        return candidates;
    }

    /**
     * Sort out winner candidate by checking if any of them has votes share more than 50%
     * else 2 most voted candidates will be added
     * if there is more than two candidates with the same amount of votes these will be reflected in the list
     *
     * @param candidates candidates and their votes
     *
     * @return winner candidates as Candidate's
     */
    private List<Candidate> findWinningCandidates(Map<Candidate, Long> candidates) {
        if (candidates.isEmpty()) {
            return Collections.emptyList();
        }
        double secondHighestVotes = 0L;
        double totalVotes = getTotalVotes(candidates);
        double highestNumberOfVotes = getHighestNumberOfVotes(candidates);
        List<Candidate> potentialWinners = new ArrayList<>();

        for (Map.Entry<Candidate, Long> entry : candidates.entrySet()) {
            double candidateVotes = entry.getValue();
            double voteShare = (candidateVotes / totalVotes) * 100;

            if (voteShare > 50.0f) {
                potentialWinners.add(entry.getKey());
                return potentialWinners;
            } else if (candidateVotes != highestNumberOfVotes && candidateVotes > secondHighestVotes) {
                secondHighestVotes = candidateVotes;
            } else if (potentialWinners.size() < 2) {
                if (candidateVotes == secondHighestVotes || candidateVotes < secondHighestVotes) {
                    secondHighestVotes = candidateVotes;
                }
            } else if (potentialWinners.size() == 2) {
                if (candidateVotes == highestNumberOfVotes || candidateVotes == secondHighestVotes) {
                    potentialWinners.add(entry.getKey());
                    return potentialWinners;
                }
            }
            potentialWinners.add(entry.getKey());
        }
        return potentialWinners;
    }

    /**
     * Calculate total number of votes
     *
     * @return the total
     */
    private double getTotalVotes(Map<Candidate, Long> candidates) {
        return candidates
                .values()
                .stream()
                .mapToDouble(votes -> votes)
                .sum();
    }

    /**
     * Calculate highest number of collected
     *
     * @return highest number
     */
    private double getHighestNumberOfVotes(Map<Candidate, Long> candidates) {
        return candidates
                .values()
                .stream()
                .max(Comparator.naturalOrder())
                .get();
    }

    /**
     * Check how many candidates there are as potential winners
     */
    private void validateCandidates(List<Candidate> candidates) throws IllegalStateException {
        if (candidates.size() > 2) {
            logger.error("More than two participants has been found with the same amount of votes");
            throw new IllegalStateException("To many participants (" +candidates.size()+ ") have the same amount of votes. No clear winner.");
        }
    }
}
