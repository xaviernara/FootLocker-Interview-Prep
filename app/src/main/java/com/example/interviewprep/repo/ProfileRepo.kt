package com.example.interviewprep.repo

import com.example.interviewprep.model.ProfilePage
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface ProfileRepo {

    suspend fun getProfileFromApi(): Response<ProfilePage>

    suspend fun getProfileFromDb(): Flow<List<ProfilePage>>

    suspend fun insertProfileToDb(profilePage: ProfilePage)





}