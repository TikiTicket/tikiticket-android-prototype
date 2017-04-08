package com.veinhorn.tikiticket.android.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hannesdorfmann.mosby3.mvp.MvpFragment;
import com.tikiticket.core.Credentials;
import com.tikiticket.core.api.Profile;
import com.veinhorn.tikiticket.android.R;
import com.veinhorn.tikiticket.android.core.credentials.CredentialsStorage;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by veinhorn on 27.2.17.
 */

public class ProfileFragment extends MvpFragment<ProfileView, ProfilePresenter>
    implements ProfileView {

    @BindView(R.id.login)
    protected TextView login;

    @BindView(R.id.firstName)
    protected TextView firstName;

    @BindView(R.id.lastName)
    protected TextView lastName;

    @BindView(R.id.patronymic)
    protected TextView patronymic;

    @BindView(R.id.phoneNumber)
    protected TextView phoneNumber;

    @BindView(R.id.country)
    protected TextView country;

    @BindView(R.id.address)
    protected TextView address;

    @BindView(R.id.email)
    protected TextView email;

    @BindView(R.id.gender)
    protected TextView gender;

    @BindView(R.id.age)
    protected TextView age;

    /** Используется для установки старого заголовка в ActionBar */
    private String oldTitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, parent, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        oldTitle = getActivity().getTitle().toString();
        getActivity().setTitle(getString(R.string.drawer_profile));

        presenter.showProfile();
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().setTitle(oldTitle);
    }

    @Override
    public void showProfile(Profile profile) {
        login.setText(profile.getLogin());
        firstName.setText(profile.getFirstName());
        lastName.setText(profile.getSecondName());
        patronymic.setText(profile.getPatronymic());
        phoneNumber.setText(profile.getPhoneNumber());
        country.setText(profile.getCountry());
        address.setText(profile.getAddress());
        email.setText(profile.getEmail());
        gender.setText(profile.getGender());
        age.setText(profile.getAge());
    }

    @Override
    public ProfilePresenter createPresenter() {
        Credentials credentials = CredentialsStorage.read(getActivity());
        return new ProfilePresenter(credentials);
    }
}
