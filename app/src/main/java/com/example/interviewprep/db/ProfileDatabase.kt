package com.example.interviewprep.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.interviewprep.db.dao.ProfileDAO
import com.example.interviewprep.model.ProfilePage

@TypeConverters(ProfileTypeConverters::class)
@Database(entities = [ProfilePage::class], version = 1, exportSchema = false)
abstract class ProfileDatabase : RoomDatabase() {

    abstract fun profileDao() : ProfileDAO
}