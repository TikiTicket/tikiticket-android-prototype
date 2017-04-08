package com.tikiticket.core.test.api;

import com.tikiticket.core.Connector;
import com.tikiticket.core.api.Profile;
import com.tikiticket.core.api.ProfileManager;
import com.tikiticket.core.api.impl.ProfileManagerImpl;
import com.tikiticket.core.exception.TikiTicketException;
import com.tikiticket.core.test.BaseTest;
import com.tikiticket.core.test.HttpClientConnector;
import org.apache.http.impl.client.HttpClients;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by veinhorn on 18.3.17.
 */
public class ProfileManagerTest extends BaseTest {
    private Connector connector;

    @Before
    public void init() {
        connector = new HttpClientConnector(HttpClients.createDefault());
    }

    @Test
    public void testProfileManager() throws TikiTicketException {
        ProfileManager profileManager = new ProfileManagerImpl(connector);
        Profile profile = profileManager.getProfile();
        System.out.println(profile.toString());
    }
}
