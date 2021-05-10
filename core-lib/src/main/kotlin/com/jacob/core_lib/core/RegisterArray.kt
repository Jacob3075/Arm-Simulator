package com.jacob.core_lib.core

import com.jacob.core_lib.common.addresses.RegisterAddress
import com.jacob.core_lib.common.addresses.RegisterAddress.*
import com.jacob.core_lib.registers.*
import com.jacob.core_lib.word.Word

class RegisterArray {

    val registers: Map<RegisterAddress, Register>
        get() = _registers

    private val _registers: MutableMap<RegisterAddress, Register> = mutableMapOf(
            REGISTER_1 to CoreRegister(),
            REGISTER_2 to CoreRegister(),
            REGISTER_3 to CoreRegister(),
            REGISTER_4 to CoreRegister(),
            REGISTER_5 to CoreRegister(),
            REGISTER_6 to CoreRegister(),
            REGISTER_7 to CoreRegister(),
            REGISTER_8 to CoreRegister(),
            REGISTER_9 to CoreRegister(),
            REGISTER_10 to CoreRegister(),
            REGISTER_11 to CoreRegister(),
            REGISTER_12 to CoreRegister(),
            STACK_POINTER to StackPointer(),
            LINK_REGISTER to LinkRegister(),
            PROGRAM_COUNTER to ProgramCounter(),
            STATUS_REGISTER to StatusRegister()
    )

    private val stackPointer = _registers[STACK_POINTER]!! as StackPointer
    private val linkRegister = _registers[LINK_REGISTER]!! as LinkRegister
    private val statusRegister = _registers[STATUS_REGISTER]!! as StatusRegister
    val programCounter = _registers[PROGRAM_COUNTER]!! as ProgramCounter

    fun getRegisterAt(registerAddress: RegisterAddress) = registers[registerAddress]
            ?: throw IllegalArgumentException("Invalid Register Address")

    fun setValueAtRegister(registerAddress: RegisterAddress, wordToStore: Word) {
        _registers.replace(registerAddress, CoreRegister(wordToStore))
    }

    override fun toString() = """RegisterArray(
        | registers=$registers,
        | stackPointer=$stackPointer,
        | linkRegister=$linkRegister,
        | statusRegister=$statusRegister,
        | programCounter=$programCounter
        | )""".trimMargin()
}
