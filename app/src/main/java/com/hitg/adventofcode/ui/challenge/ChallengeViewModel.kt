package com.hitg.adventofcode.ui.challenge

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.hitg.adventofcode.domain.model.Challenge
import com.hitg.adventofcode.repository.ChallengeRepository
import com.hitg.adventofcode.repository.database.ChallengeDAO
import com.hitg.adventofcode.repository.database.ChallengeRoomDatabase

class ChallengeViewModel(day: Int, application: Application) :
    AndroidViewModel(application) {
    suspend fun runChallenge() {
        val originalChallenge = challenge.value
        if (originalChallenge != null) {
            val challengeResult = Challenge(
                originalChallenge.day, originalChallenge.name,
                originalChallenge.firstStar, originalChallenge.secondStar,
                originalChallenge.firstAnswer, originalChallenge.secondAnswer
            )
            if (originalChallenge.firstStar) {
                challengeResult.firstStar = false
                challengeResult.secondStar = false
                challengeResult.firstAnswer = ""
                challengeResult.secondAnswer = ""
            } else {
                challengeResult.firstStar = true
                challengeResult.secondStar = true
                challengeResult.firstAnswer = "42"
                challengeResult.secondAnswer = "24"
            }
            repository.update(challengeResult)
        }
    }

    val challenge: LiveData<Challenge?>
    private val repository: ChallengeRepository

    init {
        val challengeDao: ChallengeDAO =
            ChallengeRoomDatabase.getDatabase(application, viewModelScope).challengeDao()
        repository = ChallengeRepository(challengeDao)
        challenge = repository.challengeByDay(day)
    }
}