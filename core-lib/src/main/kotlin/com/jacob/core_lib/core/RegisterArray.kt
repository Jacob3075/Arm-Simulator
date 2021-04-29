package com.jacob.core_lib.core

import com.jacob.core_lib.registers.*
import com.jacob.core_lib.registers.address.RegisterAddress
import com.jacob.core_lib.registers.address.RegisterAddress.*
import com.jacob.core_lib.word.ImmediateValue

class RegisterArray {
    fun getRegisterAt(registerAddress: RegisterAddress): Register {
        return registers.find { it.getRegisterAddress() == registerAddress }
            ?: throw IllegalArgumentException("Invalid Register Address")
    }

    fun setValueAtRegister(registerAddress: RegisterAddress, immediateValue: ImmediateValue) {
        val registerToUpdate = getRegisterAt(registerAddress)
        registerToUpdate.setRegisterValue(immediateValue)
    }

    private val registers: List<Register> = listOf(
        CoreRegister(REGISTER1),
        CoreRegister(REGISTER2),
        CoreRegister(REGISTER3),
        CoreRegister(REGISTER4),
        CoreRegister(REGISTER5),
        CoreRegister(REGISTER6),
        CoreRegister(REGISTER7),
        CoreRegister(REGISTER8),
        CoreRegister(REGISTER9),
        CoreRegister(REGISTER10),
        CoreRegister(REGISTER11),
        CoreRegister(REGISTER12),
        StackPointer(),
        LinkRegister(),
        ProgramCounter(),
    )

    val statusRegister: StatusRegister = StatusRegister()

}
