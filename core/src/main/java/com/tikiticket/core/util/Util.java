package com.tikiticket.core.util;

import com.tikiticket.core.Credentials;

/**
 * Created by veinhorn on 22.3.17.
 */
public class Util {
    /** Создание кредентиалс на основе логина и пароля */
    public static Credentials newCredentials(final String login, final String password) {
        return new Credentials() {
            @Override
            public String getLogin() {
                return login;
            }

            @Override
            public String getPassword() {
                return password;
            }
        };
    }
}
