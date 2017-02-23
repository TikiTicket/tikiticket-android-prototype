package com.veinhorn.tikiticket.android.model;

import com.veinhorn.tikiticket.core.api.IOrder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by veinhorn on 7.2.17.
 */
public class Ticket {
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";
    private IOrder order;

    public Ticket(IOrder order) {
        this.order = order;
    }

    public String getFromStation() {
        return order.getDispatchStation();
    }

    public String getToStation() {
        return order.getDestinationStation();
    }

    public Date getOrderDate() {
        try {
            return new SimpleDateFormat(DATE_FORMAT).parse(order.getOrderDate());
        } catch (ParseException e) {
            return null;
        }
    }

    public Date getTripDate() {
        try {
            return new SimpleDateFormat(DATE_FORMAT).parse(order.getTripDate());
        } catch (ParseException e) {
            return null;
        }
    }
}
