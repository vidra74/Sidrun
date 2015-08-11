package com.example.danceplov.sidrun.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.os.SystemClock;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.Toast;

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
    public static final String ACTION_TEAM = "com.example.danceplov.sidrun.service.action.TEAM";

    // TODO: Rename parameters
    public static final String EXTRA_PARAM1 = "com.example.danceplov.sidrun.service.extra.PARAM1";
    public static final String EXTRA_PARAM2 = "com.example.danceplov.sidrun.service.extra.PARAM2";

    final String TAG = StadiumService.class.getSimpleName();

    // set your json string url here
    String yourJsonStringUrl = "http://www.dance.hr/sidrun/stadion.php";

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
    public static void startActionTeam(Context context, String param1, String param2) {
        Intent intent = new Intent(context, StadiumService.class);
        intent.setAction(ACTION_TEAM);
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
            } else if (ACTION_TEAM.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionTeam(param1, param2);
            }
        }
    }

    private void handleActionStadium(String param1, String param2) {
        try{

            Log.d(StadiumService.class.getSimpleName(), "update stadium data " + DateFormat.format("MM/dd/yy h:mmaa", System.currentTimeMillis()));
            // http://www.dance.hr/sidrun/stadion.php

            // instantiate our json parser
            JsonParser jParser = new JsonParser();

            // get json string from url
            String json = jParser.getJSONFromUrl(yourJsonStringUrl);

            // get the array of users
            dataJsonArr = new JSONArray(json);

            // loop through all users
            for (int i = 0; i < dataJsonArr.length(); i++) {

                JSONObject c = dataJsonArr.getJSONObject(i);

                // Storing each json item in variable
                String id = c.getString("ID");
                String naziv = c.getString("NAZIV");
                String komentar = c.getString("KOMENTAR");
                String adresa = c.getString("ADRESA");
                String grad = c.getString("GRAD");
                String drzava = c.getString("DRZAVA");
                String longitude = c.getString("LONGITUDE");
                String latitude = c.getString("LATITUDE");

                // show the values in our logcat
                Log.e(TAG, "id: " + id
                        + ", naziv: " + naziv
                        + ", adresa: " + adresa
                        + ", grad: " + grad
                        + ", drzava: " + drzava
                        + ", longitude: " + longitude
                        + ", latitude: " + latitude
                        + ", komentar: " + komentar);

            }

    } catch (JSONException e) {
        e.printStackTrace();
    }

        Log.d(TAG, "finished update " + DateFormat.format("MM/dd/yy h:mmaa", System.currentTimeMillis()));
    }

    private void handleActionTeam(String param1, String param2) {

        Log.d(StadiumService.class.getSimpleName(), "update team data " + DateFormat.format("MM/dd/yy h:mmaa", System.currentTimeMillis()));
    }
}
