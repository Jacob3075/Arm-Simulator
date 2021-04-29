package com.jacob.core_lib.core

import com.jacob.core_lib.registers.*
import com.jacob.core_lib.registers.address.RegisterAddress
import com.jacob.core_lib.registers.address.RegisterAddress.*
import com.jacob.core_lib.word.ImmediateValue

class RegisterArray {
    private val registers: List<Register> = listOf(
        CoreRegister(REGISTER_1),
        CoreRegister(REGISTER_2),
        CoreRegister(REGISTER_3),
        CoreRegister(REGISTER_4),
        CoreRegister(REGISTER_5),
        CoreRegister(REGISTER_6),
        CoreRegister(REGISTER_7),
        CoreRegister(REGISTER_8),
        CoreRegister(REGISTER_9),
        CoreRegister(REGISTER_10),
        CoreRegister(REGISTER_11),
        CoreRegister(REGISTER_12),
    )

    private val stackPointer = StackPointer()
    private val linkRegister = LinkRegister()
    private val programCounter = ProgramCounter()

    fun getRegisterAt(registerAddress: RegisterAddress): Register {
        return registers.find { it.getRegisterAddress() == registerAddress }
            ?: throw IllegalArgumentException("Invalid Register Address")
    }

    fun setValueAtRegister(registerAddress: RegisterAddress, immediateValue: ImmediateValue) {
        val registerToUpdate = getRegisterAt(registerAddress)
        registerToUpdate.setRegisterValue(immediateValue)
    }

    fun updateProgramCounter(nextInstructionAddress: Int) {
        programCounter.nextInstructionAddress = nextInstructionAddress
    }

    val statusRegister: StatusRegister = StatusRegister()

}
