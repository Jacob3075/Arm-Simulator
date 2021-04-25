package com.jacob.core_lib.word

class Bit(val value: Boolean = false) {

    fun setBit() = Bit(true)

    fun clearBit() = Bit(false)

    fun toggleBit() = Bit(!value)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Bit
        if (value != other.value) return false
        return true
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

    override fun toString(): String {
        return "Bit(value=$value)"
    }
}
