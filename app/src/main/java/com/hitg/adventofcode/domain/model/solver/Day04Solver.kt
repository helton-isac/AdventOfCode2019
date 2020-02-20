package com.hitg.adventofcode.domain.model.solver

class Day04Solver(input: String) : DaySolver {


    private val initialRange: Int = input.split("-")[0].toInt()
    private val finalRange: Int = input.split("-")[1].trim().toInt()

    override fun solvePart1(): String? {
        var count = 0
        var i = initialRange
        while (i <= finalRange) {

            when {
                i.toString()[0] > i.toString()[1] -> {
                    i = (i.toString()[0].toString()
                            + i.toString()[0].toString()
                            + i.toString()[0].toString()
                            + i.toString()[0].toString()
                            + i.toString()[0].toString()
                            + i.toString()[0].toString()).toInt()
                }
                i.toString()[1] > i.toString()[2] -> {
                    i = (i.toString()[0].toString()
                            + i.toString()[1].toString()
                            + i.toString()[1].toString()
                            + i.toString()[1].toString()
                            + i.toString()[1].toString()
                            + i.toString()[1].toString()).toInt()
                }
                i.toString()[2] > i.toString()[3] -> {
                    i = (i.toString()[0].toString()
                            + i.toString()[1].toString()
                            + i.toString()[2].toString()
                            + i.toString()[2].toString()
                            + i.toString()[2].toString()
                            + i.toString()[2].toString()).toInt()
                }
                i.toString()[3] > i.toString()[4] -> {
                    i = (i.toString()[0].toString()
                            + i.toString()[1].toString()
                            + i.toString()[2].toString()
                            + i.toString()[3].toString()
                            + i.toString()[3].toString()
                            + i.toString()[3].toString()).toInt()
                }
                i.toString()[4] > i.toString()[5] -> {
                    i = (i.toString()[0].toString()
                            + i.toString()[1].toString()
                            + i.toString()[2].toString()
                            + i.toString()[3].toString()
                            + i.toString()[4].toString()
                            + i.toString()[4].toString()).toInt()
                }
            }
            if (i > finalRange) {
                break
            }
            if (validatePassword(i.toString())) {
                count++
            }
            i++
        }
        return count.toString()
    }

    /**
     * It is a six-digit number.
     * The value is within the range given in your puzzle input.
     * Two adjacent digits are the same (like 22 in 122345).
     * Going from left to right, the digits never decrease;
     * they only ever increase or stay the same (like 111123 or 135679).
     */
    private fun validatePassword(password: String): Boolean {
        val isSixDigit = password.length == 6
        var lastLetter = '0'
        var hasAdjacentDigits = false
        for (letter in password) {
            if (letter == lastLetter) {
                hasAdjacentDigits = true
                break
            }
            lastLetter = letter
        }
        return hasAdjacentDigits && isSixDigit
    }


    override fun solvePart2(): String? {
        return null
    }
}
