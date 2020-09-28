package com.example.profilematcher.di.module

import android.app.Application
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.profilematcher.data.localdb.LocalDatabase
import com.example.profilematcher.data.localdb.UserInfoDao
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [ViewModelFactoryModule::class])
class LocalDatabaseModule  {
//
//    @Inject @Named("appContext") // replaced by @Inject
//    internal lateinit var applicationContext: Application

    companion object {
        val MIGRATION_1_2: Migration = object : Migration(1, 2){
            override fun migrate(database: SupportSQLiteDatabase) {
                // Change the table name to the correct one
                database.execSQL("ALTER TABLE UserInfo RENAME TO userinfo")
            }
        }
    }

    @Singleton
    @Provides
    fun provideLocalDatabase(app: Application): LocalDatabase {
    return  Room.databaseBuilder(
            app,
            LocalDatabase::class.java, "userInfo_DB")
            .addMigrations(MIGRATION_1_2)
            .build()
    }

    @Provides
    @Singleton
    fun provideUserInfoDao(
        localDatabase : LocalDatabase): UserInfoDao {
        return localDatabase.getUserInfoDao()
    }
}