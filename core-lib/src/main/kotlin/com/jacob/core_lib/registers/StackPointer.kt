package com.jacob.core_lib.registers

import com.jacob.core_lib.registers.address.RegisterAddress
import com.jacob.core_lib.word.Word

class StackPointer : Register {
    val registerNumber: RegisterAddress = RegisterAddress.STACKPOINTER
    override fun getRegisterAddress(): RegisterAddress {
        TODO("Not yet implemented")
    }

    override fun setRegisterValue(word: Word) {
        TODO("Not yet implemented")
    }

    override fun getRegisterValue(): Word {
        TODO("Not yet implemented")
    }

}