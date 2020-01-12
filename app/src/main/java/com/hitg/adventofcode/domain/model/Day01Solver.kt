package com.hitg.adventofcode.domain.model

class Day01Solver : DaySolver {
    fun calculateFuelRequiredForAModule(mass: Int): Int {
        return (mass / 3) - 2
    }

    fun calculateFuelRequiredFromAList(listModuleMass: List<Int>): Int {
        var result = 0
        for (mass in listModuleMass) {
            result += calculateFuelRequiredForAModule(mass)
        }
        return result
    }

    override fun solvePart1(): String {
        return "Test"
    }

    override fun solvePart2(): String {
        return "Test"
    }
}