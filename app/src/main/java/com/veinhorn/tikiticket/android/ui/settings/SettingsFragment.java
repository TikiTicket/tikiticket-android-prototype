package com.veinhorn.tikiticket.android.ui.settings;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.veinhorn.tikiticket.android.R;

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
