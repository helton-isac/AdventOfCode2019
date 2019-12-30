package com.hitg.adventofcode.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.hitg.adventofcode.domain.model.Challenge
import com.hitg.adventofcode.repository.ChallengeRepository
import com.hitg.adventofcode.repository.database.ChallengeRoomDatabase
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ChallengeRepository

    val allChallenges: LiveData<List<Challenge>>

    init {
        val challengeDao =
            ChallengeRoomDatabase.getDatabase(application, viewModelScope).challengeDao()
        repository = ChallengeRepository(challengeDao)
        allChallenges = repository.allChallenges
    }

    fun update(challenge: Challenge) = viewModelScope.launch {
        repository.update(challenge)
    }
}
