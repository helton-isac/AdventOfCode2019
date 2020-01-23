package com.hitg.adventofcode.repository.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.hitg.adventofcode.domain.model.entity.Challenge

@Dao
interface ChallengeDAO {
    @Query("SELECT * from challenge ORDER BY day ASC")
    fun getAllChallenges(): LiveData<List<Challenge>>

    @Query("SELECT * FROM challenge WHERE day=:day ")
    fun getChallengeByDay(day: Int): LiveData<Challenge?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(challenge: Challenge)

    @Query("DELETE FROM challenge")
    suspend fun deleteAll()

    @Update
    suspend fun update(challenge: Challenge)
}