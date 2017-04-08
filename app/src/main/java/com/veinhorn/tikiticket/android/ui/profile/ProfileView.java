package com.veinhorn.tikiticket.android.ui.profile;

import com.hannesdorfmann.mosby3.mvp.MvpView;
import com.tikiticket.core.api.Profile;

/**
 * Created by veinhorn on 19.3.17.
 */

public interface ProfileView extends MvpView {
    void showProfile(Profile profile);
}
