package com.jacob.core_lib.word

import org.amshove.kluent.`should not be`
import org.amshove.kluent.shouldBeFalse
import org.amshove.kluent.shouldBeTrue
import org.amshove.kluent.shouldNotBeNull
import org.junit.jupiter.api.Test

internal class BitTest {

    @Test
    internal fun `can create a new bit`() {
        val bit = Bit()
        bit.shouldNotBeNull()
    }

    @Test
    internal fun `created bit should have value of false`() {
        val bit = Bit()
        bit.value.shouldBeFalse()
    }

    @Test
    internal fun `changing bit values should return a new instance`() {
        val bit = Bit()

        val setBit = bit.setBit()
        setBit `should not be` bit
        setBit.value.shouldBeTrue()

        val clearBit = bit.clearBit()
        clearBit.value.shouldBeFalse()

        var toggleBit = bit.toggleBit()
        toggleBit `should not be` bit
        toggleBit.value.shouldBeTrue()

        toggleBit = toggleBit.toggleBit()
        toggleBit.value.shouldBeFalse()
    }
}
