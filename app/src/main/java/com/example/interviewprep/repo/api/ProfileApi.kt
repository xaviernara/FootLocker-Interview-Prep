package com.example.interviewprep.repo.api

import com.example.interviewprep.model.ProfilePage
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProfileApi {
    //https://reqres.in/api/users?page=2
    //api_key= reqres-free-v1

    @GET("users?page={page}")
    suspend fun getProfiles(
        @Query("page") page: Int = 2,
//        @Query("x_api_key") apiKey: String = "reqres-free-v1",
    ) : Response<ProfilePage>

}