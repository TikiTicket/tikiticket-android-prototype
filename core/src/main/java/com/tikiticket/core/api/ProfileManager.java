package com.tikiticket.core.api;

import com.tikiticket.core.exception.TikiTicketException;

/**
 * Created by veinhorn on 18.3.17.
 */
public interface ProfileManager {
    Profile getProfile() throws TikiTicketException;
}
