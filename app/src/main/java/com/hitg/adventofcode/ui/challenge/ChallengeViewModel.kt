package com.hitg.adventofcode.ui.challenge

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.hitg.adventofcode.domain.model.entity.Challenge
import com.hitg.adventofcode.domain.model.solver.DaySolver
import com.hitg.adventofcode.domain.model.solver.SolverFactory
import com.hitg.adventofcode.repository.ChallengeRepository
import com.hitg.adventofcode.repository.database.ChallengeDAO
import com.hitg.adventofcode.repository.database.ChallengeRoomDatabase

class ChallengeViewModel(day: Int, application: Application) :
    AndroidViewModel(application) {

    suspend fun runChallenge() {
        val originalChallenge = challenge.value
        if (originalChallenge != null) {
            val challengeResult =
                Challenge(
                    originalChallenge.day, originalChallenge.name,
                    originalChallenge.firstStar, originalChallenge.secondStar,
                    originalChallenge.firstAnswer, originalChallenge.secondAnswer
                )
            val firstAnswer = daySolver.solvePart1()
            val secondAnswer = daySolver.solvePart2()
            challengeResult.firstStar = firstAnswer != null
            challengeResult.firstAnswer = firstAnswer ?: ""
            challengeResult.secondStar = secondAnswer != null
            challengeResult.secondAnswer = secondAnswer ?: ""
            repository.update(challengeResult)
        }
    }

    val challenge: LiveData<Challenge?>
    private val repository: ChallengeRepository
    private val daySolver: DaySolver

    init {
        val challengeDao: ChallengeDAO =
            ChallengeRoomDatabase.getDatabase(application, viewModelScope).challengeDao()
        repository = ChallengeRepository(challengeDao)
        challenge = repository.challengeByDay(day)
        daySolver = SolverFactory.createSolver(application.resources, day)
    }
}