package com.jacob.core_lib

import com.jacob.core_lib.core.MemoryArray
import com.jacob.core_lib.word.Word
import org.amshove.kluent.`should be`
import org.junit.jupiter.api.Test

internal class RandomTests {
    @Test
    internal fun name() {
        val memoryArray = MemoryArray()
        val word = Word.from(5)
        memoryArray.setWordAt(5, word)
        memoryArray.getWordAt(5) `should be` word
    }
}
