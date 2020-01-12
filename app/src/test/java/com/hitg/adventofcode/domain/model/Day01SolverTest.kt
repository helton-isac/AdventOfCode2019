package com.hitg.adventofcode.domain.model

import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test

class Day01SolverTest {

    @Test
    fun `should calculate fuel by module`() {
        val day01Solver = Day01Solver()

        assertThat(day01Solver.calculateFuelRequiredForAModule(12), `is`(2))
        assertThat(day01Solver.calculateFuelRequiredForAModule(14), `is`(2))
        assertThat(day01Solver.calculateFuelRequiredForAModule(1969), `is`(654))
        assertThat(day01Solver.calculateFuelRequiredForAModule(100756), `is`(33583))
    }

    @Test
    fun `should calculate the total fuel given a list of module`() {
        val day01Solver = Day01Solver()
        val listMass = listOf(12, 14, 1969, 100756)

        assertThat(day01Solver.calculateFuelRequiredFromAList(listMass), `is`(34241))
    }
}