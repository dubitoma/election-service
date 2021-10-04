package com.voting.president.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "votes")
public class Vote implements Serializable {

    @Id
    @Column(unique = true)
    @GeneratedValue
    private int ballotId;
    @OneToOne
    private Voter voter;
    @ManyToOne
    private Candidate candidate;
    @Enumerated(EnumType.STRING)
    private Issue issue;
    private Date createdOn;

    protected Vote() {
    }

    public Vote(Voter voter, Candidate candidate, Issue issue) {
        this.voter = voter;
        this.candidate = candidate;
        this.issue = issue;
        this.createdOn = new Date();
    }

    public int getBallotId() {
        return ballotId;
    }

    public void setBallotId(int ballotId) {
        this.ballotId = ballotId;
    }

    public Voter getVoter() {
        return voter;
    }

    public void setVoter(Voter voter) {
        this.voter = voter;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "ballotId=" + ballotId +
                ", voter=" + voter +
                ", candidate=" + candidate +
                ", issue=" + issue +
                ", createdOn=" + createdOn +
                '}';
    }
}
