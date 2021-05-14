package com.jacob.core_lib.core

import com.jacob.core_lib.common.RA
import com.jacob.core_lib.common.W
import com.jacob.core_lib.getFile
import com.jacob.core_lib.parser.Parser
import com.jacob.core_lib.parser.data.toVariables
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Test


internal class CoreTest {
    @Test
    internal fun `test file 1`() {
        val file = getFile("/TestInputFile1.txt")

        val parseDataFromFile = Parser.parseDataFromFile(file)

        val instructions = parseDataFromFile.first
        val variables = parseDataFromFile.second.toVariables()

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
        val file = getFile("/TestInputFile2.txt")

        val parseDataFromFile = Parser.parseDataFromFile(file)

        val instructions = parseDataFromFile.first
        val variables = parseDataFromFile.second.toVariables()

        val program = Program(instructions, variables)
        val core = Core(program = program)

        core.runProgram()

        core.apply {
            registerArray.getRegisterAt(1.RA).getRegisterValue() `should be equal to` 5.W
            registerArray.getRegisterAt(3.RA).getRegisterValue() `should be equal to` 7.W
            registerArray.getRegisterAt(4.RA).getRegisterValue() `should be equal to` (-2).W
        }
    }
}
