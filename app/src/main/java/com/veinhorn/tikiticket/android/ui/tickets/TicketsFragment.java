package com.veinhorn.tikiticket.android.ui.tickets;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.veinhorn.tikiticket.android.R;
import com.veinhorn.tikiticket.android.core.mock.TicketWrappersMock;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by veinhorn on 19.1.17.
 */

public class TicketsFragment extends Fragment {
    @BindView(R.id.ticketsRecyclerView)
    protected RecyclerView ticketsRecyclerView;

    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tickets, parent, false);
        ButterKnife.bind(this, view);

        layoutManager = new LinearLayoutManager(getActivity());
        ticketsRecyclerView.setLayoutManager(layoutManager);

        adapter = new SectionedTicketsAdapter(new TicketWrappersMock().mock());
        ticketsRecyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(getString(R.string.drawer_tickets_item));
    }

}
