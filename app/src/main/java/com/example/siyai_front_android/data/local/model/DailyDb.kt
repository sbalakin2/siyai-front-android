package com.example.siyai_front_android.data.local.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "daily_states",
    foreignKeys = [
        ForeignKey(
            entity = MyStateDb::class,
            parentColumns = ["id"],
            childColumns = ["myStateId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class DailyDb(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val myStateId: Int,
    val date: String,
    val state: Int?,
    val note: String?
)
