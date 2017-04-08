package com.veinhorn.tikiticket.android.core.connector;

import com.tikiticket.core.Context;
import com.tikiticket.core.Credentials;
import com.tikiticket.core.base.BaseConnector;
import com.tikiticket.core.exception.TikiTicketException;
import com.tikiticket.core.util.Util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by veinhorn on 19.3.17.
 */

public class OkHttpConnector extends BaseConnector {
    private OkHttpClient httpClient;
    private Credentials creds;

    public OkHttpConnector(OkHttpClient httpClient, Credentials creds) {
        this.httpClient = httpClient;
        this.creds = creds;
    }

    @Override
    public Context doGet(String url) throws TikiTicketException {
        Request request = new Request.Builder().url(url).build();
        try {
            Response response = httpClient.newCall(request).execute();
            return Util.newContext(response.body().string(), toMap(response.headers()));
        } catch (IOException e) {
            throw new TikiTicketException("Cannot execute GET request", e);
        }
    }

    @Override
    public Context doPost(String url, Map<String, String> params) throws TikiTicketException {
        FormBody.Builder body = new FormBody.Builder();
        for (Map.Entry<String, String> param : params.entrySet())
            body.add(param.getKey(), param.getValue());
        Request request = new Request.Builder().url(url).post(body.build()).build();
        try {
            Response response = httpClient.newCall(request).execute();
            return Util.newContext(response.body().string(), toMap(response.headers()));
        } catch (IOException e) {
            throw new TikiTicketException("Cannot execute POST request", e);
        }
    }

    @Override
    public Credentials getCredentials() {
        return creds;
    }

    private Map<String, String> toMap(Headers headers) {
        Map<String, String> mapHeaders = new HashMap<>();
        for (String name : headers.names()) {
            mapHeaders.put(name, headers.values(name).get(0));
        }
        return mapHeaders;
    }
}
