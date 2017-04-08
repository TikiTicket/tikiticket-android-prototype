package com.veinhorn.tikiticket.android.ui.drawer;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

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
import com.tikiticket.core.Credentials;
import com.tikiticket.core.util.Util;
import com.veinhorn.tikiticket.android.R;
import com.veinhorn.tikiticket.android.core.credentials.CredentialsStorage;
import com.veinhorn.tikiticket.android.core.dao.ProfileRecord;
import com.veinhorn.tikiticket.android.ui.profile.ProfileFragment;
import com.veinhorn.tikiticket.android.ui.purchase.PurchaseActivity;
import com.veinhorn.tikiticket.android.ui.tickets.TicketsFragment;

/**
 * Created by veinhorn on 12.1.17.
 * Builds navigation drawer
 */

public class NavigationDrawer {
    private static final String UNKNOWN = "Unknown";
    private static final long UNSELECTED = -1;

    /** Fragments */
    private static final int PROFILE_FRAGMENT = -1;
    private static final int MY_TICKETS_FRAGMENT = 1;
    private static final int PURCHASE_ACTIVITY = 2;
    private static final int SETTINGS_FRAGMENT = 3;
    private static final int SIGN_OUT_ITEM = 5;
    /** */

    private AppCompatActivity activity;
    private Toolbar toolbar;
    private Credentials creds;

    private Drawer drawer;

    public NavigationDrawer(AppCompatActivity activity, Toolbar toolbar) {
        this.activity = activity;
        this.toolbar = toolbar;
    }

    public NavigationDrawer withCreds(Credentials creds) {
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
                .addDrawerItems(createItems())
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        // Toast.makeText(activity, Integer.valueOf(position).toString(), Toast.LENGTH_SHORT).show();
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
    private IDrawerItem[] createItems() {
        return new IDrawerItem[] {
                ticketsItem(), purchaseItem(), settingsItem(), communityItem(), signOut()
        };
    }

    private PrimaryDrawerItem ticketsItem() {
        return new PrimaryDrawerItem()
                .withName(activity.getString(R.string.drawer_tickets_item))
                .withIcon(FontAwesome.Icon.faw_ticket);
    }

    private SecondaryDrawerItem purchaseItem() {
        return new SecondaryDrawerItem()
                .withName(activity.getString(R.string.drawer_purchase_item))
                .withIcon(FontAwesome.Icon.faw_buysellads);
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

    private SecondaryDrawerItem signOut() {
        return new SecondaryDrawerItem()
                .withName(activity.getString(R.string.drawer_signout_item))
                .withIcon(FontAwesome.Icon.faw_sign_out);
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
                        displaySelectedFragment(PROFILE_FRAGMENT);
                        drawer.setSelection(UNSELECTED);
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
            /** PROFILE_FRAGMENT used for Profile */
            case PROFILE_FRAGMENT:
                /** If we already have Profile in back stack - do nothing */
                if (fm.getBackStackEntryCount() > 0) break;
                fragment = new ProfileFragment();
                break;
            case MY_TICKETS_FRAGMENT:
                clearBackStack(fm);
                fragment = new TicketsFragment();
                break;
            case PURCHASE_ACTIVITY:
                Intent purchaseActivity = new Intent(activity, PurchaseActivity.class);
                activity.startActivity(purchaseActivity);
                break;
            case SETTINGS_FRAGMENT:

                /*clearBackStack(fm);
                fragment = new SettingsFragment();*/
                break;
            /** Очистка данных пользователя, т.е логина и пароля */
            case SIGN_OUT_ITEM:
                CredentialsStorage.clean(activity);
                new ProfileRecord().deleteProfile();
                activity.finish();
                break;
        }

        if (fragment != null && itemId == PROFILE_FRAGMENT) {
            fm.beginTransaction().add(R.id.contentFrame, fragment).addToBackStack(null).commit();
        } else if (fragment != null) {
            fm.beginTransaction().replace(R.id.contentFrame, fragment).commit();
        }
    }

    private void clearBackStack(FragmentManager fm) {
        for (int i = 0; i < fm.getBackStackEntryCount(); i++) fm.popBackStack();
    }
}
