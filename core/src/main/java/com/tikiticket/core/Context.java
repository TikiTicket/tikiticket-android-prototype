package com.tikiticket.core;

import java.util.Map;

/**
 * Created by veinhorn on 14.3.17.
 * Контекст отражает состояние, в котором находится Connector и содержит в себе такие
 * данные как последняя загруженная страница, последние хедеры и текущий статус
 */
public interface Context {
    String getHtml();
    // TODO: Возможно имеет смысл сделать вместо мапы лист с парами, в котором могут диблироваться названия
    Map<String, String> getHeaders();
}
