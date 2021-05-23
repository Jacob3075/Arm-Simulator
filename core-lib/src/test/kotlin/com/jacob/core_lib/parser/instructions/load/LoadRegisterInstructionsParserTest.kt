package com.jacob.core_lib.parser.instructions.load

import com.jacob.core_lib.common.addresses.RegisterAddress
import com.jacob.core_lib.instructions.load.LoadRegisterAddress
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be instance of`
import org.junit.jupiter.api.Test

internal class LoadRegisterInstructionsParserTest {

    @Test
    internal fun `creates correct load instruction`() {
        val line = "LDR R0, [R1]"

        val loadInstruction = LoadRegisterInstructionsParser(line).parse()

        loadInstruction `should be instance of` LoadRegisterAddress::class

        (loadInstruction as LoadRegisterAddress)

        loadInstruction.destinationRegister.registerAddress `should be equal to` RegisterAddress.REGISTER_0
        loadInstruction.sourceRegister.registerAddress `should be equal to` RegisterAddress.REGISTER_1
    }

}
