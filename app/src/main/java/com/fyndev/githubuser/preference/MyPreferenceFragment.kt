package com.fyndev.githubuser.preference

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.fyndev.githubuser.R

class MyPreferenceFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences)
    }
}