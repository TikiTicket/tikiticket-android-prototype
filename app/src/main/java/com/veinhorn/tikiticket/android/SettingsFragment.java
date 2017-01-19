package com.veinhorn.tikiticket.android;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by veinhorn on 19.1.17.
 */

public class SettingsFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
