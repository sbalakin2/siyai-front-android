package com.example.siyai_front_android.data.repositories

import com.example.siyai_front_android.data.local.DailyDao
import com.example.siyai_front_android.data.local.MyStateDao
import com.example.siyai_front_android.data.local.model.MyStateDb
import com.example.siyai_front_android.data.mappers.toCycleDbList
import com.example.siyai_front_android.data.mappers.toDailyDb
import com.example.siyai_front_android.data.mappers.toDailyEntity
import com.example.siyai_front_android.data.mappers.toMyStateEntity
import com.example.siyai_front_android.domain.dto.Cycle
import com.example.siyai_front_android.domain.dto.Daily
import com.example.siyai_front_android.domain.dto.MyState
import com.example.siyai_front_android.domain.repositories.MyStateRepository
import com.example.siyai_front_android.domain.repositories.ProfileStorageRepository
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class MyStateRepositoryImpl @Inject constructor(
    private val myStateDao: MyStateDao,
    private val dailyDao: DailyDao,
    private val profileStorageRepository: ProfileStorageRepository
) : MyStateRepository {

    override suspend fun changeCycles(cycles: List<Cycle>) {
        val stateId = getOrCreateMyState()
        myStateDao.updateCyclesForState(stateId, cycles.toCycleDbList(stateId))
    }

    override suspend fun getMyState(): MyState? {
        val userEmail = profileStorageRepository.getEmail()
        return myStateDao.getMyStateWithCycles(userEmail)?.toMyStateEntity()
    }

    override suspend fun saveStateForToday(state: Int?, note: String?) {
        val today = getTodayString()
        val daily = Daily(
            date = today,
            state = state,
            note = note
        )
        dailyDao.insertState(daily.toDailyDb(getOrCreateMyState()))
    }

    override suspend fun getStateForDate(): Daily? {
        return dailyDao.getStateForDate(getTodayString())?.toDailyEntity()
    }

    private suspend fun getOrCreateMyState(): Int {
        val userEmail = profileStorageRepository.getEmail()
        val existingStateId = myStateDao.getStateIdByEmail(userEmail)
        return if (existingStateId != null) {
            existingStateId
        } else {
            val newState = MyStateDb(userEmail = userEmail)
            myStateDao.insertMyState(newState).toInt()
        }
    }

    private fun getTodayString(): String {
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return formatter.format(Date())
    }
}