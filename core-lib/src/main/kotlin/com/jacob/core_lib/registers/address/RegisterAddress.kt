package com.jacob.core_lib.registers.address

enum class RegisterAddress(val address: Int) {

    REGISTER1(1),
    REGISTER2(2),
    REGISTER3(3),
    REGISTER4(4),
    REGISTER5(5),
    REGISTER6(6),
    REGISTER7(7),
    REGISTER8(8),
    REGISTER9(9),
    REGISTER10(10),
    REGISTER11(11),
    REGISTER12(12),
    STACKPOINTER(13),
    LINKREGISTER(14),
    PROGRAMCOUNTER(15), ;

    override fun toString(): String {
        return "RegisterAddress(address=$address)"
    }
}
