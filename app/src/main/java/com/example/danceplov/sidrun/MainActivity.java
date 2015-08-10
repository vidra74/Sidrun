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
        dbStadium.insertStadium("Porporela", "Hrvatska", "Dubrovnik", "Porat bb", "Nose valovi po jugu", 0.0, 0.0);
        dbStadium.insertStadium("Porat", "Hrvatska", "Dubrovnik", "Porat bb", "Prljavo, makni barke", 0.0, 0.0);
        dbStadium.insertStadium("Kolorina", "Hrvatska", "Dubrovnik", "Od Tabakerije bb", "Hlad u 17, pazi kod juga", 0.0, 0.0);
        dbStadium.insertStadium("Danèe", "Hrvatska", "Dubrovnik", "Don Frana Bulica bb", "Nose valovi po maestralu", 0.0, 0.0);

        Cursor myCursor = dbStadium.getStadiums();
        if (myCursor != null){
            myCursor.moveToFirst();
            while(!myCursor.isAfterLast()){
                StadiumObjectList.getsStadiumList(this).addStadium(myCursor.getString(1),
                        myCursor.getString(2),
                        myCursor.getString(3),
                        myCursor.getString(4),
                        myCursor.getString(5),
                        myCursor.getDouble(6),
                        myCursor.getDouble(7),
                        myCursor.getLong(0));
                myCursor.moveToNext();
            }
        }
        myCursor.close();
        dbStadium.close();

        Intent msgIntent = new Intent(this, StadiumService.class);
        msgIntent.setAction(StadiumService.ACTION_FOO);
        msgIntent.putExtra(StadiumService.EXTRA_PARAM1, "Stadium service started");
        msgIntent.putExtra(StadiumService.EXTRA_PARAM2, "FOO");
        startService(msgIntent);
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
