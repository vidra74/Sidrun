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

        Stadium stad1 = new Stadium("Porporela", "Hrvatska", "Dubrovnik", "Porat bb", "Nose valovi po jugu", 0.0, 0.0);
        Stadium stad2 = new Stadium("Porat", "Hrvatska", "Dubrovnik", "Porat bb", "Prljavo, makni barke", 0.0, 0.0);
        Stadium stad3 = new Stadium("Kolorina", "Hrvatska", "Dubrovnik", "Od Tabakerije bb", "Hlad u 17, pazi kod juga", 0.0, 0.0);
        Stadium stad4 = new Stadium("Danche", "Hrvatska", "Dubrovnik", "Don Frana Bulica bb", "Nose valovi po maestralu", 0.0, 0.0);
        mStadiums.add(stad1);
        mStadiums.add(stad2);
        mStadiums.add(stad3);
        mStadiums.add(stad4);

    }

    public Stadium getStadium(UUID StadiumID){
        for (Stadium s : mStadiums) {
            if (s.getId().equals(StadiumID))
                return s;
        }
        return null;
    }

}
