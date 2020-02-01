package com.hitg.adventofcode.domain.model.solver

import android.content.res.Resources
import com.hitg.adventofcode.R
import java.io.InputStream

class SolverFactory {

    companion object {
        fun createSolver(res: Resources, day: Int): DaySolver {

            return when (day) {
                1 -> Day01Solver(readFromFile(res, R.raw.input1))
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

        private fun readFromFile(res: Resources, id: Int): String {
            try {
                val in_s: InputStream = res.openRawResource(id)
                val b = ByteArray(in_s.available())
                in_s.read(b)
                val c = String(b)
                return c
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return ""
        }
    }
}