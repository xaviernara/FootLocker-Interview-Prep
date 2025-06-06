package com.example.interviewprep.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.interviewprep.model.ProfilePage
import kotlinx.coroutines.flow.Flow

@Dao
interface ProfileDAO {

    @Query("SELECT * FROM profile_table")
    fun getProfiles(id: Int): Flow<ProfilePage>

    @Insert
    fun insertProfile(profilePage: ProfilePage)


}