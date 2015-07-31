package com.example.danceplov.sidrun;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Korisnik on 31.7.2015..
 */
public class StadiumList {
    private ArrayList<Stadium> mStadiums;
    private static StadiumList sStadiumListObject;
    private Context mContext;

    public ArrayList<Stadium> getStadiums() {
        return mStadiums;
    }

    public static StadiumList getsStadiumList(Context c) {
        if (null == sStadiumListObject){
            sStadiumListObject = new StadiumList(c.getApplicationContext());
        }

        return sStadiumListObject;
    }

    private StadiumList(Context Context) {

        mContext = Context;
        mStadiums = new ArrayList<Stadium>();
    }

    public Stadium getStadium(UUID StadiumID){
        for (Stadium s : mStadiums) {
            if (s.getId().equals(StadiumID))
                return s;
        }
        return null;
    }
}
