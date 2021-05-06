package com.jacob.core_lib.common.addresses

@JvmInline
value class DestinationRegister(val registerAddress: RegisterAddress) {

    override fun toString(): String {
        return "DestinationRegister(registerAddress=$registerAddress)"
    }
}
