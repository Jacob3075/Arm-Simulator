package com.jacob.core_lib.core

import com.jacob.core_lib.common.I
import com.jacob.core_lib.common.MA
import com.jacob.core_lib.common.SR
import com.jacob.core_lib.common.W
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Test


internal class CoreTest {
    @Test
    internal fun `test file 1`() {
        val instructions = test1Data.first
        val variables = test1Data.second

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
        val instructions = test2Data.first
        val variables = test2Data.second

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
        val instructions = test3Data.first
        val variables = test3Data.second

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
        val instructions = test4Data.first
        val variables = test4Data.second

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
        val instructions = test5Data.first
        val variables = test5Data.second

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
        val instructions = test6Data.first
        val variables = test6Data.second

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
        val instructions = test7Data.first
        val variables = test7Data.second

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

    @Test
    internal fun `test file 8`() {
        val instructions = test8Data.first
        val variables = test8Data.second

        val program = Program(instructions, variables)
        val core = Core(program = program)

        core.runProgram()

        core.registerArray.apply {
            getRegisterAt(4.SR).getRegisterValue() `should be equal to` 10.I
            getRegisterAt(5.SR).getRegisterValue() `should be equal to` 0.I
            getRegisterAt(6.SR).getRegisterValue() `should be equal to` 0.I
            getRegisterAt(7.SR).getRegisterValue() `should be equal to` 0.I
            getRegisterAt(8.SR).getRegisterValue() `should be equal to` 25.I
        }
    }
}
