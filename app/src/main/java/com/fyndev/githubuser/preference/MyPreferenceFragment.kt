package com.fyndev.githubuser.preference

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import com.fyndev.githubuser.R
import com.fyndev.githubuser.notification.AlarmReceiver

class MyPreferenceFragment : PreferenceFragmentCompat() {

    private lateinit var switchPreference: SwitchPreference
    private lateinit var lang: Preference

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences)

        val alarmReceiver = AlarmReceiver()

        switchPreference = findPreference(resources.getString(R.string.notification))!!
        lang = findPreference(resources.getString(R.string.language))!!

        switchPreference.onPreferenceChangeListener =
            Preference.OnPreferenceChangeListener { _, _ ->
                if (switchPreference.isChecked) {
                    activity?.let { alarmReceiver.cancelAlarm(it) }
                    Toast.makeText(activity, R.string.reminder_off, Toast.LENGTH_SHORT).show()
                    switchPreference.isChecked = false
                } else {
                    activity?.let { alarmReceiver.setRepeatingAlarm(it) }
                    Toast.makeText(activity, R.string.reminder_on, Toast.LENGTH_SHORT).show()
                    switchPreference.isChecked = true
                }
                false
            }

        lang.onPreferenceClickListener = Preference.OnPreferenceClickListener {
            startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
            false
        }
    }
}