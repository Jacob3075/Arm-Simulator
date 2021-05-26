package com.jacob.core_lib

import com.jacob.core_lib.core.ExecutionEnvironment
import com.jacob.core_lib.core.MemoryArray
import com.jacob.core_lib.core.RegisterArray
import com.jacob.core_lib.core.Variable
import java.io.File
import com.jacob.core_lib.core.Label as CoreLabel

fun getFile(fileName: String) = File(object {}.javaClass.getResource(fileName)!!.toURI())

fun getExecutionEnvironment(
    registerArray: RegisterArray = RegisterArray(),
    memoryArray: MemoryArray = MemoryArray(),
    variables: List<Variable> = emptyList(),
    labels: List<CoreLabel> = emptyList()
) = ExecutionEnvironment(registerArray, memoryArray, labels, variables)
