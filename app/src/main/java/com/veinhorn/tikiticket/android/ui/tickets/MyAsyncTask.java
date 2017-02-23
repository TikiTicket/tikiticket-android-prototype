package com.veinhorn.tikiticket.android.ui.tickets;

import android.os.AsyncTask;

import com.veinhorn.tikiticket.android.TikiTicketApp;
import com.veinhorn.tikiticket.core.api.IOrder;
import com.veinhorn.tikiticket.core.api.IOrderManager;
import com.veinhorn.tikiticket.core.exception.TikiTicketException;
import com.veinhorn.tikiticket.core.order.OrderManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by veinhorn on 8.1.17.
 */

public class MyAsyncTask extends AsyncTask<String, Void, List<IOrder>> {
    @Override
    protected List<IOrder> doInBackground(String... params) {
        IOrderManager orderManager = new OrderManager(TikiTicketApp.connector);
        try {
            List<IOrder> orders = orderManager.retrieveCurrentTrips();
            String ok = "ok";
            return orders;
        } catch (TikiTicketException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
