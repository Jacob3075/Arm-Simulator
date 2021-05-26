package com.jacob.core_lib.common

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.MemoryAddress
import com.jacob.core_lib.common.addresses.RegisterAddresses
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.word.ImmediateValue
import com.jacob.core_lib.word.Word
import kotlin.math.pow
import kotlin.reflect.KFunction1

fun Boolean.toInt() = if (this) 1 else 0

infix fun Int.`**`(exponent: Int): Int = toDouble().pow(exponent).toInt()

fun <T> List<T?>.padListTill(newLength: Int): MutableList<T?> =
    List(maxOf(newLength, size)) { index -> getOrNull(index) }.toMutableList()

fun <T> String.toRegisterAddress(convertToAddressOfType: KFunction1<RegisterAddresses, T>) =
    this.replace("R", "REGISTER_")
        .let(RegisterAddresses::valueOf)
        .let(convertToAddressOfType)

fun List<String>.toRegisterAddresses(): List<RegisterAddresses> {
    return this.map { it.replace("R", "REGISTER_") }
        .map(RegisterAddresses::valueOf)
}

val Int.W: Word
    get() = Word(this)

val Int.I: ImmediateValue
    get() = ImmediateValue(this)

val Int.MA: MemoryAddress
    get() = MemoryAddress(this)

val Int.RA: RegisterAddresses
    get() {
        require(this <= 15)
        return RegisterAddresses.valueOf("REGISTER_$this")
    }

val Int.DR: DestinationRegister
    get() = DestinationRegister(this.RA)

val Int.SR: SourceRegister
    get() = SourceRegister(this.RA)

fun String.immediateFromDec() = this.removePrefix("#")
    .toInt()
    .let(::ImmediateValue)

fun String.immediateFromHex() = this.removePrefix("#0X")
    .toInt(16)
    .let(::ImmediateValue)
