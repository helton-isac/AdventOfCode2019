package com.hitg.adventofcode.domain.model.solver

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
        // Get the file from internet: https://adventofcode.com/2019/day/1/input
        return "Test"
    }

    override fun solvePart2(): String {
        return "Test"
    }
}