package com.jacob.core_lib.word

import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should throw`
import org.amshove.kluent.invoking
import org.junit.jupiter.api.Test

internal class WordTest {

    @Test
    internal fun `create word from int value`() {
        val intValue = 200
        val word: Word = Word.from(intValue)

        word.bitArray.size `should be equal to` 32

        val fromWord: Int = word.toInt()
        fromWord `should be equal to` intValue
    }

    @Test
    fun `cannot add more bits word`() {
        val word = Word.from(50)
        invoking { word.bitArray[33] } `should throw` IndexOutOfBoundsException::class
    }
}
