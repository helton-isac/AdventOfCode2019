package com.hitg.adventofcode.domain.model.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "challenge")
data class Challenge(
    @PrimaryKey val day: Int,
    @NonNull val name: String,
    var firstStar: Boolean = false,
    var secondStar: Boolean = false,
    var firstAnswer: String = "",
    var secondAnswer: String = ""
)