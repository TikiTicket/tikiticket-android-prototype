package com.veinhorn.tikiticket.android;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by veinhorn on 16.1.17.
 */

public class Authenticator extends AsyncTask<String, String, String> {
    private Activity activity;

    public Authenticator(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            Thread.currentThread().sleep(3000);
        } catch (InterruptedException e) {
            Log.e("Authenticator", "Cannot sleep", e);
        }
        return "";
    }

    @Override
    protected void onPostExecute(String res) {
        activity.moveTaskToBack(false);
        activity.finish();
    }
}
