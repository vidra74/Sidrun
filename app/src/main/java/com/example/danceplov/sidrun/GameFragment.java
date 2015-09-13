package com.example.danceplov.sidrun;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class GameFragment extends Fragment implements AbsListView.OnItemClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String PARAM_UUID_GAME = "Game";
    private static final String PARAM_ROW_ID_UUID = "RowID";

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
    private GameAdapter stadiumAdapter;
    private SimpleCursorAdapter curStadiumAdapter;
    private DBAdapter dbAdapter;

    private class GameAdapter extends ArrayAdapter<GameObject> {

        public final int VIEW_TYPE_BIG = 0;
        public final int VIEW_TYPE_SMALL = 1;

        public GameAdapter(ArrayList<GameObject> gameList){
            super(getActivity(), 0, gameList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (null == convertView){

                int ViewType = getItemViewType(position);

                switch (ViewType){
                    case VIEW_TYPE_SMALL: {
                        convertView = getActivity().getLayoutInflater()
                                .inflate(R.layout.game_list_small_item, null);
                        break;
                    }
                    case VIEW_TYPE_BIG: {
                        convertView = getActivity().getLayoutInflater()
                                .inflate(R.layout.game_list_first_item, null);
                        break;
                    }
                }
            }

            GameObject game = getItem(position);
            TextView tv = (TextView)convertView.findViewById(android.R.id.text1);
            tv.setText(game.toString());

            return convertView;
        }

        @Override
        public int getItemViewType(int position) {
            // return super.getItemViewType(position);
            return (position == 0) ? VIEW_TYPE_BIG : VIEW_TYPE_SMALL;
        }

        @Override
        public int getViewTypeCount() {
            // return super.getViewTypeCount();
            return 2;
        }
    }

    private class GameCursorAdapter extends CursorAdapter {

        public GameCursorAdapter(Context context, Cursor gamecursor){
            // CursorAdapter(Context context, Cursor c)
            super(context, gamecursor);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            View convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false);
            return convertView;
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {

            String rezultat = cursor.getString(1) + " - " + cursor.getString(2) + "  " + cursor.getString(4) + ":" + cursor.getString(5);
            TextView tv = (TextView)view.findViewById(android.R.id.text1);
            tv.setText(rezultat);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return super.getView(position, convertView, parent);
        }
    }

    // TODO: Rename and change types of parameters
    public static GameFragment newInstance(String param1, String param2) {
        GameFragment fragment = new GameFragment();
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
    public GameFragment() {
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
        View view = inflater.inflate(R.layout.fragment_game2, container, false);

        // Set the adapter
        mListView = (AbsListView) view.findViewById(android.R.id.list);

        dbAdapter = new DBAdapter(getActivity());
        dbAdapter.open();

        ArrayList<GameObject> listaUtakmica = new ArrayList<GameObject>();
        Cursor myCursor = dbAdapter.getGamesCursor();
        myCursor.moveToFirst();
        while (!myCursor.isAfterLast()) {
            GameObject game = new GameObject(myCursor.getString(1),
                    myCursor.getString(2),
                    myCursor.getString(3),
                    myCursor.getDouble(4),
                    myCursor.getDouble(5),
                    myCursor.getLong(0));
            listaUtakmica.add(game);
            GameObjectList.getGamesList(getActivity()).addGame(game.getmTeam1Name(),
                    game.getmTeam2Name(),
                    game.getmStadium(),
                    game.getmGoalTeam1(),
                    game.getmGoalTeam2(),
                    game.getmGameDBId());
            myCursor.moveToNext();
        }
        myCursor.moveToFirst();
        //((AdapterView<ListAdapter>) mListView).setAdapter(new GameAdapter(listaUtakmica));
        ((AdapterView<ListAdapter>) mListView).setAdapter(new GameCursorAdapter(getActivity(), myCursor));

        // make sure to close the cursor
        // myCursor.close();
        dbAdapter.close();

        // Set OnItemClickListener so we can be notified on item clicks
        mListView.setOnItemClickListener(this);

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
            // mListener.onFragmentInteraction(DummyContent.ITEMS.get(position).id);
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
        public void onFragmentInteraction(String id);
    }

}
