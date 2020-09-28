package com.example.profilematcher.di.module

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import javax.inject.Named
import javax.inject.Singleton

@Module
abstract class ContextModule {
    @Binds
    @Singleton
    abstract fun bindApplicationContext(application: Application): Context

}