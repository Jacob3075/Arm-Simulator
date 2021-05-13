package com.jacob.core_lib.parser.instructions.store

import com.jacob.core_lib.common.addresses.RegisterAddress
import com.jacob.core_lib.instructions.store.StoreRegisterAddress
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be instance of`
import org.junit.jupiter.api.Test

internal class StoreRegisterInstructionParserTest {
    @Test
    internal fun `creates correct instruction`() {
        val instructionString = "STR R1, [R0]"

        val instruction = StoreRegisterInstructionParser(instructionString).invoke()

        instruction `should be instance of` StoreRegisterAddress::class

        instruction as StoreRegisterAddress

        instruction.sourceRegister.registerAddress `should be equal to` RegisterAddress.REGISTER_1
        instruction.destinationRegister.registerAddress `should be equal to` RegisterAddress.REGISTER_0
    }
}
