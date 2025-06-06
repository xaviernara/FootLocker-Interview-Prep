package com.example.interviewprep.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profile_table")
data class ProfilePage(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "data_list")
    var `data`: List<Data?>? = null,
    var page: Int? = null,
    var perPage: Int? = null,
    @ColumnInfo(name = "support_data")
    var support: Support? = null,
    var total: Int? = null,
    var totalPages: Int? = null
)