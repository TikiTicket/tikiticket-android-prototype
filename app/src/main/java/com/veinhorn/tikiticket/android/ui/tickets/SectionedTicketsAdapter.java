package com.veinhorn.tikiticket.android.ui.tickets;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.afollestad.sectionedrecyclerview.SectionedRecyclerViewAdapter;
import com.veinhorn.tikiticket.android.R;
import com.veinhorn.tikiticket.android.core.TicketWrapper;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by veinhorn on 6.4.17.
 * Используется для разделения списка билетов на подразделы
 * "Предстоящие" и "Совершённые"
 */

public class SectionedTicketsAdapter extends SectionedRecyclerViewAdapter<SectionedTicketsAdapter.ViewHolder> {
    private static final int CURRENT_SECTION = 0;
    private static final int OLD_SECTION = 1;
    private static final int SECTION_COUNT = 2;

    private int currentCount;
    private int oldCount;

    private List<TicketWrapper> tickets;

    public SectionedTicketsAdapter(List<TicketWrapper> tickets) {
        this.tickets = tickets;

        /** Сортировка билетов, сначала "предстоящие" потом "совершённые"
         *  (для корректного разбития по секциям) */
        Collections.sort(this.tickets, new TicketsComparator());
        /** Подсчет количества элементов в секциях при создании адаптера
         *  Подсчет производится единожды, для оптимизации */
        for (TicketWrapper ticket : tickets) {
            if (ticket.isActive()) currentCount++;
            else                   oldCount++;
        }
    }

    @Override
    public int getSectionCount() {
        return SECTION_COUNT;
    }

    @Override
    public int getItemCount(int section) {
        switch (section) {
            case CURRENT_SECTION: return currentCount;
            case OLD_SECTION:     return oldCount;
            default:              return 0;
        }
    }

    // TODO: Заменить захардкоженые строки строками из ресурсов
    @Override
    public void onBindHeaderViewHolder(ViewHolder holder, int section) {
        if (section == CURRENT_SECTION) holder.headerTitle.setText("Предстоящие");
        else if (section == OLD_SECTION) holder.headerTitle.setText("Совершенные");
    }

    // TODO: Пофиксить возможный баг с NullPointerException#setText()
    @Override
    public void onBindViewHolder(ViewHolder holder, int section, int relativePosition, int absolutePosition) {
        holder.route.setText(tickets.get(absolutePosition).createRoute());
        holder.cost.setText(tickets.get(absolutePosition).getCost().toString());
        holder.seats.setText(tickets.get(absolutePosition).getNumberOfSeats().toString());
        holder.dispatchDate.setText(tickets.get(absolutePosition).getFormattedDispatchDate());
        holder.trainNumber.setText(tickets.get(absolutePosition).getTrainNumber());

        // TODO: Подобрать более подходящие цвета для удобочитаемости
        if (tickets.get(absolutePosition).isActive()) {
            holder.trainNumber.setTextColor(Color.GREEN);
        } else {
            holder.trainNumber.setTextColor(Color.RED);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                                  .inflate(selectHeaderOrLayout(viewType), parent, false);
        return new ViewHolder(view);
    }

    private int selectHeaderOrLayout(int viewType) {
        return viewType == VIEW_TYPE_HEADER ? R.layout.item_ticket_header : R.layout.item_ticket;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @Nullable
        @BindView(R.id.headerTitle)
        public TextView headerTitle;

        @Nullable
        @BindView(R.id.route)
        public TextView route;

        @Nullable
        @BindView(R.id.cost)
        public TextView cost;

        @Nullable
        @BindView(R.id.seats)
        public TextView seats;

        @Nullable
        @BindView(R.id.dispatchDate)
        public TextView dispatchDate;

        @Nullable
        @BindView(R.id.trainNumber)
        public TextView trainNumber;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    private class TicketsComparator implements Comparator<TicketWrapper> {
        @Override
        public int compare(TicketWrapper t1, TicketWrapper t2) {
            return Boolean.valueOf(t2.isActive()).compareTo(t1.isActive());
        }
    }
}
