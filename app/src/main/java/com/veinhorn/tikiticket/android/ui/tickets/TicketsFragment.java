package com.veinhorn.tikiticket.android.ui.tickets;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.veinhorn.tikiticket.android.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by veinhorn on 19.1.17.
 */

public class TicketFragment extends Fragment {
    @BindView(R.id.tripListView)
    ListView tripListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tickets, parent, false);
        ButterKnife.bind(this, view);

        tripListView.setAdapter(new TicketAdapterMock(getActivity()));

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(getString(R.string.drawer_tickets_item));
    }

}
