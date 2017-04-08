package com.veinhorn.tikiticket.android;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.orm.SugarApp;
import com.tikiticket.core.Connector;
import com.tikiticket.core.Credentials;
import com.tikiticket.core.api.ManagerFactory;
import com.veinhorn.tikiticket.android.core.connector.OkHttpConnector;

import okhttp3.OkHttpClient;

/**
 * Created by veinhorn on 8.1.17.
 * Базовая конфигурация Connector'a для tikiticket-core на основе OkHttp + CookieCache
 * Так же тут конфигурируется Sugar ORM for Android
 */

public class TikiTicketApp extends SugarApp {
    /** Эти объекты инициализируются определенным образом, возможно имеет смысл вынести
     *  их в singleton. При создании application они не инициализируются */
    private static OkHttpClient httpClient;

    public static Connector connector;
    public static ManagerFactory managerFactory;
    /** */

    @Override
    public void onCreate() {
        super.onCreate();
        /** Used only for debugging purposes */
        Stetho.initialize(Stetho.newInitializerBuilder(this)
                                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                                .build());

        ClearableCookieJar cookieJar = new PersistentCookieJar(
                new SetCookieCache(), new SharedPrefsCookiePersistor(getBaseContext()));

        httpClient = new OkHttpClient.Builder()
                                .addNetworkInterceptor(new StethoInterceptor()) // for network debugging
                                .cookieJar(cookieJar)
                                .followRedirects(false)
                                .build();
    }

    public static void initializeConnector(Credentials creds) {
        connector = new OkHttpConnector(httpClient, creds);
        managerFactory = new ManagerFactory(connector);
    }
}
