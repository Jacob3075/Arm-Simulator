package com.jacob.core_lib.common.addresses

enum class RegisterAddress(private val address: Int) {

    REGISTER_0(0),
    REGISTER_1(1),
    REGISTER_2(2),
    REGISTER_3(3),
    REGISTER_4(4),
    REGISTER_5(5),
    REGISTER_6(6),
    REGISTER_7(7),
    REGISTER_8(8),
    REGISTER_9(9),
    REGISTER_10(10),
    REGISTER_11(11),
    REGISTER_12(12),
    STACK_POINTER(13),
    LINK_REGISTER(14),
    PROGRAM_COUNTER(15),
    STATUS_REGISTER(16), ;


    override fun toString(): String {
        return "RegisterAddress(address=$address)"
    }
}
