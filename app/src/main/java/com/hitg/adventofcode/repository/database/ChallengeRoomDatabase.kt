package com.hitg.adventofcode.repository.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hitg.adventofcode.domain.model.Challenge

@Database(entities = [Challenge::class], version = 1, exportSchema = false)
abstract class ChallengeRoomDatabase : RoomDatabase() {

    abstract fun challengeDao(): ChallengeDAO

    companion object {

        @Volatile
        private var INSTANCE: ChallengeRoomDatabase? = null

        fun getDatabase(context: Context): ChallengeRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ChallengeRoomDatabase::class.java,
                    "challenge_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}