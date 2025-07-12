package com.example.siyai_front_android.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.siyai_front_android.data.local.model.CycleDb
import com.example.siyai_front_android.data.local.model.DailyDb
import com.example.siyai_front_android.data.local.model.MyStateDb

@Database(
    entities = [MyStateDb::class, CycleDb::class, DailyDb::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun myStateDao(): MyStateDao
    abstract fun dailyDao(): DailyDao

    companion object {

        private const val DB_NAME = "AppDatabase"
        private var INSTANCE: AppDatabase? = null
        private val LOCK = Any()

        fun getInstance(context: Context): AppDatabase {
            INSTANCE?.let { return it }

            synchronized(LOCK) {
                INSTANCE?.let { return it }

                return Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}