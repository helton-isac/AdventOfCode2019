package com.hitg.adventofcode.repository.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hitg.adventofcode.domain.model.Challenge

@Dao
interface ChallengeDAO {
    @Query("SELECT * from challenge ORDER BY day ASC")
    fun getAllChallenges(): List<Challenge>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(challenge: Challenge)

    @Query("DELETE FROM challenge")
    suspend fun deleteAll()
}