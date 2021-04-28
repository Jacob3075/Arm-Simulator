package com.jacob.core_lib.instruction

import com.jacob.core_lib.register.DestinationRegister
import com.jacob.core_lib.register.RegisterAddress
import com.jacob.core_lib.word.Word
import org.amshove.kluent.shouldNotBeNull
import org.junit.jupiter.api.Test

internal class MoveTest {

    @Test
    internal fun `create new move instruction`() {
        val destinationRegister = DestinationRegister(RegisterAddress.REGISTER1)
        val immediateValue = ImmediateValue(Word(50))
        val moveInstruction = Move(destinationRegister, immediateValue)

        moveInstruction.shouldNotBeNull()
    }
}
