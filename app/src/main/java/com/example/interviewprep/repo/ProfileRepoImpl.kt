package com.example.interviewprep.repo

import com.example.interviewprep.db.dao.ProfileDAO
import com.example.interviewprep.model.ProfilePage
import com.example.interviewprep.network.ProfileApi
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class ProfileRepoImpl @Inject constructor(
    private val profileApi: ProfileApi,
    private val profileDao: ProfileDAO
) : ProfileRepo{
    override suspend fun getProfileFromApi(): Response<ProfilePage> {
        return profileApi.getProfiles()
    }

    override suspend fun getProfileFromDb(): Flow<List<ProfilePage>> {
        return profileDao.getAllProfiles()
    }

    override suspend fun insertProfileToDb(profilePage: ProfilePage) {
        profileDao.insertProfile(profilePage)
    }

}