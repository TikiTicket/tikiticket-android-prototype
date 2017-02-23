package com.veinhorn.tikiticket.android.model.creds;

import android.content.Context;
import android.content.SharedPreferences;

import com.veinhorn.tikiticket.core.api.ICredentials;
import com.veinhorn.tikiticket.core.util.Util;

/**
 * Created by veinhorn on 16.1.17.
 * Used for storing/editing/clearing user credentials in SharedPreferences
 */

public class CredentialsStorage {
    private static final String LOGIN_KEY = "user.login";
    private static final String PASSWORD_KEY = "user.password";

    /**
     * Reads user credentials from private storage
     * @param context
     * @return
     */
    public static ICredentials read(Context context) {
        SharedPreferences prefs = openPreferences(context);
        String login = prefs.getString(LOGIN_KEY, null);
        String password = prefs.getString(PASSWORD_KEY, null);
        if (login == null || password == null) return null;
        return Util.newCredentials(login, password);
    }

    /**
     * Write user credentials into private storage
     * @param context
     * @param creds
     */
    public static void write(Context context, ICredentials creds) {
        SharedPreferences prefs = openPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(LOGIN_KEY, creds.getLogin());
        editor.putString(PASSWORD_KEY, creds.getPassword());
        editor.apply();
    }

    private static SharedPreferences openPreferences(Context context) {
        return context.getSharedPreferences("user_creds", Context.MODE_PRIVATE);
    }
}
