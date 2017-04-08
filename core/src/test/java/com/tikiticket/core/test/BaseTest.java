package com.tikiticket.core.test;

import com.tikiticket.core.Connector;
import com.tikiticket.core.auth.AuthConnector;
import org.apache.http.impl.client.CloseableHttpClient;

/**
 * Created by veinhorn on 18.3.17.
 */
public class BaseTest {
    protected AuthConnector newAuthConnector(CloseableHttpClient client, Long timeout) {
        Connector con = new HttpClientConnector(client);
        return timeout == null ? new AuthConnector(con) : new AuthConnector(con, timeout);
    }
}
