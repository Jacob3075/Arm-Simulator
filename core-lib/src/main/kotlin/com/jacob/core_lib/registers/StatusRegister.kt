package com.jacob.core_lib.registers

import com.jacob.core_lib.registers.address.RegisterAddress
import com.jacob.core_lib.word.Word

class StatusRegister : Register {
    var negative: Boolean = false
    var zero: Boolean = false
    var carry: Boolean = false
    var overFlow: Boolean = false

    override fun getRegisterAddress(): RegisterAddress {
        TODO("Not yet implemented")
    }

    override fun setRegisterValue(word: Word) {
        TODO("Not yet implemented")
    }
}
