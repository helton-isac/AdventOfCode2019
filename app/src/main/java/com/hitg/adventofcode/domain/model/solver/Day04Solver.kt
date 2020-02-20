package com.hitg.adventofcode.domain.model.solver

class Day04Solver(input: String) : DaySolver {


    private val initialRange: Int = input.split("-")[0].toInt()
    private val finalRange: Int = input.split("-")[1].trim().toInt()

    fun validatePassword(
        password: String,
        validateFunction: (password: String) -> Boolean
    ): Boolean {
        return isValidLength(password)
                && isLeftRightIncreasing(password)
                && validateFunction(password)
    }

    /**
     * It is a six-digit number.
     * The value is within the range given in your puzzle input.
     * Two adjacent digits are the same (like 22 in 122345).
     * Going from left to right, the digits never decrease;
     * they only ever increase or stay the same (like 111123 or 135679).
     */
    fun validatePasswordForFirstPuzzle(password: String): Boolean {
        var lastLetter = '0'
        var hasAdjacentDigits = false
        for (letter in password) {
            if (letter == lastLetter) {
                hasAdjacentDigits = true
                break
            }
            lastLetter = letter
        }
        return hasAdjacentDigits
    }

    /**
     * The two adjacent matching digits are not part of a larger group of matching digits.
     */
    fun validatePasswordForSecondPuzzle(password: String): Boolean {
        var lastLetter = '0'
        var countEqualsAdjacents = 0
        for (letter in password) {
            if (letter == lastLetter) {
                countEqualsAdjacents++
            } else {
                if (countEqualsAdjacents == 2) {
                    break
                }
                countEqualsAdjacents = 1
            }
            lastLetter = letter
        }
        return countEqualsAdjacents == 2
    }

    private fun isValidLength(password: String): Boolean {
        return password.length == 6
    }

    private fun isLeftRightIncreasing(password: String): Boolean {
        var lastLetter = '0'
        for (currentLetter in password) {
            if (currentLetter < lastLetter) {
                return false
            }
            lastLetter = currentLetter
        }
        return true
    }

    override fun solvePart1(): String? {
        return validatePasswords(this::validatePasswordForFirstPuzzle)
    }

    override fun solvePart2(): String? {
        return validatePasswords(this::validatePasswordForSecondPuzzle)
    }

    private fun validatePasswords(validateFunction: (password: String) -> Boolean): String {
        var count = 0
        var passwordCandidate = initialRange
        while (passwordCandidate <= finalRange) {

            passwordCandidate = getNextValidPassword(passwordCandidate)
            if (passwordCandidate > finalRange) {
                break
            }
            if (validatePassword(passwordCandidate.toString(), validateFunction)) {
                count++
            }
            passwordCandidate++
        }
        return count.toString()
    }

    private fun getNextValidPassword(password: Int): Int {
        return when {
            password.toString()[0] > password.toString()[1] -> {
                (password.toString()[0].toString()
                        + password.toString()[0].toString()
                        + password.toString()[0].toString()
                        + password.toString()[0].toString()
                        + password.toString()[0].toString()
                        + password.toString()[0].toString()).toInt()
            }
            password.toString()[1] > password.toString()[2] -> {
                (password.toString()[0].toString()
                        + password.toString()[1].toString()
                        + password.toString()[1].toString()
                        + password.toString()[1].toString()
                        + password.toString()[1].toString()
                        + password.toString()[1].toString()).toInt()
            }
            password.toString()[2] > password.toString()[3] -> {
                (password.toString()[0].toString()
                        + password.toString()[1].toString()
                        + password.toString()[2].toString()
                        + password.toString()[2].toString()
                        + password.toString()[2].toString()
                        + password.toString()[2].toString()).toInt()
            }
            password.toString()[3] > password.toString()[4] -> {
                (password.toString()[0].toString()
                        + password.toString()[1].toString()
                        + password.toString()[2].toString()
                        + password.toString()[3].toString()
                        + password.toString()[3].toString()
                        + password.toString()[3].toString()).toInt()
            }
            password.toString()[4] > password.toString()[5] -> {
                (password.toString()[0].toString()
                        + password.toString()[1].toString()
                        + password.toString()[2].toString()
                        + password.toString()[3].toString()
                        + password.toString()[4].toString()
                        + password.toString()[4].toString()).toInt()
            }
            else -> {
                password
            }
        }
    }

}
