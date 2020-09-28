package com.example.profilematcher.ui.main

import com.example.profilematcher.ui.profiles.ProfilesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentsBindingModule {

    @ContributesAndroidInjector
    abstract fun contributeProfilesFragment() : ProfilesFragment?
}