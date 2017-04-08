package com.tikiticket.core.api;

import com.tikiticket.core.exception.TikiTicketException;

/**
 * Created by veinhorn on 22.3.17.
 * Отвечает за аутентификацию пользователя
 */

public interface AuthManager {
    /**
     * Аутентифицирует пользователя и возвращает результат аутентификации
     * @return результат аутентификации
     */
    boolean authenticate() throws TikiTicketException;
}
