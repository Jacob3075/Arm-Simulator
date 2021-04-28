package com.jacob.core_lib.instructions

import com.jacob.core_lib.registers.address.DestinationRegister
import com.jacob.core_lib.registers.address.RegisterAddress
import com.jacob.core_lib.word.ImmediateValue
import org.amshove.kluent.shouldNotBeNull
import org.junit.jupiter.api.Test

internal class MoveTest {

    @Test
    internal fun `create new move instruction`() {
        val destinationRegister = DestinationRegister(RegisterAddress.REGISTER1)
        val immediateValue = ImmediateValue(50)
        val moveInstruction = Move(destinationRegister, immediateValue)

        moveInstruction.shouldNotBeNull()
    }
}
