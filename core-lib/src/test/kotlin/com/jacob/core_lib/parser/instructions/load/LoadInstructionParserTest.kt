package com.jacob.core_lib.parser.instructions.load

import arrow.core.getOrElse
import com.jacob.core_lib.instructions.conditionals.Always
import com.jacob.core_lib.instructions.load.LoadRegisterAddressWithImmediateOffset
import com.jacob.core_lib.instructions.load.LoadVariableAddress
import com.jacob.core_lib.parser.instructions.InstructionString
import org.amshove.kluent.`should be instance of`
import org.junit.jupiter.api.Test

internal class LoadInstructionParserTest {
    @Test
    internal fun `returns load variable parser when given single letter variable name`() {
        val instructionString = InstructionString("LDR R0, =A")

        val loadInstruction = LoadInstructionParser.from(instructionString).getOrElse { null }!!

        loadInstruction `should be instance of` Always::class
        loadInstruction as Always

        loadInstruction.instruction `should be instance of` LoadVariableAddress::class
    }

    @Test
    internal fun `returns load variable parser when given multiple letter variable name`() {
        val instructionString = InstructionString("LDR R0, =ABC")

        val loadInstruction = LoadInstructionParser.from(instructionString).getOrElse { null }!!

        loadInstruction `should be instance of` Always::class
        loadInstruction as Always

        loadInstruction.instruction `should be instance of` LoadVariableAddress::class
    }

    @Test
    internal fun `returns load memory address parser`() {
        val instructionString = InstructionString("LDR R0, [R1]")

        val loadInstruction = LoadInstructionParser.from(instructionString).getOrElse { null }!!

        loadInstruction `should be instance of` Always::class
        loadInstruction as Always

        loadInstruction.instruction `should be instance of` LoadRegisterAddressWithImmediateOffset::class
    }
}
