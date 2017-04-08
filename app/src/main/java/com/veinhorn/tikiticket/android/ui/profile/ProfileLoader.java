package com.veinhorn.tikiticket.android.ui.profile;

import android.os.AsyncTask;

import com.tikiticket.core.Credentials;
import com.tikiticket.core.api.Profile;
import com.veinhorn.tikiticket.android.core.repository.ProfileRepository;

/**
 * Created by veinhorn on 19.3.17.
 */

public class ProfileLoader extends AsyncTask<Void, Void, Profile> {
    public interface ProfileLoaderListener {
        void onProfileLoaded(Profile profile);
    }

    private Credentials credentials;
    private ProfileLoaderListener listener;
    private ProfileRepository profileRepo;

    public ProfileLoader(Credentials credentials, ProfileLoaderListener listener) {
        this.credentials = credentials;
        this.listener = listener;
        profileRepo = new ProfileRepository();
    }

    @Override
    protected Profile doInBackground(Void... params) {
        return profileRepo.findProfile(credentials.getLogin());
    }

    // TODO: Если профайл нулл, вызывать какой-нибудь калл бэк с тоастом
    @Override
    protected void onPostExecute(Profile profile) {
        if (profile != null) listener.onProfileLoaded(profile);
        // else show call some callback in listener
        // listener.onProfileLoaded(profile);
    }
}
