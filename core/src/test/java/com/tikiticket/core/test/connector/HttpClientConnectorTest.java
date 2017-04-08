package com.tikiticket.core.test.connector;


import com.tikiticket.core.Connector;
import com.tikiticket.core.Context;
import com.tikiticket.core.exception.TikiTicketException;
import com.tikiticket.core.test.HttpClientConnector;
import org.apache.http.impl.client.HttpClients;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by veinhorn on 13.3.17.
 */

public class HttpClientConnectorTest {
    private static final String LOGIN_PAGE_URL = "https://poezd.rw.by/wps/portal/home/login_main";

    private Connector connector;
    private Context context;

    @Before
    public void init() throws TikiTicketException {
        connector = new HttpClientConnector(HttpClients.createDefault());
        context = connector.doGet(LOGIN_PAGE_URL);
    }

    @Test
    public void testConnector() throws TikiTicketException {
        assertEquals(connector.isAuthenticated(), false);
        // TODO: Добавить проверки для кредентиалс
    }
}
