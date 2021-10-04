package com.voting.president.dto;

import com.voting.president.entities.Candidate;

/**
 * Data Transfer Object for Candidates.
 *
 */
public class CandidateDto {
    private String fullName;
    private int number;
    private String agenda;

    /**
     * Construct a CandidateDto from a fully instantiated Candidate.
     *
     * @param candidate Candidate Object
     */
    public CandidateDto(Candidate candidate) {
        this(candidate.getFirstName() + " " + candidate.getLastName(), candidate.getNumber(), candidate.getAgenda());
    }

    /**
     * Constructor to fully initialise the CandidateDto
     *
     * @param fullName candidate's name and surname
     * @param number candidate's number
     * @param agenda candidate's brief agenda
     */
    private CandidateDto(String fullName, int number, String agenda) {
        this.fullName = fullName;
        this.number = number;
        this.agenda = agenda;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getAgenda() {
        return agenda;
    }

    public void setAgenda(String agenda) {
        this.agenda = agenda;
    }
}
