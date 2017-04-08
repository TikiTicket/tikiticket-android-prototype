package com.tikiticket.core;

import com.tikiticket.core.exception.TikiTicketException;

import java.util.Map;

/**
 * Created by veinhorn on 14.3.17.
 * Связующее звено между логикой ядра и различными HTTP клиентами
 */
public interface Connector {
    Context doGet(String url) throws TikiTicketException;
    Context doPost(String url, Map<String, String> params) throws TikiTicketException;
    /** Предоставляет учетные данные для аутентификации пользователя */
    Credentials getCredentials();
    boolean isAuthenticated();
}
