package com.jacob.core_lib.registers

import com.jacob.core_lib.word.Word

interface Register {
    fun getRegisterValue(): Word
}
