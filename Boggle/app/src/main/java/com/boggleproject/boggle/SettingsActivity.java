package com.boggleproject.boggle;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.annotation.Nullable;

/**
 * Created by lukas on 02.10.2017.
 */

public class SettingsActivity extends PreferenceActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new SettingsFragment()).commit();
    }
}
