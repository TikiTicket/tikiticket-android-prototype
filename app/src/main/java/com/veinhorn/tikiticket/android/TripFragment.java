package com.veinhorn.tikiticket.android;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by veinhorn on 19.1.17.
 */

public class TripFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.fragment_trip, parent, false);
    }

    public static TripFragment newInstance() {
        return new TripFragment();
    }
}
