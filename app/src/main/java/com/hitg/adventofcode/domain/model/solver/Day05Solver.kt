package com.hitg.adventofcode.domain.model.solver

import com.hitg.adventofcode.domain.model.computer.IntCodeComputer

class Day05Solver(private val input: String) : DaySolver {

    override fun solvePart1(): String? {
        val computer = IntCodeComputer(listOf(1))
        val program = input.trim().split(",") as MutableList<String>
        computer.executeProgram(program)
        return computer.outputResult
    }

    override fun solvePart2(): String? {
        val computer = IntCodeComputer(listOf(5))
        val program = input.trim().split(",") as MutableList<String>
        computer.executeProgram(program)
        return computer.outputResult
    }
}