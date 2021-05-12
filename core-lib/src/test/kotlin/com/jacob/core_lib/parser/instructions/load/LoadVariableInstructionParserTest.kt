package com.jacob.core_lib.parser.instructions.load

import com.jacob.core_lib.common.addresses.RegisterAddress
import com.jacob.core_lib.instructions.load.LoadVariableAddress
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be instance of`
import org.amshove.kluent.`should throw`
import org.amshove.kluent.invoking
import org.junit.jupiter.api.Test

internal class LoadVariableInstructionParserTest {

    @Test
    internal fun `creates correct load instruction with single letter variable name`() {
        val line = "LDR R0, =A"

        val loadInstruction = LoadVariableInstructionParser(line).invoke()

        loadInstruction `should be instance of` LoadVariableAddress::class

        loadInstruction as LoadVariableAddress

        loadInstruction.destinationRegister.registerAddress `should be equal to` RegisterAddress.REGISTER_0
        loadInstruction.variableName `should be equal to` "A"
    }

    @Test
    internal fun `creates correct load instruction with multiple letter variable name`() {
        val line = "LDR R0, =ABCD"

        val loadInstruction = LoadVariableInstructionParser(line).invoke()

        loadInstruction `should be instance of` LoadVariableAddress::class

        (loadInstruction as LoadVariableAddress)

        loadInstruction.destinationRegister.registerAddress `should be equal to` RegisterAddress.REGISTER_0
        loadInstruction.variableName `should be equal to` "ABCD"
    }

    @Test
    internal fun `throws exception if invalid register is given`() {
        val line = "LDR R20, =A"

        invoking { LoadVariableInstructionParser(line).invoke() } `should throw` IllegalArgumentException::class
    }
}
