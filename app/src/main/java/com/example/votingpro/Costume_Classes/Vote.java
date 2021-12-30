package com.example.votingpro.Costume_Classes;

import java.io.Serializable;

public class Vote implements Serializable {

    private int Id;
    private String Name;
    private String Status;
    private String Subject;
    private String VotingDay;
    private String VotingEmotions;
    private boolean isVoting;

    public Vote() {
    }

    public Vote(int id, String name, String status, String subject, String votingDay, String votingEmotions, boolean isVoting) {
        Id = id;
        Name = name;
        Status = status;
        Subject = subject;
        VotingDay = votingDay;
        VotingEmotions = votingEmotions;
        this.isVoting = isVoting;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getVotingDay() {
        return VotingDay;
    }

    public void setVotingDay(String votingDay) {
        VotingDay = votingDay;
    }

    public String getVotingEmotions() {
        return VotingEmotions;
    }

    public void setVotingEmotions(String votingEmotions) {
        VotingEmotions = votingEmotions;
    }

    public boolean isVoting() {
        return isVoting;
    }

    public void setVoting(boolean voting) {
        isVoting = voting;
    }

    @Override
    public String toString() {
        return Name;
    }
}
