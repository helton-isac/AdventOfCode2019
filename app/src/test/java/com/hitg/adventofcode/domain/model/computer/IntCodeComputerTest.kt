package com.hitg.adventofcode.domain.model.computer

import org.hamcrest.CoreMatchers
import org.junit.Assert.assertThat
import org.junit.Test

class IntCodeComputerTest {


    @Test
    fun `given the input 1,9,10,3,2,3,11,0,99,30,40,50 it should answer 3500 when Executing the program`() {
        val input = "1,9,10,3,2,3,11,0,99,30,40,50"
        val computer = IntCodeComputer()

        assertThat(
            computer.executeProgram(input.split(",") as MutableList<String>),
            CoreMatchers.`is`("3500")
        )
    }

    @Test
    fun `given the input 1,0,0,0,99 it should answer 2 when Executing the program`() {
        val input = "1,0,0,0,99"
        val computer = IntCodeComputer()

        assertThat(
            computer.executeProgram(input.split(",") as MutableList<String>),
            CoreMatchers.`is`("2")
        )
    }

    @Test
    fun `given the input 2,3,0,3,99 it should answer 2 when Executing the program`() {
        val input = "2,3,0,3,99"
        val computer = IntCodeComputer()

        assertThat(
            computer.executeProgram(input.split(",") as MutableList<String>),
            CoreMatchers.`is`("2")
        )
    }

    @Test
    fun `given the input 2,4,4,5,99,0 it should answer 2 when Executing the program`() {
        val input = "2,4,4,5,99,0"
        val computer = IntCodeComputer()

        assertThat(
            computer.executeProgram(input.split(",") as MutableList<String>),
            CoreMatchers.`is`("2")
        )
    }

    @Test
    fun `given the input 1,1,1,4,99,5,6,0,99 it should answer 30 when Executing the program`() {
        val input = "1,1,1,4,99,5,6,0,99"
        val computer = IntCodeComputer()

        assertThat(
            computer.executeProgram(input.split(",") as MutableList<String>),
            CoreMatchers.`is`("30")
        )
    }
}