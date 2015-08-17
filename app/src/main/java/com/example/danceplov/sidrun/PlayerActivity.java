package com.example.danceplov.sidrun;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class PlayerActivity extends ActionBarActivity
        implements PlayerFragment.OnFragmentInteractionListener{

    @Override
    public void onFragmentInteraction(String id) {
        // pokreni kasnije detail intent
        ;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        FragmentManager fragMag = getSupportFragmentManager();
        Fragment frag = fragMag.findFragmentById(R.id.fragment_player);

        if (null == frag){
            PlayerFragment slfrag = new PlayerFragment();
            fragMag.beginTransaction().add(R.id.fragment_player, slfrag).commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_player, menu);
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
