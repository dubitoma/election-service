package com.voting.president.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.voting.president.entities.Candidate;
import com.voting.president.entities.Vote;
import com.voting.president.entities.Voter;

/**
 * VoteRepository interface
 */
public interface VoteRepository extends JpaRepository<Vote, Integer> {
    /**
     * Find vote associated with Voter
     * @param voter person who has voted for a candidate
     * @return found vote
     */
    Vote findByVoter(Voter voter);

    /**
     * Count number of votes for a given candidate
     * @param candidate a participator of an election
     * @return total votes found by candidate
     */
    Long countVotesByCandidate(Candidate candidate);

}
