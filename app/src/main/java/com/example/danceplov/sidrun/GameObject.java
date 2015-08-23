package com.example.danceplov.sidrun;

import java.util.UUID;

/**
 * Created by Frano on 17.08.2015.
 */
public class GameObject {

        private UUID mId;
        private String mTeam1Name;
        private String mTeam2Name;
        private String mStadium;
        private Double mGoalTeam1;
        private Double mGoalTeam2;
        private long mGameDBId;


    GameObject(String Team1, String Team2, String Stadium, Double GoalTeam1, Double GoalTeam2, long GameDBId){

        mTeam1Name = Team1;
        mTeam2Name = Team2;
        mStadium = Stadium;
        mGoalTeam1 = GoalTeam1;
        mGoalTeam2 = GoalTeam2;
        mId = UUID.randomUUID();
        mGameDBId = GameDBId;

    }

    GameObject(){

        mTeam1Name = "";
        mTeam2Name = "";
        mStadium = "";
        mGoalTeam1 = 0.0;
        mGoalTeam2 = 0.0;
        mId = UUID.randomUUID();
        mGameDBId = -1;
    }

    public UUID getmId() {
        return mId;
    }

    public String getmTeam1Name() {
        return mTeam1Name;
    }

    public void setmTeam1Name(String mTeam1Name) {
        this.mTeam1Name = mTeam1Name;
    }

    public String getmTeam2Name() {
        return mTeam2Name;
    }

    public void setmTeam2Name(String mTeam2Name) {
        this.mTeam2Name = mTeam2Name;
    }

    public String getmStadium() {
        return mStadium;
    }

    public void setmStadium(String mStadium) {
        this.mStadium = mStadium;
    }

    public Double getmGoalTeam1() {
        return mGoalTeam1;
    }

    public void setmGoalTeam1(Double mGoalTeam1) {
        this.mGoalTeam1 = mGoalTeam1;
    }

    public Double getmGoalTeam2() {
        return mGoalTeam2;
    }

    public void setmGoalTeam2(Double mGoalTeam2) {
        this.mGoalTeam2 = mGoalTeam2;
    }

    public long getmGameDBId() {
        return mGameDBId;
    }

    public void setmGameDBId(long mGameDBId) {
        this.mGameDBId = mGameDBId;
    }

    public String toString() {
        return getmTeam1Name() + " - " + getmTeam2Name() + "  " + (getmGoalTeam1()).toString() + ":" + getmGoalTeam2().toString();
    }
}
