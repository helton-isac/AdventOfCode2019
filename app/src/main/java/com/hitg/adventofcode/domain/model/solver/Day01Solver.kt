package com.hitg.adventofcode.domain.model.solver

import java.util.*


class Day01Solver(private val input: String) : DaySolver {

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


    override fun solvePart1(): String? {
        val scanner = Scanner(input)
        val listModuleMass = mutableListOf<Int>()
        while (scanner.hasNext()) {
            listModuleMass.add(scanner.nextInt())
        }
        scanner.close()
        return calculateFuelRequiredFromAList(listModuleMass).toString()
    }

    fun calculateRecursiveFuel(listModuleMass: List<Int>): Int {
        var result = 0
        for (mass in listModuleMass) {
            var partialResult = calculateFuelRequiredForAModule(mass)
            while (partialResult > 0) {
                result += partialResult
                partialResult = calculateFuelRequiredForAModule(partialResult)
            }
        }
        return result
    }

    override fun solvePart2(): String? {
        val scanner = Scanner(input)
        val listModuleMass = mutableListOf<Int>()
        while (scanner.hasNext()) {
            listModuleMass.add(scanner.nextInt())
        }
        scanner.close()
        return calculateRecursiveFuel(listModuleMass).toString()
    }
}