package com.tikiticket.core.api.impl;

import com.tikiticket.core.Connector;
import com.tikiticket.core.auth.AuthManager;

/**
 * Created by veinhorn on 18.3.17.
 * Прослойка между оеализациями менеджером и менеджером аутентификации, которая возможно будет
 * включать в себя некую общую логику для всех менеджеров.
 */
abstract class BaseManager extends AuthManager {
    BaseManager(Connector connector) {
        super(connector);
    }
}
