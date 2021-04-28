package com.jacob.core_lib.word

import org.amshove.kluent.*
import org.junit.jupiter.api.Test

internal class WordTest {

    @Test
    internal fun `create word from int value`() {
        val intValue = 200

        val word = Word(intValue)

        word.shouldNotBeNull()
    }

    @Test
    internal fun `created word from int has same value when converted back to int`() {
        val intValue = 200
        val word = Word(intValue)

        word.value `should be equal to` intValue
    }

    @Test
    internal fun `can create word from negative numbers`() {
        val intValue = -50
        val word = Word(intValue)

        word.shouldNotBeNull()
    }

    @Test
    internal fun `words created from negative numbers should have the same value when converted back to int`() {
        val intValue = -50
        val word = Word(intValue)

        word.value `should be equal to` intValue
    }
}
