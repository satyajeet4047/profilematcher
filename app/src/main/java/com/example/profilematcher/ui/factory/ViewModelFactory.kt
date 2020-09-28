@file:Suppress("UNCHECKED_CAST")

package com.example.profilematcher.ui.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton


/*
    Class to provide respective fragment view models.

 */
@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
@Singleton
class ViewModelFactory @Inject constructor(private val creators: MutableMap<Class<out ViewModel?>?, Provider<ViewModel?>?>?) :

    ViewModelProvider.Factory {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        val creator = creators?.get(modelClass) ?:
        creators?.asIterable()?.firstOrNull { modelClass.isAssignableFrom(it.key) }?.value
        ?: throw IllegalArgumentException("unknown model class $modelClass") as Throwable

        return try {
            creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }


}
