package com.hitg.adventofcode.domain.model.solver

import com.hitg.adventofcode.domain.model.computer.IntCodeComputer

class Day07Solver(private val input: String) : DaySolver {


    override fun solvePart1(): String? {
        var largestOutput = 0
        for (a in 0..4) {
            val ampA = IntCodeComputer(listOf(a, 0))
            val programAmpA = input.trim().split(",") as MutableList<String>
            ampA.executeProgram(programAmpA)
            val outputAmpA = ampA.outputResult
            for (b in 0..4) {
                if (b != a) {
                    val ampB = IntCodeComputer(listOf(b, outputAmpA.toInt()))
                    val programAmpB = input.trim().split(",") as MutableList<String>
                    ampB.executeProgram(programAmpB)
                    val outputAmpB = ampB.outputResult
                    for (c in 0..4) {
                        if (c != a && c != b) {
                            val ampC = IntCodeComputer(listOf(c, outputAmpB.toInt()))
                            val programAmpC = input.trim().split(",") as MutableList<String>
                            ampC.executeProgram(programAmpC)
                            val outputAmpC = ampC.outputResult
                            for (d in 0..4) {
                                if (d != a && d != b && d != c) {
                                    val ampD = IntCodeComputer(listOf(d, outputAmpC.toInt()))
                                    val programAmpD = input.trim().split(",") as MutableList<String>
                                    ampD.executeProgram(programAmpD)
                                    val outputAmpD = ampD.outputResult
                                    for (e in 0..4) {
                                        if (e != a && e != b && e != c && e != d) {
                                            val ampE =
                                                IntCodeComputer(listOf(e, outputAmpD.toInt()))
                                            val programAmpE =
                                                input.trim().split(",") as MutableList<String>
                                            ampE.executeProgram(programAmpE)
                                            val outputAmpE = ampE.outputResult

                                            if (outputAmpE.toInt() > largestOutput) {
                                                largestOutput = outputAmpE.toInt()
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return largestOutput.toString()
    }

    override fun solvePart2(): String? {
        return null
    }
}