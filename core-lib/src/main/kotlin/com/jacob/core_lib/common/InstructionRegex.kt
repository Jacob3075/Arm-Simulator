package com.jacob.core_lib.common

class InstructionRegex private constructor() {

    class Add private constructor() {
        companion object {
            val REGISTER = "^ADD R([0-9]|1[0-4]), ?R([0-9]|1[0-4]), ?R([0-9]|1[0-4])$".toRegex() // ADD r4, r4, r5
            val IMMEDIATE = "^ADD R([0-9]|1[0-4]), ?R([0-9]|1[0-4]), ?#-?\\d+$".toRegex() // ADD r4, r4, #5
        }
    }

    class Sub private constructor() {
        companion object {
            val REGISTER = "^SUB R([0-9]|1[0-4]), ?R([0-9]|1[0-4]), ?R([0-9]|1[0-4])$".toRegex() // SUB R4, R4, R5
            val IMMEDIATE = "^SUB R([0-9]|1[0-4]), ?R([0-9]|1[0-4]), ?#\\d+$".toRegex() // SUB R4, R4, #5
        }
    }

    class Move private constructor() {
        companion object {
            val REGISTER = "^MOV R([0-9]|1[0-4]), ?R([0-9]|1[0-4])$".toRegex() // MOV R1, R5
            val IMMEDIATE = "^MOV R([0-9]|1[0-4]), ?#-?\\d+$".toRegex() // MOV R1, #5
        }
    }

    class Load private constructor() {
        companion object {
            val REGISTER = "^LDR R([0-9]|1[0-4]), ?\\[R([0-9]|1[0-4]])]$".toRegex() // LDR R1, [R0]
            val VARIABLE = "^LDR R([0-9]|1[0-4]), ?=[A-Z]+$".toRegex() // LDR R0,=A
        }
    }

    class Store private constructor() {
        companion object {
            val REGISTER = "^STR R([0-9]|1[0-4]), ?\\[R([0-9]|1[0-4]])]$".toRegex()
            val VARIABLE = "^STR R([0-9]|1[0-4]), ?=[A-Z]+$".toRegex()
        }
    }
}
