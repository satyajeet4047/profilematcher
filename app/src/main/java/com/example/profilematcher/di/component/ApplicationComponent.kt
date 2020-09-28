package com.example.profilematcher.di.component
import android.app.Application
import com.example.profilematcher.ProfileMatcherApp
import com.example.profilematcher.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Named
import javax.inject.Singleton

/*
    Main Application component class to inject different modules
 */
@Singleton
@Component(modules = [ContextModule::class, LocalDatabaseModule::class, AndroidSupportInjectionModule::class,  MainActivityBindingModule::class,
    ViewModelFactoryModule::class, NetworkModule::class])
interface ApplicationComponent : AndroidInjector<DaggerApplication>{

    fun inject(profileMatcherApp: ProfileMatcherApp)


    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent


    }
}