package com.jacob.core_lib.parser.instructions.load

import com.jacob.core_lib.common.DR
import com.jacob.core_lib.common.I
import com.jacob.core_lib.common.SR
import com.jacob.core_lib.instructions.conditionals.Always
import com.jacob.core_lib.instructions.load.LoadRegisterAddressWithPostOffset
import com.jacob.core_lib.parser.instructions.InstructionString
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be instance of`
import org.junit.jupiter.api.Test

internal class LoadRegisterPostOffsetParserTest {
    @Test
    internal fun `creates correct load instruction with single digit positive offset`() {
        val line = InstructionString("LDR R0, [R1], #3")

        val instruction = LoadRegisterPostOffsetParser(line).parse()

        instruction `should be instance of` Always::class
        instruction as Always

        instruction.instruction.apply {
            this `should be instance of` LoadRegisterAddressWithPostOffset::class
            this as LoadRegisterAddressWithPostOffset

            destinationRegister `should be equal to` 0.DR
            sourceRegister `should be equal to` 1.SR
            offset `should be equal to` 3.I
        }
    }

    @Test
    internal fun `creates correct load instruction with multiple digit positive offset`() {
        val line = InstructionString("LDR R0, [R1], #30")

        val instruction = LoadRegisterPostOffsetParser(line).parse()

        instruction `should be instance of` Always::class
        instruction as Always

        instruction.instruction.apply {
            this `should be instance of` LoadRegisterAddressWithPostOffset::class
            this as LoadRegisterAddressWithPostOffset

            destinationRegister `should be equal to` 0.DR
            sourceRegister `should be equal to` 1.SR
            offset `should be equal to` 30.I
        }
    }

    @Test
    internal fun `creates correct load instruction with single digit negative offset`() {
        val line = InstructionString("LDR R0, [R1], #-3")

        val instruction = LoadRegisterPostOffsetParser(line).parse()

        instruction `should be instance of` Always::class
        instruction as Always

        instruction.instruction.apply {
            this `should be instance of` LoadRegisterAddressWithPostOffset::class
            this as LoadRegisterAddressWithPostOffset

            destinationRegister `should be equal to` 0.DR
            sourceRegister `should be equal to` 1.SR
            offset `should be equal to` (-3).I
        }
    }

    @Test
    internal fun `creates correct load instruction with multiple digit negative offset`() {
        val line = InstructionString("LDR R0, [R1], #-30")

        val instruction = LoadRegisterPostOffsetParser(line).parse()

        instruction `should be instance of` Always::class
        instruction as Always

        instruction.instruction.apply {
            this `should be instance of` LoadRegisterAddressWithPostOffset::class
            this as LoadRegisterAddressWithPostOffset

            destinationRegister `should be equal to` 0.DR
            sourceRegister `should be equal to` 1.SR
            offset `should be equal to` (-30).I
        }
    }
}
