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

    val challenge: LiveData<Challenge?>
    private val repository: ChallengeRepository

    init {
        val challengeDao: ChallengeDAO =
                ChallengeRoomDatabase.getDatabase(application, viewModelScope).challengeDao()
        repository = ChallengeRepository(challengeDao)
        challenge = repository.challengeByDay(day)
    }
}