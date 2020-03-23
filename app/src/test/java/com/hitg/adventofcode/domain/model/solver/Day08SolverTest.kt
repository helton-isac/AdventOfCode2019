package com.hitg.adventofcode.domain.model.solver

import org.hamcrest.CoreMatchers
import org.junit.Assert.assertThat
import org.junit.Test

class Day08SolverTest {

    @Test
    fun `given an image 3x2 123456789012 it should answer 1 to the checksum`() {
        val input = "123456789012"
        val day08Solver = Day08Solver(input)

        assertThat(day08Solver.checkSum(3 * 2, input), CoreMatchers.`is`("1"))
    }
}