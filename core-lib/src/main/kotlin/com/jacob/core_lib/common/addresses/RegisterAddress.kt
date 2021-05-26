package com.jacob.core_lib.common.addresses

sealed interface RegisterAddress {
    fun getRegisterAddress(): RegisterAddresses
}

@JvmInline
value class DestinationRegister(private val registerAddress: RegisterAddresses) : RegisterAddress {
    override fun getRegisterAddress(): RegisterAddresses = registerAddress

    override fun toString(): String {
        return "DestinationRegister(registerAddress=$registerAddress)"
    }
}

@JvmInline
value class SourceRegister(private val registerAddress: RegisterAddresses) : RegisterAddress {
    override fun getRegisterAddress(): RegisterAddresses = registerAddress
}
