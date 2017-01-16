package com.veinhorn.tikiticket.android;

import android.app.Activity;
import android.support.v7.widget.Toolbar;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.veinhorn.tikiticket.core.api.ICredentials;

/**
 * Created by veinhorn on 12.1.17.
 * Contains all navigation drawer building logic
 */

class NavigationDrawer {
    private Activity activity;
    private Toolbar toolbar;
    private ICredentials creds;

    public NavigationDrawer(Activity activity, Toolbar toolbar) {
        this.activity = activity;
        this.toolbar = toolbar;
    }

    public NavigationDrawer withCreds(ICredentials creds) {
        this.creds = creds;
        return this;
    }

    public Drawer build() {
        return new DrawerBuilder()
                .withActivity(activity)
                .withToolbar(toolbar)
                .withAccountHeader(buildAccountHeader())
                .build();
    }

    private AccountHeader buildAccountHeader() {
        return new AccountHeaderBuilder()
                .withActivity(activity)
                .withHeaderBackground(R.drawable.header_background)
                .addProfiles(buildProfileItem())
                .withSelectionListEnabled(false)
                .build();
    }

    private ProfileDrawerItem buildProfileItem() {
        return new ProfileDrawerItem()
                .withName(creds.getLogin())
                .withIcon(FontAwesome.Icon.faw_user);
    }
}
