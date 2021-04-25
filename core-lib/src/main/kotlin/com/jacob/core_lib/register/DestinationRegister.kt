package com.jacob.core_lib.register

class DestinationRegister(val registerAddress: RegisterAddress): Register {

    override fun toString(): String {
        return "DestinationRegister(registerAddress=$registerAddress)"
    }
}
