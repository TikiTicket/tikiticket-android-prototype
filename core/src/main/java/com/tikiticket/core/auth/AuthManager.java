package com.tikiticket.core.auth;

import com.tikiticket.core.Connector;

/**
 * Created by veinhorn on 16.3.17.
 * Реализация Manager'a, упрощающая аутентификацию пользователя
 */
public abstract class AuthManager {
    protected Connector connector;

    public AuthManager(Connector connector) {
        this.connector = new AuthConnector(connector);
    }
}
