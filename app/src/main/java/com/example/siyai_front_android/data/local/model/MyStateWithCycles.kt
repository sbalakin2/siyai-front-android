package com.example.siyai_front_android.data.local.model

import androidx.room.Embedded
import androidx.room.Relation

data class MyStateWithCycles(
    @Embedded val myState: MyStateDb,
    @Relation(
        parentColumn = "id",
        entityColumn = "myStateId"
    )
    val cycles: List<CycleDb>
)