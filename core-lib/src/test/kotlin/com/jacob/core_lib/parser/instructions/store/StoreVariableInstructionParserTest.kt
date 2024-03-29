package com.jacob.core_lib.parser.instructions.store

import com.jacob.core_lib.common.SR
import com.jacob.core_lib.instructions.conditionals.Always
import com.jacob.core_lib.instructions.store.StoreVariableAddress
import com.jacob.core_lib.parser.instructions.InstructionString
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be instance of`
import org.junit.jupiter.api.Test

internal class StoreVariableInstructionParserTest {
    @Test
    internal fun `creates correct instruction for single letter variable names`() {
        val instructionString = InstructionString("STR R1, =A")

        val instruction = StoreVariableParser(instructionString).parse() as Always

        instruction.instruction.apply {
            this `should be instance of` StoreVariableAddress::class
            this as StoreVariableAddress

            sourceRegister `should be equal to` 1.SR
            variableName `should be equal to` "A"
        }
    }

    @Test
    internal fun `creates correct instruction for multiple letter variable names`() {
        val instructionString = InstructionString("STR R1, =ABC")

        val instruction = StoreVariableParser(instructionString).parse() as Always

        instruction.instruction.apply {
            this `should be instance of` StoreVariableAddress::class
            this as StoreVariableAddress

            sourceRegister `should be equal to` 1.SR
            variableName `should be equal to` "ABC"
        }
    }
}
