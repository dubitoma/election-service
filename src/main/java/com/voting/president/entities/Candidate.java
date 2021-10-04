package com.voting.president.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "candidates")
public class Candidate implements Serializable {

    @Id
    @Column(unique = true)
    private int number;
    private String firstName;
    private String lastName;
    private String agenda;

    protected Candidate() {
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAgenda() {
        return agenda;
    }

    public void setAgenda(String agenda) {
        this.agenda = agenda;
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "number=" + number +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", agenda=" + agenda +
                '}';
    }
}
