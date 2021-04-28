package com.jacob.core_lib.core

import com.jacob.core_lib.registers.address.RegisterAddress
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

internal class RegisterArrayTest {

    @Test
    internal fun `returns correct register`() {
        val registerArray = RegisterArray()
        val registerAddress = RegisterAddress.REGISTER1
        val registerAt = registerArray.getRegisterAt(registerAddress)
        registerAt.getRegisterAddress() shouldBeEqualTo registerAddress
    }
}
