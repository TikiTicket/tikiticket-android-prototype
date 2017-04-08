package com.veinhorn.tikiticket.android.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.mikepenz.materialdrawer.Drawer;
import com.tikiticket.core.Credentials;
import com.veinhorn.tikiticket.android.R;
import com.veinhorn.tikiticket.android.TikiTicketApp;
import com.veinhorn.tikiticket.android.core.credentials.CredentialsStorage;
import com.veinhorn.tikiticket.android.ui.auth.LoginActivity;
import com.veinhorn.tikiticket.android.ui.drawer.NavigationDrawer;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.toolbar) protected Toolbar toolbar;

    private Drawer drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        /** If we cannot read credentials fromStation storage, we should start login activity */
        /** Если в хранилище есть данные пол-ля, это значит что они уже валидные,
         *  так как невалидные данные туда попасть не могут  */
        Credentials creds = CredentialsStorage.read(this);
        if (creds == null) {
            startActivity(new Intent(this, LoginActivity.class));
        } else {
            TikiTicketApp.initializeConnector(creds);
        }

        drawer = new NavigationDrawer(this, toolbar)
                .withCreds(creds)
                .withDefaultFragment()
                .build();
    }

    @Override
    protected void onResume() {
        super.onResume();
        /** Здесь необходимо обновить логин пользователя в профайле nav drawer'a после
         * успешной аутентификации */
        Credentials credentials = CredentialsStorage.read(this);
    }

}
