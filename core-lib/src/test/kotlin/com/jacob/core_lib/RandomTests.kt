package com.jacob.core_lib

import com.jacob.core_lib.core.MemoryArray
import com.jacob.core_lib.word.Word
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be`
import org.junit.jupiter.api.Test

internal class RandomTests {
    @Test
    internal fun name() {
        val memoryArray = MemoryArray()
        val word = Word(5)
        memoryArray.setWordAt(5, word)
        memoryArray.getWordAt(5) `should be` word
    }

    @Test
    fun `binary form of word`() {
        var binaryString = Integer.toBinaryString(-10)
        binaryString = binaryString.takeLast(32)
        Integer.parseUnsignedInt(binaryString, 2) `should be equal to` -10
    }
}
