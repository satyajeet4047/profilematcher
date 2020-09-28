package com.example.profilematcher.ui.main

import android.os.Bundle
import com.example.profilematcher.R
import com.example.profilematcher.ui.profiles.ProfilesFragment
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.fl_container, ProfilesFragment.newInstance())
            .commit()
    }
}