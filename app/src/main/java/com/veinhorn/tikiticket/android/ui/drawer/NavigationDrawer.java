package com.veinhorn.tikiticket.android.ui.drawer;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

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
import com.veinhorn.tikiticket.android.ui.profile.ProfileFragment;
import com.veinhorn.tikiticket.android.ui.tickets.TicketFragment;
import com.veinhorn.tikiticket.core.api.ICredentials;
import com.veinhorn.tikiticket.core.util.Util;

/**
 * Created by veinhorn on 12.1.17.
 * Builds navigation drawer
 */

public class NavigationDrawer {
    private static final String UNKNOWN = "Unknown";

    private AppCompatActivity activity;
    private Toolbar toolbar;
    private ICredentials creds;

    private Drawer drawer;

    public NavigationDrawer(AppCompatActivity activity, Toolbar toolbar) {
        this.activity = activity;
        this.toolbar = toolbar;
    }

    public NavigationDrawer withCreds(ICredentials creds) {
        if (creds != null) this.creds = creds;
        else this.creds = Util.newCredentials(UNKNOWN, UNKNOWN);
        return this;
    }

    public NavigationDrawer withDefaultFragment() {
        displaySelectedFragment(1); // TODO: Replace this code
        return this;
    }

    public Drawer build() {
        drawer = new DrawerBuilder()
                .withActivity(activity)
                .withToolbar(toolbar)
                .withAccountHeader(buildAccountHeader())
                .addDrawerItems(ticketsItem(), settingsItem(), communityItem())
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        Toast.makeText(activity, Integer.valueOf(position).toString(), Toast.LENGTH_SHORT).show();
                        drawer.closeDrawer();
                        displaySelectedFragment(position);
                        return true;
                    }
                })
                .withSelectedItemByPosition(1)
                .build();
        return drawer;
    }

    /** Navigation Drawer items */
    private PrimaryDrawerItem ticketsItem() {
        return new PrimaryDrawerItem()
                .withName(activity.getString(R.string.my_tickets_drawer_item))
                .withIcon(FontAwesome.Icon.faw_ticket);
    }

    private SecondaryDrawerItem settingsItem() {
        return new SecondaryDrawerItem()
                .withName(activity.getString(R.string.drawer_settings_item))
                .withIcon(FontAwesome.Icon.faw_cog);
    }

    private SecondaryDrawerItem communityItem() {
        return new SecondaryDrawerItem()
                .withName(activity.getString(R.string.drawer_community_item))
                .withIcon(FontAwesome.Icon.faw_users);
    }
    /** */

    private AccountHeader buildAccountHeader() {
        return new AccountHeaderBuilder()
                .withActivity(activity)
                .withHeaderBackground(R.drawable.header_background)
                .addProfiles(buildProfileItem())
                .withSelectionListEnabled(false)
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean current) {
                        displaySelectedFragment(-1);
                        drawer.closeDrawer();
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

    private void displaySelectedFragment(int itemId) {
        FragmentManager fm = activity.getSupportFragmentManager();
        Fragment fragment = null;

        switch (itemId) {
            /** -1 used for Profile */
            case -1:
                /** If we already have Profile in back stack - do nothing */
                if (fm.getBackStackEntryCount() > 0) break;
                fragment = new ProfileFragment();
                break;
            case 1:
                clearBackStack(fm);
                fragment = new TicketFragment();
                break;
        }

        if (fragment != null && itemId == -1) {
            fm.beginTransaction().add(R.id.contentFrame, fragment).addToBackStack(null).commit();
        } else if (fragment != null) {
            fm.beginTransaction().replace(R.id.contentFrame, fragment).commit();
        }
    }

    private void clearBackStack(FragmentManager fm) {
        for (int i = 0; i < fm.getBackStackEntryCount(); i++) fm.popBackStack();
    }
}
