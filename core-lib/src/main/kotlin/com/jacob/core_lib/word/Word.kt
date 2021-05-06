package com.jacob.core_lib.word

open class Word(private val value: Int) {
    override fun toString(): String {
        return "Word(value=$value)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        other ?: return false
        if (javaClass != other.javaClass && javaClass.isAssignableFrom(other.javaClass)) return false

        other as Word

        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

    operator fun plus(otherWord: Word): Word {
        return Word(value + otherWord.value)
    }
}
