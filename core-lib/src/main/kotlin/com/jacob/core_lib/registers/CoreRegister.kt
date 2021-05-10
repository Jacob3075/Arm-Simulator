package com.jacob.core_lib.registers

import com.jacob.core_lib.word.Word

class CoreRegister(private val value: Word = Word(0)) : Register {

    override fun getRegisterValue(): Word {
        return value
    }

    override fun toString(): String {
        return "CoreRegister(value=$value)"
    }
}
