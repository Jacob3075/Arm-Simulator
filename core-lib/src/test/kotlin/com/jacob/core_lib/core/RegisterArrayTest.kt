package com.jacob.core_lib.core

import com.jacob.core_lib.common.addresses.RegisterAddress
import com.jacob.core_lib.word.ImmediateValue
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Test

internal class RegisterArrayTest {

    @Test
    fun `update value at register`() {
        var registerArray = RegisterArray()
        var registerAddress = RegisterAddress.REGISTER_9
        var immediateValue = ImmediateValue(50)
        registerArray.setValueAtRegister(registerAddress, immediateValue)

        registerArray.getRegisterAt(registerAddress).getRegisterValue() `should be equal to` immediateValue

        registerArray = RegisterArray()
        registerAddress = RegisterAddress.REGISTER_6
        immediateValue = ImmediateValue(23)
        registerArray.setValueAtRegister(registerAddress, immediateValue)

        registerArray.getRegisterAt(registerAddress).getRegisterValue() `should be equal to` immediateValue
    }
}
