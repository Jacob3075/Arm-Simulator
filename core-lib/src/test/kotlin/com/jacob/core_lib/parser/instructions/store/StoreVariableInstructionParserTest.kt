package com.jacob.core_lib.parser.instructions.store

import com.jacob.core_lib.common.addresses.RegisterAddress
import com.jacob.core_lib.instructions.store.StoreVariableAddress
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be instance of`
import org.junit.jupiter.api.Test

internal class StoreVariableInstructionParserTest {
    @Test
    internal fun `creates correct instruction for single letter variable names`() {
        val instructionString = "STR R1, =A"

        val instruction = StoreVariableInstructionParser(instructionString).invoke()

        instruction `should be instance of` StoreVariableAddress::class

        instruction as StoreVariableAddress

        instruction.sourceRegister.registerAddress `should be equal to` RegisterAddress.REGISTER_1
        instruction.variableName `should be equal to` "A"
    }

    @Test
    internal fun `creates correct instruction for multiple letter variable names`() {
        val instructionString = "STR R1, =ABC"

        val instruction = StoreVariableInstructionParser(instructionString).invoke()

        instruction `should be instance of` StoreVariableAddress::class

        instruction as StoreVariableAddress

        instruction.sourceRegister.registerAddress `should be equal to` RegisterAddress.REGISTER_1
        instruction.variableName `should be equal to` "ABC"
    }
}
