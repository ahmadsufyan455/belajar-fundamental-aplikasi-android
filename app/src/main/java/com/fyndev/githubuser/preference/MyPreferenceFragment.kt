package com.fyndev.githubuser.preference

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.fyndev.githubuser.R

class MyPreferenceFragment : PreferenceFragmentCompat() {

    private lateinit var lang: Preference

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences)

        lang = findPreference(resources.getString(R.string.language))!!

        lang.onPreferenceClickListener = Preference.OnPreferenceClickListener {
            startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
            false
        }
    }
}