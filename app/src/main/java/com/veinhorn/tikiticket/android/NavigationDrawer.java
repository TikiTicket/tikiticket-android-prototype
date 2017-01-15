package com.veinhorn.tikiticket.android;

import android.app.Activity;
import android.support.v7.widget.Toolbar;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;

/**
 * Created by veinhorn on 12.1.17.
 * Holds all nav drawer building logic
 */

public class NavigationDrawer {
    private Activity activity;
    private Toolbar toolbar;

    public NavigationDrawer(Activity activity,
                            Toolbar toolbar) {
        this.activity = activity;
        this.toolbar = toolbar;
    }

    public Drawer build() {
        return new DrawerBuilder()
                .withActivity(activity)
                .withToolbar(toolbar)
                .build();
    }
}
