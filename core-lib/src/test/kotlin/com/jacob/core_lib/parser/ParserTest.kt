package com.jacob.core_lib.parser

import com.jacob.core_lib.common.I
import com.jacob.core_lib.common.RA
import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.getFile
import com.jacob.core_lib.instructions.add.Add
import com.jacob.core_lib.instructions.move.Move
import com.jacob.core_lib.instructions.sub.Sub
import com.jacob.core_lib.parser.data.toVariables
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be in`
import org.junit.jupiter.api.Test

internal class ParserTest {
    @Test
    internal fun `test file 1`() {
        val file = getFile("/TestInputFile1.txt")

        val parseDataFromFile = Parser.parseDataFromFile(file)

        val instructions = parseDataFromFile.first
        val variables = parseDataFromFile.second.toVariables()

        instructions.size `should be equal to` 4
        variables.size `should be equal to` 0

        Move.of(DestinationRegister(1.RA), 5.I) `should be in` instructions
        Add.of(DestinationRegister(3.RA), SourceRegister(1.RA), SourceRegister(2.RA)) `should be in` instructions
        Sub.of(DestinationRegister(4.RA), SourceRegister(3.RA), 5.I)
    }

    @Test
    internal fun `test file 2`() {
        val file = getFile("/TestInputFile2.txt")

        val parseDataFromFile = Parser.parseDataFromFile(file)

        val instructions = parseDataFromFile.first
        val variables = parseDataFromFile.second.toVariables()

        instructions.size `should be equal to` 3
        variables.size `should be equal to` 0

        Move.of(DestinationRegister(1.RA), 5.I) `should be in` instructions
        Add.of(DestinationRegister(3.RA), SourceRegister(1.RA), 2.I) `should be in` instructions
        Sub.of(DestinationRegister(4.RA), SourceRegister(1.RA), 3.I)
    }
}
