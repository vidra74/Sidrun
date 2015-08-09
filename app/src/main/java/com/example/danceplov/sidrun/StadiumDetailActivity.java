package com.example.danceplov.sidrun;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.UUID;


public class StadiumDetailActivity extends ActionBarActivity {

    private static final String PARAM_UUID_STADIUM = "Stadium";
    private static final String PARAM_ROW_ID_STADIUM = "RowID";
    String mStadionName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stadium_detail);
        UUID idStadium = UUID.randomUUID();
        long rowId = 0;
        Intent i = getIntent();
        Bundle extras = i.getExtras();
        if (extras != null) {
            idStadium = (UUID)i.getSerializableExtra(PARAM_UUID_STADIUM);
            rowId = (long)i.getSerializableExtra(PARAM_ROW_ID_STADIUM);
        } else finish();

        FragmentManager fragMag = getSupportFragmentManager();
        Fragment frag = fragMag.findFragmentById(R.id.fragment_stadion_detail);

        if (null == frag){
            StadiumDetailFragment slfrag = StadiumDetailFragment.newInstance(idStadium, rowId);
            fragMag.beginTransaction().add(R.id.fragment_stadion_detail, slfrag).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_stadium_detail, menu);
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
