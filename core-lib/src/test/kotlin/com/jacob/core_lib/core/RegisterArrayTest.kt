package com.jacob.core_lib.core

import com.jacob.core_lib.registers.address.RegisterAddress
import com.jacob.core_lib.word.ImmediateValue
import org.amshove.kluent.`should be equal to`
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

    @Test
    fun `update value at register`() {
        var registerArray = RegisterArray()
        var registerAddress = RegisterAddress.REGISTER10
        var immediateValue = ImmediateValue(50)
        registerArray.setValueAtRegister(registerAddress, immediateValue)

        registerArray.getRegisterAt(registerAddress).getRegisterValue() `should be equal to` immediateValue

        registerArray = RegisterArray()
        registerAddress = RegisterAddress.REGISTER7
        immediateValue = ImmediateValue(23)
        registerArray.setValueAtRegister(registerAddress, immediateValue)

        registerArray.getRegisterAt(registerAddress).getRegisterValue() `should be equal to` immediateValue
    }
}
