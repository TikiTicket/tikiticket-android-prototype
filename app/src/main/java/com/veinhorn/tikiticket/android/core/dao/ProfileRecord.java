package com.veinhorn.tikiticket.android.core.dao;

import com.orm.SugarRecord;
import com.orm.dsl.Table;
import com.tikiticket.core.api.Profile;
import com.tikiticket.core.api.impl.ProfileImpl;

import java.util.List;

/**
 * Created by veinhorn on 19.3.17.
 */
@Table(name = "profile")
public class ProfileRecord extends SugarRecord {
    private Long id;

    String login;
    String firstName;
    String secondName;
    String patronymic;
    String phoneNumber;
    String country;
    String address;
    String email;
    String gender;
    String age;

    /**
     * Поиск пользователя по логину
     * @param login логин пользователя
     * @return null в случае отсутствия пользователя в базе данных
     */
    public Profile findProfile(String login) {
        List<ProfileRecord> profiles = ProfileRecord.find(ProfileRecord.class, "login = ?", login);
        if (profiles.isEmpty()) return null;
        return toProfile(profiles.get(0));
    }

    /** Сохранение учетных данных пользователя в базе данных */
    public void saveProfile(Profile profile) {
        ProfileRecord record = new ProfileRecord();
        record.login = profile.getLogin();
        record.firstName = profile.getFirstName();
        record.secondName = profile.getSecondName();
        record.patronymic = profile.getPatronymic();
        record.phoneNumber = profile.getPhoneNumber();
        record.country = profile.getCountry();
        record.address = profile.getAddress();
        record.email = profile.getEmail();
        record.gender = profile.getGender();
        record.age = profile.getAge();

        /** Возвращает id сохраненного профайла */
        record.save();
    }

    /** Удаление учетных данных пользователя из базы данных */
    public void deleteProfile() {
        deleteAll(ProfileRecord.class);
    }

    private Profile toProfile(ProfileRecord record) {
        ProfileImpl profile = new ProfileImpl();
        profile.setLogin(record.login);
        profile.setFirstName(record.firstName);
        profile.setSecondName(record.secondName);
        profile.setPatronymic(record.patronymic);
        profile.setPhoneNumber(record.phoneNumber);
        profile.setCountry(record.country);
        profile.setAddress(record.address);
        profile.setEmail(record.email);
        profile.setGender(record.gender);
        profile.setAge(record.age);

        return profile;
    }
}
