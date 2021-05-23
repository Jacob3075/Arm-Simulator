@file:Suppress("ClassName")

package com.jacob.core_lib.registers

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class StatusRegisterTest {
    private val statusRegister = StatusRegister()
    private val minus: Int.(Int) -> Int = Int::minus

    @Nested
    inner class SubtractionOperation {

        @Nested
        inner class BothPositive {
            @Test
            internal fun `both values equal`() {
                statusRegister.updateCPSR(5, 5, minus)

                verifyCPSR(negative = false, zero = true, carry = true, overFlow = false)
            }

            @Test
            internal fun `second value greater than first`() {
                statusRegister.updateCPSR(5, 8, minus)

                verifyCPSR(negative = true, zero = false, carry = false, overFlow = false)
            }

            @Test
            internal fun `first value greater than second`() {
                statusRegister.updateCPSR(8, 3, minus)

                verifyCPSR(negative = false, zero = false, carry = true, overFlow = false)
            }
        }

        @Nested
        inner class BothNegative {
            @Test
            internal fun `both values are equal`() {
                statusRegister.updateCPSR(-5, -5, minus)

                verifyCPSR(negative = false, zero = true, carry = true, overFlow = false)
            }

            @Test
            internal fun `second value greater magnitude`() {
                statusRegister.updateCPSR(-5, -8, minus)

                verifyCPSR(negative = false, zero = false, carry = true, overFlow = false)
            }

            @Test
            internal fun `first value greater magnitude`() {
                statusRegister.updateCPSR(-5, -3, minus)

                verifyCPSR(negative = true, zero = false, carry = false, overFlow = false)
            }
        }

        @Nested
        inner class `Positive - Negative` {
            @Test
            internal fun `first value positive and second negative with equal magnitude`() {
                statusRegister.updateCPSR(5, -5, minus)

                verifyCPSR(negative = false, zero = false, carry = false, overFlow = false)
            }

            @Test
            internal fun `first value positive and second negative with magnitude greater then first`() {
                statusRegister.updateCPSR(3, -5, minus)

                verifyCPSR(negative = false, zero = false, carry = false, overFlow = false)
            }

            @Test
            internal fun `first value positive with magnitude lesser then first and second negative`() {
                statusRegister.updateCPSR(5, -3, minus)

                verifyCPSR(negative = false, zero = false, carry = false, overFlow = false)
            }
        }

        @Nested
        inner class `Negative - Positive` {
            @Test
            internal fun `first value positive and second negative with equal magnitude`() {
                statusRegister.updateCPSR(-5, 5, minus)

                verifyCPSR(negative = true, zero = false, carry = true, overFlow = false)
            }

            @Test
            internal fun `first value negative with magnitude lesser then first and second positive`() {
                statusRegister.updateCPSR(-3, 5, minus)

                verifyCPSR(negative = true, zero = false, carry = true, overFlow = false)
            }

            @Test
            internal fun `first value negative with magnitude greater then first and second positive`() {
                statusRegister.updateCPSR(-5, 3, minus)

                verifyCPSR(negative = true, zero = false, carry = true, overFlow = false)
            }
        }
    }

    @Nested
    inner class AdditionOperation

    fun verifyCPSR(negative: Boolean, zero: Boolean, carry: Boolean, overFlow: Boolean) {
        assertEquals(
            negative,
            statusRegister.negative,
            "Failed For 'Negative' Flag"
        )
        assertEquals(
            zero,
            statusRegister.zero,
            "Failed For 'Zero' Flag"
        )
        assertEquals(
            carry,
            statusRegister.carry,
            "Failed For 'Carry' Flag"
        )
        assertEquals(
            overFlow,
            statusRegister.overFlow,
            "Failed For 'Overflow' Flag"
        )
    }
}
