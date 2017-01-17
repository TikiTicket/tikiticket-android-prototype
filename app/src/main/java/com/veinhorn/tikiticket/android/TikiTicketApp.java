package com.veinhorn.tikiticket.android;

import android.app.Application;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.veinhorn.tikiticket.core.IConnector;
import com.veinhorn.tikiticket.core.api.ManagerFactory;

import okhttp3.OkHttpClient;

/**
 * Created by veinhorn on 8.1.17.
 * Initializes HTTP client layer plus IConnector
 */

public class TikiTicketApp extends Application {
    private static OkHttpClient httpClient;
    public static IConnector connector;

    @Override
    public void onCreate() {
        super.onCreate();
        ClearableCookieJar cookieJar = new PersistentCookieJar(
                new SetCookieCache(), new SharedPrefsCookiePersistor(getBaseContext()));
        httpClient = new OkHttpClient.Builder().cookieJar(cookieJar).followRedirects(false).build();
        connector = new OkHttpConnector(httpClient);
        ManagerFactory.init(connector);
    }
}
