package com.hitg.adventofcode.domain.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "challenge")
data class Challenge(
    @PrimaryKey val day: Int,
    @NonNull val name: String,
    val firstStar: Boolean = false,
    val secondStar: Boolean = false,
    val firstAnswer: String = "",
    val secondAnswer: String = ""
)