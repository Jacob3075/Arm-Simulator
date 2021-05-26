package com.jacob.core_lib.parser.instructions.load

import com.jacob.core_lib.common.DR
import com.jacob.core_lib.common.SR
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

        loadInstruction.destinationRegister `should be equal to` 0.DR
        loadInstruction.sourceRegister `should be equal to` 1.SR
    }

}
