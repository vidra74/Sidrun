package com.example.danceplov.sidrun;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */

public class StadiumListFragment extends Fragment implements AbsListView.OnItemClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String PARAM_UUID_STADIUM = "Stadium";
    private static final String PARAM_ROW_ID_STADIUM = "RowID";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    // private ListAdapter mAdapter;
    private StadiumAdapter stadiumAdapter;
    private SimpleCursorAdapter curStadiumAdapter;
    private DBAdapter dbAdapter;

    private class StadiumAdapter extends ArrayAdapter<StadiumObject> {

        public StadiumAdapter(ArrayList<StadiumObject> stadList){
            super(getActivity(), 0, stadList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (null == convertView){
                convertView = getActivity().getLayoutInflater()
                        .inflate(android.R.layout.simple_list_item_1, null);
            }

            StadiumObject stad = getItem(position);
            TextView tv = (TextView)convertView.findViewById(android.R.id.text1);
            tv.setText(stad.getmStadiumName());

            return convertView;
        }
    }
    // TODO: Rename and change types of parameters
    public static StadiumListFragment newInstance(String param1, String param2) {
        StadiumListFragment fragment = new StadiumListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public StadiumListFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        // TODO: Change Adapter to display your content

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stadiumlist, container, false);

        // Set the adapter
        mListView = (AbsListView) view.findViewById(android.R.id.list);

        // stadiumAdapter = new StadiumAdapter(StadiumObjectList.getsStadiumList(getActivity()).getStadiums());
        StadiumObjectList.getsStadiumList(getActivity()).clearAllStadiums();

        dbAdapter = new DBAdapter(getActivity());
        dbAdapter.open();

        ArrayList<StadiumObject> listaStadiona = new ArrayList<StadiumObject>();
        Cursor myCursor = dbAdapter.getStadiumsCursor();
        myCursor.moveToFirst();
        while (!myCursor.isAfterLast()) {
            StadiumObject stadion = new StadiumObject(myCursor.getString(1),
                                                        myCursor.getString(2),
                                                        myCursor.getString(3),
                                                        myCursor.getString(4),
                                                        myCursor.getString(5),
                                                        myCursor.getDouble(6),
                                                        myCursor.getDouble(7),
                                                        myCursor.getLong(0));
            listaStadiona.add(stadion);
            StadiumObjectList.getsStadiumList(getActivity()).addStadium(stadion.getmStadiumName(),
                    stadion.getmStadiumCountry(),
                    stadion.getmStadiumCity(),
                    stadion.getmStadiumAddress(),
                    stadion.getmStadiumComment(),
                    stadion.getmStadiumLongitude(),
                    stadion.getmStadiumLatitude(),
                    stadion.getmStadiumDBId());
            myCursor.moveToNext();
        }
        // make sure to close the cursor
        myCursor.close();
        dbAdapter.close();

        // ((AdapterView<ListAdapter>) mListView).setAdapter(stadiumAdapter);
        ((AdapterView<ListAdapter>) mListView).setAdapter(new StadiumAdapter(listaStadiona));
        // Set OnItemClickListener so we can be notified on item clicks
        mListView.setOnItemClickListener(this);

        getActivity().setTitle(R.string.title_activity_stadion);

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (null != mListener) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.

            // mListener.onFragmentInteraction(((StadiumAdapter) mListView.getAdapter()).getItem(position).getId());

            // start Activity directly with details
            View v = getView().findViewById(R.id.fragment_stadion_detail);
            if (null == v){
                Intent detailActivity = new Intent(getActivity(), StadiumDetailActivity.class);
                StadiumObject soStadium = (StadiumObject) mListView.getAdapter().getItem(position);
                UUID idStadium = soStadium.getId();
                long idRow = soStadium.getmStadiumDBId();
                detailActivity.putExtra(PARAM_UUID_STADIUM, idStadium);
                detailActivity.putExtra(PARAM_ROW_ID_STADIUM, idRow);
                startActivity(detailActivity);
            }


        }
    }

    /**
     * The default content for this Fragment has a TextView that is shown when
     * the list is empty. If you would like to change the text, call this method
     * to supply the text it should use.
     */
    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mListView.getEmptyView();

        if (emptyView instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(UUID id);
    }

}
