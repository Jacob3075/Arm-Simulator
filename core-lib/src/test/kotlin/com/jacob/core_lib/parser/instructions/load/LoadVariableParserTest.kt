package com.jacob.core_lib.parser.instructions.load

import com.jacob.core_lib.common.DR
import com.jacob.core_lib.instructions.load.LoadVariableAddress
import com.jacob.core_lib.parser.InstructionString
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be instance of`
import org.amshove.kluent.`should throw`
import org.amshove.kluent.invoking
import org.junit.jupiter.api.Test

internal class LoadVariableParserTest {

    @Test
    internal fun `creates correct load instruction with single letter variable name`() {
        val line = InstructionString("LDR R0, =A")

        val loadInstruction = LoadVariableParser(line).parse()

        loadInstruction `should be instance of` LoadVariableAddress::class

        loadInstruction as LoadVariableAddress

        loadInstruction.destinationRegister `should be equal to` 0.DR
        loadInstruction.variableName `should be equal to` "A"
    }

    @Test
    internal fun `creates correct load instruction with multiple letter variable name`() {
        val line = InstructionString("LDR R0, =ABCD")

        val loadInstruction = LoadVariableParser(line).parse()

        loadInstruction `should be instance of` LoadVariableAddress::class

        (loadInstruction as LoadVariableAddress)

        loadInstruction.destinationRegister `should be equal to` 0.DR
        loadInstruction.variableName `should be equal to` "ABCD"
    }

    @Test
    internal fun `throws exception if invalid register is given`() {
        val line = InstructionString("LDR R20, =A")

        invoking { LoadVariableParser(line).parse() } `should throw` IllegalArgumentException::class
    }
}
