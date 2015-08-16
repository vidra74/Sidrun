package com.example.danceplov.sidrun;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.danceplov.sidrun.service.StadiumService;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        setContentView(R.layout.activity_main);

        DBAdapter dbStadium = new DBAdapter(this);
        dbStadium.open();
        dbStadium.deleteAllStadiums();
        // dbStadium.insertStadium("Porporela", "Hrvatska", "Dubrovnik", "Porat bb", "Nose valovi po jugu", 0.0, 0.0);
        // dbStadium.insertStadium("Porat", "Hrvatska", "Dubrovnik", "Porat bb", "Prljavo, makni barke", 0.0, 0.0);
        // dbStadium.insertStadium("Kolorina", "Hrvatska", "Dubrovnik", "Od Tabakerije bb", "Hlad u 17, pazi kod juga", 0.0, 0.0);
        // dbStadium.insertStadium("Dan�e", "Hrvatska", "Dubrovnik", "Don Frana Bulica bb", "Nose valovi po maestralu", 0.0, 0.0);

        dbStadium.close();

        Intent setStadium = new Intent(this, StadiumService.class);
        setStadium.setAction(StadiumService.ACTION_STADIUM);
        setStadium.putExtra(StadiumService.EXTRA_PARAM1, "Stadium service started");
        setStadium.putExtra(StadiumService.EXTRA_PARAM2, "Stadium");
        startService(setStadium);

        Intent setPlayer = new Intent(this, StadiumService.class);
        setPlayer.setAction(StadiumService.ACTION_PLAYER);
        setPlayer.putExtra(StadiumService.EXTRA_PARAM1, "Player service started");
        setPlayer.putExtra(StadiumService.EXTRA_PARAM2, "Player");
        startService(setPlayer);

        Intent setGame = new Intent(this, StadiumService.class);
        setGame.setAction(StadiumService.ACTION_GAME);
        setGame.putExtra(StadiumService.EXTRA_PARAM1, "Game service started");
        setGame.putExtra(StadiumService.EXTRA_PARAM2, "Game");
        startService(setGame);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
