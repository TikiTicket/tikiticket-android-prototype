package com.veinhorn.tikiticket.android;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by veinhorn on 16.1.17.
 * Used for user authentication
 */

public class LoginActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        new Authenticator(this).execute();
    }

    /**
     * It's used for disabling going back to the MainActivity
     */
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
