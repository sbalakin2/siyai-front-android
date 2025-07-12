package com.example.siyai_front_android.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.siyai_front_android.data.local.model.DailyDb

@Dao
interface DailyDao {
    @Query("SELECT * FROM daily_states WHERE date = :date LIMIT 1")
    suspend fun getStateForDate(date: String): DailyDb?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertState(state: DailyDb)

    @Query("SELECT * FROM daily_states ORDER BY date DESC")
    suspend fun getAllStates(): List<DailyDb>

    @Query("DELETE FROM daily_states WHERE date = :date")
    suspend fun deleteStateForDate(date: String)
}