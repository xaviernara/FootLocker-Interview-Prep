package com.example.interviewprep.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.interviewprep.model.ProfilePage
import kotlinx.coroutines.flow.Flow

@Dao
interface ProfileDAO {

    @Query("SELECT * FROM profile_table")
    fun getAllProfiles(): Flow<List<ProfilePage>>

    @Insert
    fun insertProfile(profilePage: ProfilePage)


}