package com.tikiticket.core.auth;

import com.tikiticket.core.*;
import com.tikiticket.core.exception.TikiTicketException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by veinhorn on 18.3.17.
 * Реализация Connector'а, которая проксирует запросы на сервер,
 * и в случае если timeout истек, проводит повторную аутентификацию пользователя
 */
public class AuthConnector implements Connector {
    private static final String LOGIN_PAGE_URL = "https://poezd.rw.by/wps/portal/home/login_main";

    /** Таймаут в секундах, после которого сервер перестает принимать пользователя
     *  за аутентифицированного. На rw.by примерно 180 секудн */
    private static final long TIMEOUT = 120;

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";

    /** Время последней успешной аутентификации пользователя */
    private long authTime;

    private long timeout;

    private Connector connector;

    public AuthConnector(Connector connector) {
        this.connector = connector;
        timeout = TIMEOUT;
    }

    public AuthConnector(Connector connector, long timeout) {
        this.connector = connector;
        this.timeout = timeout;
    }

    @Override
    public Context doGet(String url) throws TikiTicketException {
        Context authCtx = authenticate();
        if (url == null) return authCtx;
        return connector.doGet(url);
    }

    @Override
    public Context doPost(String url, Map<String, String> params) throws TikiTicketException {
        Context authCtx = authenticate();
        if (url == null) return authCtx;
        return connector.doPost(url, params);
    }

    @Override
    public Credentials getCredentials() {
        return connector.getCredentials();
    }

    @Override
    public boolean isAuthenticated() {
        return authTime != 0 && ((System.currentTimeMillis() - authTime) / 1000L < timeout);
    }

    /** Если в аутентификации нет необходимости, возвращает null */
    private Context authenticate() throws TikiTicketException {
        if (!isAuthenticated()) {
            Context ctx = connector.doGet(LOGIN_PAGE_URL);
            String authUrl = new LoginPageParser().parse(ctx);
            Context authCtx = connector.doPost(authUrl, toParams(getCredentials()));

            /** Обновление времени последней успешной аутентификации */
            authTime = System.currentTimeMillis();

            return authCtx;
        }
        return null;
    }

    // TODO: Возможно имеет смысл вынести как утильный метод
    private Map<String, String> toParams(Credentials creds) {
        Map<String, String> params = new HashMap<>();
        params.put(LOGIN, creds.getLogin());
        params.put(PASSWORD, creds.getPassword());
        return params;
    }

    // TODO: Возможно имеет смысл вынести как утильный метод
    private String createUrl(String relativeUrl) {
        return Constants.BASE_URL + relativeUrl;
    }

    private class LoginPageParser implements PageParser<String> {
        @Override
        public String parse(Context ctx) throws TikiTicketException {
            try {
                return parse(Jsoup.parse(ctx.getHtml()));
            } catch (Exception e) {
                throw new TikiTicketException("Cannot fetch auth url on login page", e);
            }
        }

        private String parse(Document document) {
            return createUrl(document.getElementById("login").attr("action"));
        }
    }
}
