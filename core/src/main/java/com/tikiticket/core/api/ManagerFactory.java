package com.tikiticket.core.api;

import com.tikiticket.core.Connector;
import com.tikiticket.core.api.impl.AuthManagerImpl;
import com.tikiticket.core.api.impl.ProfileManagerImpl;

/**
 * Created by veinhorn on 19.3.17.
 * Пока что временное решение, для упрощения создания Manager'ов
 */

public class ManagerFactory {
    private Connector connector;

    public ManagerFactory(Connector connector) {
        this.connector = connector;
    }

    public ProfileManager newProfileManager() {
        return new ProfileManagerImpl(connector);
    }

    public AuthManager newAuthManager() {
        return new AuthManagerImpl(connector);
    }
}
