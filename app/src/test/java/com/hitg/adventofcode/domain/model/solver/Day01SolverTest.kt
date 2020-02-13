package com.hitg.adventofcode.domain.model.solver

import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test

class Day01SolverTest {

    @Test
    fun `should calculate fuel by module`() {
        val input = ""
        val day01Solver = Day01Solver(input)

        assertThat(day01Solver.calculateFuelRequiredForAModule(12), `is`(2))
        assertThat(day01Solver.calculateFuelRequiredForAModule(14), `is`(2))
        assertThat(day01Solver.calculateFuelRequiredForAModule(1969), `is`(654))
        assertThat(day01Solver.calculateFuelRequiredForAModule(100756), `is`(33583))
    }

    @Test
    fun `should calculate the total fuel given a list of module`() {
        val input = ""
        val day01Solver = Day01Solver(input)
        val listMass = listOf(12, 14, 1969, 100756)

        assertThat(day01Solver.calculateFuelRequiredFromAList(listMass), `is`(34241))
    }

    @Test
    fun `should calculate the total recursive fuel given a list of module`() {
        val input = ""
        val day01Solver = Day01Solver(input)
        val listMass = listOf(12, 14, 1969, 100756)

        assertThat(day01Solver.calculateRecursiveFuel(listMass), `is`(51316))
    }

    @Test
    fun `given the input 12 14 1969 100756 it should answer 34241 to the first puzzle`() {
        val input = "12\n14\n1969\n100756"
        val day01Solver = Day01Solver(input)


        assertThat(day01Solver.solvePart1(), `is`("34241"))
    }

    @Test
    fun `given the input 12 14 1969 100756 it should answer 34241 to the second puzzle`() {
        val input = "12\n14\n1969\n100756"
        val day01Solver = Day01Solver(input)



        assertThat(day01Solver.solvePart2(), `is`("51316"))
    }
}