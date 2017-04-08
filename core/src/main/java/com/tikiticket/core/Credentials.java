package com.tikiticket.core;

/**
 * Created by veinhorn on 18.3.17.
 * Этот интерфейс предоставляет данные для аутентификации пользователя
 */
public interface Credentials {
    String getLogin();
    String getPassword();
}
