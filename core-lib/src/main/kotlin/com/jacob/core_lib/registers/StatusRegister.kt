package com.jacob.core_lib.registers

class StatusRegister {
    var negative: Boolean = false
        private set
    var zero: Boolean = false
        private set
    var carry: Boolean = false
        private set
    var overFlow: Boolean = false
        private set
}
