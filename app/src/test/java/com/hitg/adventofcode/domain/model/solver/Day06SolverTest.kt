package com.hitg.adventofcode.domain.model.solver

import org.hamcrest.CoreMatchers
import org.junit.Assert.assertThat
import org.junit.Test

class Day06SolverTest {
    private val input = "COM)B\n" +
            "B)C\n" +
            "C)D\n" +
            "D)E\n" +
            "E)F\n" +
            "B)G\n" +
            "G)H\n" +
            "D)I\n" +
            "E)J\n" +
            "J)K\n" +
            "K)L"

    private val secondInput = "COM)B\n" +
            "B)C\n" +
            "C)D\n" +
            "D)E\n" +
            "E)F\n" +
            "B)G\n" +
            "G)H\n" +
            "D)I\n" +
            "E)J\n" +
            "J)K\n" +
            "K)L\n" +
            "K)YOU\n" +
            "I)SAN"

    @Test
    fun `given my input it should answer 42 to the first puzzle`() {
        val day06Solver = Day06Solver(input)

        assertThat(day06Solver.solvePart1(), CoreMatchers.`is`("42"))
    }

    @Test
    fun `given my new input it should answer 4 to the second puzzle`() {
        val day06Solver = Day06Solver(secondInput)

        assertThat(day06Solver.solvePart2(), CoreMatchers.`is`("4"))
    }
}