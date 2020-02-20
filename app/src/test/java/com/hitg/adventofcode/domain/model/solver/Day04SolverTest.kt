package com.hitg.adventofcode.domain.model.solver

import org.hamcrest.CoreMatchers
import org.junit.Assert.assertThat
import org.junit.Test

class Day04SolverTest {

    private val input = "206938-679128"

    @Test
    fun `given my input it should answer 1653 to the first puzzle`() {
        val day04Solver = Day04Solver(input)

        assertThat(day04Solver.solvePart1(), CoreMatchers.`is`("1653"))
    }
}