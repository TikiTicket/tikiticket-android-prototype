package com.veinhorn.tikiticket.android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by veinhorn on 16.1.17.
 * Used for user authentication
 */

public class LoginActivity extends Activity {
    @BindView(R.id.loginEditText)
    protected EditText loginEditText;

    @BindView(R.id.passwordEditText)
    protected EditText passwordEditText;

    @BindView(R.id.signInButton)
    protected Button signInButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.signInButton)
    protected void signIn() {
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
