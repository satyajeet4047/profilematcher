package com.example.profilematcher.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.profilematcher.di.util.ViewModelKey
import com.example.profilematcher.ui.factory.ViewModelFactory
import com.example.profilematcher.ui.profiles.ProfilesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ContextModule::class])
abstract class ViewModelFactoryModule {


    @Binds
    @IntoMap
    @ViewModelKey(ProfilesViewModel::class)
    internal abstract fun bindProfilesViewModel(profilesViewModel: ProfilesViewModel) : ViewModel


    @Binds
    internal abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory) : ViewModelProvider.Factory
}