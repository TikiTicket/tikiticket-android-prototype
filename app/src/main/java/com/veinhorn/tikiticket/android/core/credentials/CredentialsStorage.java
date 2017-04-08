package com.veinhorn.tikiticket.android.core.credentials;

import android.content.Context;
import android.content.SharedPreferences;

import com.tikiticket.core.Credentials;
import com.tikiticket.core.util.Util;

/**
 * Created by veinhorn on 16.1.17.
 * Обеспечивает возможность сохранения/считывания/очистки персональных данных пользователя,
 * необходимых для аутентификации пользователя
 */

public class CredentialsStorage {
    private static final String CREDS_NAME = "user_creds";

    private static final String LOGIN_KEY = "user.login";
    private static final String PASSWORD_KEY = "user.password";

    /** Получение логина и пароля из хранилища */
    public static Credentials read(Context context) {
        SharedPreferences prefs = openPreferences(context);
        String login = prefs.getString(LOGIN_KEY, null);
        String password = prefs.getString(PASSWORD_KEY, null);
        if (login == null || password == null) return null;
        return Util.newCredentials(login, password);
    }

    /** Сохранение логина и пароля в хранилище */
    public static void write(Context context, Credentials creds) {
        SharedPreferences prefs = openPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(LOGIN_KEY, creds.getLogin());
        editor.putString(PASSWORD_KEY, creds.getPassword());
        editor.apply();
    }

    /** Удаление логина и пароля пользователя из хранилища */
    public static void clean(Context context) {
        SharedPreferences prefs = openPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.apply();
    }

    private static SharedPreferences openPreferences(Context context) {
        return context.getSharedPreferences(CREDS_NAME, Context.MODE_PRIVATE);
    }
}
