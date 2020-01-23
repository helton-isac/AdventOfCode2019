package com.hitg.adventofcode.domain.model.solver

class SolverFactory {

    companion object {
        fun createSolver(day: Int): DaySolver {
            return when (day) {
                1 -> Day01Solver()
                2 -> Day02Solver()
                3 -> Day03Solver()
                4 -> Day04Solver()
                5 -> Day05Solver()
                6 -> Day06Solver()
                7 -> Day07Solver()
                8 -> Day08Solver()
                9 -> Day09Solver()
                10 -> Day10Solver()
                11 -> Day11Solver()
                12 -> Day12Solver()
                13 -> Day13Solver()
                14 -> Day14Solver()
                15 -> Day15Solver()
                16 -> Day16Solver()
                17 -> Day17Solver()
                18 -> Day18Solver()
                19 -> Day19Solver()
                20 -> Day20Solver()
                21 -> Day21Solver()
                22 -> Day22Solver()
                23 -> Day23Solver()
                24 -> Day24Solver()
                25 -> Day25Solver()
                else -> throw Exception("Day Not Found")
            }
        }
    }
}