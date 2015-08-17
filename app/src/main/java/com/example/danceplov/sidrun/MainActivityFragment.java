package com.example.danceplov.sidrun;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements AbsListView.OnItemClickListener{

    public ListView mListViewIzbornik;

    public final String[] Izbornik = {
            "Igrači",
            "Rezultati",
            "Stadion"
    };

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mListViewIzbornik = (ListView)getView().findViewById(R.id.lvIzbornik);

        mListViewIzbornik.setAdapter(new ArrayAdapter<String>(getActivity(),
                R.layout.list_item_izbornik,
                R.id.list_item_text,
                Izbornik));

        mListViewIzbornik.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:
            {
                Intent playerListIntent = new Intent(getActivity(), PlayerActivity.class);
                startActivity(playerListIntent);
                break;
            }
            case 1:
            {
                Intent gameListIntent = new Intent(getActivity(), GameActivity.class);
                startActivity(gameListIntent);
                break;
            }
            case 2:
            {
                Intent stadiumListIntent = new Intent(getActivity(), StadiumActivity.class);
                startActivity(stadiumListIntent);
                break;
            }
        }
    }
}
