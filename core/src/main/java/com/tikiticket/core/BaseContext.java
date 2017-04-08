package com.tikiticket.core;

import java.util.Map;

/**
 * Created by veinhorn on 16.3.17.
 */
public class BaseContext implements Context {
    private String html;
    private Map<String, String> headers;
    //private int status;

    public BaseContext(String html, Map<String, String> headers/*, int status*/) {
        this.html = html;
        this.headers = headers;
        // this.status = status;
    }

    @Override
    public String getHtml() {
        return html;
    }

    @Override
    public Map<String, String> getHeaders() {
        return headers;
    }

    /*@Override
    public int getStatus() {
        return status;
    }*/
}
