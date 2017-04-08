package com.veinhorn.tikiticket.android.ui.auth;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.tikiticket.core.Credentials;
import com.tikiticket.core.util.Util;
import com.veinhorn.tikiticket.android.R;
import com.veinhorn.tikiticket.android.TikiTicketApp;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import info.hoang8f.widget.FButton;

/**
 * Created by veinhorn on 16.1.17.
 */

public class LoginActivity extends Activity {
    @BindView(R.id.loginEditText)
    protected EditText loginEditText;

    @BindView(R.id.passwordEditText)
    protected EditText passwordEditText;

    @BindView(R.id.signInButton)
    protected FButton signInButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    /**
     * Каждый раз здесь необходимо пересоздавать коннектор на основе
     * введенных пользователем данных
     */
    @OnClick(R.id.signInButton)
    protected void signIn() {
        // TODO: Validate credentials fromStation EditText
        String login = loginEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        Credentials creds = Util.newCredentials(login, password);

        TikiTicketApp.initializeConnector(creds);
        Log.i("LoginActivity", "Trying to sign in...");
        new Authenticator(this, creds).execute();
    }

    /**
     * It's used for disabling going back to the MainActivity
     */
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
