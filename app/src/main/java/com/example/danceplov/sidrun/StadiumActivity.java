package com.example.danceplov.sidrun;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class StadiumActivity extends ActionBarActivity
    implements StadiumListFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        setContentView(R.layout.activity_stadium);

        FragmentManager fragMag = getSupportFragmentManager();
        Fragment frag = fragMag.findFragmentById(R.id.fragment_stadium);

        if (null == frag){
            StadiumListFragment slfrag = new StadiumListFragment();
            fragMag.beginTransaction().add(R.id.fragment_stadium, slfrag).commit();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_stadion, menu);
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

    @Override
    public void onFragmentInteraction(String id) {
        Toast.makeText(this, "Vrijednost onFragmentInteraction " + id, Toast.LENGTH_SHORT).show();

        Intent StadiumDetail = new Intent(this, StadiumDetailActivity.class);
        StadiumDetail.putExtra("Stadion", id);
        startActivity(StadiumDetail);


    }
}
