package com.hitg.adventofcode.domain.model.solver

import android.content.res.Resources
import com.hitg.adventofcode.R
import java.io.InputStream

class SolverFactory {

    companion object {
        fun createSolver(res: Resources, day: Int): DaySolver {

            return when (day) {
                1 -> Day01Solver(readFromFile(res, R.raw.input1))
                2 -> Day02Solver(readFromFile(res, R.raw.input2))
                3 -> Day03Solver(readFromFile(res, R.raw.input3))
                4 -> Day04Solver(readFromFile(res, R.raw.input4))
                5 -> Day05Solver(readFromFile(res, R.raw.input5))
                6 -> Day06Solver(readFromFile(res, R.raw.input6))
                7 -> Day07Solver(readFromFile(res, R.raw.input7))
                8 -> Day08Solver(readFromFile(res, R.raw.input8))
                9 -> Day09Solver(readFromFile(res, R.raw.input9))
                10 -> Day10Solver(readFromFile(res, R.raw.input10))
                11 -> Day11Solver(readFromFile(res, R.raw.input11))
                12 -> Day12Solver(readFromFile(res, R.raw.input12))
                13 -> Day13Solver(readFromFile(res, R.raw.input13))
                14 -> Day14Solver(readFromFile(res, R.raw.input14))
                15 -> Day15Solver(readFromFile(res, R.raw.input15))
                16 -> Day16Solver(readFromFile(res, R.raw.input16))
                17 -> Day17Solver(readFromFile(res, R.raw.input17))
                18 -> Day18Solver(readFromFile(res, R.raw.input18))
                19 -> Day19Solver(readFromFile(res, R.raw.input19))
                20 -> Day20Solver(readFromFile(res, R.raw.input20))
                21 -> Day21Solver(readFromFile(res, R.raw.input21))
                22 -> Day22Solver(readFromFile(res, R.raw.input22))
                23 -> Day23Solver(readFromFile(res, R.raw.input23))
                24 -> Day24Solver(readFromFile(res, R.raw.input24))
                25 -> Day25Solver(readFromFile(res, R.raw.input25))
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