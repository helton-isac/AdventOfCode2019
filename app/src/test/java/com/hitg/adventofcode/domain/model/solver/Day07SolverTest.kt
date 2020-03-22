package com.hitg.adventofcode.domain.model.solver

import org.hamcrest.CoreMatchers
import org.junit.Assert.assertThat
import org.junit.Test

class Day07SolverTest {

    @Test
    fun `given the input 3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0 it should answer 43210 to the first puzzle`() {
        val input = "3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0"
        val day07Solver = Day07Solver(input)

        assertThat(day07Solver.solvePart1(), CoreMatchers.`is`("43210"))
    }

    @Test
    fun `given the input 3,23,3,24,1002,24,10,24,1002,23,-1,23,101,5,23,23,1,24,23,23,4,23,99,0,0 it should answer 54321 to the first puzzle`() {
        val input = "3,23,3,24,1002,24,10,24,1002,23,-1,23,101,5,23,23,1,24,23,23,4,23,99,0,0"
        val day07Solver = Day07Solver(input)

        assertThat(day07Solver.solvePart1(), CoreMatchers.`is`("54321"))
    }

    @Test
    fun `given the input 3,31,3,32,1002,32,10,32,1001,31,-2,31,1007,31,0,33,1002,33,7,33,1,33,31,31,1,32,31,31,4,31,99,0,0,0 it should answer 65210 to the first puzzle`() {
        val input =
            "3,31,3,32,1002,32,10,32,1001,31,-2,31,1007,31,0,33,1002,33,7,33,1,33,31,31,1,32,31,31,4,31,99,0,0,0"
        val day07Solver = Day07Solver(input)

        assertThat(day07Solver.solvePart1(), CoreMatchers.`is`("65210"))
    }

}