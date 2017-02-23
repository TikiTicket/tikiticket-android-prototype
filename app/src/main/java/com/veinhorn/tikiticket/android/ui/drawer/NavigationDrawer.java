package com.veinhorn.tikiticket.android.ui.drawer;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.veinhorn.tikiticket.android.R;
import com.veinhorn.tikiticket.core.api.ICredentials;
import com.veinhorn.tikiticket.core.util.Util;

/**
 * Created by veinhorn on 12.1.17.
 * Builds navigation drawer
 */

public class NavigationDrawer {
    private static final String TAG = "NavigationDrawer";

    private AppCompatActivity activity;
    private Toolbar toolbar;
    private ICredentials creds;

    /** Used in header listener for closing */
    private Drawer drawer;

    public NavigationDrawer(AppCompatActivity activity, Toolbar toolbar) {
        this.activity = activity;
        this.toolbar = toolbar;
        // remove this line (buggy behaviour)
        // this.toolbar.setTitle(activity.getString(R.string.my_tickets_drawer_item));
    }

    public NavigationDrawer withCreds(ICredentials creds) {
        if (creds != null) this.creds = creds;
        else this.creds = Util.newCredentials("Unknown", "Unknown");
        return this;
    }

    public Drawer build() {
        PrimaryDrawerItem myTicketsItem = new PrimaryDrawerItem()
                .withName(activity.getString(R.string.my_tickets_drawer_item))
                .withIcon(FontAwesome.Icon.faw_ticket);


        SecondaryDrawerItem settingsItem = new SecondaryDrawerItem()
                .withName("Settings")
                .withIcon(FontAwesome.Icon.faw_cog);

        SecondaryDrawerItem communityItem = new SecondaryDrawerItem()
                .withName("Community")
                .withIcon(FontAwesome.Icon.faw_users);

        drawer = new DrawerBuilder()
                .withActivity(activity)
                .withToolbar(toolbar)
                .withAccountHeader(buildAccountHeader())
                .addDrawerItems(myTicketsItem, settingsItem, communityItem)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        switch (position) {
                            case 1:
                                toolbar.setTitle(R.string.my_tickets_drawer_item);
                                return true;
                        }
                        return false;
                    }
                })
                .withSelectedItemByPosition(1)
                .build();
        return drawer;
    }

    private AccountHeader buildAccountHeader() {
        return new AccountHeaderBuilder()
                .withActivity(activity)
                .withHeaderBackground(R.drawable.header_background)
                .addProfiles(buildProfileItem())
                .withSelectionListEnabled(false)
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean current) {
                        drawer.closeDrawer();
                        new MaterialDialog.Builder(activity)
                                //.title("Test dialog")
                                .customView(R.layout.profile_dialog_view, false)
                                .show();
                        return true;
                    }
                })
                .build();
    }

    private ProfileDrawerItem buildProfileItem() {
        return new ProfileDrawerItem()
                .withName(creds.getLogin())
                .withIcon(FontAwesome.Icon.faw_user);
    }
}
