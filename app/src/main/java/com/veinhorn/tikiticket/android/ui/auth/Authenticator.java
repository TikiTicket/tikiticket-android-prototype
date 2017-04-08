package com.veinhorn.tikiticket.android.ui.auth;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.tikiticket.core.Credentials;
import com.tikiticket.core.api.AuthManager;
import com.tikiticket.core.exception.TikiTicketException;
import com.veinhorn.tikiticket.android.TikiTicketApp;
import com.veinhorn.tikiticket.android.core.credentials.CredentialsStorage;

/**
 * Created by veinhorn on 16.1.17.
 * Responsible for user authentication
 */

public class Authenticator extends AsyncTask<String, String, Boolean> {
    private static final String TAG = "Authenticator";

    private Activity activity;
    private Credentials credentials;

    public Authenticator(Activity activity, Credentials credentials) {
        this.activity = activity;
        this.credentials = credentials;
    }

    /** Аутентификация пользователя */
    @Override
    protected Boolean doInBackground(String... params) {
        AuthManager authManager = TikiTicketApp.managerFactory.newAuthManager();
        /** Если по какой-то причине аутентификация не прошла, вернуть false */
        try {
            return authManager.authenticate();
        } catch (TikiTicketException e) {
            Log.e("Authenticator", "Cannot authenticate user", e);
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean isAuthenticated) {
        /** В случае успешной аутентификации необходимо обновить логин и пароль в хранилище */
        if (isAuthenticated) {
            CredentialsStorage.write(activity, credentials);
            activity.moveTaskToBack(false);
            activity.finish();
            Toast.makeText(activity, "Successfully signed in", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(activity, "Cannot validate user credentials", Toast.LENGTH_SHORT).show();
        }
    }
}
