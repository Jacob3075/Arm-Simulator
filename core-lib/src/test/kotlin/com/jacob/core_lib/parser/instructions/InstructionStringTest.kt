package com.jacob.core_lib.parser.instructions

import com.jacob.core_lib.common.LS
import com.jacob.core_lib.instructions.conditionals.Conditionals
import com.jacob.core_lib.instructions.shift.ShiftOperation
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should contain all`
import org.amshove.kluent.`should contain`
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class InstructionStringTest {
    @Test
    internal fun `field contains correct value for simple instruction`() {
        val instructionString = InstructionString("ADD R1, R2, R12")

        instructionString.apply {
            mainInstruction `should be equal to` "ADD R1, R2, R12"
            mnemonic `should be equal to` "ADD"
            conditional `should be equal to` Conditionals.AL
            shiftOperation `should be equal to` ShiftOperation.None

            operands.apply {
                this `should contain` "R1"
                this `should contain` "R2"
                this `should contain` "R12"
                this `should be equal to` listOf("R1", "R2", "R12")
            }
        }
    }

    @Test
    internal fun `field contains correct value for instruction with shift`() {
        val instructionString = InstructionString("ADDAL R1, R2, R12, LSL #12")

        instructionString.apply {
            mainInstruction `should be equal to` "ADD R1, R2, R12"
            mnemonic `should be equal to` "ADD"
            conditional `should be equal to` Conditionals.AL
            shiftOperation `should be equal to` 12.LS

            operands.apply {
                this `should contain` "R1"
                this `should contain` "R2"
                this `should contain` "R12"
                this `should be equal to` listOf("R1", "R2", "R12")
            }
        }
    }

    @Test
    internal fun `field contains correct value for instruction with conditional`() {
        val instructionString = InstructionString("ADDEQ R1, R2, R12")

        instructionString.apply {
            mainInstruction `should be equal to` "ADD R1, R2, R12"
            mnemonic `should be equal to` "ADD"
            conditional `should be equal to` Conditionals.EQ
            shiftOperation `should be equal to` ShiftOperation.None

            operands.apply {
                this `should contain` "R1"
                this `should contain` "R2"
                this `should contain` "R12"
                this `should be equal to` listOf("R1", "R2", "R12")
            }
        }
    }

    @Test
    internal fun `field contains correct value for instruction with conditional and shift`() {
        val instructionString = InstructionString("ADDEQ R1, R2, R13, LSL #10")

        instructionString.apply {
            mainInstruction `should be equal to` "ADD R1, R2, R13"
            mnemonic `should be equal to` "ADD"
            conditional `should be equal to` Conditionals.EQ
            shiftOperation `should be equal to` 10.LS

            operands.apply {
                this `should contain` "R1"
                this `should contain` "R2"
                this `should contain` "R13"
                this `should be equal to` listOf("R1", "R2", "R13")
            }
        }
    }

    @Test
    internal fun `when label instruction is given, correct fields are set`() {
        val instructionString = InstructionString("SOME_LABEL-12:")

        instructionString.apply {
            mainInstruction `should be equal to` "SOME_LABEL-12:"
            shiftOperation `should be equal to` ShiftOperation.None
            conditional `should be equal to` Conditionals.AL
        }
    }

    @Test
    internal fun `when branch instruction is given fields have correct value`() {
        val instructionString = InstructionString("B SOME_LABEL-12")

        instructionString.apply {
            mnemonic `should be equal to` "B"
            operands `should contain all` listOf("SOME_LABEL-12")
            mainInstruction `should be equal to` "B SOME_LABEL-12"
            conditional `should be equal to` Conditionals.AL
            shiftOperation `should be equal to` ShiftOperation.None
        }
    }

    @Test
    internal fun `when branch instruction with conditional is given fields have correct value`() {
        val instructionString = InstructionString("BEQ SOME_LABEL-12")

        instructionString.apply {
            mnemonic `should be equal to` "B"
            operands `should contain all` listOf("SOME_LABEL-12")
            mainInstruction `should be equal to` "B SOME_LABEL-12"
            conditional `should be equal to` Conditionals.EQ
            shiftOperation `should be equal to` ShiftOperation.None
        }
    }

    @Nested
    inner class `Instructions With Offset Tests` {
        @Test
        internal fun `memory instruction with immediate offset`() {
            val instructionString = InstructionString("LDR R1, [R2, #3]")

            instructionString.apply {
                mnemonic `should be equal to` "LDR"
                mainInstruction `should be equal to` "LDR R1, [R2, #3]"
                conditional `should be equal to` Conditionals.AL
                shiftOperation `should be equal to` ShiftOperation.None

                operands.apply {
                    this `should contain` "R1"
                    this `should contain` "R2"
                    this `should be equal to` listOf("R1", "R2", "#3")
                }
            }
        }

        @Test
        internal fun `memory instruction with post offset`() {
            val instructionString = InstructionString("LDR R1, [R2], #3")

            instructionString.apply {
                mnemonic `should be equal to` "LDR"
                mainInstruction `should be equal to` "LDR R1, [R2], #3"
                conditional `should be equal to` Conditionals.AL
                shiftOperation `should be equal to` ShiftOperation.None

                operands.apply {
                    this `should contain` "R1"
                    this `should contain` "R2"
                    this `should be equal to` listOf("R1", "R2", "#3")
                }
            }
        }

        @Test
        internal fun `memory instruction with pre offset`() {
            val instructionString = InstructionString("STR R1, [R2, #3]!")

            instructionString.apply {
                mnemonic `should be equal to` "STR"
                mainInstruction `should be equal to` "STR R1, [R2, #3]!"
                conditional `should be equal to` Conditionals.AL
                shiftOperation `should be equal to` ShiftOperation.None

                operands.apply {
                    this `should contain` "R1"
                    this `should contain` "R2"
                    this `should be equal to` listOf("R1", "R2", "#3")
                }
            }
        }

        @Test
        internal fun `memory instruction with pre offset with conditional`() {
            val instructionString = InstructionString("STREQ R1, [R2, #3]!")

            instructionString.apply {
                mnemonic `should be equal to` "STR"
                mainInstruction `should be equal to` "STR R1, [R2, #3]!"
                conditional `should be equal to` Conditionals.EQ
                shiftOperation `should be equal to` ShiftOperation.None

                operands.apply {
                    this `should contain` "R1"
                    this `should contain` "R2"
                    this `should be equal to` listOf("R1", "R2", "#3")
                }
            }
        }

        @Test
        internal fun `memory instruction with post offset with offset`() {
            val instructionString = InstructionString("LDREQ R1, [R2], #3")

            instructionString.apply {
                mnemonic `should be equal to` "LDR"
                mainInstruction `should be equal to` "LDR R1, [R2], #3"
                conditional `should be equal to` Conditionals.EQ
                shiftOperation `should be equal to` ShiftOperation.None

                operands.apply {
                    this `should contain` "R1"
                    this `should contain` "R2"
                    this `should be equal to` listOf("R1", "R2", "#3")
                }
            }
        }
    }
}
