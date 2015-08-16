package com.example.danceplov.sidrun;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Korisnik on 6.8.2015..
 */
public class DBAdapter {
    static final String STADIUM_ROWID = "_id";
    static final String STADIUM_STADIUM = "stadium";
    static final String STADIUM_STADIUM_COUNTRY = "stadium_country";
    static final String STADIUM_STADIUM_CITY = "stadium_city";
    static final String STADIUM_STADIUM_ADDRESS = "stadium_address";
    static final String STADIUM_COMMENT = "comment";
    static final String STADIUM_LONGITUDE = "stadium_longitude";
    static final String STADIUM_LATITUDE = "stadium_latitude";

    static final String GAME_ROWID = "_id";
    static final String GAME_STADIUM = "stadium";
    static final String GAME_TEAM1 = "tim1";
    static final String GAME_TEAM2 = "tim2";
    static final String GAME_GOALS_TEAM1 = "gol1";
    static final String GAME_GOALS_TEAM2 = "gol2";

    static final String PLAYER_ROWID = "_id";
    static final String PLAYER_TEAM = "tim";
    static final String PLAYER_NAME = "ime";
    static final String PLAYER_SURNAME = "prezime";

    static final String DATABASE_NAME = "Sidrun";
    static final String DATABASE_TABLE_STADIUM = "stadiums";
    static final String DATABASE_TABLE_GAME = "games";
    static final String DATABASE_TABLE_PLAYER = "players";
    static final int DATABASE_VERSION = 1;

    static final String DATABASE_TABLE_STADIUM_CREATE = "create table stadiums (_id integer primary key autoincrement, " +
                                            "stadium text not null, " +
                                            "stadium_country text not null, " +
                                            "stadium_city text not null, " +
                                            "stadium_address text, " +
                                            "comment text, " +
                                            "stadium_longitude real, " +
                                            "stadium_latitude real); ";

    static final String DATABASE_TABLE_GAME_CREATE = "create table games (_id integer primary key autoincrement, " +
            "stadium text not null, " +
            "tim1 text not null, " +
            "tim2 text not null, " +
            "gol1 real, " +
            "gol2 real); ";

    static final String DATABASE_TABLE_PLAYER_CREATE = "create table players (_id integer primary key autoincrement, " +
            "team text not null, " +
            "ime text not null, " +
            "prezime text not null); ";

    final Context context;
    DatabaseHelper DBHelper;
    SQLiteDatabase db;

    public DBAdapter(Context ctx){
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }
        /* -- nedovoljan API za rad (zahtjeva API 11)
        public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
            super(context, name, factory, version, errorHandler);
        }
        */

        @Override
        public String getDatabaseName() {
            return super.getDatabaseName();
        }

        @Override
        public void setWriteAheadLoggingEnabled(boolean enabled) {
            super.setWriteAheadLoggingEnabled(enabled);
        }

        @Override
        public SQLiteDatabase getWritableDatabase() {
            return super.getWritableDatabase();
        }

        @Override
        public SQLiteDatabase getReadableDatabase() {
            return super.getReadableDatabase();
        }

        @Override
        public synchronized void close() {
            super.close();
        }

        @Override
        public void onConfigure(SQLiteDatabase db) {
            super.onConfigure(db);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(DATABASE_TABLE_STADIUM_CREATE);
            } catch(SQLException e){
                e.printStackTrace();
            }
            try {
                db.execSQL(DATABASE_TABLE_GAME_CREATE);
            } catch(SQLException e){
                e.printStackTrace();
            }
            try {
                db.execSQL(DATABASE_TABLE_PLAYER_CREATE);
            } catch(SQLException e){
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_STADIUM);
                db.execSQL(DATABASE_TABLE_STADIUM_CREATE);
            } catch(SQLException e){
                e.printStackTrace();
            }
            try {
                db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_GAME);
                db.execSQL(DATABASE_TABLE_GAME_CREATE);
            } catch(SQLException e){
                e.printStackTrace();
            }
            try {
                db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_PLAYER);
                db.execSQL(DATABASE_TABLE_PLAYER_CREATE);
            } catch(SQLException e){
                e.printStackTrace();
            }
        }

        @Override
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            super.onDowngrade(db, oldVersion, newVersion);
        }

        @Override
        public void onOpen(SQLiteDatabase db) {
            super.onOpen(db);
        }
    }

    public DBAdapter open() throws SQLException{
        db = DBHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        DBHelper.close();
    }

    public long insertStadium(String stadium,
                              String stadium_country,
                              String stadium_city,
                              String stadium_address,
                              String comment,
                              Double stadium_longitude,
                              Double stadium_latitude){

        ContentValues stadiumValues = new ContentValues();
        stadiumValues.put(STADIUM_STADIUM, stadium);
        stadiumValues.put(STADIUM_STADIUM_COUNTRY, stadium_country);
        stadiumValues.put(STADIUM_STADIUM_CITY, stadium_city);
        stadiumValues.put(STADIUM_STADIUM_ADDRESS, stadium_address);
        stadiumValues.put(STADIUM_COMMENT, comment);
        stadiumValues.put(STADIUM_LONGITUDE, stadium_longitude);
        stadiumValues.put(STADIUM_LATITUDE, stadium_latitude);

        return db.insert(DATABASE_TABLE_STADIUM, null, stadiumValues);
    }

    public boolean deleteStadium(long stadiumId){

        return (db.delete(DATABASE_TABLE_STADIUM, STADIUM_ROWID + " = " + stadiumId, null) > 0);
    }

    public boolean deleteAllStadiums(){

        return (db.delete(DATABASE_TABLE_STADIUM, null, null) > 0);
    }

    public Cursor getStadiumsCursor(){

        return db.query(DATABASE_TABLE_STADIUM,
                        new String[]{STADIUM_ROWID, STADIUM_STADIUM, STADIUM_STADIUM_COUNTRY,
                                STADIUM_STADIUM_CITY, STADIUM_STADIUM_ADDRESS, STADIUM_COMMENT,
                                STADIUM_LONGITUDE, STADIUM_LATITUDE},
                        null,
                        null,
                        null,
                        null,
                        null);
    }

    public Cursor getStadiumCursor(long stadiumId) throws SQLException{

        Cursor curStadium = db.query(DATABASE_TABLE_STADIUM,
                                        new String[]{STADIUM_ROWID, STADIUM_STADIUM, STADIUM_STADIUM_COUNTRY,
                                                STADIUM_STADIUM_CITY, STADIUM_STADIUM_ADDRESS, STADIUM_COMMENT,
                                                STADIUM_LONGITUDE, STADIUM_LATITUDE},
                                        STADIUM_ROWID + " = " + stadiumId,
                                        null,
                                        null,
                                        null,
                                        null);

        if (curStadium != null){
            curStadium.moveToFirst();
        }
        return curStadium;
    }

    public boolean upgradeStadium(long stadiumId,
                                  String stadium,
                                  String stadium_country,
                                  String stadium_city,
                                  String stadium_address,
                                  String comment,
                                  Double stadium_longitude,
                                  Double stadium_latitude){
        ContentValues stadiumValues = new ContentValues();
        stadiumValues.put(STADIUM_STADIUM, stadium);
        stadiumValues.put(STADIUM_STADIUM_COUNTRY, stadium_country);
        stadiumValues.put(STADIUM_STADIUM_CITY, stadium_city);
        stadiumValues.put(STADIUM_STADIUM_ADDRESS, stadium_address);
        stadiumValues.put(STADIUM_COMMENT, comment);
        stadiumValues.put(STADIUM_LONGITUDE, stadium_longitude);
        stadiumValues.put(STADIUM_LATITUDE, stadium_latitude);

        return (db.update(DATABASE_TABLE_STADIUM, stadiumValues, STADIUM_ROWID + " = " + stadiumId, null) > 0);
    }

    public long insertGame(String stadium,
                              String team1,
                              String team2,
                              Double goal1,
                              Double goal2){

        ContentValues gameValues = new ContentValues();
        gameValues.put(GAME_STADIUM, stadium);
        gameValues.put(GAME_TEAM1, team1);
        gameValues.put(GAME_TEAM2, team2);
        gameValues.put(GAME_GOALS_TEAM1, goal1);
        gameValues.put(GAME_GOALS_TEAM2, goal2);

        return db.insert(DATABASE_TABLE_STADIUM, null, gameValues);
    }

    public boolean deleteGame(long gameId){

        return (db.delete(DATABASE_TABLE_GAME, GAME_ROWID + " = " + gameId, null) > 0);
    }

    public boolean deleteAllGames(){

        return (db.delete(DATABASE_TABLE_GAME, null, null) > 0);
    }

    public Cursor getGamesCursor(){

        return db.query(DATABASE_TABLE_GAME,
                new String[]{GAME_ROWID, GAME_STADIUM, GAME_TEAM1,
                        GAME_TEAM2, GAME_GOALS_TEAM1, GAME_GOALS_TEAM2},
                null,
                null,
                null,
                null,
                null);
    }

    public Cursor getGameCursor(long gameId) throws SQLException{

        Cursor curGame = db.query(DATABASE_TABLE_GAME,
                new String[]{GAME_ROWID, GAME_STADIUM, GAME_TEAM1,
                        GAME_TEAM2, GAME_GOALS_TEAM1, GAME_GOALS_TEAM2},
                STADIUM_ROWID + " = " + gameId,
                null,
                null,
                null,
                null);

        if (curGame != null){
            curGame.moveToFirst();
        }
        return curGame;
    }

    public boolean upgradeGame(long gameId,
                               String stadium,
                               String team1,
                               String team2,
                               Double goal1,
                               Double goal2){
        ContentValues gameValues = new ContentValues();
        gameValues.put(GAME_STADIUM, stadium);
        gameValues.put(GAME_TEAM1, team1);
        gameValues.put(GAME_TEAM2, team2);
        gameValues.put(GAME_GOALS_TEAM1, goal1);
        gameValues.put(GAME_GOALS_TEAM2, goal2);

        return (db.update(DATABASE_TABLE_GAME, gameValues, STADIUM_ROWID + " = " + gameId, null) > 0);
    }

    public long insertPlayer(String team,
                              String player_name,
                              String player_surname){

        ContentValues playerValues = new ContentValues();
        playerValues.put(PLAYER_TEAM, team);
        playerValues.put(PLAYER_NAME, player_name);
        playerValues.put(PLAYER_SURNAME, player_surname);

        return db.insert(DATABASE_TABLE_PLAYER, null, playerValues);
    }

    public boolean deletePalyer(long playerID){

        return (db.delete(DATABASE_TABLE_PLAYER, PLAYER_ROWID + " = " + playerID, null) > 0);
    }

    public boolean deleteAllPlayers(){

        return (db.delete(DATABASE_TABLE_PLAYER, null, null) > 0);
    }

    public Cursor getPlayersCursor(){

        return db.query(DATABASE_TABLE_PLAYER,
                new String[]{PLAYER_ROWID, PLAYER_TEAM, PLAYER_NAME,
                        PLAYER_SURNAME},
                null,
                null,
                null,
                null,
                null);
    }

    public Cursor getPlayerCursor(long playerId) throws SQLException{

        Cursor curPlayer = db.query(DATABASE_TABLE_PLAYER,
                new String[]{PLAYER_ROWID, PLAYER_TEAM, PLAYER_NAME,
                        PLAYER_SURNAME},
                PLAYER_ROWID + " = " + playerId,
                null,
                null,
                null,
                null);

        if (curPlayer != null){
            curPlayer.moveToFirst();
        }
        return curPlayer;
    }

    public boolean upgradePlayer(long playerID,
                                  String team,
                                  String name,
                                  String surname){
        ContentValues playerValues = new ContentValues();
        playerValues.put(PLAYER_TEAM, team);
        playerValues.put(PLAYER_NAME, name);
        playerValues.put(PLAYER_SURNAME, surname);

        return (db.update(DATABASE_TABLE_PLAYER, playerValues, PLAYER_ROWID + " = " + playerID, null) > 0);
    }



}
