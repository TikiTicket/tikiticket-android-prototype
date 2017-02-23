package com.veinhorn.tikiticket.android.ui.auth;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.veinhorn.tikiticket.android.model.creds.CredentialsStorage;
import com.veinhorn.tikiticket.core.api.IAuthManager;
import com.veinhorn.tikiticket.core.api.ICredentials;
import com.veinhorn.tikiticket.core.api.ManagerFactory;
import com.veinhorn.tikiticket.core.exception.TikiTicketException;

/**
 * Created by veinhorn on 16.1.17.
 * Responsible for user authentication
 */

public class Authenticator extends AsyncTask<String, String, Boolean> {
    private static final String TAG = "Authenticator";

    private Activity activity;
    private ICredentials credentials;

    public Authenticator(Activity activity, ICredentials credentials) {
        this.activity = activity;
        this.credentials = credentials;
    }

    @Override
    protected Boolean doInBackground(String... params) {
        try {
            IAuthManager authManager = ManagerFactory.newAuthManager();
            return authManager.isValidCredentials(credentials);
        } catch (TikiTicketException e) {
            Log.e(TAG, "Cannot validate user creds", e);
        }
        return false;
    }

    @Override
    protected void onPostExecute(Boolean isValidCreds) {
        if (isValidCreds) {
            CredentialsStorage.write(activity, credentials);
            activity.moveTaskToBack(false);
            activity.finish();
            Toast.makeText(activity, "Successfully signed in", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(activity, "Cannot validate user credentials", Toast.LENGTH_SHORT).show();
        }
    }
}
