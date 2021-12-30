package com.example.votingpro.Costume_Classes;

import java.io.Serializable;

public class Main_Category implements Serializable {

    // id --> 0
    private int Local_Id;
    private String DbRef;
    private String ProfileImage;
    private String Category_Name;
    private String Name;
    private String Status;
    private String Subject;
    private int Like_Voting;
    private int Neutral_Voting;
    private int Dislike_Voting;
    private int All_Voting;
    private boolean IsVoting;

    public Main_Category() {
    }

    public Main_Category(String dbRef, String profileImage, String name, String status, String subject, int like_Voting, int neutral_Voting, int dislike_Voting, int all_Voting, boolean isVoting) {
        this.DbRef = dbRef;
        this.ProfileImage = profileImage;
        this.Name = name;
        this.Status = status;
        this.Subject = subject;
        this.Like_Voting = like_Voting;
        this.Neutral_Voting = neutral_Voting;
        this.Dislike_Voting = dislike_Voting;
        this.All_Voting = all_Voting;
        this.IsVoting = isVoting;
    }

    public Main_Category(String profileImage, String name, String status, String subject, int like_Voting, int neutral_Voting, int dislike_Voting, int all_Voting, boolean isVoting) {
        this.ProfileImage = profileImage;
        this.Name = name;
        this.Status = status;
        this.Subject = subject;
        this.Like_Voting = like_Voting;
        this.Neutral_Voting = neutral_Voting;
        this.Dislike_Voting = dislike_Voting;
        this.All_Voting = all_Voting;
        this.IsVoting = isVoting;
    }

    public Main_Category(String name, String status, String subject, int like_Voting, int neutral_Voting, int dislike_Voting, int all_Voting, boolean isVoting) {
        Name = name;
        Status = status;
        Subject = subject;
        Like_Voting = like_Voting;
        Neutral_Voting = neutral_Voting;
        Dislike_Voting = dislike_Voting;
        All_Voting = all_Voting;
        IsVoting = isVoting;
    }

    public String getCategory_Name() {
        return Category_Name;
    }

    public void setCategory_Name(String category_Name) {
        Category_Name = category_Name;
    }

    public int getLocal_Id() {
        return Local_Id;
    }

    public void setLocal_Id(int local_Id) {
        Local_Id = local_Id;
    }

    public String getDbRef() {
        return DbRef;
    }

    public void setDbRef(String dbRef) {
        DbRef = dbRef;
    }

    public String getProfileImage() {
        return ProfileImage;
    }

    public void setProfileImage(String profileImage) {
        ProfileImage = profileImage;
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

    public int getLike_Voting() {
        return Like_Voting;
    }

    public void setLike_Voting(int like_Voting) {
        Like_Voting = like_Voting;
    }

    public int getNeutral_Voting() {
        return Neutral_Voting;
    }

    public void setNeutral_Voting(int neutral_Voting) {
        Neutral_Voting = neutral_Voting;
    }

    public int getDislike_Voting() {
        return Dislike_Voting;
    }

    public void setDislike_Voting(int dislike_Voting) {
        Dislike_Voting = dislike_Voting;
    }

    public int getAll_Voting() {
        return All_Voting;
    }

    public void setAll_Voting(int all_Voting) {
        All_Voting = all_Voting;
    }

    public boolean isVoting() {
        return IsVoting;
    }

    public void setVoting(boolean voting) {
        IsVoting = voting;
    }
    @Override
    public String toString() {
        return Name;
    }
}
