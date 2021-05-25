package com.jacob.core_lib.core

import com.jacob.core_lib.common.MA
import com.jacob.core_lib.common.RA
import com.jacob.core_lib.common.W
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Test


internal class CoreTest {
    @Test
    internal fun `test file 1`() {
        val sampleInputData = test1Data

        val instructions = sampleInputData.first
        val variables = sampleInputData.second

        val program = Program(instructions, variables)
        val core = Core(program = program)

        core.runProgram()

        core.apply {
            registerArray.getRegisterAt(1.RA).getRegisterValue() `should be equal to` 5.W
            registerArray.getRegisterAt(2.RA).getRegisterValue() `should be equal to` 10.W
            registerArray.getRegisterAt(3.RA).getRegisterValue() `should be equal to` 15.W
            registerArray.getRegisterAt(4.RA).getRegisterValue() `should be equal to` 10.W
        }
    }

    @Test
    internal fun `test file 2`() {
        val sampleInputData = test2Data

        val instructions = sampleInputData.first
        val variables = sampleInputData.second

        val program = Program(instructions, variables)
        val core = Core(program = program)

        core.runProgram()

        core.apply {
            registerArray.getRegisterAt(1.RA).getRegisterValue() `should be equal to` 5.W
            registerArray.getRegisterAt(3.RA).getRegisterValue() `should be equal to` 7.W
            registerArray.getRegisterAt(4.RA).getRegisterValue() `should be equal to` (-2).W
        }
    }

    @Test
    internal fun `test file 3`() {
        val sampleInputData = test3Data

        val instructions = sampleInputData.first
        val variables = sampleInputData.second

        val program = Program(instructions, variables)
        val core = Core(program = program)

        core.runProgram()

        core.apply {
            registerArray.getRegisterAt(1.RA).getRegisterValue() `should be equal to` 5.W
            registerArray.getRegisterAt(2.RA).getRegisterValue() `should be equal to` 10.W
            registerArray.getRegisterAt(4.RA).getRegisterValue() `should be equal to` 5.W
            registerArray.getRegisterAt(5.RA).getRegisterValue() `should be equal to` 2.W
        }
    }

    @Test
    internal fun `test file 4`() {
        val sampleInputData = test4Data

        val instructions = sampleInputData.first
        val variables = sampleInputData.second

        val program = Program(instructions, variables)
        val core = Core(program = program)

        core.runProgram()

        core.apply {
            registerArray.getRegisterAt(1.RA).getRegisterValue() `should be equal to` 5.W
            registerArray.getRegisterAt(2.RA).getRegisterValue() `should be equal to` 10.W
            registerArray.getRegisterAt(5.RA).getRegisterValue() `should be equal to` 10.W
            registerArray.getRegisterAt(6.RA).getRegisterValue() `should be equal to` 15.W
            registerArray.getRegisterAt(7.RA).getRegisterValue() `should be equal to` 15.W
            registerArray.getRegisterAt(8.RA).getRegisterValue() `should be equal to` (-5).W

            memoryArray.getWordAt(0.MA) `should be equal to` 15.W
            memoryArray.getWordAt(1.MA) `should be equal to` (-5).W
        }
    }
}
