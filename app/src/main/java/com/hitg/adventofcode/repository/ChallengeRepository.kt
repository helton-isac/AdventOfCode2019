package com.hitg.adventofcode.repository

import androidx.lifecycle.LiveData
import com.hitg.adventofcode.domain.model.Challenge
import com.hitg.adventofcode.repository.database.ChallengeDAO

class ChallengeRepository(private val challengeDAO: ChallengeDAO) {

    val allChallenges: LiveData<List<Challenge>> = challengeDAO.getAllChallenges()

    suspend fun insert(challenge: Challenge) {
        challengeDAO.insert(challenge)
    }
}