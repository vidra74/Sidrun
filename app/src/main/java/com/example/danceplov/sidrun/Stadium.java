package com.example.danceplov.sidrun;

/**
 * Created by Korisnik on 31.7.2015..
 */
public class Stadium {
    public String mStadiumName;
    public String mStadiumCountry;
    public String mStadiumCity;
    public String mStadiumAddress;
    public String mStadiumComment;
    public Double mStadiumLongitude;
    public Double mStadiumLatitude;

    public String getmStadiumName() {
        return mStadiumName;
    }

    public String getmStadiumCountry() {
        return mStadiumCountry;
    }

    public String getmStadiumCity() {
        return mStadiumCity;
    }

    public String getmStadiumAddress() {
        return mStadiumAddress;
    }

    public String getmStadiumComment() {
        return mStadiumComment;
    }

    public Double getmStadiumLongitude() {
        return mStadiumLongitude;
    }

    public Double getmStadiumLatitude() {
        return mStadiumLatitude;
    }

    public void setmStadiumName(String mStadiumName) {
        this.mStadiumName = mStadiumName;
    }

    public void setmStadiumCountry(String mStadiumCountry) {
        this.mStadiumCountry = mStadiumCountry;
    }

    public void setmStadiumCity(String mStadiumCity) {
        this.mStadiumCity = mStadiumCity;
    }

    public void setmStadiumAddress(String mStadiumAddress) {
        this.mStadiumAddress = mStadiumAddress;
    }

    public void setmStadiumComment(String mStadiumComment) {
        this.mStadiumComment = mStadiumComment;
    }

    public void setmStadiumLongitude(Double mStadiumLongitude) {
        this.mStadiumLongitude = mStadiumLongitude;
    }

    public void setmStadiumLatitude(Double mStadiumLatitude) {
        this.mStadiumLatitude = mStadiumLatitude;
    }

    public void Stadium(String Name, String Country, String City, String Address, String Comment, Double Longitude, Double Latitude){
        mStadiumName = Name;
        mStadiumCountry = Country;
        mStadiumCity = City;
        mStadiumAddress = Address;
        mStadiumComment = Comment;
        mStadiumLongitude = Longitude;
        mStadiumLatitude = Latitude;

    }

    public void Stadium(){
        mStadiumName = "";
        mStadiumCountry = "";
        mStadiumCity = "";
        mStadiumAddress = "";
        mStadiumComment = "";
        mStadiumLongitude = 0.0;
        mStadiumLatitude = 0.0;
    }
}
