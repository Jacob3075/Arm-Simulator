package com.jacob.core_lib.word

open class Word(val value: Int) {
    override fun toString() = "Word(value=$value)"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        other ?: return false
        if (javaClass != other.javaClass && javaClass.isAssignableFrom(other.javaClass)) return false

        other as Word

        if (value != other.value) return false

        return true
    }

    override fun hashCode() = value.hashCode()

    operator fun plus(otherWord: Word) = Word(value + otherWord.value)

    operator fun minus(otherWord: Word) = Word(value - otherWord.value)

    operator fun times(otherWord: Word) = Word(value * otherWord.value)
}
