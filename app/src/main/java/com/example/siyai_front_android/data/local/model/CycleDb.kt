package com.example.siyai_front_android.data.local.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "cycles",
    foreignKeys = [
        ForeignKey(
            entity = MyStateDb::class,
            parentColumns = ["id"],
            childColumns = ["myStateId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class CycleDb(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val myStateId: Int,
    val start: Long,
    val end: Long,
    val isOnPeriod: Boolean
)
