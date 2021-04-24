package com.jacob.core_lib

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class SomeClassTest {
    @Test
    internal fun name() {
        assertEquals(2, 1 + 1)
    }

    @Test
    internal fun anotherTest() {
        assertTrue(SomeClass().someNewMethod())
    }
}

