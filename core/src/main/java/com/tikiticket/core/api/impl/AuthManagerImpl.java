package com.tikiticket.core.api.impl;

import com.tikiticket.core.Connector;
import com.tikiticket.core.Context;
import com.tikiticket.core.api.AuthManager;
import com.tikiticket.core.exception.TikiTicketException;

/**
 * Created by veinhorn on 22.3.17.
 * Аутентификация пользователя по некоторым критериям, таким как совпадение Location ссылки и
 * пустое тело респонса
 */

public class AuthManagerImpl extends BaseManager implements AuthManager {
    private static final String BUY_URL = "https://poezd.rw.by/wps/myportal/home/rp/buyTicket";

    public AuthManagerImpl(Connector connector) {
        super(connector);
    }

    @Override
    public boolean authenticate() throws TikiTicketException {
        Context authCtx = connector.doGet(null);
        return authCtx.getHeaders().get("Location").equals(BUY_URL) && authCtx.getHtml().equals("");
    }
}
