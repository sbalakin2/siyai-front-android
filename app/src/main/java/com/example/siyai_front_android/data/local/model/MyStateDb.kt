package com.example.siyai_front_android.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_state")
data class MyStateDb(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val userEmail: String
)
