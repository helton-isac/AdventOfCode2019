package com.hitg.adventofcode.domain.model.computer

import com.hitg.adventofcode.domain.model.computer.IntCodeComputer.OpCode.*

class IntCodeComputer(var input: Int? = null) {

    enum class ParameterMode(val value: Int) {
        POSITION_MODE(0),
        IMMEDIATE_MODE(1)
    }

    enum class OpCode(val value: Int) {
        ADDS(1),
        MULTIPLIES(2),
        INPUT(3),
        OUTPUT(4),
        FINISHED(99)
    }

    var outputResult: String = ""

    private fun adds(
        program: MutableList<String>,
        parametersMode: List<Int>,
        positionFirstValue: Int,
        positionSecondValue: Int,
        positionToStore: Int
    ) {
        val firstValue = getParameterValue(program, parametersMode, 0, positionFirstValue)
        val secondValue = getParameterValue(program, parametersMode, 1, positionSecondValue)
        program[positionToStore] = (firstValue + secondValue).toString()
    }

    private fun multiplies(
        program: MutableList<String>,
        parametersMode: List<Int>,
        positionFirstValue: Int,
        positionSecondValue: Int,
        positionToStore: Int
    ) {
        val firstValue = getParameterValue(program, parametersMode, 0, positionFirstValue)
        val secondValue = getParameterValue(program, parametersMode, 1, positionSecondValue)
        program[positionToStore] = (firstValue * secondValue).toString()
    }

    private fun getParameterValue(
        program: MutableList<String>,
        parametersMode: List<Int>,
        parameterIndex: Int,
        value: Int
    ): Int {
        return when (parametersMode[parameterIndex]) {
            ParameterMode.POSITION_MODE.value ->
                program[value].toInt()
            ParameterMode.IMMEDIATE_MODE.value ->
                value
            else -> throw Exception("Unknown Parameter Mode")
        }
    }

    fun executeProgram(program: MutableList<String>): String {
        var position = 0
        while (program[position].toInt() != FINISHED.value) {
            val instruction = "0000" + program[position]
            val opCode = extractOpCodeFromInstruction(instruction)
            val parameterModes = extractParametersModeFromInstruction(instruction)

            when (opCode.toInt()) {
                ADDS.value -> {
                    adds(
                        program,
                        parameterModes,
                        program[position + 1].toInt(),
                        program[position + 2].toInt(),
                        program[position + 3].toInt()
                    )
                    position += 4
                }
                MULTIPLIES.value -> {
                    multiplies(
                        program,
                        parameterModes,
                        program[position + 1].toInt(),
                        program[position + 2].toInt(),
                        program[position + 3].toInt()
                    )
                    position += 4
                }
                INPUT.value -> {
                    input(
                        program,
                        program[position + 1].toInt()
                    )
                    position += 2
                }
                OUTPUT.value -> {
                    output(
                        program,
                        program[position + 1].toInt()
                    )
                    position += 2
                }
                else -> throw Exception("Unknown Opcode")
            }

        }

        return program[0]
    }

    private fun extractOpCodeFromInstruction(instruction: String): String {
        return instruction.subSequence(instruction.length - 2, instruction.length).toString()
    }

    private fun extractParametersModeFromInstruction(instruction: String): List<Int> {
        return listOf(
            instruction.subSequence(
                instruction.length - 3,
                instruction.length - 2
            ).toString().toInt(),
            instruction.subSequence(
                instruction.length - 4,
                instruction.length - 3
            ).toString().toInt(),
            instruction.subSequence(
                instruction.length - 5,
                instruction.length - 4
            ).toString().toInt()
        )
    }

    private fun output(program: MutableList<String>, position: Int) {
        outputResult = program[position]
    }

    private fun input(program: MutableList<String>, position: Int) {
        program[position] = input.toString()
    }


}