package com.jacob.core_lib.registers.address

@JvmInline
value class DestinationRegister(val registerAddress: RegisterAddress) {

    override fun toString(): String {
        return "DestinationRegister(registerAddress=$registerAddress)"
    }
}
