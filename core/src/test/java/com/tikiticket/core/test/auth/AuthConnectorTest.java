package com.tikiticket.core.test.auth;

import com.tikiticket.core.Context;
import com.tikiticket.core.auth.AuthConnector;
import com.tikiticket.core.exception.TikiTicketException;
import com.tikiticket.core.test.BaseTest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by veinhorn on 16.3.17.
 */
public class AuthConnectorTest extends BaseTest {
    private static final String BUY_URL = "https://poezd.rw.by/wps/myportal/home/rp/buyTicket";

    private CloseableHttpClient httpClient;
    private AuthConnector connector;

    @Before
    public void init() {
        httpClient = HttpClients.createDefault();
        connector = newAuthConnector(httpClient, null);
    }

    @Test
    public void testAuthConnector() throws TikiTicketException {
        Context authCtx = connector.doGet(null);
        assertEquals(connector.isAuthenticated(), true);
        assertEquals(authCtx.getHtml(), "");
        assertEquals(authCtx.getHeaders().get("Location"), BUY_URL);
    }

    @Test
    public void testAuthConnectorTimeout() throws TikiTicketException, InterruptedException {
        AuthConnector newConnector = newAuthConnector(httpClient, 2L);
        newConnector.doGet(null);
        Thread.sleep(3000);
        assertEquals(newConnector.isAuthenticated(), false);
    }
}
