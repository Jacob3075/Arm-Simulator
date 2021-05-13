package com.jacob.core_lib.core

import com.jacob.core_lib.*
import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.MemoryAddress
import com.jacob.core_lib.common.addresses.RegisterAddress
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.instructions.load.LoadMemoryAddress
import com.jacob.core_lib.instructions.store.StoreMemoryAddress
import com.jacob.core_lib.word.Word
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class CoreTest {

    @Test
    internal fun `running move instructions updates the correct registers`() {
        val memoryArray = MemoryArray()
        val registerArray = RegisterArray()

        val registerAddress1 = RegisterAddress.REGISTER_1
        val registerAddress2 = RegisterAddress.REGISTER_2

        val move1 = createMoveInstruction(registerAddress1, 10)
        val move2 = createMoveInstruction(registerAddress2, 20)

        val instructions: List<Instruction> = listOf(
            move1,
            move2,
        )
        val program = Program(instructions)

        val core = Core(memoryArray, registerArray, program)

        core.runProgram()

        registerArray.getRegisterAt(registerAddress1)
            .getRegisterValue() `should be equal to` Word(10)

        registerArray.getRegisterAt(registerAddress2)
            .getRegisterValue() `should be equal to` Word(20)

        registerArray.getRegisterAt(RegisterAddress.REGISTER_3).getRegisterValue() `should be equal to` Word(0)
        registerArray.getRegisterAt(RegisterAddress.REGISTER_4).getRegisterValue() `should be equal to` Word(0)
        registerArray.getRegisterAt(RegisterAddress.REGISTER_5).getRegisterValue() `should be equal to` Word(0)
        registerArray.getRegisterAt(RegisterAddress.REGISTER_6).getRegisterValue() `should be equal to` Word(0)
        registerArray.getRegisterAt(RegisterAddress.REGISTER_7).getRegisterValue() `should be equal to` Word(0)
        registerArray.getRegisterAt(RegisterAddress.REGISTER_8).getRegisterValue() `should be equal to` Word(0)
        registerArray.getRegisterAt(RegisterAddress.REGISTER_9).getRegisterValue() `should be equal to` Word(0)
        registerArray.getRegisterAt(RegisterAddress.REGISTER_10).getRegisterValue() `should be equal to` Word(0)
        registerArray.getRegisterAt(RegisterAddress.REGISTER_11).getRegisterValue() `should be equal to` Word(0)
        registerArray.getRegisterAt(RegisterAddress.REGISTER_12).getRegisterValue() `should be equal to` Word(0)
    }

    @Test
    internal fun `running branch instructions jump to the correct point`() {
        val memoryArray = MemoryArray()
        val registerArray = RegisterArray()

        val registerAddress1 = RegisterAddress.REGISTER_1
        val registerAddress2 = RegisterAddress.REGISTER_2
        val registerAddress3 = RegisterAddress.REGISTER_6

        val labelName = "LABEL1"

        val move1 = createMoveInstruction(registerAddress1, 10)
        val move2 = createMoveInstruction(registerAddress2, 20)
        val move3 = createMoveInstruction(registerAddress3, 30)
        val branch = createBranchInstruction(labelName)
        val label = createLabelInstruction(labelName)

        val instructions: List<Instruction> = listOf(
            move1,
            branch,
            move2,
            label,
            move3,
        )
        val program = Program(instructions)

        val core = Core(memoryArray, registerArray, program)

        core.runProgram()

        registerArray.getRegisterAt(registerAddress1)
            .getRegisterValue() `should be equal to` Word(10)

        registerArray.getRegisterAt(registerAddress3)
            .getRegisterValue() `should be equal to` Word(30)

        registerArray.getRegisterAt(RegisterAddress.REGISTER_2)
            .getRegisterValue() `should be equal to` Word(0)

        registerArray.getRegisterAt(RegisterAddress.REGISTER_3).getRegisterValue() `should be equal to` Word(0)
        registerArray.getRegisterAt(RegisterAddress.REGISTER_4).getRegisterValue() `should be equal to` Word(0)
        registerArray.getRegisterAt(RegisterAddress.REGISTER_5).getRegisterValue() `should be equal to` Word(0)
        registerArray.getRegisterAt(RegisterAddress.REGISTER_7).getRegisterValue() `should be equal to` Word(0)
        registerArray.getRegisterAt(RegisterAddress.REGISTER_8).getRegisterValue() `should be equal to` Word(0)
        registerArray.getRegisterAt(RegisterAddress.REGISTER_9).getRegisterValue() `should be equal to` Word(0)
        registerArray.getRegisterAt(RegisterAddress.REGISTER_10).getRegisterValue() `should be equal to` Word(0)
        registerArray.getRegisterAt(RegisterAddress.REGISTER_11).getRegisterValue() `should be equal to` Word(0)
        registerArray.getRegisterAt(RegisterAddress.REGISTER_12).getRegisterValue() `should be equal to` Word(0)
    }

    @Nested
    inner class AddInstructionTests {
        @Test
        internal fun `running add instructions using registers reads and updates the correct registers`() {
            val memoryArray = MemoryArray()
            val registerArray = RegisterArray()

            val registerAddress1 = RegisterAddress.REGISTER_1
            val registerAddress2 = RegisterAddress.REGISTER_2
            val registerAddress3 = RegisterAddress.REGISTER_3
            val registerAddress4 = RegisterAddress.REGISTER_4

            registerArray.setValueAtRegister(registerAddress1, Word(10))
            registerArray.setValueAtRegister(registerAddress2, Word(20))

            val add1 = createAddInstruction(registerAddress3, registerAddress1, registerAddress2)
            val add2 = createAddInstruction(registerAddress4, registerAddress2, registerAddress3)

            val instructions: List<Instruction> = listOf(
                add1,
                add2,
            )
            val program = Program(instructions)

            val core = Core(memoryArray, registerArray, program)

            core.runProgram()

            registerArray.getRegisterAt(registerAddress1)
                .getRegisterValue() `should be equal to` Word(10)

            registerArray.getRegisterAt(registerAddress2)
                .getRegisterValue() `should be equal to` Word(20)

            registerArray.getRegisterAt(registerAddress3)
                .getRegisterValue() `should be equal to` Word(30)

            registerArray.getRegisterAt(registerAddress4)
                .getRegisterValue() `should be equal to` Word(50)

            registerArray.getRegisterAt(RegisterAddress.REGISTER_5).getRegisterValue() `should be equal to` Word(0)
            registerArray.getRegisterAt(RegisterAddress.REGISTER_6).getRegisterValue() `should be equal to` Word(0)
            registerArray.getRegisterAt(RegisterAddress.REGISTER_7).getRegisterValue() `should be equal to` Word(0)
            registerArray.getRegisterAt(RegisterAddress.REGISTER_8).getRegisterValue() `should be equal to` Word(0)
            registerArray.getRegisterAt(RegisterAddress.REGISTER_9).getRegisterValue() `should be equal to` Word(0)
            registerArray.getRegisterAt(RegisterAddress.REGISTER_10).getRegisterValue() `should be equal to` Word(0)
            registerArray.getRegisterAt(RegisterAddress.REGISTER_11).getRegisterValue() `should be equal to` Word(0)
            registerArray.getRegisterAt(RegisterAddress.REGISTER_12).getRegisterValue() `should be equal to` Word(0)
        }

        @Test
        internal fun `running add instructions using immediate value reads and updates the correct registers`() {
            val memoryArray = MemoryArray()
            val registerArray = RegisterArray()

            val registerAddress1 = RegisterAddress.REGISTER_1
            val registerAddress2 = RegisterAddress.REGISTER_2
            val registerAddress3 = RegisterAddress.REGISTER_3
            val registerAddress4 = RegisterAddress.REGISTER_4

            registerArray.setValueAtRegister(registerAddress1, Word(10))
            registerArray.setValueAtRegister(registerAddress2, Word(20))

            val add1 = createAddInstruction(registerAddress3, registerAddress1, 20)
            val add2 = createAddInstruction(registerAddress4, registerAddress2, 30)

            val instructions: List<Instruction> = listOf(
                add1,
                add2,
            )
            val program = Program(instructions)

            val core = Core(memoryArray, registerArray, program)

            core.runProgram()

            registerArray.getRegisterAt(registerAddress1)
                .getRegisterValue() `should be equal to` Word(10)

            registerArray.getRegisterAt(registerAddress2)
                .getRegisterValue() `should be equal to` Word(20)

            registerArray.getRegisterAt(registerAddress3)
                .getRegisterValue() `should be equal to` Word(30)

            registerArray.getRegisterAt(registerAddress4)
                .getRegisterValue() `should be equal to` Word(50)

            registerArray.getRegisterAt(RegisterAddress.REGISTER_5).getRegisterValue() `should be equal to` Word(0)
            registerArray.getRegisterAt(RegisterAddress.REGISTER_6).getRegisterValue() `should be equal to` Word(0)
            registerArray.getRegisterAt(RegisterAddress.REGISTER_7).getRegisterValue() `should be equal to` Word(0)
            registerArray.getRegisterAt(RegisterAddress.REGISTER_8).getRegisterValue() `should be equal to` Word(0)
            registerArray.getRegisterAt(RegisterAddress.REGISTER_9).getRegisterValue() `should be equal to` Word(0)
            registerArray.getRegisterAt(RegisterAddress.REGISTER_10).getRegisterValue() `should be equal to` Word(0)
            registerArray.getRegisterAt(RegisterAddress.REGISTER_11).getRegisterValue() `should be equal to` Word(0)
            registerArray.getRegisterAt(RegisterAddress.REGISTER_12).getRegisterValue() `should be equal to` Word(0)
        }
    }

    @Nested
    inner class SubInstructionTests {

        @Test
        internal fun `running sub instructions using registers reads and updates the correct registers`() {
            val memoryArray = MemoryArray()
            val registerArray = RegisterArray()

            val registerAddress1 = RegisterAddress.REGISTER_1
            val registerAddress2 = RegisterAddress.REGISTER_2
            val registerAddress3 = RegisterAddress.REGISTER_3
            val registerAddress4 = RegisterAddress.REGISTER_4

            registerArray.setValueAtRegister(registerAddress1, Word(30))
            registerArray.setValueAtRegister(registerAddress2, Word(10))

            val sub1 = createSubInstruction(registerAddress3, registerAddress1, registerAddress2)
            val sub2 = createSubInstruction(registerAddress4, registerAddress3, registerAddress2)

            val instructions: List<Instruction> = listOf(
                sub1,
                sub2,
            )
            val program = Program(instructions)

            val core = Core(memoryArray, registerArray, program)

            core.runProgram()

            registerArray.getRegisterAt(registerAddress1)
                .getRegisterValue() `should be equal to` Word(30)

            registerArray.getRegisterAt(registerAddress2)
                .getRegisterValue() `should be equal to` Word(10)

            registerArray.getRegisterAt(registerAddress3)
                .getRegisterValue() `should be equal to` Word(20)

            registerArray.getRegisterAt(registerAddress4)
                .getRegisterValue() `should be equal to` Word(10)

            registerArray.getRegisterAt(RegisterAddress.REGISTER_5).getRegisterValue() `should be equal to` Word(0)
            registerArray.getRegisterAt(RegisterAddress.REGISTER_6).getRegisterValue() `should be equal to` Word(0)
            registerArray.getRegisterAt(RegisterAddress.REGISTER_7).getRegisterValue() `should be equal to` Word(0)
            registerArray.getRegisterAt(RegisterAddress.REGISTER_8).getRegisterValue() `should be equal to` Word(0)
            registerArray.getRegisterAt(RegisterAddress.REGISTER_9).getRegisterValue() `should be equal to` Word(0)
            registerArray.getRegisterAt(RegisterAddress.REGISTER_10).getRegisterValue() `should be equal to` Word(0)
            registerArray.getRegisterAt(RegisterAddress.REGISTER_11).getRegisterValue() `should be equal to` Word(0)
            registerArray.getRegisterAt(RegisterAddress.REGISTER_12).getRegisterValue() `should be equal to` Word(0)
        }

        @Test
        internal fun `running sub instructions using immediate value reads and updates the correct registers`() {
            val memoryArray = MemoryArray()
            val registerArray = RegisterArray()

            val registerAddress1 = RegisterAddress.REGISTER_1
            val registerAddress2 = RegisterAddress.REGISTER_2
            val registerAddress3 = RegisterAddress.REGISTER_3
            val registerAddress4 = RegisterAddress.REGISTER_4

            registerArray.setValueAtRegister(registerAddress1, Word(30))
            registerArray.setValueAtRegister(registerAddress2, Word(20))

            val sub1 = createSubInstruction(registerAddress3, registerAddress1, 20)
            val sub2 = createSubInstruction(registerAddress4, registerAddress2, 10)

            val instructions: List<Instruction> = listOf(
                sub1,
                sub2,
            )

            val program = Program(instructions)

            val core = Core(memoryArray, registerArray, program)

            core.runProgram()

            registerArray.getRegisterAt(registerAddress1)
                .getRegisterValue() `should be equal to` Word(30)

            registerArray.getRegisterAt(registerAddress2)
                .getRegisterValue() `should be equal to` Word(20)

            registerArray.getRegisterAt(registerAddress3)
                .getRegisterValue() `should be equal to` Word(10)

            registerArray.getRegisterAt(registerAddress4)
                .getRegisterValue() `should be equal to` Word(10)

            registerArray.getRegisterAt(RegisterAddress.REGISTER_5).getRegisterValue() `should be equal to` Word(0)
            registerArray.getRegisterAt(RegisterAddress.REGISTER_6).getRegisterValue() `should be equal to` Word(0)
            registerArray.getRegisterAt(RegisterAddress.REGISTER_7).getRegisterValue() `should be equal to` Word(0)
            registerArray.getRegisterAt(RegisterAddress.REGISTER_8).getRegisterValue() `should be equal to` Word(0)
            registerArray.getRegisterAt(RegisterAddress.REGISTER_9).getRegisterValue() `should be equal to` Word(0)
            registerArray.getRegisterAt(RegisterAddress.REGISTER_10).getRegisterValue() `should be equal to` Word(0)
            registerArray.getRegisterAt(RegisterAddress.REGISTER_11).getRegisterValue() `should be equal to` Word(0)
            registerArray.getRegisterAt(RegisterAddress.REGISTER_12).getRegisterValue() `should be equal to` Word(0)
        }
    }

    @Test
    internal fun `running load instructions reads and updates correct memory and register addresses`() {
        val memoryArray = MemoryArray()
        val registerArray = RegisterArray()

        val memoryAddress1 = MemoryAddress(0)
        val memoryAddress2 = MemoryAddress(2)
        val destinationRegister1 = DestinationRegister(RegisterAddress.REGISTER_1)
        val destinationRegister2 = DestinationRegister(RegisterAddress.REGISTER_2)

        memoryArray.setWordAt(memoryAddress1, Word(10))
        memoryArray.setWordAt(memoryAddress2, Word(20))

        val load1 = LoadMemoryAddress(destinationRegister1, memoryAddress1)
        val load2 = LoadMemoryAddress(destinationRegister2, memoryAddress2)

        val instructions: List<Instruction> = listOf(
            load1,
            load2,
        )
        val program = Program(instructions)

        val core = Core(memoryArray, registerArray, program)

        core.runProgram()

        registerArray.getRegisterAt(destinationRegister1.registerAddress)
            .getRegisterValue() `should be equal to` Word(10)

        registerArray.getRegisterAt(destinationRegister2.registerAddress)
            .getRegisterValue() `should be equal to` Word(20)
    }

    @Test
    internal fun `running store instructions reads and updates correct register and memory addresses`() {
        val memoryArray = MemoryArray()
        val registerArray = RegisterArray()

        val memoryAddress1 = MemoryAddress(0)
        val memoryAddress2 = MemoryAddress(2)
        val sourceRegister1 = SourceRegister(RegisterAddress.REGISTER_1)
        val sourceRegister2 = SourceRegister(RegisterAddress.REGISTER_2)

        registerArray.setValueAtRegister(sourceRegister1.registerAddress, Word(10))
        registerArray.setValueAtRegister(sourceRegister2.registerAddress, Word(20))

        val store1 = StoreMemoryAddress(memoryAddress1, sourceRegister1)
        val store2 = StoreMemoryAddress(memoryAddress2, sourceRegister2)

        val instructions: List<Instruction> = listOf(
            store1,
            store2,
        )
        val program = Program(instructions)

        val core = Core(memoryArray, registerArray, program)

        core.runProgram()

        registerArray.getRegisterAt(sourceRegister1.registerAddress)
            .getRegisterValue() `should be equal to` Word(10)

        registerArray.getRegisterAt(sourceRegister2.registerAddress)
            .getRegisterValue() `should be equal to` Word(20)
    }
}
