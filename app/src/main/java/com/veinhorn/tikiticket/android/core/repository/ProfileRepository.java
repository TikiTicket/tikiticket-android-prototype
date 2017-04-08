package com.veinhorn.tikiticket.android.core.repository;

import android.util.Log;

import com.tikiticket.core.api.Profile;
import com.tikiticket.core.api.ProfileManager;
import com.tikiticket.core.exception.TikiTicketException;
import com.veinhorn.tikiticket.android.TikiTicketApp;
import com.veinhorn.tikiticket.android.core.dao.ProfileRecord;

/**
 * Created by veinhorn on 19.3.17.
 * Является по своей сути прокси, для кэширования запросов на сервер
 * Подумать об абстракции REST/rw server/DB
 */

public class ProfileRepository {
    private ProfileManager profileManager;
    private ProfileRecord profileRecord;

    /** Возможность использовать DI вместо явного создания через глобальную переменную */
    public ProfileRepository() {
        profileManager = TikiTicketApp.managerFactory.newProfileManager();
        profileRecord = new ProfileRecord();
    }

    /**
     * Получение учетных данных пользователя от сервиса с последующей сквозной записью в базу
     * данных в случае их отсутствия
     * @param login
     * @return
     */
    public Profile findProfile(String login) {
        try {
            Profile profile = profileRecord.findProfile(login);
            if (profile == null) {
                profile = profileManager.getProfile();
                profileRecord.saveProfile(profile);
            }
            return profile;
        } catch (TikiTicketException e) {
            Log.e("ProfileRepository", "Cannot load profile data", e);
            return null;
        }
    }

    /** Удаление учетных данных пользователя */
    public void deleteProfile() {
        profileRecord.deleteProfile();
    }
}
