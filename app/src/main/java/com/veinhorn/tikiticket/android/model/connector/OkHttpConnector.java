package com.veinhorn.tikiticket.android.model.connector;

import com.veinhorn.tikiticket.core.IConnector;
import com.veinhorn.tikiticket.core.ResponseContext;
import com.veinhorn.tikiticket.core.api.ICredentials;
import com.veinhorn.tikiticket.core.context.ContextHolder;
import com.veinhorn.tikiticket.core.util.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by veinhorn on 8.1.17.
 */

public class OkHttpConnector implements IConnector {
    private OkHttpClient httpClient;
    private ContextHolder contextHolder;

    public OkHttpConnector(OkHttpClient httpClient) {
        this.httpClient = httpClient;
        contextHolder = new ContextHolder();
    }

    @Override
    public ResponseContext doPost(String url, List<Pair> pairs) throws IOException {
        FormBody.Builder body = new FormBody.Builder();
        // MultipartBody.Builder body = new MultipartBody.Builder().setType(MultipartBody.FORM);
        for (Pair pair : pairs) body.add(pair.getKey(), pair.getValue());

        Request request = new Request.Builder().url(url).post(body.build()).build();
        final Response response = httpClient.newCall(request).execute();
        return new ResponseContext() {
            @Override
            public String getHtml() throws IOException {
                return response.body().string();
            }

            @Override
            public List<Pair> getHeaders() {
                Headers headers = response.headers();
                List<Pair> pairs = new ArrayList<>();
                for (String name : headers.names()) {
                    Pair pair = new Pair(name, headers.values(name).get(0));
                    pairs.add(pair);
                }
                return pairs;
            }
        };
    }

    @Override
    public ResponseContext doGet(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        final Response response = httpClient.newCall(request).execute();
        return new ResponseContext() {
            @Override
            public String getHtml() throws IOException {
                return response.body().string();
            }

            @Override
            public List<Pair> getHeaders() {
                Headers headers = response.headers();
                List<Pair> pairs = new ArrayList<>();
                for (String name : headers.names()) {
                    Pair pair = new Pair(name, headers.values(name).get(0));
                    pairs.add(pair);
                }
                return pairs;
            }
        };
    }

    @Override
    public ICredentials getCredentials() {
        return new ICredentials() {
            @Override public String getLogin() {
                return "MCKLAS";
            }

            @Override public String getPassword() {
                return "3k2l0a0s";
            }
        };
    }

    @Override
    public ContextHolder getContextHolder() {
        return contextHolder;
    }
}
