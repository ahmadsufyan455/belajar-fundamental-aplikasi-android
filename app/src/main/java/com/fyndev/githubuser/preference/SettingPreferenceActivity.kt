package com.fyndev.githubuser.preference

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fyndev.githubuser.R
import com.fyndev.githubuser.databinding.ActivitySettingPreferenceBinding

class SettingPreferenceActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingPreferenceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingPreferenceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .add(R.id.setting_holder, MyPreferenceFragment())
            .commit()

        binding.icBack.setOnClickListener { finish() }
    }
}