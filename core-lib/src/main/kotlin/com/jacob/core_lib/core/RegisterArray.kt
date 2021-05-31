package com.jacob.core_lib.core

import com.jacob.core_lib.common.addresses.RegisterAddress
import com.jacob.core_lib.common.addresses.RegisterAddresses
import com.jacob.core_lib.common.addresses.RegisterAddresses.*
import com.jacob.core_lib.registers.*
import com.jacob.core_lib.word.Word

class RegisterArray(
    private val stackPointer: StackPointer = StackPointer(),
    private val linkRegister: LinkRegister = LinkRegister(),
    val programCounter: ProgramCounter = ProgramCounter(),
    val statusRegister: StatusRegister = StatusRegister(),
) {

    val registers: Map<RegisterAddresses, Register>
        get() = _registers

    private val _registers: MutableMap<RegisterAddresses, Register> = mutableMapOf(
        REGISTER_0 to CoreRegister(),
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
        STACK_POINTER to stackPointer,
        LINK_REGISTER to linkRegister,
        PROGRAM_COUNTER to programCounter,
        STATUS_REGISTER to statusRegister
    )

    fun getRegisterAt(registerAddress: RegisterAddress) = registers[registerAddress.getRegisterAddress()]
        ?: throw IllegalArgumentException("Invalid Register Address")

    fun setValueAtRegister(registerAddress: RegisterAddress, wordToStore: Word) {
        _registers.replace(registerAddress.getRegisterAddress(), CoreRegister(wordToStore))
    }

    override fun toString() = """RegisterArray(
        | registers=$registers,
        | stackPointer=$stackPointer,
        | linkRegister=$linkRegister,
        | statusRegister=$statusRegister,
        | programCounter=$programCounter
        | )""".trimMargin()
}
