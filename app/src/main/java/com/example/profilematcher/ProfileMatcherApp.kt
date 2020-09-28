package com.example.profilematcher

import com.example.profilematcher.di.component.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class ProfileMatcherApp : DaggerApplication() {


    override fun onCreate() {
        super.onCreate()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val applicationComponent = DaggerApplicationComponent.builder().application(this)!!.build()
        applicationComponent.inject(this)
        return applicationComponent
    }
}