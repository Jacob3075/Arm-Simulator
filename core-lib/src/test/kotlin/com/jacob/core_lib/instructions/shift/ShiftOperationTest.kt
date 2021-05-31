package com.jacob.core_lib.instructions.shift

import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class ShiftOperationTest {
    @Nested
    inner class RightShiftTest {
        @Test
        internal fun `returns correct result when given single digit shift amount`() {
            val rightShift = RightShift(2)
            val result = rightShift.shift(20, 0)

            result `should be equal to` 5
        }

        @Test
        internal fun `returns correct result when given multiple digit shift amount`() {
            val rightShift = RightShift(11)
            val result = rightShift.shift(4096, 0)

            result `should be equal to` 2
        }

        @Test
        internal fun `returns correct result when given negative value`() {
            val rightShift = RightShift(3)
            val result = rightShift.shift(-93, 0)

            result `should be equal to` 536870900
        }
    }

    @Nested
    inner class LeftShiftTest {
        @Test
        internal fun `return correct result when given single digit shift amount`() {
            val leftShift = LeftShift(2)
            val result = leftShift.shift(20, 0)

            result `should be equal to` 80
        }

        @Test
        internal fun `return correct result when given multiple digit shift amount`() {
            val leftShift = LeftShift(11)
            val result = leftShift.shift(2, 0)

            result `should be equal to` 4096
        }

        @Test
        internal fun `returns correct result when given negative value`() {
            val leftShift = LeftShift(3)
            val result = leftShift.shift(-93, 0)

            result `should be equal to` -744
        }
    }

    @Nested
    inner class RightRotateShiftTest {
        @Test
        internal fun `returns correct value when given positive value 1`() {
            val rightRotate = RightRotateShift(2)
            val result = rightRotate.shift(93, 0)

            result `should be equal to` 1073741847
        }

        @Test
        internal fun `returns correct value when given positive value 2`() {
            val rightRotate = RightRotateShift(1)
            val result = rightRotate.shift(152145415, 0)

            result `should be equal to` -2071410941
        }

        @Test
        internal fun `returns correct value when given positive value 3`() {
            val rightRotate = RightRotateShift(12)
            val result = rightRotate.shift(152145415, 0)

            result `should be equal to` -529493736
        }

        @Test
        internal fun `returns correct value when given negative value 1`() {
            val rightRotate = RightRotateShift(2)
            val result = rightRotate.shift(-93, 0)

            result `should be equal to` -24
        }

        @Test
        internal fun `returns correct value when given negative value 2`() {
            val rightRotate = RightRotateShift(1)
            val result = rightRotate.shift(-152145415, 0)

            result `should be equal to` -76072708
        }
    }

    @Nested
    inner class ArithmeticRiShiftTest {
        @Test
        internal fun `return correct result when positive value 1`() {
            val arithmeticRightShift = ArithmeticRightShift(2)
            val result = arithmeticRightShift.shift(93, 0)

            result `should be equal to` 23
        }

        @Test
        internal fun `return correct result when positive value 2`() {
            val arithmeticRightShift = ArithmeticRightShift(2)
            val result = arithmeticRightShift.shift(152145415, 0)

            result `should be equal to` 38036353
        }

        @Test
        internal fun `returns correct result when given negative value`() {
            val arithmeticRightShift = ArithmeticRightShift(3)
            val result = arithmeticRightShift.shift(-152145415, 0)

            result `should be equal to` -19018177
        }
    }

    // TODO
    inner class RotateRightExtendedTest {
        // https://onlinetoolz.net/bitshift
        internal fun `returns correct result when given positive value and carry is 1`() {
            val rightRotateExtended = RightRotateExtendedShift(2)
            val result = rightRotateExtended.shift(93, 1)

            println(Integer.toBinaryString(93))
            println(Integer.toBinaryString(result))
            println(result)

            result `should be equal to` -1073741801
        }

        internal fun `returns correct result when given positive value and carry is 0`() {
            val rightRotateExtended = RightRotateExtendedShift(2)
            val result = rightRotateExtended.shift(200999999, 0)

            println(Integer.toBinaryString(93))
            println(Integer.toBinaryString(result))
            println(result)

            result `should be equal to` -2147483625
        }
    }
}
