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

        StadiumObject stad1 = new StadiumObject("Porporela", "Hrvatska", "Dubrovnik", "Porat bb", "Nose valovi po jugu", 0.0, 0.0);
        StadiumObject stad2 = new StadiumObject("Porat", "Hrvatska", "Dubrovnik", "Porat bb", "Prljavo, makni barke", 0.0, 0.0);
        StadiumObject stad3 = new StadiumObject("Kolorina", "Hrvatska", "Dubrovnik", "Od Tabakerije bb", "Hlad u 17, pazi kod juga", 0.0, 0.0);
        StadiumObject stad4 = new StadiumObject("Danche", "Hrvatska", "Dubrovnik", "Don Frana Bulica bb", "Nose valovi po maestralu", 0.0, 0.0);
        mStadiumObjects.add(stad1);
        mStadiumObjects.add(stad2);
        mStadiumObjects.add(stad3);
        mStadiumObjects.add(stad4);

    }

    public StadiumObject getStadium(UUID StadiumID){
        for (StadiumObject s : mStadiumObjects) {
            if (s.getId().equals(StadiumID))
                return s;
        }
        return null;
    }

}
