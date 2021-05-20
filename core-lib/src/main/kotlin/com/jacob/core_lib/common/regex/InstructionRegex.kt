package com.jacob.core_lib.common.regex

class InstructionRegex private constructor() {

    class Add private constructor() {
        companion object {
            val MNEMONIC = """^ADD""".toRegex()
            val REGISTER = "^ADD R([0-9]|1[0-4]), ?R([0-9]|1[0-4]), ?R([0-9]|1[0-4])$".toRegex() // ADD R4, R4, R5
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

    class Move private constructor() {
        companion object {
            val MNEMONIC = "^MOV".toRegex()
            val REGISTER = "^MOV R([0-9]|1[0-4]), ?R([0-9]|1[0-4])$".toRegex() // MOV R1, R5
            val IMMEDIATE_DEC = "^MOV R([0-9]|1[0-4]), ?#-?\\d+$".toRegex() // MOV R1, #5
            val IMMEDIATE_HEX =
                "^MOV R([0-9]|1[0-4]), ?R([0-9]|1[0-4]), ?#0X(\\d|[A-F])+$".toRegex() // MOV R4, R4, #0X012AF
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

    companion object {
        val LABEL = "^[A-Z]([A-Z]|_|-|\\d)*:$".toRegex() // LABEL_NEW-1:
    }
}
