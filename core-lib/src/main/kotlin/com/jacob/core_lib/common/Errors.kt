package com.jacob.core_lib.common

sealed class Errors {
    data class InvalidInstruction(val errorMessage: String) : Errors()
    data class InvalidVariableDefinition(val errorMessage: String) : Errors()
    data class HeaderNotPresent(val errorMessage: String = "Section header is not present") : Errors()
}
