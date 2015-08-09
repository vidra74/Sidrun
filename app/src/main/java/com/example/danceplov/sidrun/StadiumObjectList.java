package com.example.danceplov.sidrun;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Korisnik on 31.7.2015..
 */
public class StadiumObjectList {
    private ArrayList<StadiumObject> mStadiumObjects;
    private static StadiumObjectList sStadiumObjectListObject;
    private Context mContext;

    public ArrayList<StadiumObject> getStadiums() {
        return mStadiumObjects;
    }

    public static StadiumObjectList getsStadiumList(Context c) {
        if (null == sStadiumObjectListObject){
            sStadiumObjectListObject = new StadiumObjectList(c.getApplicationContext());
        }

        return sStadiumObjectListObject;
    }

    private StadiumObjectList(Context Context) {

        mContext = Context;
        mStadiumObjects = new ArrayList<StadiumObject>();

    }

    public void addStadium(String stadium,
                            String stadium_country,
                            String stadium_city,
                            String stadium_address,
                            String comment,
                            Double stadium_longitude,
                            Double stadium_latitude,
                            long stadium_row_id){

        StadiumObject stad1 = new StadiumObject(stadium, stadium_country, stadium_city, stadium_address, comment, stadium_longitude, stadium_latitude, stadium_row_id);
        mStadiumObjects.add(stad1);
    }

    public StadiumObject getStadium(UUID StadiumID){
        for (StadiumObject s : mStadiumObjects) {
            if (s.getId().equals(StadiumID))
                return s;
        }
        return null;
    }

}
