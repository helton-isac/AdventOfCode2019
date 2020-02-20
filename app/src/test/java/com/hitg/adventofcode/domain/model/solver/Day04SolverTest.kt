package com.hitg.adventofcode.domain.model.solver

import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test

class Day04SolverTest {

    private val input = "206938-679128"

    @Test
    fun `given my input it should answer 1653 to the first puzzle`() {
        val day04Solver = Day04Solver(input)

        assertThat(day04Solver.solvePart1(), `is`("1653"))
    }

    @Test
    fun `given my input it should answer 1133 to the second puzzle`() {
        val day04Solver = Day04Solver(input)

        assertThat(day04Solver.solvePart2(), `is`("1133"))
    }

    @Test
    fun `given 111111 the validatePasswordForFirstPuzzle method must return true`() {
        val day04Solver = Day04Solver(input)

        assertThat(
            day04Solver.validatePassword(
                "111111",
                day04Solver::validatePasswordForFirstPuzzle
            ), `is`(true)
        )
    }

    @Test
    fun `given 223450 the validatePasswordForFirstPuzzle method must return false`() {
        val day04Solver = Day04Solver(input)

        assertThat(
            day04Solver.validatePassword(
                "223450",
                day04Solver::validatePasswordForFirstPuzzle
            ), `is`(false)
        )
    }


    @Test
    fun `given 123789 the validatePasswordForFirstPuzzle method must return false`() {
        val day04Solver = Day04Solver(input)

        assertThat(
            day04Solver.validatePassword(
                "123789",
                day04Solver::validatePasswordForFirstPuzzle
            ), `is`(false)
        )
    }

    @Test
    fun `given 112233 the validatePasswordForSecondPuzzle method must return true`() {
        val day04Solver = Day04Solver(input)

        assertThat(
            day04Solver.validatePassword(
                "112233",
                day04Solver::validatePasswordForSecondPuzzle
            ), `is`(true)
        )
    }

    @Test
    fun `given 123444 the validatePasswordForSecondPuzzle method must return false`() {
        val day04Solver = Day04Solver(input)

        assertThat(
            day04Solver.validatePassword(
                "123444",
                day04Solver::validatePasswordForSecondPuzzle
            ), `is`(false)
        )
    }

    @Test
    fun `given 111122 the validatePasswordForSecondPuzzle method must return true`() {
        val day04Solver = Day04Solver(input)

        assertThat(
            day04Solver.validatePassword(
                "111122",
                day04Solver::validatePasswordForSecondPuzzle
            ), `is`(true)
        )
    }
}