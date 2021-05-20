package com.jacob.core_lib.parser.instructions.sub

import com.jacob.core_lib.instructions.sub.SubImmediate
import org.junit.jupiter.api.Test

internal class SubHexImmediateInstructionParserTest {
    @Test
    internal fun name() {
        val instructionString = "SUB R1, R2, #0X1ABC"
        val instructionParser = SubHexImmediateInstructionParser(instructionString)
        val instruction = instructionParser.invoke()
        instruction as SubImmediate
        println(instruction.immediateValue)
    }
}
