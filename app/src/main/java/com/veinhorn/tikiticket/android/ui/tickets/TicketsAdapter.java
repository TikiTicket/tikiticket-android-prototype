package com.veinhorn.tikiticket.android.ui.tickets;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.veinhorn.tikiticket.android.R;
import com.veinhorn.tikiticket.android.core.TicketWrapper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by veinhorn on 1.4.17.
 */
@Deprecated
public class TicketsAdapter extends RecyclerView.Adapter<TicketsAdapter.ViewHolder> {
    private List<TicketWrapper> tickets;

    public TicketsAdapter(List<TicketWrapper> tickets) {
        this.tickets = tickets;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.route)
        public TextView route;

        @BindView(R.id.cost)
        public TextView cost;

        @BindView(R.id.seats)
        public TextView seats;

        @BindView(R.id.dispatchDate)
        public TextView dispatchDate;

        @BindView(R.id.trainNumber)
        public TextView trainNumber;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ticket, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    // TODO: Добавить валидацию значений, проверку на null и тд.
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.route.setText(tickets.get(position).createRoute());
        viewHolder.cost.setText(tickets.get(position).getCost().toString());
        viewHolder.seats.setText(tickets.get(position).getNumberOfSeats().toString());
        viewHolder.dispatchDate.setText(tickets.get(position).getFormattedDispatchDate());
        viewHolder.trainNumber.setText(tickets.get(position).getTrainNumber());

        // TODO: Подобрать более подходящие цвета для удобочитаемости
        if (tickets.get(position).isActive()) {
            viewHolder.trainNumber.setTextColor(Color.GREEN);
        } else {
            viewHolder.trainNumber.setTextColor(Color.RED);
        }
    }

    @Override
    public int getItemCount() {
        return tickets.size();
    }
}
