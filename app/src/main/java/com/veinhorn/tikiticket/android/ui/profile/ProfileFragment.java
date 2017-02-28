package com.veinhorn.tikiticket.android.ui.profile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.veinhorn.tikiticket.android.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by veinhorn on 27.2.17.
 */

public class ProfileFragment extends Fragment {
    @BindView(R.id.firstName)
    protected TextView firstName;

    @BindView(R.id.lastName)
    protected TextView lastName;

    private String oldTitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, parent, false);
        ButterKnife.bind(this, view);

        firstName.setText("Борис");
        lastName.setText("Корогвич");

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        oldTitle = getActivity().getTitle().toString();
        getActivity().setTitle(getString(R.string.drawer_profile));
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().setTitle(oldTitle);
    }
}
