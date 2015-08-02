package com.example.danceplov.sidrun;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.UUID;


/**
 * A placeholder fragment containing a simple view.
 */
public class StadiumDetailFragment extends Fragment {

    private static final String PARAM_UUID_STADIUM = "Stadium";

    public StadiumObject mStadiumObject;
    private ViewHolder mViewHolder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UUID idStadium = (UUID)getArguments().getSerializable(PARAM_UUID_STADIUM);
        mStadiumObject = StadiumObjectList.getsStadiumList(getActivity()).getStadium(idStadium);
    }

    public StadiumDetailFragment() {
    }

    private class ViewHolder {
        TextView mtvStadium;
        TextView mtvStadiumCountry;
        TextView mtvStadiumCity;
        TextView mtvStadiumAddress;
        TextView mtvStadiumLongitude;
        TextView mtvStadiumLatitude;
        TextView mtvStadiumComment;

        public void ViewHolder(){};

        public TextView getStadium() {
            return mtvStadium;
        }

        public void setStadium(TextView mtvStadium) {
            this.mtvStadium = mtvStadium;
        }

        public TextView getStadiumCountry() {
            return mtvStadiumCountry;
        }

        public void setStadiumCountry(TextView mtvStadiumCountry) {
            this.mtvStadiumCountry = mtvStadiumCountry;
        }

        public TextView getStadiumCity() {
            return mtvStadiumCity;
        }

        public void setStadiumCity(TextView mtvStadiumCity) {
            this.mtvStadiumCity = mtvStadiumCity;
        }

        public TextView getStadiumAddress() {
            return mtvStadiumAddress;
        }

        public void setStadiumAddress(TextView mtvStadiumAddress) {
            this.mtvStadiumAddress = mtvStadiumAddress;
        }

        public TextView getStadiumLongitude() {
            return mtvStadiumLongitude;
        }

        public void setStadiumLongitude(TextView mtvStadiumLongitude) {
            this.mtvStadiumLongitude = mtvStadiumLongitude;
        }

        public TextView getStadiumLatitude() {
            return mtvStadiumLatitude;
        }

        public void setStadiumLatitude(TextView mtvStadiumLatitude) {
            this.mtvStadiumLatitude = mtvStadiumLatitude;
        }

        public TextView getStadiumComment() {
            return mtvStadiumComment;
        }

        public void setStadiumComment(TextView mtvStadiumComment) {
            this.mtvStadiumComment = mtvStadiumComment;
        }
    }

    public static StadiumDetailFragment newInstance(UUID uuidStadium) {
        StadiumDetailFragment fragment = new StadiumDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(PARAM_UUID_STADIUM, uuidStadium);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_stadium_detail, container, false);
        mViewHolder = new ViewHolder();

        mViewHolder.setStadium((TextView) v.findViewById(R.id.tvStadium));
        mViewHolder.setStadiumAddress((TextView) v.findViewById(R.id.tvStadiumAddress));
        mViewHolder.setStadiumCity((TextView) v.findViewById(R.id.tvStadiumCity));
        mViewHolder.setStadiumComment((TextView) v.findViewById(R.id.tvStadiumComment));
        mViewHolder.setStadiumCountry((TextView) v.findViewById(R.id.tvStadiumCountry));
        mViewHolder.setStadiumLatitude((TextView) v.findViewById(R.id.tvStadiumLatitude));
        mViewHolder.setStadiumLongitude((TextView) v.findViewById(R.id.tvStadiumLongitude));

        mViewHolder.getStadium().setText(getActivity().getString(R.string.stadium, mStadiumObject.getmStadiumName()));
        mViewHolder.getStadiumAddress().setText(getActivity().getString(R.string.stadium_address, mStadiumObject.getmStadiumAddress()));
        mViewHolder.getStadiumCountry().setText(getActivity().getString(R.string.stadium_country, mStadiumObject.getmStadiumCountry()));
        mViewHolder.getStadiumCity().setText(getActivity().getString(R.string.stadium_city, mStadiumObject.getmStadiumCity()));
        mViewHolder.getStadiumLongitude().setText(getActivity().getString(R.string.stadium_longitude, mStadiumObject.getmStadiumLongitude()));
        mViewHolder.getStadiumLatitude().setText(getActivity().getString(R.string.stadium_latitude, mStadiumObject.getmStadiumLatitude()));
        mViewHolder.getStadiumComment().setText(getActivity().getString(R.string.stadium_comment, mStadiumObject.getmStadiumComment()));

        return v;
    }
}
