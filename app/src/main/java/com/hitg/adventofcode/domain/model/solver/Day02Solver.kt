package com.hitg.adventofcode.domain.model.solver

import com.hitg.adventofcode.domain.model.computer.IntCodeComputer

class Day02Solver(private val input: String) : DaySolver {

    private val computer = IntCodeComputer()

    override fun solvePart1(): String? {
        val program = input.split(",") as MutableList<String>
        // before running the program, replace position 1 with the value 12
        // and replace position 2 with the value 2.
        program[1] = "12"
        program[2] = "2"

        return computer.executeProgram(program)
    }

    override fun solvePart2(): String? {

        for (i in 0..100) {
            for (j in 0..100) {
                val program = input.split(",") as MutableList<String>
                program[1] = i.toString()
                program[2] = j.toString()
                if (computer.executeProgram(program) == "19690720") {
                    return (100 * i + j).toString()
                }
            }
        }

        return "Undefined"
    }
}