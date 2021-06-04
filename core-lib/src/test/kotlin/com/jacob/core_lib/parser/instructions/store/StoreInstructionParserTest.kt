package com.jacob.core_lib.parser.instructions.store

import arrow.core.getOrElse
import com.jacob.core_lib.instructions.conditionals.Always
import com.jacob.core_lib.instructions.store.StoreRegisterAddressWithImmediateOffset
import com.jacob.core_lib.instructions.store.StoreVariableAddress
import com.jacob.core_lib.parser.instructions.InstructionString
import org.amshove.kluent.`should be instance of`
import org.amshove.kluent.shouldBeTrue
import org.junit.jupiter.api.Test

internal class StoreInstructionParserTest {
    @Test
    internal fun `creates correct instruction when given single letter variable names`() {
        val instructionString = InstructionString("STR R1, =A")

        val instruction = StoreInstructionParser.from(instructionString).getOrElse { null }!!

        instruction `should be instance of` Always::class
        instruction as Always

        instruction.instruction `should be instance of` StoreVariableAddress::class
    }

    @Test
    internal fun `creates correct instruction when given multiple letter variable names`() {
        val instructionString = InstructionString("STR R1, =ABC")

        val instruction = StoreInstructionParser.from(instructionString).getOrElse { null }!!

        instruction `should be instance of` Always::class
        instruction as Always

        instruction.instruction `should be instance of` StoreVariableAddress::class
    }

    @Test
    internal fun `creates correct instruction when given valid register`() {
        val instructionString = InstructionString("STR R1, [R2]")

        val instruction = StoreInstructionParser.from(instructionString).getOrElse { null }!!

        instruction `should be instance of` Always::class
        instruction as Always

        instruction.instruction `should be instance of` StoreRegisterAddressWithImmediateOffset::class
    }

    @Test
    internal fun `throws exception of invalid register is given`() {
        val instructionString = InstructionString("STR R1, [R22]")

        StoreInstructionParser.from(instructionString).isInvalid.shouldBeTrue()
    }
}
