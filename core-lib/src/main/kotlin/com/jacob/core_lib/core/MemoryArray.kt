package com.jacob.core_lib.core

import com.jacob.core_lib.common.padListTill
import com.jacob.core_lib.word.Word

class MemoryArray {
    private var mainMemory: MutableList<Word?> = ArrayList()

    fun getWordAt(location: Int): Word = mainMemory[location] ?: Word.from(0)

    fun setWordAt(location: Int, word: Word) {
        mainMemory = mainMemory.padListTill(location + 1)
        mainMemory[location] = word
    }

}
