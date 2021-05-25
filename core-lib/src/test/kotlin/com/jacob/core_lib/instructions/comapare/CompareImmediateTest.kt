package com.jacob.core_lib.instructions.comapare

import com.jacob.core_lib.common.I
import com.jacob.core_lib.common.RA
import com.jacob.core_lib.common.W
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.core.RegisterArray
import com.jacob.core_lib.getExecutionEnvironment
import org.amshove.kluent.`should be`
import org.junit.jupiter.api.Test

internal class CompareImmediateTest {
    @Test
    internal fun `updates correct flags when both register and immediate are positive and equal`() {
        val registerArray = RegisterArray()

        val sourceRegister = SourceRegister(1.RA)
        val immediateValue = 5.I

        registerArray.setValueAtRegister(sourceRegister.registerAddress, 5.W)

        val executionEnvironment = getExecutionEnvironment(registerArray = registerArray)

        val compareInstruction = CompareImmediate(sourceRegister, immediateValue)
        compareInstruction.execute(executionEnvironment)

        registerArray.statusRegister.negative `should be` false
        registerArray.statusRegister.zero `should be` true
        registerArray.statusRegister.carry `should be` true
        registerArray.statusRegister.overFlow `should be` false
    }

    @Test
    internal fun `updates correct flags when both register and immediate are negative and equal`() {
        val registerArray = RegisterArray()

        val sourceRegister = SourceRegister(1.RA)
        val immediateValue = (-5).I

        registerArray.setValueAtRegister(sourceRegister.registerAddress, (-5).W)

        val executionEnvironment = getExecutionEnvironment(registerArray = registerArray)

        val compareInstruction = CompareImmediate(sourceRegister, immediateValue)
        compareInstruction.execute(executionEnvironment)

        registerArray.statusRegister.negative `should be` false
        registerArray.statusRegister.zero `should be` true
        registerArray.statusRegister.carry `should be` true
        registerArray.statusRegister.overFlow `should be` false
    }

    @Test
    internal fun `updates correct flags when both register and immediate are positive and not equal`() {
        val registerArray = RegisterArray()

        val sourceRegister = SourceRegister(1.RA)
        val immediateValue = 5.I

        registerArray.setValueAtRegister(sourceRegister.registerAddress, 13.W)

        val executionEnvironment = getExecutionEnvironment(registerArray = registerArray)

        val compareInstruction = CompareImmediate(sourceRegister, immediateValue)
        compareInstruction.execute(executionEnvironment)

        registerArray.statusRegister.negative `should be` false
        registerArray.statusRegister.zero `should be` false
        registerArray.statusRegister.carry `should be` true
        registerArray.statusRegister.overFlow `should be` false
    }

    @Test
    internal fun `updates correct flags when both register and immediate are negative and not equal`() {
        val registerArray = RegisterArray()

        val sourceRegister = SourceRegister(1.RA)
        val immediateValue = (-5).I

        registerArray.setValueAtRegister(sourceRegister.registerAddress, (-14).W)

        val executionEnvironment = getExecutionEnvironment(registerArray = registerArray)

        val compareInstruction = CompareImmediate(sourceRegister, immediateValue)
        compareInstruction.execute(executionEnvironment)

        registerArray.statusRegister.negative `should be` true
        registerArray.statusRegister.zero `should be` false
        registerArray.statusRegister.carry `should be` false
        registerArray.statusRegister.overFlow `should be` false
    }
}
