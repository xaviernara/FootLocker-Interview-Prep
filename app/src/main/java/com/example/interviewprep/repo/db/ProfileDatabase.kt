package com.example.interviewprep.repo.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.interviewprep.dao.ProfileDAO
import com.example.interviewprep.model.ProfilePage

@Database(entities = [ProfilePage::class], version = 1)
abstract class ProfileDatabase: RoomDatabase() {

    abstract fun profileDao(): ProfileDAO

}