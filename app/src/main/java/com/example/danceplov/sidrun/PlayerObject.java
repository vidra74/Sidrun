package com.example.danceplov.sidrun;

import java.util.UUID;

public class PlayerObject {

    private UUID mId;
    private String mTeam;
    private String mName;
    private String mSurname;
    private long mPlayerDBId;


    PlayerObject(String Team, String Name, String Surname, long PlayerDBId){

        mTeam = Team;
        mName = Name;
        mSurname = Surname;
        mId = UUID.randomUUID();
        mPlayerDBId = PlayerDBId;

    }

    public UUID getmId() {
        return mId;
    }

    public String getmTeam() {

        return mTeam;
    }

    public void setmTeam(String mTeam) {
        this.mTeam = mTeam;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmSurname() {
        return mSurname;
    }

    public void setmSurname(String mSurname) {
        this.mSurname = mSurname;
    }

    public long getmPlayerDBId() {
        return mPlayerDBId;
    }

    public void setmPlayerDBId(long mPlayerDBId) {
        this.mPlayerDBId = mPlayerDBId;
    }

    PlayerObject(){

        mTeam = "";
        mName = "";
        
        mSurname = "";
        mId = UUID.randomUUID();
        mPlayerDBId = -1;
    }


    public String toString() {
        return getmSurname() + "  " + getmName() + " - " + getmTeam();
    }
}
