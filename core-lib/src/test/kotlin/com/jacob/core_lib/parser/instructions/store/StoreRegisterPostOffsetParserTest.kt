package com.jacob.core_lib.parser.instructions.store

import com.jacob.core_lib.common.DR
import com.jacob.core_lib.common.I
import com.jacob.core_lib.common.SR
import com.jacob.core_lib.instructions.store.StoreRegisterAddressWithPostOffset
import com.jacob.core_lib.parser.instructions.InstructionString
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be instance of`
import org.junit.jupiter.api.Test

internal class StoreRegisterPostOffsetParserTest {
    @Test
    internal fun `creates correct store instruction with single digit positive offset`() {
        val line = InstructionString("STR R0, [R1], #3")

        val loadInstruction = StoreRegisterPostOffsetParser(line).parse()

        loadInstruction `should be instance of` StoreRegisterAddressWithPostOffset::class

        loadInstruction as StoreRegisterAddressWithPostOffset

        loadInstruction.sourceRegister `should be equal to` 0.SR
        loadInstruction.destinationRegister `should be equal to` 1.DR
        loadInstruction.offset `should be equal to` 3.I
    }

    @Test
    internal fun `creates correct store instruction with multiple digit positive offset`() {
        val line = InstructionString("STR R0, [R1], #30")

        val loadInstruction = StoreRegisterPostOffsetParser(line).parse()

        loadInstruction `should be instance of` StoreRegisterAddressWithPostOffset::class

        loadInstruction as StoreRegisterAddressWithPostOffset

        loadInstruction.sourceRegister `should be equal to` 0.SR
        loadInstruction.destinationRegister `should be equal to` 1.DR
        loadInstruction.offset `should be equal to` 30.I
    }

    @Test
    internal fun `creates correct store instruction with single digit negative offset`() {
        val line = InstructionString("STR R0, [R1], #-3")

        val loadInstruction = StoreRegisterPostOffsetParser(line).parse()

        loadInstruction `should be instance of` StoreRegisterAddressWithPostOffset::class

        loadInstruction as StoreRegisterAddressWithPostOffset

        loadInstruction.sourceRegister `should be equal to` 0.SR
        loadInstruction.destinationRegister `should be equal to` 1.DR
        loadInstruction.offset `should be equal to` (-3).I
    }

    @Test
    internal fun `creates correct store instruction with multiple digit negative offset`() {
        val line = InstructionString("STR R0, [R1], #-30")

        val loadInstruction = StoreRegisterPostOffsetParser(line).parse()

        loadInstruction `should be instance of` StoreRegisterAddressWithPostOffset::class

        loadInstruction as StoreRegisterAddressWithPostOffset

        loadInstruction.sourceRegister `should be equal to` 0.SR
        loadInstruction.destinationRegister `should be equal to` 1.DR
        loadInstruction.offset `should be equal to` (-30).I
    }
}
