package com.example.profilematcher.di.module


import com.example.profilematcher.ui.main.FragmentsBindingModule
import com.example.profilematcher.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
 abstract class MainActivityBindingModule {

    @ContributesAndroidInjector(modules = [FragmentsBindingModule::class])
   internal abstract fun bindMainActivity(): MainActivity?
}