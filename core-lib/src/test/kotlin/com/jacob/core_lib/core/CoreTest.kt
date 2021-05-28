package com.jacob.core_lib.core

import com.jacob.core_lib.common.MA
import com.jacob.core_lib.common.SR
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

        core.registerArray.apply {
            getRegisterAt(1.SR).getRegisterValue() `should be equal to` 5.W
            getRegisterAt(2.SR).getRegisterValue() `should be equal to` 10.W
            getRegisterAt(3.SR).getRegisterValue() `should be equal to` 15.W
            getRegisterAt(4.SR).getRegisterValue() `should be equal to` 10.W
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

        core.registerArray.apply {
            getRegisterAt(1.SR).getRegisterValue() `should be equal to` 5.W
            getRegisterAt(3.SR).getRegisterValue() `should be equal to` 7.W
            getRegisterAt(4.SR).getRegisterValue() `should be equal to` (-2).W
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

        core.registerArray.apply {
            getRegisterAt(1.SR).getRegisterValue() `should be equal to` 5.W
            getRegisterAt(2.SR).getRegisterValue() `should be equal to` 10.W
            getRegisterAt(4.SR).getRegisterValue() `should be equal to` 5.W
            getRegisterAt(5.SR).getRegisterValue() `should be equal to` 2.W
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
            registerArray.apply {
                getRegisterAt(1.SR).getRegisterValue() `should be equal to` 5.W
                getRegisterAt(2.SR).getRegisterValue() `should be equal to` 10.W
                getRegisterAt(5.SR).getRegisterValue() `should be equal to` 10.W
                getRegisterAt(6.SR).getRegisterValue() `should be equal to` 15.W
                getRegisterAt(7.SR).getRegisterValue() `should be equal to` 15.W
                getRegisterAt(8.SR).getRegisterValue() `should be equal to` (-5).W
            }

            memoryArray.apply {
                getWordAt(0.MA) `should be equal to` 15.W
                getWordAt(1.MA) `should be equal to` (-5).W
            }
        }
    }

    @Test
    internal fun `test file 5`() {
        val sampleInputData = test5Data

        val instructions = sampleInputData.first
        val variables = sampleInputData.second

        val program = Program(instructions, variables)
        val core = Core(program = program)

        core.runProgram()

        core.apply {
            registerArray.apply {
                getRegisterAt(1.SR).getRegisterValue() `should be equal to` 0.W
                getRegisterAt(2.SR).getRegisterValue() `should be equal to` 1.W
                getRegisterAt(3.SR).getRegisterValue() `should be equal to` 2.W
                getRegisterAt(4.SR).getRegisterValue() `should be equal to` 3.W

                getRegisterAt(5.SR).getRegisterValue() `should be equal to` 30.W
                getRegisterAt(6.SR).getRegisterValue() `should be equal to` 21.W
                getRegisterAt(7.SR).getRegisterValue() `should be equal to` (-30).W
                getRegisterAt(8.SR).getRegisterValue() `should be equal to` 38.W

            }

            memoryArray.apply {
                getWordAt(0.MA) `should be equal to` 30.W
                getWordAt(1.MA) `should be equal to` 21.W
                getWordAt(2.MA) `should be equal to` (-30).W
                getWordAt(3.MA) `should be equal to` 38.W
            }
        }
    }

    @Test
    internal fun `test file 6`() {
        val sampleInputData = test6Data

        val instructions = sampleInputData.first
        val variables = sampleInputData.second

        val program = Program(instructions, variables)
        val core = Core(program = program)

        core.runProgram()

        core.apply {
            registerArray.apply {
                getRegisterAt(1.SR).getRegisterValue() `should be equal to` 1.W
                getRegisterAt(2.SR).getRegisterValue() `should be equal to` 1.W
                getRegisterAt(3.SR).getRegisterValue() `should be equal to` 0.W
                getRegisterAt(4.SR).getRegisterValue() `should be equal to` 1.W

                getRegisterAt(5.SR).getRegisterValue() `should be equal to` 20.W
                getRegisterAt(6.SR).getRegisterValue() `should be equal to` 10.W
                getRegisterAt(7.SR).getRegisterValue() `should be equal to` 10.W
                getRegisterAt(8.SR).getRegisterValue() `should be equal to` 20.W
                getRegisterAt(9.SR).getRegisterValue() `should be equal to` 0.W

            }

            memoryArray.apply {
                getWordAt(0.MA) `should be equal to` 10.W
                getWordAt(1.MA) `should be equal to` 20.W
            }
        }
    }

    @Test
    internal fun `test file 7`() {
        val sampleInputData = test7Data

        val instructions = sampleInputData.first
        val variables = sampleInputData.second

        val program = Program(instructions, variables)
        val core = Core(program = program)

        core.runProgram()

        core.apply {
            registerArray.apply {
                getRegisterAt(6.SR).getRegisterValue() `should be equal to` 17.W
                getRegisterAt(7.SR).getRegisterValue() `should be equal to` 7.W
                getRegisterAt(8.SR).getRegisterValue() `should be equal to` 6.W
                getRegisterAt(9.SR).getRegisterValue() `should be equal to` 4.W
                getRegisterAt(10.SR).getRegisterValue() `should be equal to` 13.W
            }

            memoryArray.apply {
                getWordAt(7.MA) `should be equal to` 7.W
                getWordAt(24.MA) `should be equal to` 5.W
                getWordAt(8.MA) `should be equal to` 17.W
                getWordAt(17.MA) `should be equal to` 5.W
                getWordAt(12.MA) `should be equal to` 7.W
            }
        }
    }
}
