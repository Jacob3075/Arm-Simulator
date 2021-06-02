package com.jacob.core_lib.parser

import com.jacob.core_lib.core.*
import com.jacob.core_lib.getFile
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be in`
import org.junit.jupiter.api.Test

internal class ParserTest {
    @Test
    internal fun `test file 1`() {
        val file = getFile("/TestInputFile1.s")

        val parseDataFromFile = Parser.parseDataFromFile(file)

        val instructions = parseDataFromFile.first
        val variables = parseDataFromFile.second.toVariables()

        instructions.size `should be equal to` test1Data.first.size
        variables.size `should be equal to` test1Data.second.size

        test1Data.first.forEach { it `should be in` instructions }
        test1Data.second.toVariables().forEach { it `should be in` variables }
    }

    @Test
    internal fun `test file 2`() {
        val file = getFile("/TestInputFile2.s")

        val parseDataFromFile = Parser.parseDataFromFile(file)

        val instructions = parseDataFromFile.first
        val variables = parseDataFromFile.second.toVariables()

        instructions.size `should be equal to` test2Data.first.size
        variables.size `should be equal to` test2Data.second.size

        test2Data.first.forEach { it `should be in` instructions }
        test2Data.second.toVariables().forEach { it `should be in` variables }
    }

    @Test
    internal fun `test file 3`() {
        val file = getFile("/TestInputFile3.s")

        val parseDataFromFile = Parser.parseDataFromFile(file)

        val instructions = parseDataFromFile.first
        val variables = parseDataFromFile.second.toVariables()

        instructions.size `should be equal to` test3Data.first.size
        variables.size `should be equal to` test3Data.second.size

        test3Data.first.forEach { it `should be in` instructions }
        test3Data.second.toVariables().forEach { it `should be in` variables }
    }

    @Test
    internal fun `test file 4`() {
        val file = getFile("/TestInputFile4.s")

        val parseDataFromFile = Parser.parseDataFromFile(file)

        val instructions = parseDataFromFile.first
        val variables = parseDataFromFile.second.toVariables()

        instructions.size `should be equal to` test4Data.first.size
        variables.size `should be equal to` test4Data.second.size

        test4Data.first.forEach { it `should be in` instructions }
        test4Data.second.toVariables().forEach { it `should be in` variables }
    }

    @Test
    internal fun `test file 5`() {
        val file = getFile("/TestInputFile5.s")

        val parseDataFromFile = Parser.parseDataFromFile(file)

        val instructions = parseDataFromFile.first
        val variables = parseDataFromFile.second.toVariables()

        instructions.size `should be equal to` test5Data.first.size
        variables.size `should be equal to` test5Data.second.size

        test5Data.first.forEach { it `should be in` instructions }
        test5Data.second.toVariables().forEach { it `should be in` variables }
    }

    @Test
    internal fun `test file 6`() {
        val file = getFile("/TestInputFile6.s")

        val parseDataFromFile = Parser.parseDataFromFile(file)

        val instructions = parseDataFromFile.first
        val variables = parseDataFromFile.second.toVariables()

        instructions.size `should be equal to` test6Data.first.size
        variables.size `should be equal to` test6Data.second.size

        test6Data.first.forEach { it `should be in` instructions }
        test6Data.second.toVariables().forEach { it `should be in` variables }
    }

    @Test
    internal fun `test file 7`() {
        val file = getFile("/TestInputFile7.s")

        val parseDataFromFile = Parser.parseDataFromFile(file)

        val instructions = parseDataFromFile.first
        val variables = parseDataFromFile.second.toVariables()

        instructions.size `should be equal to` test7Data.first.size
        variables.size `should be equal to` test7Data.second.size

        test7Data.first.forEach { it `should be in` instructions }
        test7Data.second.toVariables().forEach { it `should be in` variables }

        instructions `should be equal to` test7Data.first
        variables `should be equal to` test7Data.second.toVariables()
    }

    @Test
    internal fun `test file 8`() {
        val file = getFile("/TestInputFile8.s")

        val parseDataFromFile = Parser.parseDataFromFile(file)

        val instructions = parseDataFromFile.first
        val variables = parseDataFromFile.second.toVariables()

        instructions.size `should be equal to` test8Data.first.size
        variables.size `should be equal to` test8Data.second.size

        test8Data.first.forEach { it `should be in` instructions }
        test8Data.second.toVariables().forEach { it `should be in` variables }

        instructions `should be equal to` test8Data.first
        variables `should be equal to` test8Data.second.toVariables()
    }
}
