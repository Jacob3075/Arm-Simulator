package com.jacob.core_lib.parser

import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Test

internal class InstructionStringTest {
    @Test
    internal fun name() {
        val instructionString = InstructionString("ADD R1, R2, R3")
    }

    @Test
    internal fun name1() {
        val s = " afafhodahf "
        val trim = s.trim()
        s `should be equal to` trim

    }
}
