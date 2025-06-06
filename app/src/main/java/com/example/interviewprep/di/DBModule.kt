package com.example.interviewprep.di

import android.content.Context
import androidx.room.Room
import com.example.interviewprep.dao.ProfileDAO
import com.example.interviewprep.repo.db.ProfileDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DBModule {

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context): ProfileDatabase {
        return Room.databaseBuilder(context, ProfileDatabase::class.java, "profile_db").build()
    }

    @Provides
    @Singleton
    fun providesProfileDao(profileDatabase: ProfileDatabase): ProfileDAO {
        return profileDatabase.profileDao()
    }

}