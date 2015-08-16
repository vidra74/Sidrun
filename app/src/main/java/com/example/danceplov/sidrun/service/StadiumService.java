package com.example.danceplov.sidrun.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.text.format.DateFormat;
import android.util.Log;

import com.example.danceplov.sidrun.DBAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class StadiumService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    public static final String ACTION_STADIUM = "com.example.danceplov.sidrun.service.action.STADIUM";
    public static final String ACTION_PLAYER = "com.example.danceplov.sidrun.service.action.PLAYER";
    public static final String ACTION_GAME = "com.example.danceplov.sidrun.service.action.GAME";

    // TODO: Rename parameters
    public static final String EXTRA_PARAM1 = "com.example.danceplov.sidrun.service.extra.PARAM1";
    public static final String EXTRA_PARAM2 = "com.example.danceplov.sidrun.service.extra.PARAM2";

    final String TAG = StadiumService.class.getSimpleName();

    // set your json string url here
    String jsonStadionURL = "http://www.dance.hr/sidrun/stadion.php";
    String jsonGameURL = "http://www.dance.hr/sidrun/utakmice.php";
    String jsonPlayerURL = "http://www.dance.hr/sidrun/igraci.php";

    // contacts JSONArray
    JSONArray dataJsonArr = null;

    /**
     * Starts this service to perform action Stadium update with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionStadium(Context context, String param1, String param2) {
        Intent intent = new Intent(context, StadiumService.class);
        intent.setAction(ACTION_STADIUM);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Team update with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionGames(Context context, String param1, String param2) {
        Intent intent = new Intent(context, StadiumService.class);
        intent.setAction(ACTION_GAME);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    public static void startActionPlayers(Context context, String param1, String param2) {
        Intent intent = new Intent(context, StadiumService.class);
        intent.setAction(ACTION_PLAYER);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    public StadiumService() {
        super("StadiumService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_STADIUM.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionStadium(param1, param2);
            } else if (ACTION_PLAYER.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionPlayer(param1, param2);
            } else if (ACTION_GAME.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionGame(param1, param2);
            }
        }
    }

    private void handleActionStadium(String param1, String param2) {
        DBAdapter dbStadium = new DBAdapter(this);
        try{

            Log.d(StadiumService.class.getSimpleName(), "update stadium data " + DateFormat.format("MM/dd/yy h:mmaa", System.currentTimeMillis()));
            // http://www.dance.hr/sidrun/stadion.php

            // instantiate our json parser
            JsonParser jParser = new JsonParser();

            // get json string from url
            String json = jParser.getJSONFromUrl(jsonStadionURL);

            // get the array of users
            dataJsonArr = new JSONArray(json);


            dbStadium.open();

            // loop through all
            for (int i = 0; i < dataJsonArr.length(); i++) {

                JSONObject c = dataJsonArr.getJSONObject(i);

                // Storing each json item in variable
                String id = c.getString("ID");
                String naziv = c.getString("NAZIV");
                String komentar = c.getString("KOMENTAR");
                String adresa = c.getString("ADRESA");
                String grad = c.getString("GRAD");
                String drzava = c.getString("DRZAVA");
                Double longitude = c.getDouble("LONGITUDE");
                Double latitude = c.getDouble("LATITUDE");

                // show the values in our logcat
                Log.e(TAG, "id: " + id
                        + ", naziv: " + naziv
                        + ", adresa: " + adresa
                        + ", grad: " + grad
                        + ", drzava: " + drzava
                        + ", longitude: " + longitude.toString()
                        + ", latitude: " + latitude.toString()
                        + ", komentar: " + komentar);

                dbStadium.insertStadium(naziv, drzava, grad, adresa, komentar, longitude, latitude);

            }

    } catch (JSONException e) {
            e.printStackTrace();
    }
        dbStadium.close();
        Log.d(TAG, "finished update " + DateFormat.format("MM/dd/yy h:mmaa", System.currentTimeMillis()));
    }

    private void handleActionGame(String param1, String param2) {

        DBAdapter dbStadium = new DBAdapter(this);
        try{

            Log.d(StadiumService.class.getSimpleName(), "update games data " + DateFormat.format("MM/dd/yy h:mmaa", System.currentTimeMillis()));
            // http://www.dance.hr/sidrun/stadion.php

            // instantiate our json parser
            JsonParser jParser = new JsonParser();

            // get json string from url
            String json = jParser.getJSONFromUrl(jsonGameURL);

            // get the array of users
            dataJsonArr = new JSONArray(json);


            dbStadium.open();

            // loop through all
            for (int i = 0; i < dataJsonArr.length(); i++) {

                JSONObject c = dataJsonArr.getJSONObject(i);

                // Storing each json item in variable
                String id = c.getString("ID");
                String stadion = c.getString("STADION");
                String tim1 = c.getString("TIM1");
                String tim2 = c.getString("TIM2");
                Double gol1 = c.getDouble("GOL1");
                Double gol2 = c.getDouble("GOL2");

                // show the values in our logcat
                Log.e(TAG, "id: " + id
                        + ", stadion: " + stadion
                        + ", tim1: " + tim1
                        + ", tim2: " + tim2
                        + ", gol1: " + gol1.toString()
                        + ", gol2: " + gol2.toString());

                dbStadium.insertGame(stadion, tim1, tim2, gol1, gol2);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        dbStadium.close();
        Log.d(TAG, "finished update " + DateFormat.format("MM/dd/yy h:mmaa", System.currentTimeMillis()));
    }

    private void handleActionPlayer(String param1, String param2) {

        DBAdapter dbStadium = new DBAdapter(this);
        try{

            Log.d(StadiumService.class.getSimpleName(), "update player data " + DateFormat.format("MM/dd/yy h:mmaa", System.currentTimeMillis()));
            // http://www.dance.hr/sidrun/stadion.php

            // instantiate our json parser
            JsonParser jParser = new JsonParser();

            // get json string from url
            String json = jParser.getJSONFromUrl(jsonPlayerURL);

            // get the array of users
            dataJsonArr = new JSONArray(json);


            dbStadium.open();

            // loop through all
            for (int i = 0; i < dataJsonArr.length(); i++) {

                JSONObject c = dataJsonArr.getJSONObject(i);

                // Storing each json item in variable
                String id = c.getString("ID");
                String tim = c.getString("TIM");
                String ime = c.getString("IME");
                String prezime = c.getString("PREZIME");

                // show the values in our logcat
                Log.e(TAG, "id: " + id
                        + ", tim: " + tim
                        + ", ime: " + ime
                        + ", prezime: " + prezime);

                dbStadium.insertPlayer(tim, ime, prezime);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        dbStadium.close();
        Log.d(TAG, "finished update " + DateFormat.format("MM/dd/yy h:mmaa", System.currentTimeMillis()));
    }
}
