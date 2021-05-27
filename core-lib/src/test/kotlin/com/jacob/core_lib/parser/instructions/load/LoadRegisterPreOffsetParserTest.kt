package com.jacob.core_lib.parser.instructions.load

import com.jacob.core_lib.common.DR
import com.jacob.core_lib.common.I
import com.jacob.core_lib.common.SR
import com.jacob.core_lib.instructions.load.LoadRegisterAddressWithPreOffset
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be instance of`
import org.junit.jupiter.api.Test

internal class LoadRegisterPreOffsetParserTest {
    @Test
    internal fun `creates correct load instruction with single digit positive offset`() {
        val line = "LDR R0, [R1, #3]!"

        val loadInstruction = LoadRegisterPreOffsetParser(line).parse()

        loadInstruction `should be instance of` LoadRegisterAddressWithPreOffset::class

        loadInstruction as LoadRegisterAddressWithPreOffset

        loadInstruction.destinationRegister `should be equal to` 0.DR
        loadInstruction.sourceRegister `should be equal to` 1.SR
        loadInstruction.offset `should be equal to` 3.I
    }

    @Test
    internal fun `creates correct load instruction with multiple digit positive offset`() {
        val line = "LDR R0, [R1, #30]!"

        val loadInstruction = LoadRegisterPreOffsetParser(line).parse()

        loadInstruction `should be instance of` LoadRegisterAddressWithPreOffset::class

        loadInstruction as LoadRegisterAddressWithPreOffset

        loadInstruction.destinationRegister `should be equal to` 0.DR
        loadInstruction.sourceRegister `should be equal to` 1.SR
        loadInstruction.offset `should be equal to` 30.I
    }

    @Test
    internal fun `creates correct load instruction with single digit negative offset`() {
        val line = "LDR R0, [R1, #-3]!"

        val loadInstruction = LoadRegisterPreOffsetParser(line).parse()

        loadInstruction `should be instance of` LoadRegisterAddressWithPreOffset::class

        loadInstruction as LoadRegisterAddressWithPreOffset

        loadInstruction.destinationRegister `should be equal to` 0.DR
        loadInstruction.sourceRegister `should be equal to` 1.SR
        loadInstruction.offset `should be equal to` (-3).I
    }

    @Test
    internal fun `creates correct load instruction with multiple digit negative offset`() {
        val line = "LDR R0, [R1, #-30]!"

        val loadInstruction = LoadRegisterPreOffsetParser(line).parse()

        loadInstruction `should be instance of` LoadRegisterAddressWithPreOffset::class

        loadInstruction as LoadRegisterAddressWithPreOffset

        loadInstruction.destinationRegister `should be equal to` 0.DR
        loadInstruction.sourceRegister `should be equal to` 1.SR
        loadInstruction.offset `should be equal to` (-30).I
    }
}
