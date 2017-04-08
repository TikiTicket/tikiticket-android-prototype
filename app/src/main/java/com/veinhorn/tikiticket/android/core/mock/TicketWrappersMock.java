package com.veinhorn.tikiticket.android.core.mock;

import com.veinhorn.tikiticket.android.core.TicketWrapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by veinhorn on 6.4.17.
 * Используется для создания списка тестовых билетов при
 * тестировании раздела "Мои билеты"
 */

public class TicketWrappersMock implements ListMock<TicketWrapper> {
    @Override
    public List<TicketWrapper> mock() {
        List<TicketWrapper> tickets = new ArrayList<>();

        TicketWrapper ticket = new TicketWrapper();
        ticket.setFromStation("Жабинка");
        ticket.setToStation("Минск");
        ticket.setCost("10.97");
        ticket.setNumberOfSeats(1);
        ticket.setDispatchDate(new Date());
        ticket.setTrainNumber("651БА");
        ticket.setActive(true);

        TicketWrapper ticket2 = new TicketWrapper();
        ticket2.setFromStation("Москва");
        ticket2.setToStation("Минск");
        ticket2.setCost("22.56");
        ticket2.setNumberOfSeats(3);
        ticket2.setDispatchDate(new Date());
        ticket2.setTrainNumber("450СА");
        ticket2.setActive(false);

        TicketWrapper ticket3 = new TicketWrapper();
        ticket3.setFromStation("Брест");
        ticket3.setToStation("Орша");
        ticket3.setCost("11.23");
        ticket3.setNumberOfSeats(4);
        ticket3.setDispatchDate(new Date());
        ticket3.setTrainNumber("678БА");
        ticket3.setActive(false);

        TicketWrapper ticket4 = new TicketWrapper();
        ticket4.setFromStation("Минск");
        ticket4.setToStation("Жабинка");
        ticket4.setCost("6.54");
        ticket4.setNumberOfSeats(1);
        ticket4.setDispatchDate(new Date());
        ticket4.setTrainNumber("651БА");
        ticket4.setActive(true);

        tickets.add(ticket);
        tickets.add(ticket2);
        tickets.add(ticket3);
        tickets.add(ticket4);
        return tickets;
    }
}
