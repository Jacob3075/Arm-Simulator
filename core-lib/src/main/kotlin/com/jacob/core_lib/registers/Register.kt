package com.jacob.core_lib.registers

import com.jacob.core_lib.registers.address.RegisterAddress
import com.jacob.core_lib.word.Word

interface Register {
    fun getRegisterAddress(): RegisterAddress
    fun setRegisterValue(word: Word)
}
