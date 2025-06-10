package com.example.interviewprep.db

import androidx.room.TypeConverter
import com.example.interviewprep.model.Data
import com.example.interviewprep.model.Support
import com.google.gson.reflect.TypeToken
import com.google.gson.Gson

class ProfileTypeConverters {

    @TypeConverter
    fun dataToJson(value: List<Data?>?) : String? {
       val type = object : TypeToken<List<Data?>?>() {}.type
        return Gson().toJson(value, type)
    }

    @TypeConverter
    fun jsonToData(value: String) : List<Data?>? {
        val type = object : TypeToken<List<Data?>?>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun supportToJson(value: Support) : String? {
        val type = object : TypeToken<Support>() {}.type
        return Gson().toJson(value, type)
    }

    @TypeConverter
    fun jsonToSupport(value: String) : Support? {
        val type = object : TypeToken<Support>() {}.type
        return Gson().fromJson(value, type)
    }
}