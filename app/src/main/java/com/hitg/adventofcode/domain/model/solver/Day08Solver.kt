package com.hitg.adventofcode.domain.model.solver

class Day08Solver(private val input: String) : DaySolver {

    override fun solvePart1(): String? {
        return checkSum(25 * 6, input)
    }

    @Suppress("SameParameterValue")
    fun checkSum(pixelsByLayers: Int, imageString: String): String {
        var currentPixel = 1
        var current0 = 0
        var current1 = 0
        var current2 = 0
        var fewest0 = Int.MAX_VALUE
        var currentResult = 0
        var i = 0
        while (i < imageString.length) {
            when (imageString[i]) {
                '0' -> current0++
                '1' -> current1++
                '2' -> current2++
            }
            if (currentPixel == pixelsByLayers) {
                if (current0 < fewest0) {
                    fewest0 = current0
                    currentResult = current1 * current2
                }
                current0 = 0
                current1 = 0
                current2 = 0
                currentPixel = 0
            }
            currentPixel++
            i++
        }
        return currentResult.toString()
    }

    override fun solvePart2(): String? {
        return null
    }
}