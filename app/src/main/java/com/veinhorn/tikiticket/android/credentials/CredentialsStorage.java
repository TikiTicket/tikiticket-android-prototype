package com.veinhorn.tikiticket.android.credentials;

import android.content.Context;
import android.content.SharedPreferences;

import com.veinhorn.tikiticket.core.api.ICredentials;

/**
 * Created by veinhorn on 16.1.17.
 */

public class CredentialsStorage {
    private static final String LOGIN_KEY = "user.login";
    private static final String PASSWORD_KEY = "user.password";

    public static ICredentials read(Context context) {
        SharedPreferences prefs = openPreferences(context);
        String login = prefs.getString(LOGIN_KEY, null);
        String password = prefs.getString(PASSWORD_KEY, null);
    }

    private static SharedPreferences openPreferences(Context context) {
        return context.getSharedPreferences("user_creds", Context.MODE_PRIVATE);
    }
}
