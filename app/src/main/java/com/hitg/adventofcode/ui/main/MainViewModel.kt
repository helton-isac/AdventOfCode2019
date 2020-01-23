package com.hitg.adventofcode.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.hitg.adventofcode.domain.model.entity.Challenge
import com.hitg.adventofcode.repository.ChallengeRepository
import com.hitg.adventofcode.repository.database.ChallengeDAO
import com.hitg.adventofcode.repository.database.ChallengeRoomDatabase

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ChallengeRepository

    val allChallenges: LiveData<List<Challenge>>

    private val challengeDao: ChallengeDAO =
            ChallengeRoomDatabase.getDatabase(application, viewModelScope).challengeDao()

    init {
        repository = ChallengeRepository(challengeDao)
        allChallenges = repository.allChallenges
    }

}
