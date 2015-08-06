package com.example.danceplov.sidrun;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Korisnik on 6.8.2015..
 */
public class DBAdapter {
    static final String KEY_ROWID = "_id";
    static final String KEY_STADIUM = "stadium";
    static final String KEY_STADIUM_COUNTRY = "stadium_country";
    static final String KEY_STADIUM_CITY = "stadium_city";
    static final String KEY_STADIUM_ADDRESS = "stadium_address";
    static final String KEY_COMMENT = "comment";
    static final String KEY_LONGITUDE = "stadium_longitude";
    static final String KEY_LATITUDE = "stadium_latitude";

    static final String DATABASE_NAME = "Sidrun";
    static final String DATABASE_TABLE = "stadiums";
    static final int DATABASE_VERSION = 1;

    static final String DATABASE_CREATE = "create table stadiums (_id integer primary key autoincrement, " +
                                            "stadium text not null, " +
                                            "stadium_country text not null, " +
                                            "stadium_city text not null, " +
                                            "stadium_address text, " +
                                            "comment text, " +
                                            "stadium_longitude real, " +
                                            "stadium_latitude real); ";

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
        /*
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

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

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

}
