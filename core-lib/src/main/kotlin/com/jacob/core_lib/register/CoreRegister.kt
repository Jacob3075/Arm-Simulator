package com.jacob.core_lib.register 

class CoreRegister(val registerAddress: RegisterAddress) : Register {

    override fun toString(): String {
        return "CoreRegister(registerAddress=$registerAddress)"
    }
}
