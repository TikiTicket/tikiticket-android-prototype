package com.veinhorn.tikiticket.android.ui.profile;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.tikiticket.core.Credentials;
import com.tikiticket.core.api.Profile;

/**
 * Created by veinhorn on 19.3.17.
 */

public class ProfilePresenter extends MvpBasePresenter<ProfileView> {
    private ProfileLoader profileLoader;
    // TODO: Возможно нарушение парадигмы MVP
    private Credentials credentials;

    public ProfilePresenter(Credentials credentials) {
        this.credentials = credentials;
    }

    private void cancelProfileLoadingIfRunning() {
        if (profileLoader != null) profileLoader.cancel(true);
    }

    public void showProfile() {
        cancelProfileLoadingIfRunning();
        profileLoader = new ProfileLoader(credentials, new ProfileLoaderListener());
        profileLoader.execute();
    }

    /** Called when Fragment gets destroyed, so cancel running background task */
    public void detatchView(boolean retainPresenterInstance) {
        super.detachView(retainPresenterInstance);
        if (!retainPresenterInstance) cancelProfileLoadingIfRunning();
    }

    private class ProfileLoaderListener implements ProfileLoader.ProfileLoaderListener {
        @Override
        public void onProfileLoaded(Profile profile) {
            if (isViewAttached()) getView().showProfile(profile);
        }
    }
}
