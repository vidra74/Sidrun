package com.example.danceplov.sidrun;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Frano on 17.08.2015.
 */
public class GameObjectList {
    private ArrayList<GameObject> mGameObjects;
    private static GameObjectList sGameObjectListObject;
    private Context mContext;

    public ArrayList<GameObject> getStadiums() {
        return mGameObjects;
    }

    public static GameObjectList getGamesList(Context c) {
        if (null == sGameObjectListObject){
            sGameObjectListObject = new GameObjectList(c.getApplicationContext());
        }

        return sGameObjectListObject;
    }

    private GameObjectList(Context Context) {

        mContext = Context;
        mGameObjects = new ArrayList<GameObject>();

    }

    public void addGame(String team1,
                           String team2,
                           String stadium,
                           Double goalTeam1,
                           Double goalTeam2,
                           long game_row_id){

        GameObject game1 = new GameObject(team1, team2, stadium, goalTeam1, goalTeam2, game_row_id);
        mGameObjects.add(game1);
    }

    public GameObject getGame(UUID GameID){
        for (GameObject s : mGameObjects) {
            if (s.getmId().equals(GameID))
                return s;
        }
        return null;
    }

    public GameObject getGameByRowId(long GameID){
        for (GameObject s : mGameObjects) {
            if (s.getmGameDBId() == GameID)
                return s;
        }
        return null;
    }

    public void clearAllGames(){
        mGameObjects.clear();
    }
}
