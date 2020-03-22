package com.hitg.adventofcode.domain.model.computer

import com.hitg.adventofcode.domain.model.computer.IntCodeComputer.OpCode.*

class IntCodeComputer(val input: List<Int>? = null) {



    enum class ParameterMode(val value: Int) {
        POSITION_MODE(0),
        IMMEDIATE_MODE(1)
    }

    enum class OpCode(val value: Int) {
        ADDS(1),
        MULTIPLIES(2),
        INPUT(3),
        OUTPUT(4),
        JUMP_IF_TRUE(5),
        JUMP_IF_FALSE(6),
        LESS_THAN(7),
        EQUALS(8),
        FINISHED(99)
    }

    var outputResult: String = ""

    var inputCursor = 0

    /**
     * Opcode 1 adds together numbers read from two positions and stores the result in a
     * third position.
     * The three integers immediately after the opcode tell you these three positions -
     * the first two indicate the positions from which you should read the input values,
     * and the third indicates the position at which the output should be stored.
     */
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

    /**
     * Opcode 2 works exactly like opcode 1,
     * except it multiplies the two inputs instead of adding them.
     * Again, the three integers after the opcode indicate where the inputs and outputs are,
     * not their values.
     */
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

    /**
     * Opcode 4 outputs the value of its only parameter.
     * For example, the instruction 4,50 would output the value at address 50.
     */
    private fun output(program: MutableList<String>, parametersMode: List<Int>, position: Int) {

        outputResult =
            getParameterValue(program, parametersMode, 0, position).toString()
    }

    /**
     * Opcode 3 takes a single integer as input and saves it to the position given by its
     * only parameter.
     * For example, the instruction 3,50 would take an input value and store it at address 50.
     */
    private fun input(program: MutableList<String>, position: Int) {
        if (input != null && inputCursor < input.size) {
            program[position] = input[inputCursor].toString()
            inputCursor++
        } else {
            throw Exception("No Input Available")
        }
    }

    /**
     * Opcode 5 is jump-if-true:
     * if the first parameter is non-zero, it sets the instruction pointer to the value
     * from the second parameter. Otherwise, it does nothing.
     */
    private fun jumpIfTrue(
        program: MutableList<String>,
        parametersMode: List<Int>,
        instructionPosition: Int,
        positionFirstValue: Int,
        positionSecondValue: Int
    ): Int {
        val firstValue = getParameterValue(program, parametersMode, 0, positionFirstValue)
        val secondValue = getParameterValue(program, parametersMode, 1, positionSecondValue)
        return if (firstValue != 0) {
            secondValue
        } else {
            instructionPosition + 3
        }
    }

    /**
     * Opcode 6 is jump-if-false:
     * if the first parameter is zero, it sets the instruction pointer to the value
     * from the second parameter. Otherwise, it does nothing.
     */
    private fun jumpIfFalse(
        program: MutableList<String>,
        parametersMode: List<Int>,
        instructionPosition: Int,
        positionFirstValue: Int,
        positionSecondValue: Int
    ): Int {
        val firstValue = getParameterValue(program, parametersMode, 0, positionFirstValue)
        val secondValue = getParameterValue(program, parametersMode, 1, positionSecondValue)
        return if (firstValue == 0) {
            secondValue
        } else {
            instructionPosition + 3
        }
    }

    /**
     * Opcode 7 is less than:
     * if the first parameter is less than the second parameter,
     * it stores 1 in the position given by the third parameter.
     * Otherwise, it stores 0.
     */
    private fun lessThan(
        program: MutableList<String>,
        parametersMode: List<Int>,
        positionFirstValue: Int,
        positionSecondValue: Int,
        positionThirdValue: Int
    ) {
        val firstValue = getParameterValue(program, parametersMode, 0, positionFirstValue)
        val secondValue = getParameterValue(program, parametersMode, 1, positionSecondValue)
        program[positionThirdValue] = (if (firstValue < secondValue) {
            1
        } else {
            0
        }).toString()
    }

    /**
     * Opcode 8 is equals:
     * if the first parameter is equal to the second parameter,
     * it stores 1 in the position given by the third parameter.
     * Otherwise, it stores 0.
     */
    private fun equal(
        program: MutableList<String>,
        parametersMode: List<Int>,
        positionFirstValue: Int,
        positionSecondValue: Int,
        positionThirdValue: Int
    ) {
        val firstValue = getParameterValue(program, parametersMode, 0, positionFirstValue)
        val secondValue = getParameterValue(program, parametersMode, 1, positionSecondValue)
        program[positionThirdValue] = (if (firstValue == secondValue) {
            1
        } else {
            0
        }).toString()
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
                        parameterModes,
                        program[position + 1].toInt()
                    )
                    position += 2
                }
                JUMP_IF_TRUE.value -> {
                    position = jumpIfTrue(
                        program,
                        parameterModes,
                        position,
                        program[position + 1].toInt(),
                        program[position + 2].toInt()
                    )
                }
                JUMP_IF_FALSE.value -> {
                    position = jumpIfFalse(
                        program,
                        parameterModes,
                        position,
                        program[position + 1].toInt(),
                        program[position + 2].toInt()
                    )
                }
                LESS_THAN.value -> {
                    lessThan(
                        program,
                        parameterModes,
                        program[position + 1].toInt(),
                        program[position + 2].toInt(),
                        program[position + 3].toInt()
                    )
                    position += 4
                }
                EQUALS.value -> {
                    equal(
                        program,
                        parameterModes,
                        program[position + 1].toInt(),
                        program[position + 2].toInt(),
                        program[position + 3].toInt()
                    )
                    position += 4
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


}