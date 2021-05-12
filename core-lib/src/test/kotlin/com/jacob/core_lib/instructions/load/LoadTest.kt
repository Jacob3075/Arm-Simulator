package com.jacob.core_lib.instructions.load

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.MemoryAddress
import com.jacob.core_lib.common.addresses.RegisterAddress
import org.amshove.kluent.`should be instance of`
import org.amshove.kluent.shouldNotBeNull
import org.junit.jupiter.api.Test

internal class LoadTest {
    @Test
    internal fun `creates correct instruction when given memory address`() {
        val destinationRegister = DestinationRegister(RegisterAddress.REGISTER_0)
        val memoryAddress = MemoryAddress(0)

        val loadInstruction = Load.of(destinationRegister, memoryAddress)

        loadInstruction.shouldNotBeNull()

        loadInstruction `should be instance of` Load::class
        loadInstruction `should be instance of` LoadMemoryAddress::class
    }

    @Test
    internal fun `creates correct instruction when given variable name`() {
        val destinationRegister = DestinationRegister(RegisterAddress.REGISTER_0)

        val variableName = "A"
        val loadInstruction = Load.of(destinationRegister, variableName)

        loadInstruction.shouldNotBeNull()

        loadInstruction `should be instance of` Load::class
        loadInstruction `should be instance of` LoadVariableAddress::class
    }
}
