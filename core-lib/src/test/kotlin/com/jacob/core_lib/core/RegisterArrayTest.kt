package com.jacob.core_lib.core

import com.jacob.core_lib.common.SR
import com.jacob.core_lib.word.ImmediateValue
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Test

internal class RegisterArrayTest {

    @Test
    fun `update value at register`() {
        var registerArray = RegisterArray()
        var registerAddress = 9.SR
        var immediateValue = ImmediateValue(50)
        registerArray.setValueAtRegister(registerAddress, immediateValue)

        registerArray.getRegisterAt(registerAddress).getRegisterValue() `should be equal to` immediateValue

        registerArray = RegisterArray()
        registerAddress = 6.SR
        immediateValue = ImmediateValue(23)
        registerArray.setValueAtRegister(registerAddress, immediateValue)

        registerArray.getRegisterAt(registerAddress).getRegisterValue() `should be equal to` immediateValue
    }
}
