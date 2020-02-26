package com.hitg.adventofcode.domain.model.computer

import org.hamcrest.CoreMatchers
import org.junit.Assert.assertThat
import org.junit.Assert.fail
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

    @Test
    fun `given the instructions 3,0,4,0,99 and the input 10 it should compute and outputs 10`() {
        val input = "3,0,4,0,99"
        val computer = IntCodeComputer(10)
        computer.executeProgram(input.split(",") as MutableList<String>)

        assertThat(
            computer.outputResult,
            CoreMatchers.`is`("10")
        )
    }

    @Test
    fun `given the instructions 1002,4,3,4,33 and no input it should compute 99 and store in position 10`() {
        val input = mutableListOf("1002", "4", "3", "4", "33")
        val computer = IntCodeComputer()
        computer.executeProgram(input)

        assertThat(
            input[4],
            CoreMatchers.`is`("99")
        )
    }

    @Test
    fun `3,9,8,9,10,9,4,9,99,-1,8 - Using position mode, consider whether the input is equal to 8 - output 1 (if it is) or 0 (if it is not)`() {
        val input = mutableListOf("3", "9", "8", "9", "10", "9", "4", "9", "99", "-1", "8")
        for (i in 0..10) {
            val computer = IntCodeComputer(i)
            computer.executeProgram(input)

            if (i != 8) {
                assertThat(
                    computer.outputResult,
                    CoreMatchers.`is`("0")
                )
            } else {
                assertThat(
                    computer.outputResult,
                    CoreMatchers.`is`("1")
                )
            }
        }
    }


    @Test
    fun `3,9,7,9,10,9,4,9,99,-1,8 - Using position mode, consider whether the input is less than 8 - output 1 (if it is) or 0 (if it is not)`() {
        val input = mutableListOf("3", "9", "7", "9", "10", "9", "4", "9", "99", "-1", "8")
        for (i in 0..10) {
            val computer = IntCodeComputer(i)
            computer.executeProgram(input)

            if (i >= 8) {
                assertThat(
                    computer.outputResult,
                    CoreMatchers.`is`("0")
                )
            } else {
                assertThat(
                    computer.outputResult,
                    CoreMatchers.`is`("1")
                )
            }
        }
    }

    @Test
    fun `3,3,1108,-1,8,3,4,3,99 - Using immediate mode, consider whether the input is equal to 8 - output 1 (if it is) or 0 (if it is not)`() {
        val input = mutableListOf("3", "3", "1108", "-1", "8", "3", "4", "3", "99")
        for (i in 0..10) {
            val computer = IntCodeComputer(i)
            computer.executeProgram(input)

            if (i == 8) {
                assertThat(
                    computer.outputResult,
                    CoreMatchers.`is`("1")
                )
            } else {
                assertThat(
                    computer.outputResult,
                    CoreMatchers.`is`("0")
                )
            }
        }
    }

    @Test
    fun `3,3,1107,-1,8,3,4,3,99 - Using immediate mode, consider whether the input is less than 8 - output 1 (if it is) or 0 (if it is not)`() {
        val input = mutableListOf("3", "3", "1107", "-1", "8", "3", "4", "3", "99")
        for (i in 0..10) {
            val computer = IntCodeComputer(i)
            computer.executeProgram(input)

            if (i < 8) {
                assertThat(
                    computer.outputResult,
                    CoreMatchers.`is`("1")
                )
            } else {
                assertThat(
                    computer.outputResult,
                    CoreMatchers.`is`("0")
                )
            }
        }
    }

    @Test
    fun `3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9 - jump tests that take an input, then output 0 if the input was zero or 1 if the input was non-zero`() {
        val input = mutableListOf(3, 12, 6, 12, 15, 1, 13, 14, 13, 4, 13, 99, -1, 0, 1, 9)
        for (i in 0..10) {
            val computer = IntCodeComputer(i)
            computer.executeProgram(input.map { it.toString() }.toMutableList())

            if (i == 0) {
                assertThat(
                    computer.outputResult,
                    CoreMatchers.`is`("0")
                )
            } else {
                assertThat(
                    computer.outputResult,
                    CoreMatchers.`is`("1")
                )
            }
        }
    }

    @Test
    fun `3,3,1105,-1,9,1101,0,0,12,4,12,99,1 - jump tests that take an input, then output 0 if the input was zero or 1 if the input was non-zero`() {
        val input = mutableListOf(3, 3, 1105, -1, 9, 1101, 0, 0, 12, 4, 12, 99, 1)
        for (i in 0..10) {
            val computer = IntCodeComputer(i)
            computer.executeProgram(input.map { it.toString() }.toMutableList())

            if (i == 0) {
                assertThat(
                    computer.outputResult,
                    CoreMatchers.`is`("0")
                )
            } else {
                assertThat(
                    computer.outputResult,
                    CoreMatchers.`is`("1")
                )
            }
        }
    }

    @Test
    fun `Large Example - Given an Input - output 999 if value is below 8 - 1000 if value is equal 8 or 1001 if greater than 8`() {
        val input = mutableListOf(
            3, 21, 1008, 21, 8, 20, 1005, 20, 22, 107, 8, 21, 20, 1006, 20, 31,
            1106, 0, 36, 98, 0, 0, 1002, 21, 125, 20, 4, 20, 1105, 1, 46, 104,
            999, 1105, 1, 46, 1101, 1000, 1, 20, 4, 20, 1105, 1, 46, 98, 99
        )
        for (i in 0..10) {
            val computer = IntCodeComputer(i)
            computer.executeProgram(input.map { it.toString() }.toMutableList())

            when {
                i < 8 -> {
                    assertThat(
                        computer.outputResult,
                        CoreMatchers.`is`("999")
                    )
                }
                i == 8 -> {
                    assertThat(
                        computer.outputResult,
                        CoreMatchers.`is`("1000")
                    )
                }
                i > 8 -> {
                    assertThat(
                        computer.outputResult,
                        CoreMatchers.`is`("1001")
                    )
                }
                else -> {
                    fail("Unexpected Output")
                }
            }
        }
    }
}