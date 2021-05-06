package com.jacob.core_lib.common.addresses

@JvmInline
value class MemoryAddress(val memoryAddress: Int) {
    init {
        require(memoryAddress >= 0)
    }

    override fun toString() = "MemoryAddress(memoryAddress=$memoryAddress)"
}
