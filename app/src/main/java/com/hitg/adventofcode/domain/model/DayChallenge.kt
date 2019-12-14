package com.hitg.adventofcode.domain.model

interface DayChallenge {
    fun getDay(): Int

    fun getTitle(): String

    fun hasFirstStar(): Boolean = false

    fun hasSecondStar(): Boolean = false
}