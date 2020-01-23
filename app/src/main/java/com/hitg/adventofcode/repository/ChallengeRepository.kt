package com.hitg.adventofcode.repository

import androidx.lifecycle.LiveData
import com.hitg.adventofcode.domain.model.entity.Challenge
import com.hitg.adventofcode.repository.database.ChallengeDAO

class ChallengeRepository(private val challengeDAO: ChallengeDAO) {

    val allChallenges: LiveData<List<Challenge>> = challengeDAO.getAllChallenges()

    fun challengeByDay(day: Int): LiveData<Challenge?> {
        return challengeDAO.getChallengeByDay(day)
    }

    suspend fun update(challenge: Challenge) {
        challengeDAO.update(challenge)
    }
}