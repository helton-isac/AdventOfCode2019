package com.hitg.adventofcode.repository.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.hitg.adventofcode.domain.model.Challenge
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Challenge::class], version = 1, exportSchema = false)
abstract class ChallengeRoomDatabase : RoomDatabase() {

    abstract fun challengeDao(): ChallengeDAO

    companion object {

        @Volatile
        private var INSTANCE: ChallengeRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): ChallengeRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ChallengeRoomDatabase::class.java,
                    "challenge_database"
                ).addCallback(ChallengeDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

    private class ChallengeDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.challengeDao())
                }
            }
        }

        suspend fun populateDatabase(challengeDao: ChallengeDAO) {
            challengeDao.deleteAll()
            challengeDao.insert(Challenge(day = 1, name = "The Tyranny of the Rocket Equation"))
            challengeDao.insert(Challenge(day = 2, name = "1202 Program Alarm"))
            challengeDao.insert(Challenge(day = 3, name = "Crossed Wires"))
            challengeDao.insert(Challenge(day = 4, name = "Secure Container"))
            challengeDao.insert(Challenge(day = 5, name = "Sunny with a Chance of Asteroids"))
            challengeDao.insert(Challenge(day = 6, name = "Universal Orbit Map"))
            challengeDao.insert(Challenge(day = 7, name = "Amplification Circuit"))
            challengeDao.insert(Challenge(day = 8, name = "Space Image Format"))
            challengeDao.insert(Challenge(day = 9, name = "Sensor Boost"))
            challengeDao.insert(Challenge(day = 10, name = "Monitoring Station"))
            challengeDao.insert(Challenge(day = 11, name = "Space Police"))
            challengeDao.insert(Challenge(day = 12, name = "The N-Body Problem"))
            challengeDao.insert(Challenge(day = 13, name = "Care Package"))
            challengeDao.insert(Challenge(day = 14, name = "Space Stoichiometry"))
            challengeDao.insert(Challenge(day = 15, name = "Oxygen System"))
            challengeDao.insert(Challenge(day = 16, name = "Flawed Frequency Transmission"))
            challengeDao.insert(Challenge(day = 17, name = "Set and Forget"))
            challengeDao.insert(Challenge(day = 18, name = "Many-Worlds Interpretation"))
            challengeDao.insert(Challenge(day = 19, name = "Tractor Beam"))
            challengeDao.insert(Challenge(day = 20, name = "Donut Maze"))
            challengeDao.insert(Challenge(day = 21, name = "Springdroid Adventure"))
            challengeDao.insert(Challenge(day = 22, name = "Slam Shuffle"))
            challengeDao.insert(Challenge(day = 23, name = "Category Six"))
            challengeDao.insert(Challenge(day = 24, name = "Planet of Discord"))
            challengeDao.insert(Challenge(day = 25, name = "Cryostasis"))
        }
    }
}