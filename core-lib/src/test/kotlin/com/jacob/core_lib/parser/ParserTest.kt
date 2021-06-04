package com.jacob.core_lib.parser

import arrow.core.getOrElse
import com.jacob.core_lib.core.*
import com.jacob.core_lib.getFile
import com.jacob.core_lib.instructions.Instruction
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be in`
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class ParserTest {
    companion object {
        @Suppress("unused")
        @JvmStatic
        fun testInput() = listOf(
            Arguments.of("/TestInputFile1.s", test1Data),
            Arguments.of("/TestInputFile2.s", test2Data),
            Arguments.of("/TestInputFile3.s", test3Data),
            Arguments.of("/TestInputFile4.s", test4Data),
            Arguments.of("/TestInputFile5.s", test5Data),
            Arguments.of("/TestInputFile6.s", test6Data),
            Arguments.of("/TestInputFile7.s", test7Data),
            Arguments.of("/TestInputFile8.s", test8Data),
        )
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("testInput")
    internal fun `test parser for input files`(
        fileName: String,
        testVariable: Pair<List<Instruction>, List<ParsedVariable>>
    ) {
        val file = getFile(fileName)

        val parseDataFromFile =
            Parser.parseDataFromFile(file).getOrElse { Pair(emptyList(), emptyList()) }

        val instructions = parseDataFromFile.first
        val variables = parseDataFromFile.second.toVariables()

        instructions.size `should be equal to` testVariable.first.size
        variables.size `should be equal to` testVariable.second.size

        testVariable.first.forEach { it `should be in` instructions }
        testVariable.second.toVariables().forEach { it `should be in` variables }

        instructions `should be equal to` testVariable.first
        variables `should be equal to` testVariable.second.toVariables()
    }
}
