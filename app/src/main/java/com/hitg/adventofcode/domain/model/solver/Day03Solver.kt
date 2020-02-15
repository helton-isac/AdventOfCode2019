package com.hitg.adventofcode.domain.model.solver

import java.util.*
import kotlin.math.abs
import kotlin.math.min

class Day03Solver(input: String) : DaySolver {

    private lateinit var firstWireDirectionInstruction: List<String>
    private lateinit var secondWireDirectionInstruction: List<String>

    init {
        val sc = Scanner(input)
        sc.use {
            val delimiters = ","
            firstWireDirectionInstruction = it.nextLine().split(delimiters)
            secondWireDirectionInstruction = it.nextLine().split(delimiters)
        }
    }


    override fun solvePart1(): String? {
        val firsWirePath = createPathFirstWire(firstWireDirectionInstruction)
        return findClosestIntersection(secondWireDirectionInstruction, firsWirePath)
    }

    private fun getManhattanDistance(horizontal: Int, vertical: Int): Int {
        return abs(horizontal) + abs(vertical)
    }

    private fun findClosestIntersection(
        wireInstructions: List<String>,
        pathSet: MutableSet<String>
    ): String {
        var minimumManhattanDistanceDistance = Int.MAX_VALUE
        var currentHorizontal = 0
        var currentVertical = 0
        for (instruction in wireInstructions) {
            val direction = instruction.substring(0, 1)
            val amount = instruction.substring(1).toInt()
            when (direction) {
                "U" -> {
                    repeat(amount) {
                        currentVertical++
                        minimumManhattanDistanceDistance = checkMinimumManhattanDistance(
                            pathSet,
                            currentHorizontal,
                            currentVertical,
                            minimumManhattanDistanceDistance
                        )
                    }
                }
                "D" -> {
                    repeat(amount) {
                        currentVertical--
                        minimumManhattanDistanceDistance = checkMinimumManhattanDistance(
                            pathSet,
                            currentHorizontal,
                            currentVertical,
                            minimumManhattanDistanceDistance
                        )
                    }
                }
                "R" -> {
                    repeat(amount) {
                        currentHorizontal++
                        minimumManhattanDistanceDistance = checkMinimumManhattanDistance(
                            pathSet,
                            currentHorizontal,
                            currentVertical,
                            minimumManhattanDistanceDistance
                        )
                    }
                }
                "L" -> {
                    repeat(amount) {
                        currentHorizontal--
                        minimumManhattanDistanceDistance = checkMinimumManhattanDistance(
                            pathSet,
                            currentHorizontal,
                            currentVertical,
                            minimumManhattanDistanceDistance
                        )
                    }
                }
            }
        }
        return minimumManhattanDistanceDistance.toString()
    }

    private fun checkMinimumManhattanDistance(
        pathSet: MutableSet<String>,
        currentHorizontal: Int,
        currentVertical: Int,
        minimumManhattanDistanceDistance: Int
    ): Int {
        if (pathSet.contains("[${currentHorizontal},${currentVertical}]")) {
            return min(
                minimumManhattanDistanceDistance,
                getManhattanDistance(currentHorizontal, currentVertical)
            )

        }
        return minimumManhattanDistanceDistance
    }

    private fun createPathFirstWire(wire: List<String>): MutableSet<String> {
        var horizontal = 0
        var vertical = 0
        val set = mutableSetOf<String>()
        for (instruction in wire) {
            val direction = instruction.substring(0, 1)
            val amount = instruction.substring(1).toInt()
            when (direction) {
                "U" -> {
                    repeat(amount) {
                        vertical++
                        set.add("[${horizontal},${vertical}]")
                    }
                }
                "D" -> {
                    repeat(amount) {
                        vertical--
                        set.add("[${horizontal},${vertical}]")
                    }
                }
                "R" -> {
                    repeat(amount) {
                        horizontal++
                        set.add("[${horizontal},${vertical}]")
                    }
                }
                "L" -> {
                    repeat(amount) {
                        horizontal--
                        set.add("[${horizontal},${vertical}]")
                    }
                }
            }
        }
        return set
    }

    override fun solvePart2(): String? {
        return null
    }
}