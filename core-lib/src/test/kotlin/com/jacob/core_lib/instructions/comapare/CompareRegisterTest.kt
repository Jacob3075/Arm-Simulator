package com.jacob.core_lib.instructions.comapare

import com.jacob.core_lib.common.SR
import com.jacob.core_lib.common.W
import com.jacob.core_lib.core.RegisterArray
import com.jacob.core_lib.getExecutionEnvironment
import org.amshove.kluent.`should be`
import org.junit.jupiter.api.Test

internal class CompareRegisterTest {
    @Test
    internal fun `updates correct flags when register values are positive and equal`() {
        val registerArray = RegisterArray()

        val sourceRegister1 = 1.SR
        val sourceRegister2 = 2.SR

        registerArray.setValueAtRegister(sourceRegister1, 5.W)
        registerArray.setValueAtRegister(sourceRegister2, 5.W)

        val executionEnvironment = getExecutionEnvironment(registerArray = registerArray)

        val compareInstruction = CompareRegister(sourceRegister1, sourceRegister2)
        compareInstruction.execute(executionEnvironment)

        registerArray.statusRegister.negative `should be` false
        registerArray.statusRegister.zero `should be` true
        registerArray.statusRegister.carry `should be` true
        registerArray.statusRegister.overFlow `should be` false
    }

    @Test
    internal fun `updates correct flags when one register value is negative and equal to the other `() {
        val registerArray = RegisterArray()

        val sourceRegister1 = 1.SR
        val sourceRegister2 = 2.SR

        registerArray.setValueAtRegister(sourceRegister1, 5.W)
        registerArray.setValueAtRegister(sourceRegister2, (-5).W)

        val executionEnvironment = getExecutionEnvironment(registerArray = registerArray)

        val compareInstruction = CompareRegister(sourceRegister1, sourceRegister2)
        compareInstruction.execute(executionEnvironment)

        registerArray.statusRegister.negative `should be` false
        registerArray.statusRegister.zero `should be` false
        registerArray.statusRegister.carry `should be` false
        registerArray.statusRegister.overFlow `should be` false
    }

    @Test
    internal fun `updates correct flags when one register value is negative and not equal to the other `() {
        val registerArray = RegisterArray()

        val sourceRegister1 = 1.SR
        val sourceRegister2 = 2.SR

        registerArray.setValueAtRegister(sourceRegister1, 5.W)
        registerArray.setValueAtRegister(sourceRegister2, (-10).W)

        val executionEnvironment = getExecutionEnvironment(registerArray = registerArray)

        val compareInstruction = CompareRegister(sourceRegister1, sourceRegister2)
        compareInstruction.execute(executionEnvironment)

        registerArray.statusRegister.negative `should be` false
        registerArray.statusRegister.zero `should be` false
        registerArray.statusRegister.carry `should be` false
        registerArray.statusRegister.overFlow `should be` false
    }

    @Test
    internal fun `updates correct flags when register values are positive and not equal`() {
        val registerArray = RegisterArray()

        val sourceRegister1 = 1.SR
        val sourceRegister2 = 2.SR

        registerArray.setValueAtRegister(sourceRegister1, 5.W)
        registerArray.setValueAtRegister(sourceRegister2, 9.W)

        val executionEnvironment = getExecutionEnvironment(registerArray = registerArray)

        val compareInstruction = CompareRegister(sourceRegister1, sourceRegister2)
        compareInstruction.execute(executionEnvironment)

        registerArray.statusRegister.negative `should be` true
        registerArray.statusRegister.zero `should be` false
        registerArray.statusRegister.carry `should be` false
        registerArray.statusRegister.overFlow `should be` false
    }
}
