package com.tikiticket.core.test;

import com.tikiticket.core.BaseContext;
import com.tikiticket.core.Connector;
import com.tikiticket.core.Context;
import com.tikiticket.core.Credentials;
import com.tikiticket.core.exception.TikiTicketException;

import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by veinhorn on 14.3.17.
 * Реализация Connector'a на базе Apache HttpClient
 */
public class HttpClientConnector implements Connector {
    private CloseableHttpClient httpClient;

    public HttpClientConnector(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public Context doGet(String url) throws TikiTicketException {
        HttpGet httpGet = new HttpGet(url);
        try(CloseableHttpResponse response = httpClient.execute(httpGet)) {
            String res = EntityUtils.toString(response.getEntity());
            return new BaseContext(res, toMap(response.getAllHeaders()));
        } catch (IOException e) {
            throw new TikiTicketException("Cannot execute GET request to the server", e);
        }
    }

    @Override
    public Context doPost(String url, Map<String, String> params) throws TikiTicketException {
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> pairs = new ArrayList<>();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            pairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(pairs));
            CloseableHttpResponse response = httpClient.execute(httpPost);
            String res = EntityUtils.toString(response.getEntity());
            return new BaseContext(res, toMap(response.getAllHeaders()));
        } catch (IOException e) {
            throw new TikiTicketException("Cannot execute POST request to the server", e);
        }
    }

    @Override
    public Credentials getCredentials() {
        return new DefaultCredentials();
    }

    @Override
    public boolean isAuthenticated() {
        return false;
    }

    private Map<String, String> toMap(Header[] headers) {
        Map<String, String> mapHeaders = new HashMap<>();
        for (Header header : headers) mapHeaders.put(header.getName(), header.getValue());
        return mapHeaders;
    }
}
