package com.hitg.adventofcode.ui.challenge

import android.app.Application
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ChallengeViewModelModelFactory(private val day: Int,
                                     @NonNull
                                     private val fragment: Fragment) :
        ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val application = checkApplication(checkActivity(fragment))

        @Suppress("UNCHECKED_CAST")
        return ChallengeViewModel(day, application) as T
    }

    private fun checkApplication(activity: FragmentActivity): Application {
        return activity.application
                ?: throw IllegalStateException("Your activity/fragment is not yet attached to "
                        + "Application. You can't request ViewModel before onCreate call.")
    }

    private fun checkActivity(fragment: Fragment): FragmentActivity {
        return fragment.activity
                ?: throw java.lang.IllegalStateException("Can't create ViewModelProvider for detached fragment")
    }
}

