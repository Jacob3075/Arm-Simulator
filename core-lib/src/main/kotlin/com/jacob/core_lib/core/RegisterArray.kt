package com.jacob.core_lib.core

import com.jacob.core_lib.register.*
import com.jacob.core_lib.register.RegisterAddress.*

class RegisterArray {
    val registers: List<Register> = listOf(
        CoreRegister(REGISTER1),
        CoreRegister(REGISTER2),
        CoreRegister(REGISTER3),
        CoreRegister(REGISTER4),
        CoreRegister(REGISTER5),
        CoreRegister(REGISTER6),
        CoreRegister(REGISTER7),
        CoreRegister(REGISTER8),
        CoreRegister(REGISTER9),
        CoreRegister(REGISTER10),
        CoreRegister(REGISTER11),
        CoreRegister(REGISTER12),
        StackPointer(),
        LinkRegister(),
        ProgramCounter(),
    )

}
