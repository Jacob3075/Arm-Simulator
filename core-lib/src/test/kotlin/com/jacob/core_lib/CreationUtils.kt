package com.jacob.core_lib

import com.jacob.core_lib.instructions.Branch
import com.jacob.core_lib.instructions.Label
import com.jacob.core_lib.instructions.Move
import com.jacob.core_lib.instructions.add.Add
import com.jacob.core_lib.registers.address.DestinationRegister
import com.jacob.core_lib.registers.address.RegisterAddress
import com.jacob.core_lib.registers.address.SourceRegister
import com.jacob.core_lib.word.ImmediateValue

fun createMoveInstruction(registerAddress: RegisterAddress, immediateValue: Int) =
    Move(DestinationRegister(registerAddress), ImmediateValue(immediateValue))

fun createAddInstruction(
    destinationRegister: RegisterAddress,
    sourceRegister1: RegisterAddress,
    sourceRegister2: RegisterAddress
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

fun createBranchInstruction(labelName: String) = Branch(labelName)

fun createLabelInstruction(labelName: String) = Label(labelName)
