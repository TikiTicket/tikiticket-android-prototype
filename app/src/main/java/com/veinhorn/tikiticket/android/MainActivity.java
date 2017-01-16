package com.veinhorn.tikiticket.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.veinhorn.tikiticket.android.credentials.CredentialsStorage;
import com.veinhorn.tikiticket.core.api.ICredentials;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.toolbar) protected Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        /** If we cannot read credentials from storage, we should start login activity */
        ICredentials creds = CredentialsStorage.read(this);
        if (creds == null) {
            startActivity(new Intent(this, LoginActivity.class));
        }

        new NavigationDrawer(this, toolbar).withCreds(creds).build();
    }
}
