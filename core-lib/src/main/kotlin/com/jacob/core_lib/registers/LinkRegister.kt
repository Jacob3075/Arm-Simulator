package com.jacob.core_lib.registers

import com.jacob.core_lib.common.addresses.RegisterAddress
import com.jacob.core_lib.word.Word

class LinkRegister : Register {

    val registerNumber: RegisterAddress = RegisterAddress.LINK_REGISTER
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
