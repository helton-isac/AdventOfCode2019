package com.hitg.adventofcode.domain.model.computer

import com.hitg.adventofcode.domain.model.computer.IntCodeComputer.OpCode.*

class IntCodeComputer {

    enum class OpCode(val value: Int) {
        ADDS(1),
        MULTIPLIES(2),
        FINISHED(99)
    }

    private fun adds(
        program: MutableList<String>,
        positionFirstValue: Int,
        positionSecondValue: Int,
        positionToStore: Int
    ) {
        val firstValue = program[positionFirstValue].toInt()
        val secondValue = program[positionSecondValue].toInt()
        program[positionToStore] = (firstValue + secondValue).toString()
    }

    private fun multiplies(
        program: MutableList<String>,
        positionFirstValue: Int,
        positionSecondValue: Int,
        positionToStore: Int
    ) {
        val firstValue = program[positionFirstValue].toInt()
        val secondValue = program[positionSecondValue].toInt()
        program[positionToStore] = (firstValue * secondValue).toString()
    }

    fun executeProgram(program: MutableList<String>): String {
        var position = 0
        while (program[position].toInt() != FINISHED.value) {

            when (program[position].toInt()) {
                ADDS.value ->
                    adds(
                        program,
                        program[position + 1].toInt(),
                        program[position + 2].toInt(),
                        program[position + 3].toInt()
                    )
                MULTIPLIES.value ->
                    multiplies(
                        program,
                        program[position + 1].toInt(),
                        program[position + 2].toInt(),
                        program[position + 3].toInt()
                    )
                else -> throw Exception("Unknown Opcode")
            }
            position += 4
        }

        return program[0]
    }
}