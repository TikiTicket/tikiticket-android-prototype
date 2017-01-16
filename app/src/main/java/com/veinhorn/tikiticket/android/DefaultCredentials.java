package com.veinhorn.tikiticket.android;

import android.content.Context;
import android.util.Log;

import com.veinhorn.tikiticket.core.api.ICredentials;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by veinhorn on 16.1.17.
 */

public class DefaultCredentials implements ICredentials {
    private static final String TAG = "DefaultCredentials";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";

    private Properties props;

    public DefaultCredentials(Context context) {
        props = new Properties();
        try {
            props.load(context.getResources().openRawResource(R.raw.creds));
        } catch (IOException e) {
            Log.e(TAG, "Cannot open default credentials file", e);
        }
    }

    @Override
    public String getLogin() {
        return props.getProperty(LOGIN);
    }

    @Override
    public String getPassword() {
        return props.getProperty(PASSWORD);
    }
}
