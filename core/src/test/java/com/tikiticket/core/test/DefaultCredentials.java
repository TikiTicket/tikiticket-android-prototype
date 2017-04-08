package com.tikiticket.core.test;

import com.tikiticket.core.Credentials;

/**
 * Created by veinhorn on 19.3.17.
 */
public class DefaultCredentials implements Credentials {
    @Override
    public String getLogin() {
        return "MCKLAS";
    }

    @Override
    public String getPassword() {
        return "3k2l0a0s";
    }
}
