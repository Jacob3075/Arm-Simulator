package com.jacob.core_lib.registers

import com.jacob.core_lib.common.addresses.RegisterAddress
import com.jacob.core_lib.word.Word

interface Register {
    fun getRegisterAddress(): RegisterAddress
    fun setRegisterValue(word: Word)
    fun getRegisterValue(): Word
}
