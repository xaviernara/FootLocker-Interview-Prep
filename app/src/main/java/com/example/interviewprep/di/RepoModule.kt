package com.example.interviewprep.di

import com.example.interviewprep.repo.ProfileRepo
import com.example.interviewprep.repo.ProfileRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {

    @Binds
    @Singleton
    abstract fun bindRepo(repo: ProfileRepoImpl): ProfileRepo

}