package com.jacob.core_lib.registers

import com.jacob.core_lib.word.Word

class StatusRegister : Register {
    val negative: Boolean = false
    val zero: Boolean = false
    val carry: Boolean = false
    val overFlow: Boolean = false

    override fun getRegisterValue(): Word {
        TODO("Not yet implemented")
    }
}
