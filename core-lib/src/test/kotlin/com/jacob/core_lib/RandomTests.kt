package com.jacob.core_lib

import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Test

internal class RandomTests {

    @Test
    fun `binary form of word`() {
        var binaryString = Integer.toBinaryString(-10)
        binaryString = binaryString.takeLast(32)
        Integer.parseUnsignedInt(binaryString, 2) `should be equal to` -10
    }
}
