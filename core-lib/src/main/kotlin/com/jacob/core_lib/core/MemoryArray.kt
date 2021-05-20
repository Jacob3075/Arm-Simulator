package com.jacob.core_lib.core

import com.jacob.core_lib.common.addresses.MemoryAddress
import com.jacob.core_lib.common.padListTill
import com.jacob.core_lib.word.Word

class MemoryArray {
    private var _mainMemory: MutableList<Word?> = ArrayList()
    val mainMemory: List<Word?>
        get() = _mainMemory

    fun getWordAt(memoryAddress: MemoryAddress): Word = _mainMemory[memoryAddress.memoryAddress] ?: Word(0)

    fun setWordAt(memoryAddress: MemoryAddress, word: Word) {
        _mainMemory = _mainMemory.padListTill(memoryAddress.memoryAddress + 1)
        _mainMemory[memoryAddress.memoryAddress] = word
    }

    override fun toString(): String {
        return "MemoryArray(mainMemory=$_mainMemory)"
    }
}
