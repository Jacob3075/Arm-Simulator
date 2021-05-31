package com.jacob.core_lib.common.regex

object InstructionRegex {

    object Add {
        val MNEMONIC = "^ADD".toRegex()
        val REGISTER = "^ADD R([0-9]|1[0-4]), ?R([0-9]|1[0-4]), ?R([0-9]|1[0-4])".toRegex() // ADD R4, R4, R5
        val IMMEDIATE_DEC = "^ADD R([0-9]|1[0-4]), ?R([0-9]|1[0-4]), ?#-?\\d+$".toRegex() // ADD R4, R4, #5
        val IMMEDIATE_HEX =
            "^ADD R([0-9]|1[0-4]), ?R([0-9]|1[0-4]), ?#0X(\\d|[A-F])+$".toRegex() // ADD R4, R4, #0X012AF
    }

    object Sub {
        val MNEMONIC = "^SUB".toRegex()
        val REGISTER = "^SUB R([0-9]|1[0-4]), ?R([0-9]|1[0-4]), ?R([0-9]|1[0-4])$".toRegex() // SUB R4, R4, R5
        val IMMEDIATE_DEC = "^SUB R([0-9]|1[0-4]), ?R([0-9]|1[0-4]), ?#\\d+$".toRegex() // SUB R4, R4, #5
        val IMMEDIATE_HEX =
            "^SUB R([0-9]|1[0-4]), ?R([0-9]|1[0-4]), ?#0X(\\d|[A-F])+$".toRegex() // SUB R4, R4, #0X012AF
    }

    object Multiply {
        val MNEMONIC = "^MUL".toRegex()
        val REGISTER = "^MUL R([0-9]|1[0-4]), ?R([0-9]|1[0-4]), ?R([0-9]|1[0-4])$".toRegex() // MUL R4, R4, R5
        val IMMEDIATE_DEC = "^MUL R([0-9]|1[0-4]), ?R([0-9]|1[0-4]), ?#\\d+$".toRegex() // MUL R4, R4, #5
        val IMMEDIATE_HEX =
            "^MUL R([0-9]|1[0-4]), ?R([0-9]|1[0-4]), ?#0X(\\d|[A-F])+$".toRegex() // MUL R4, R4, #0X012AF
    }

    object Move {
        val MNEMONIC = "^MOV".toRegex()
        val REGISTER = "^MOV R([0-9]|1[0-4]), ?R([0-9]|1[0-4])$".toRegex() // MOV R1, R5
        val IMMEDIATE_DEC = "^MOV R([0-9]|1[0-4]), ?#-?\\d+$".toRegex() // MOV R1, #5
        val IMMEDIATE_HEX =
            "^MOV ?R([0-9]|1[0-4]), ?#0X(\\d|[A-F])+$".toRegex() // MOV R4, R4, #0X012AF
    }

    object Compare {
        val MNEMONIC = "^CMP".toRegex()
        val REGISTER = "^CMP R([0-9]|1[0-4]), R([0-9]|1[0-4])".toRegex() // CMP, R1, R2
        val IMMEDIATE_DEC = "^CMP R([0-9]|1[0-4]), ?#-?\\d+$".toRegex() // CMP R1, #1234
        val IMMEDIATE_HEX = "^CMP R([0-9]|1[0-4]), ?#0X(\\d|[A-F])+".toRegex() // CMP R1, #0X12AFBC
    }

    object Load {
        val MNEMONIC = "^LDR".toRegex()
        val REGISTER_IMMEDIATE_OFFSET =
            "^LDR R([0-9]|1[0-4]), ?\\[R([0-9]|1[0-4])(, ?#-?\\d+)?]$".toRegex() // LDR R1, [R0]
        val REGISTER_PRE = "^LDR R([0-9]|1[0-4]), ?\\[R([0-9]|1[0-4])(, ?#-?\\d+)?]!$".toRegex() // LDR R1, [R0]
        val REGISTER_POST = "^LDR R([0-9]|1[0-4]), ?\\[R([0-9]|1[0-4])](, ?#-?\\d+)?$".toRegex() // LDR R1, [R0]
        val VARIABLE = "^LDR R([0-9]|1[0-4]), ?=[A-Z]+$".toRegex() // LDR R0,=A
    }

    object Store {
        val MNEMONIC = "^STR".toRegex()
        val IMMEDIATE_OFFSET =
            "^STR R([0-9]|1[0-4]), ?\\[R([0-9]|1[0-4])(, ?#-?\\d+)?]$".toRegex() // STR R1, [R0]
        val REGISTER_PRE = "^STR R([0-9]|1[0-4]), ?\\[R([0-9]|1[0-4])(, ?#-?\\d+)?]!$".toRegex() // STR R1, [R0]
        val REGISTER_POST = "^STR R([0-9]|1[0-4]), ?\\[R([0-9]|1[0-4])](, ?#-?\\d+)?$".toRegex() // STR R1, [R0]
        val VARIABLE = "^STR R([0-9]|1[0-4]), ?=[A-Z]+$".toRegex()
    }

    object Branch {
        val MNEMONIC = "^B".toRegex()
        val LABEL = "^B [A-Z]([A-Z]|_|-|\\d)*$".toRegex() // B LABEL_NAME-28
    }

    object Shifts {
        val TYPES = "(LSL|LSR|ASR|ROR|RRX)".toRegex()

        object LeftShift {
            val MNEMONIC = "^LSL".toRegex()
            val REGISTER = "^LSL R([0-9]|1[0-4]), ?R([0-9]|1[0-4]), ?R([0-9]|1[0-4])$".toRegex()
            val IMMEDIATE_DEC = "^LSL R([0-9]|1[0-4]), ?R([0-9]|1[0-4]), #-?\\d+$".toRegex()
            val IMMEDIATE_HEX = "^LSL R([0-9]|1[0-4]), ?R([0-9]|1[0-4]), ?#0X(\\d|[A-F])+$".toRegex()
            val OPERATION = "LSL #\\d+".toRegex()
        }

        object RightShift {
            val MNEMONIC = "^LSR".toRegex()
            val REGISTER = "^LSR R([0-9]|1[0-4]), ?R([0-9]|1[0-4]), ?R([0-9]|1[0-4])$".toRegex()
            val IMMEDIATE_DEC = "^LSR R([0-9]|1[0-4]), ?R([0-9]|1[0-4]), #-?\\d+$".toRegex()
            val IMMEDIATE_HEX = "^LSR R([0-9]|1[0-4]), ?R([0-9]|1[0-4]), ?#0X(\\d|[A-F])+$".toRegex()
            val OPERATION = "LSR #\\d+".toRegex()
        }

        object RightRotateShift {
            val MNEMONIC = "^ROR".toRegex()
            val REGISTER = "^ROR R([0-9]|1[0-4]), ?R([0-9]|1[0-4]), ?R([0-9]|1[0-4])$".toRegex()
            val IMMEDIATE_DEC = "^ROR R([0-9]|1[0-4]), ?R([0-9]|1[0-4]), #-?\\d+$".toRegex()
            val IMMEDIATE_HEX = "^ROR R([0-9]|1[0-4]), ?R([0-9]|1[0-4]), ?#0X(\\d|[A-F])+$".toRegex()
            val OPERATION = "ROR #\\d+".toRegex()
        }

        object RightRotateExtendedShift {
            val MNEMONIC = "^RRX".toRegex()
            val REGISTER = "^RRX R([0-9]|1[0-4]), ?R([0-9]|1[0-4]), ?R([0-9]|1[0-4])$".toRegex()
            val IMMEDIATE_DEC = "^RRX R([0-9]|1[0-4]), ?R([0-9]|1[0-4]), #-?\\d+$".toRegex()
            val IMMEDIATE_HEX = "^RRX R([0-9]|1[0-4]), ?R([0-9]|1[0-4]), ?#0X(\\d|[A-F])+$".toRegex()
            val OPERATION = "RRX #\\d+".toRegex()
        }


        object ArithmeticRightShift {
            val MNEMONIC = "^ASR".toRegex()
            val REGISTER = "^ASR R([0-9]|1[0-4]), ?R([0-9]|1[0-4]), ?R([0-9]|1[0-4])$".toRegex()
            val IMMEDIATE_DEC = "^ASR R([0-9]|1[0-4]), ?R([0-9]|1[0-4]), #-?\\d+$".toRegex()
            val IMMEDIATE_HEX = "^ASR R([0-9]|1[0-4]), ?R([0-9]|1[0-4]), ?#0X(\\d|[A-F])+$".toRegex()
            val OPERATION = "ASR #\\d+".toRegex()
        }
    }

    object Conditional {
        val TYPES = "(EQ|NE|CS|CC|MI|PL|VS|VC|HI|LS|GE|LT|GT|LE|AL)".toRegex()
        val Equal = "^EQ$".toRegex()
        val NotEqual = "^NE$".toRegex()
        val CarrySet = "^CS$".toRegex()
        val CarryClear = "^CC$".toRegex()
    }

    val LABEL = "^[A-Z]([A-Z]|_|-|\\d)*:$".toRegex() // LABEL_NEW-1:
}
