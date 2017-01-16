package com.veinhorn.tikiticket.android;

import android.app.Activity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
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
        this.toolbar.setTitle(activity.getString(R.string.my_tickets_drawer_item));
    }

    public NavigationDrawer withCreds(ICredentials creds) {
        this.creds = creds;
        return this;
    }

    public Drawer build() {
        PrimaryDrawerItem myTicketsItem = new PrimaryDrawerItem()
                .withName(activity.getString(R.string.my_tickets_drawer_item))
                .withIcon(FontAwesome.Icon.faw_ticket);


        Drawer drawer = new DrawerBuilder()
                .withActivity(activity)
                .withToolbar(toolbar)
                .withAccountHeader(buildAccountHeader())
                .addDrawerItems(myTicketsItem)
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
                .build();
        drawer.setSelection(1);
        return drawer;
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
