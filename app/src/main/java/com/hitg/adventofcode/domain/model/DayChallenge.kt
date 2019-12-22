package com.hitg.adventofcode.domain.model

interface DayChallenge {
    fun getDay(): Int

    fun getTitle(): String

    fun hasFirstStar(): Boolean

    fun hasSecondStar(): Boolean

    fun calculateFirstAnswer(): String

    fun calculateSecondAnswer(): String
}