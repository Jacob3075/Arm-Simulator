package com.jacob.core_lib

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.RegisterAddress
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.core.ExecutionEnvironment
import com.jacob.core_lib.core.MemoryArray
import com.jacob.core_lib.core.RegisterArray
import com.jacob.core_lib.core.Variable
import com.jacob.core_lib.instructions.Branch
import com.jacob.core_lib.instructions.Label
import com.jacob.core_lib.instructions.add.Add
import com.jacob.core_lib.instructions.move.Move
import com.jacob.core_lib.instructions.sub.Sub
import com.jacob.core_lib.word.ImmediateValue
import java.io.File
import com.jacob.core_lib.core.Label as CoreLabel

fun createMoveInstruction(registerAddress: RegisterAddress, immediateValue: Int) =
    Move.of(DestinationRegister(registerAddress), ImmediateValue(immediateValue))

fun createMoveInstruction(destinationAddress: RegisterAddress, sourceAddress: RegisterAddress) =
    Move.of(DestinationRegister(destinationAddress), SourceRegister(sourceAddress))

fun createAddInstruction(
    destinationRegister: RegisterAddress,
    sourceRegister1: RegisterAddress,
    sourceRegister2: RegisterAddress,
) = Add.of(
    DestinationRegister(destinationRegister),
    SourceRegister(sourceRegister1),
    SourceRegister(sourceRegister2)
)

fun createAddInstruction(
    destinationRegister: RegisterAddress,
    sourceRegister1: RegisterAddress,
    immediateValue: Int,
) = Add.of(
    DestinationRegister(destinationRegister),
    SourceRegister(sourceRegister1),
    ImmediateValue(immediateValue)
)

fun createSubInstruction(
    destinationRegister: RegisterAddress,
    sourceRegister1: RegisterAddress,
    sourceRegister2: RegisterAddress,
) = Sub.of(
    DestinationRegister(destinationRegister),
    SourceRegister(sourceRegister1),
    SourceRegister(sourceRegister2)
)

fun createSubInstruction(
    destinationRegister: RegisterAddress,
    sourceRegister1: RegisterAddress,
    immediateValue: Int,
) = Sub.of(
    DestinationRegister(destinationRegister),
    SourceRegister(sourceRegister1),
    ImmediateValue(immediateValue)
)

fun createBranchInstruction(labelName: String) = Branch(labelName)

fun createLabelInstruction(labelName: String) = Label(labelName)

fun getFile(fileName: String) = File(object {}.javaClass.getResource(fileName)!!.toURI())

fun getExecutionEnvironment(
    registerArray: RegisterArray = RegisterArray(),
    memoryArray: MemoryArray = MemoryArray(),
    variables: List<Variable> = emptyList(),
    labels: List<CoreLabel> = emptyList()
) = ExecutionEnvironment(registerArray, memoryArray, labels, variables)
