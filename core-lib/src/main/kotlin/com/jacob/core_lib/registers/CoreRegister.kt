package com.jacob.core_lib.registers

import com.jacob.core_lib.registers.address.RegisterAddress
import com.jacob.core_lib.word.Word

class CoreRegister(private val registerAddress: RegisterAddress) : Register {

    private var value: Word = Word(0)

    override fun getRegisterAddress(): RegisterAddress {
        return registerAddress
    }

    override fun setRegisterValue(word: Word) {
        value = word
    }

    override fun getRegisterValue(): Word {
        return value
    }

    override fun toString(): String {
        return "CoreRegister(registerAddress=$registerAddress, value=$value)"
    }
}
