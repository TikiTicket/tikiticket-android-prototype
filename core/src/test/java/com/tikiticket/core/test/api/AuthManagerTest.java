package com.tikiticket.core.test.api;

import com.tikiticket.core.Connector;
import com.tikiticket.core.api.AuthManager;
import com.tikiticket.core.api.impl.AuthManagerImpl;
import com.tikiticket.core.exception.TikiTicketException;
import com.tikiticket.core.test.BaseTest;
import com.tikiticket.core.test.HttpClientConnector;
import com.tikiticket.core.util.Util;
import org.apache.http.impl.client.HttpClients;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by veinhorn on 22.3.17.
 */
public class AuthManagerTest extends BaseTest {
    private Connector connector;

    @Before
    public void init() {
        connector = new HttpClientConnector(HttpClients.createDefault());
    }

    @Test
    public void testAuthManager() throws TikiTicketException {

        AuthManager authManager = new AuthManagerImpl(connector);
        System.out.println(authManager.authenticate(Util.newCredentials("MCKLAS123", "3k2l0a0s123")));
    }
}
