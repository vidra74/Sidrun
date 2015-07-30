package com.example.danceplov.sidrun;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    ImageButton mStadioniButton;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

/*
        ;*/
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mStadioniButton = (ImageButton)getView().findViewById(R.id.ibStadioni);
        mStadioniButton.setOnClickListener(new ImageButton.OnClickListener() {
               @Override
               public void onClick(View arg0) {
                   Intent browserIntent = new Intent(getActivity(), StadionActivity.class);
                   startActivity(browserIntent);
               }
           }
        );
    }
}
