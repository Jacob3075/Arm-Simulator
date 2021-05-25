package com.jacob.core_lib.common.regex

class InstructionRegex private constructor() {

    class Add private constructor() {
        companion object {
            val MNEMONIC = "^ADD".toRegex()
            val REGISTER = "^ADD R([0-9]|1[0-4]), ?R([0-9]|1[0-4]), ?R([0-9]|1[0-4])".toRegex() // ADD R4, R4, R5
            val IMMEDIATE_DEC = "^ADD R([0-9]|1[0-4]), ?R([0-9]|1[0-4]), ?#-?\\d+$".toRegex() // ADD R4, R4, #5
            val IMMEDIATE_HEX =
                "^ADD R([0-9]|1[0-4]), ?R([0-9]|1[0-4]), ?#0X(\\d|[A-F])+$".toRegex() // ADD R4, R4, #0X012AF
        }
    }

    class Sub private constructor() {
        companion object {
            val MNEMONIC = "^SUB".toRegex()
            val REGISTER = "^SUB R([0-9]|1[0-4]), ?R([0-9]|1[0-4]), ?R([0-9]|1[0-4])$".toRegex() // SUB R4, R4, R5
            val IMMEDIATE_DEC = "^SUB R([0-9]|1[0-4]), ?R([0-9]|1[0-4]), ?#\\d+$".toRegex() // SUB R4, R4, #5
            val IMMEDIATE_HEX =
                "^SUB R([0-9]|1[0-4]), ?R([0-9]|1[0-4]), ?#0X(\\d|[A-F])+$".toRegex() // SUB R4, R4, #0X012AF
        }
    }

    class Multiply private constructor() {
        companion object {
            val MNEMONIC = "^MUL".toRegex()
            val REGISTER = "^MUL R([0-9]|1[0-4]), ?R([0-9]|1[0-4]), ?R([0-9]|1[0-4])$".toRegex() // MUL R4, R4, R5
            val IMMEDIATE_DEC = "^MUL R([0-9]|1[0-4]), ?R([0-9]|1[0-4]), ?#\\d+$".toRegex() // MUL R4, R4, #5
            val IMMEDIATE_HEX =
                "^MUL R([0-9]|1[0-4]), ?R([0-9]|1[0-4]), ?#0X(\\d|[A-F])+$".toRegex() // MUL R4, R4, #0X012AF
        }
    }

    class Move private constructor() {
        companion object {
            val MNEMONIC = "^MOV".toRegex()
            val REGISTER = "^MOV R([0-9]|1[0-4]), ?R([0-9]|1[0-4])$".toRegex() // MOV R1, R5
            val IMMEDIATE_DEC = "^MOV R([0-9]|1[0-4]), ?#-?\\d+$".toRegex() // MOV R1, #5
            val IMMEDIATE_HEX =
                "^MOV ?R([0-9]|1[0-4]), ?#0X(\\d|[A-F])+$".toRegex() // MOV R4, R4, #0X012AF
        }
    }

    class Compare private constructor() {
        companion object {
            val MNEMONIC = "^CMP".toRegex()
            val REGISTER = "^CMP R([0-9]|1[0-4]), R([0-9]|1[0-4])".toRegex() // CMP, R1, R2
            val IMMEDIATE_DEC = "^CMP R([0-9]|1[0-4]), ?#-?\\d+$".toRegex() // CMP R1, #1234
            val IMMEDIATE_HEX = "^CMP R([0-9]|1[0-4]), ?#0X(\\d|[A-F])+".toRegex() // CMP R1, #0X12AFBC
        }
    }

    class Load private constructor() {
        companion object {
            val MNEMONIC = "^LDR".toRegex()
            val REGISTER = "^LDR R([0-9]|1[0-4]), ?\\[R([0-9]|1[0-4]])]$".toRegex() // LDR R1, [R0]
            val VARIABLE = "^LDR R([0-9]|1[0-4]), ?=[A-Z]+$".toRegex() // LDR R0,=A
        }
    }

    class Store private constructor() {
        companion object {
            val MNEMONIC = "^STR".toRegex()
            val REGISTER = "^STR R([0-9]|1[0-4]), ?\\[R([0-9]|1[0-4]])]$".toRegex()
            val VARIABLE = "^STR R([0-9]|1[0-4]), ?=[A-Z]+$".toRegex()
        }
    }

    class Branch private constructor() {
        companion object {
            val MNEMONIC = "^B".toRegex()
            val LABEL = "^B [A-Z]([A-Z]|_|-|\\d)*$".toRegex() // B LABEL_NAME-28
        }
    }

    class Shifts private constructor() {
        companion object {
            val TYPES = "(LSL|LSR|ASR|ROR|RRX)".toRegex()

            class LeftShift private constructor() {
                companion object {
                    val MNEMONIC = "^LSL".toRegex()
                    val REGISTER = "^LSL R([0-9]|1[0-4]), ?R([0-9]|1[0-4]), ?R([0-9]|1[0-4])$".toRegex()
                    val IMMEDIATE_DEC = "^LSL R([0-9]|1[0-4]), ?R([0-9]|1[0-4]), #-?\\d+$".toRegex()
                    val IMMEDIATE_HEX = "^LSL R([0-9]|1[0-4]), ?R([0-9]|1[0-4]), ?#0X(\\d|[A-F])+$".toRegex()
                    val OPERATION = "LSL #\\d+".toRegex()
                }
            }

            class RightShift private constructor() {
                companion object {
                    val MNEMONIC = "^LSR".toRegex()
                    val REGISTER = "^LSR R([0-9]|1[0-4]), ?R([0-9]|1[0-4]), ?R([0-9]|1[0-4])$".toRegex()
                    val IMMEDIATE_DEC = "^LSR R([0-9]|1[0-4]), ?R([0-9]|1[0-4]), #-?\\d+$".toRegex()
                    val IMMEDIATE_HEX = "^LSR R([0-9]|1[0-4]), ?R([0-9]|1[0-4]), ?#0X(\\d|[A-F])+$".toRegex()
                    val OPERATION = "LSR #\\d+".toRegex()
                }
            }

            val ARITHMETIC_RIGHT_SHIFT = "^ASR".toRegex()
            val ROTATE_RIGHT = "^ROR".toRegex()
            val ROTATE_RIGHT_EXTENDED = "^RRX".toRegex()
        }
    }

    companion object {
        val LABEL = "^[A-Z]([A-Z]|_|-|\\d)*:$".toRegex() // LABEL_NEW-1:
    }
}
