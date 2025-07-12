package com.example.siyai_front_android.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.siyai_front_android.data.local.model.CycleDb
import com.example.siyai_front_android.data.local.model.MyStateDb
import com.example.siyai_front_android.data.local.model.MyStateWithCycles

@Dao
interface MyStateDao {

    @Query("SELECT * FROM my_state WHERE userEmail = :userEmail")
    suspend fun getMyStateWithCycles(userEmail: String): MyStateWithCycles?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMyState(myState: MyStateDb): Long

    @Query("SELECT id FROM my_state WHERE userEmail = :userEmail LIMIT 1")
    suspend fun getStateIdByEmail(userEmail: String): Int?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCycles(cycles: List<CycleDb>)

    @Query("DELETE FROM cycles WHERE myStateId = :stateId")
    suspend fun deleteCyclesForState(stateId: Int)

    @Transaction
    suspend fun updateCyclesForState(stateId: Int, cycles: List<CycleDb>) {
        deleteCyclesForState(stateId)
        if (cycles.isNotEmpty()) { insertCycles(cycles) }
    }

    @Query("DELETE FROM my_state WHERE userEmail = :userEmail")
    suspend fun deleteUserState(userEmail: String)
}