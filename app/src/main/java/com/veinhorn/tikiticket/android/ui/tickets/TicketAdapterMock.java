package com.veinhorn.tikiticket.android.ui.tickets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.veinhorn.tikiticket.android.R;
import com.veinhorn.tikiticket.android.model.Ticket;
import com.veinhorn.tikiticket.core.order.Order;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by veinhorn on 7.2.17.
 * Mock adapter that's used fot imitating ticket list
 */

public class TicketAdapterMock extends ArrayAdapter<Ticket> {
    private LayoutInflater inflater;

    private static final List<Ticket> TICKETS = Arrays.asList(
            newOrder("Minsk", "Zhabinka", "1994-02-18 10:24", "1994-02-18 23:24"),
            newOrder("Minsk", "Zhabinka", "1993-01-28 12:45", "1993-01-28 14:45")
    );

    private static Ticket newOrder(String from, String to, String orderDate, String tripDate) {
        Order order = new Order();
        order.setDispatchStation(from);
        order.setDestinationStation(to);
        order.setOrderDate(orderDate);
        order.setTripDate(tripDate);
        return new Ticket(order);
    }

    public TicketAdapterMock(Context context) {
        super(context, R.layout.item_ticket, TICKETS);
        inflater = LayoutInflater.from(context);
    }

    static class ViewHolder {
        @BindView(R.id.fromStation) TextView fromStation;
        @BindView(R.id.toStation) TextView toStation;

        @BindView(R.id.orderDate) TextView orderDate;
        @BindView(R.id.orderTime) TextView orderTime;

        @BindView(R.id.tripDate) TextView tripDate;
        @BindView(R.id.tripTime) TextView tripTime;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Ticket ticket = getItem(position);
        ViewHolder viewHolder;

        if (convertView != null) {
            viewHolder = (ViewHolder) convertView.getTag();
        } else {
            convertView = inflater.inflate(R.layout.item_ticket, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }

        viewHolder.fromStation.setText(ticket.getFromStation());
        viewHolder.toStation.setText(ticket.getToStation());
        viewHolder.orderDate.setText(formatDate(ticket.getOrderDate()));
        viewHolder.orderTime.setText(formatTime(ticket.getOrderDate()));
        viewHolder.tripDate.setText(formatDate(ticket.getTripDate()));
        viewHolder.tripTime.setText(formatTime(ticket.getTripDate()));

        return convertView;
    }

    private String formatDate(Date date) {
        if (date == null) return "";
        Calendar c = toCalendar(date);
        return "" + c.get(Calendar.DAY_OF_MONTH) + " month";
    }

    private String formatTime(Date date) {
        if (date == null) return "";
        Calendar c = toCalendar(date);
        return c.get(Calendar.HOUR) + ":" + c.get(Calendar.MINUTE);
    }

    private Calendar toCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }
}
