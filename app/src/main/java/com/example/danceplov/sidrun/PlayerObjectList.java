package com.example.danceplov.sidrun;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Frano on 17.08.2015.
 */
public class PlayerObjectList {
    private ArrayList<PlayerObject> mPlayerObjects;
    private static PlayerObjectList sPlayerObjectListObject;
    private Context mContext;

    public ArrayList<PlayerObject> getGame() {
        return mPlayerObjects;
    }

    public static PlayerObjectList getPlayersList(Context c) {
        if (null == sPlayerObjectListObject){
            sPlayerObjectListObject = new PlayerObjectList(c.getApplicationContext());
        }

        return sPlayerObjectListObject;
    }

    private PlayerObjectList(Context Context) {

        mContext = Context;
        mPlayerObjects = new ArrayList<PlayerObject>();

    }

    public void addPlayer(String team,
                           String name,
                           String surname,
                           long player_row_id){

        PlayerObject play1 = new PlayerObject(team, name, surname, player_row_id);
        mPlayerObjects.add(play1);
    }

    public PlayerObject getPlayer(UUID PlayerID){
        for (PlayerObject s : mPlayerObjects) {
            if (s.getmId().equals(PlayerID))
                return s;
        }
        return null;
    }

    public PlayerObject getPlayerByRowId(long PlayerID){
        for (PlayerObject s : mPlayerObjects) {
            if (s.getmPlayerDBId() == PlayerID)
                return s;
        }
        return null;
    }

    public void clearAllPlayers(){
        mPlayerObjects.clear();
    }
}
