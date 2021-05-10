package com.jacob.core_lib.core

import com.jacob.core_lib.common.addresses.MemoryAddress
import com.jacob.core_lib.common.padListTill
import com.jacob.core_lib.word.Word

class MemoryArray {
    private var mainMemory: MutableList<Word?> = ArrayList()

    fun getWordAt(memoryAddress: MemoryAddress): Word = mainMemory[memoryAddress.memoryAddress] ?: Word(0)

    fun setWordAt(memoryAddress: MemoryAddress, word: Word) {
        mainMemory = mainMemory.padListTill(memoryAddress.memoryAddress + 1)
        mainMemory[memoryAddress.memoryAddress] = word
    }

    override fun toString(): String {
        return "MemoryArray(mainMemory=$mainMemory)"
    }
}
